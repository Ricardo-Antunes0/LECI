/*
 *  \author António Rui Borges - 2012-2015
 *  \authur Artur Pereira - 2016-2020
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
#include <sys/stat.h>
#include <string.h>

#include <iostream>

#include "core.h"
#include "devtools.h"
#include "daal.h"

namespace sofs21
{
    uint16_t grpUnqueueHiddenInode()
    {
        soProbe(404, "%s()\n", __FUNCTION__);

        SOSuperblock* super_block = soGetSuperblockPointer();

        if(super_block->iqcount == 0)
            return NullInodeReference;

        uint16_t older_inode = super_block->iqueue[super_block->iqhead];

        super_block->iqueue[super_block->iqhead] = NullInodeReference;

        if(DELETED_QUEUE_SIZE-1 == super_block->iqhead) 
            super_block->iqhead = 0;
        else
            super_block->iqhead = super_block->iqhead+1;
        super_block->iqcount--;

        return older_inode;
    }
};

