/*
 *  \author António Rui Borges - 2012-2015
 *  \authur Artur Pereira - 2016-2020
 */

#include "freedatablocks.h"
#include "bin_freedatablocks.h"
#include "grp_freedatablocks.h"

#include <string.h>
#include <errno.h>
#include <iostream>

#include "core.h"
#include "devtools.h"
#include "daal.h"

namespace sofs21
{
    void grpReplenishFromCache(void)
    {
        soProbe(443, "%s()\n", __FUNCTION__);

        /* replace this comment and following line with your code */
        //binReplenishFromCache();
        SOSuperblock *sbp = soGetSuperblockPointer();

        if(sbp->retrieval_cache.idx != REF_CACHE_SIZE){
            return;
        }

        uint32_t idx = sbp->insertion_cache.idx;

        for(uint32_t ref_b = REF_CACHE_SIZE - idx, ref_a = 0; ref_a < idx; ref_a++, ref_b++){
            sbp->retrieval_cache.ref[ref_b] = sbp->insertion_cache.ref[ref_a];
            sbp->insertion_cache.ref[ref_a] = NullBlockReference;
        }

        sbp->retrieval_cache.idx = REF_CACHE_SIZE - idx;
        sbp->insertion_cache.idx = 0;

        soSaveSuperblock();
    }
};

