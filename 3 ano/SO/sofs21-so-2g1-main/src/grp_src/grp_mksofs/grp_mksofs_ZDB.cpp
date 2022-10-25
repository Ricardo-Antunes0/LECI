#include "grp_mksofs.h"

#include "rawdisk.h"
#include "core.h"
#include "devtools.h"
#include "bin_mksofs.h"

#include <string.h>
#include <inttypes.h>

namespace sofs21
{
    void grpZeroFreeDataBlocks(uint32_t ntotal, uint16_t itsize, uint32_t dbtotal)
    {
        soProbe(607, "%s(%u, %u, %u)\n", __FUNCTION__, ntotal, itsize, dbtotal);

        // Buffer 
        u_int32_t buff[RPB] = {};
    
        u_int32_t nBlocksInodes = itsize/IPB;

        // Fill buffer with 0's
        // memset(buff, 0, sizeof(buff));

        for(u_int32_t i=nBlocksInodes+2; i<dbtotal+nBlocksInodes+1; i++) {
            soWriteRawBlock(i, buff);
        }


        /* replace this comment and following line with your code */
        //binZeroFreeDataBlocks(ntotal, itsize, dbtotal);
    }
};

