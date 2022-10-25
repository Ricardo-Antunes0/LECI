/*
 *  \author António Rui Borges - 2012-2015
 *  \authur Artur Pereira - 2016-2020
 */

#include "freeinodes.h"
#include "bin_freeinodes.h"
#include "freeinodes.h"
#include "bin_freeinodes.h"
#include "grp_freeinodes.h"

#include <stdio.h>
#include <errno.h>
#include <inttypes.h>
#include <time.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>

#include <iostream>

#include "core.h"
#include "devtools.h"
#include "daal.h"

namespace sofs21
{
    bool grpHideInode(uint16_t in)
    {
        soProbe(403, "%s(%u)\n", __FUNCTION__, in);

        int inode_handler = soOpenInode(in);                             //inode open
        SOInode *node_pointer = soGetInodePointer(inode_handler);        //pointer to the inode
        SOSuperblock *block_pointer = soGetSuperblockPointer();         //pointer to the block


        if(in > block_pointer->itotal){
            throw SOException(EINVAL, __FUNCTION__);         //EINVAL exception will be thrown if given inode number is invalid
        }
        if(block_pointer->iqcount == DELETED_QUEUE_SIZE){  //verify if the deleted queue is full 
            return false;
        }
        node_pointer->mode = ~node_pointer->mode; //makes comp [0 to 1 and 1 to 0]
        uint8_t idx = (block_pointer->iqhead + block_pointer->iqcount) % DELETED_QUEUE_SIZE;
        block_pointer->iqueue[idx]=in;
        block_pointer->iqcount +=1;
        soSaveInode(inode_handler);
        soCloseInode(inode_handler); 
        soSaveSuperblock();
        return true;
       
    }
};
