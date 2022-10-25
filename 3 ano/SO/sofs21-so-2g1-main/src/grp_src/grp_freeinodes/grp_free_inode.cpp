/*
 *  \author António Rui Borges - 2012-2015
 *  \authur Artur Pereira - 2016-2021
 */

#include "freeinodes.h"
#include "bin_freeinodes.h"
#include "grp_freeinodes.h"

#include <stdio.h>
#include <errno.h>
#include <inttypes.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>

#include "core.h"
#include "devtools.h"
#include "daal.h"

namespace sofs21
{
    void grpFreeInode(uint16_t in)
    {
        soProbe(402, "%s(%u)\n", __FUNCTION__, in);

        /* replace this comment and following line with your code */
        if (in <=0 || in> MAX_INODES){
            throw SOException(EINVAL,__FUNCTION__);
        }

        int inode_handler = soOpenInode(in);
        SOSuperblock* sb_pointer= soGetSuperblockPointer();
        SOInode* inode_pointer = soGetInodePointer(inode_handler);
        uint16_t inodenumber= soGetInodeNumber(inode_handler);

        uint32_t block = inodenumber/32; //buscar o block do bitmap 
        uint32_t set = block*32; //bloco
        uint32_t position = inodenumber%32;//posição
        uint32_t bit = set + position;

        sb_pointer -> ibitmap[bit]=1; 


        inode_pointer->mode =0;
        inode_pointer ->owner =0;
        inode_pointer -> group =0;

        sb_pointer ->ifree = sb_pointer->ifree+1;
        sb_pointer->iidx = sb_pointer->iidx +1;
        soSaveInode(inode_handler);
        soCloseInode(inode_handler);
        soSaveSuperblock();
        //binFreeInode(in);
    }
};

