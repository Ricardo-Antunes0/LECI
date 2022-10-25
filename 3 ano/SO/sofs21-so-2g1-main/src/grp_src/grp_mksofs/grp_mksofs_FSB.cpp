#include "grp_mksofs.h"

#include "rawdisk.h"
#include "core.h"
#include "devtools.h"
#include "bin_mksofs.h"

#include <string.h>
#include <inttypes.h>

namespace sofs21
{
    void grpFillInSuperblock(const char *name, uint32_t ntotal, uint16_t itsize, uint32_t dbtotal)
    {
        soProbe(602, "%s(%s, %u, %u, %u)\n", __FUNCTION__, name, ntotal, itsize, dbtotal);

        
        SOSuperblock sbp = SOSuperblock();
        sbp.magic = 0xFFFF;
        sbp.ntotal = ntotal;
        strcpy(sbp.name,name);
        sbp.mntstat = 0;
        sbp.version = VERSION_NUMBER;
        
        sbp.itotal=IPB*itsize;
       
        sbp.ifree = sbp.itotal - 1;
        sbp.iidx = 0;
        sbp.ibitmap[0]=0;
        sbp.ibitmap[sbp.itotal] = {1};
                        
        
        sbp.dbtotal = dbtotal;
        sbp.dbfree = dbtotal-1;
        sbp.dbp_start= 1 + sbp.itotal/IPB;
                
        sbp.rbm_start = 1 + (sbp.itotal/IPB) + dbtotal;
        sbp.rbm_size = ntotal - dbtotal - (sbp.itotal/IPB) - 1;
        
        sbp.retrieval_cache = SOSuperblock::ReferenceCache();

        if(REF_CACHE_SIZE >= dbtotal){
            sbp.retrieval_cache.idx = REF_CACHE_SIZE - dbtotal + 1;
            
        }else{
            sbp.retrieval_cache.idx = 0;
        }
        
        sbp.retrieval_cache.ref[REF_CACHE_SIZE] = {0} ;

        int k = 0;
        for (int i = 0; i < REF_CACHE_SIZE; i++)
        {
            int aux1 = sbp.retrieval_cache.idx;

            if (i < aux1)
            {
                sbp.retrieval_cache.ref[i] = NullBlockReference;
            }
            else
            {
                sbp.retrieval_cache.ref[i] = k + 1;
                k++;
            }
        }

        sbp.insertion_cache = SOSuperblock::ReferenceCache();
        
        for( int i = 0; i < REF_CACHE_SIZE; i++){
            sbp.insertion_cache.ref[i] = NullBlockReference;
        }
        sbp.insertion_cache.idx = 0;

        soWriteRawBlock(0, &sbp);

    }
};

