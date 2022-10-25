/*
 *  \authur Artur Pereira - 2016-2021
 */

#include "inodeblocks.h"
#include "bin_inodeblocks.h"
#include "grp_inodeblocks.h"

#include "freedatablocks.h"
#include "daal.h"
#include "core.h"
#include "devtools.h"

#include <inttypes.h>
#include <errno.h>
#include <assert.h>

#define CHANGED 0x1
#define NOT_EMPTY 0x2

namespace sofs21
{

    /* free all blocks between positions idx and RPB - 1
        * existing in the block of references passed as argument.
        * The return value has the following meaning:
        * - bit CHANGED is raised if the block of references changed
        * - bit NOT_EMPTY is raised if the block of references contains non NullBlockReference references
        */
    static uint32_t grpFreeIndirectInodeBlocks(int ih, uint32_t *ref, uint32_t idx);


    /* free all blocks between positions idx and RPB*RPB - 1
        * covered by the block of indirect references passed as argument.
        * The return value has the following meaning:
        * - bit CHANGED is raised if the block of indirect references changed
        * - bit NOT_EMPTY is raised if the block of indirect references contains non NullBlockReference references
        */
    static uint32_t grpFreeDoubleIndirectInodeBlocks(int ih, uint32_t *iref, uint32_t idx);

    /* ******************************************************* */

    void grpFreeInodeBlocks(int ih, uint32_t fibn)
    {
        soProbe(303, "%s(%d, %u)\n", __FUNCTION__, ih, fibn);

        /* replace this comment and following line with your code */


        SOSuperblock* super_block_pointer = soGetSuperblockPointer();
        if(ih  > super_block_pointer->itotal ){
            throw SOException(EINVAL, __FUNCTION__);
        }

        if((fibn > RPB + N_DIRECT + (RPB*RPB)) || (fibn < 0)){
            throw SOException(EINVAL, __FUNCTION__);
        }

        SOInode* inode_pointer = soGetInodePointer(ih);

        if (fibn > RPB + N_DIRECT + (RPB*RPB)){
            return;
        }

        if (fibn < N_DIRECT){
            for(int i = fibn;i<N_DIRECT;i++){
                if(inode_pointer->d[i]!=NullBlockReference){
                    soFreeDataBlock(inode_pointer->d[i]);
                }
                inode_pointer->d[i] = NullBlockReference;
            }

            if(inode_pointer->i1 != NullBlockReference){
                grpFreeIndirectInodeBlocks(ih, &inode_pointer->i1, N_DIRECT);
            }

            if(inode_pointer->i2 != NullBlockReference){
                grpFreeDoubleIndirectInodeBlocks(ih, &inode_pointer->i2, N_DIRECT + RPB);
            }
        }


        if((fibn >= N_DIRECT) && (fibn < N_DIRECT + RPB)){
            if(inode_pointer->i1 != NullBlockReference){
                grpFreeIndirectInodeBlocks(ih, &inode_pointer->i1, fibn);
            }

            if(inode_pointer->i2 != NullBlockReference){
                grpFreeDoubleIndirectInodeBlocks(ih, &inode_pointer->i2, N_DIRECT + RPB);
            }
        }


        if(fibn >= N_DIRECT + RPB){
            if(inode_pointer->i2 != NullBlockReference){
                grpFreeDoubleIndirectInodeBlocks(ih, &inode_pointer->i2, fibn);
            }
        }

    }

    /* ******************************************************* */
    static uint32_t grpFreeIndirectInodeBlocks(int ih, uint32_t *ref, uint32_t idx)
    {
        soProbe(303, "%s(%d, %p, %u)\n", __FUNCTION__, ih, ref, idx);

        /* replace this comment and following line with your code */

        uint32_t a[RPB];
        soReadDataBlock(*ref, a);

        for(long unsigned int i = 0; i < RPB; i++){
            if((i + N_DIRECT) >= idx){
                if(a[i] != NullBlockReference){
                    soFreeDataBlock(a[i]);
                    a[i] = NullBlockReference;
                }
            }
        }
        soWriteDataBlock(*ref, a);

        uint32_t c = 0;
        for(uint32_t i=0; i < RPB;i++){
            if(a[i] == NullBlockReference){
                c++;
            }
        }
        
        if(c == RPB){
            *ref = NullBlockReference;
        }
        return 0;
    }

    /* ******************************************************* */


    static uint32_t grpFreeDoubleIndirectInodeBlocks(int ih, uint32_t *iref, uint32_t idx)
    {
        soProbe(303, "%s(%d, %p, %u)\n", __FUNCTION__, ih, iref, idx);

        /* replace this comment and following line with your code */

        idx = idx - N_DIRECT;

        uint32_t a[RPB];
        soReadDataBlock(*iref, a);

        uint32_t j = RPB;
        int flag = 0;
        uint32_t aux = (idx % RPB );
        for(long unsigned int i = 0; i < RPB; i++){
            if(flag == 1){
                if(a[i] != NullBlockReference){
                    grpFreeIndirectInodeBlocks(ih, &a[i], 0);
                }
            }
            if(((j + aux) >= idx) && (flag == 0)){
                if(a[i] != NullBlockReference){
                    grpFreeIndirectInodeBlocks(ih, &a[i], aux+N_DIRECT);
                }
                flag = 1;
            }
            j += RPB;
        }
        soWriteDataBlock(*iref, a);

        int c = 0;
        for(uint32_t i=0; i < RPB;i++){
            if(a[i] == NullBlockReference){
                c++;
            }
        }
        if(c == RPB){
            *iref = NullBlockReference;
        }
        return 0;
    }
    /* ******************************************************* */
}