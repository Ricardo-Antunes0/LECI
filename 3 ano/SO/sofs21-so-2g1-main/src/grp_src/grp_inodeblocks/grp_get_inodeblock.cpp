/*
 *  \authur Artur Pereira - 2016-2021
 */

#include "inodeblocks.h"
#include "bin_inodeblocks.h"
#include "grp_inodeblocks.h"

#include "daal.h"
#include "core.h"
#include "devtools.h"

#include <errno.h>

namespace sofs21
{
    /* ********************************************************* */

//#if false
    /* Considering bn is the number of a data block containing references to
     * data blocks, return the value of its idx position
     */
    static uint32_t grpGetIndirectInodeBlock(uint32_t bn, uint32_t idx);

    /* Considering bn is the number of a data block containing references
     * to data blocks containing references to data blocks (double indirection),
     * return the value of its idx position
     */
    static uint32_t grpGetDoubleIndirectInodeBlock(uint32_t bn, uint32_t idx);
//#endif

    /* ********************************************************* */

    uint32_t grpGetInodeBlock(int ih, uint32_t ibn)
    {
        soProbe(301, "%s(%d, %u)\n", __FUNCTION__, ih, ibn);
        SOInode *inodePointer = soGetInodePointer(ih);
        /* replace this comment and following line with your code */
        uint32_t N_INDIRECT = RPB + N_DIRECT - 1;  
        uint32_t N_DOUBLE_INDIRECT = N_INDIRECT + (RPB * RPB);
        uint32_t dataBlockNumber;
        if (ibn < 0 || ibn > N_DOUBLE_INDIRECT){
            throw SOException(EINVAL, __FUNCTION__);
        }

        if (ibn < N_DIRECT){
            dataBlockNumber = inodePointer->d[ibn];
        } else if (ibn < N_INDIRECT){
            dataBlockNumber = grpGetIndirectInodeBlock(inodePointer->i1, ibn - N_DIRECT);
        } else{
            dataBlockNumber = grpGetDoubleIndirectInodeBlock(inodePointer->i2, ibn - N_INDIRECT);
        }
        return dataBlockNumber;


        //return binGetInodeBlock(ih, fbn);
    }

    /* ********************************************************* */

//#if false
    static uint32_t grpGetIndirectInodeBlock(uint32_t bn, uint32_t idx)
    {
        soProbe(301, "%s(%d, %d)\n", __FUNCTION__, bn, idx);
        if (bn == NullBlockReference){
            return NullBlockReference;
        }
       
        uint32_t ref_idx = idx % RPB;
        uint32_t ref[RPB];
        soReadDataBlock(bn, &ref);

        return ref[ref_idx];


    }
//#endif

    /* ********************************************************* */

//#if false
    static uint32_t grpGetDoubleIndirectInodeBlock(uint32_t bn, uint32_t idx)
    {
        soProbe(301, "%s(%d, %d)\n", __FUNCTION__, bn, idx);
        
        if (bn == NullBlockReference){
            return NullBlockReference;
        }

        uint32_t ref_idx = idx / RPB;
        uint32_t ref[RPB];
        soReadDataBlock(bn, &ref);

        return grpGetIndirectInodeBlock(ref[ref_idx], idx);
        
    }
//#endif
};

