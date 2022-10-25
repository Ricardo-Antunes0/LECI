/*
 *  \author António Rui Borges - 2012-2015
 *  \authur Artur Pereira - 2009-2020
 */

#include "freedatablocks.h"
#include "bin_freedatablocks.h"
#include "grp_freedatablocks.h"

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
    uint32_t grpAllocDataBlock()
    {
        soProbe(441, "%s()\n", __FUNCTION__);

        /* replace this comment and following line with your code */
        //return binAllocDataBlock();
        SOSuperblock *sbp = soGetSuperblockPointer();
        
        //throw ENOSPC if there are no free DB
        if(sbp->dbfree <= 0)
            throw SOException(ENOSPC,__FUNCTION__); 

        // if the retrieval cache is empty
        if(sbp->retrieval_cache.idx == REF_CACHE_SIZE)
         	soReplenishFromCache();
        else
            soReplenishFromBitmap();
              
                
        // the first reference in the retrieval cache is retrieved
        // update superblock field retrieval_cache and dbfree
        //sbp->retrieval_cache.ref[sbp->retrieval_cache.idx] = NullBlockReference;
        uint32_t idx = sbp->retrieval_cache.idx;
        uint32_t n_allocated = sbp->retrieval_cache.ref[idx];
        sbp->retrieval_cache.ref[idx] = NullBlockReference;
        sbp->retrieval_cache.idx += 1;
        sbp->dbfree -= 1;
        
        soSaveSuperblock();
        
        //return first reference of the retrieval cache
        return n_allocated;
    }
};

