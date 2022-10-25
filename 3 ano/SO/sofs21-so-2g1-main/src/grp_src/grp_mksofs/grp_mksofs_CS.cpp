#include "grp_mksofs.h"

#include "core.h"
#include "devtools.h"
#include "bin_mksofs.h"

#include <inttypes.h>
#include <iostream>

namespace sofs21
{
    void grpComputeDiskStructure(uint32_t ntotal, uint16_t itotal, uint16_t & itsize, uint32_t & dbtotal)
    {
        soProbe(601, "%s(%u, %u, ...)\n", __FUNCTION__, ntotal, itotal);

        /* replace this comment and following line with your code */
        //binComputeDiskStructure(ntotal, itotal, itsize, dbtotal);

        //if, at entry, the proposed value for itotal is 0, value ntotal/20 should be used as the proposed value
        if(itotal == 0){
            itotal = ntotal/20;
        }
            
        //itotal must be rounded up to a multiple of IPB
        uint16_t rem = itotal % IPB;
        if(rem != 0){
            itotal += (IPB - rem);
        }
        //itotal must be lower than or equal to the rounded up value of ntotal/8
        rem = ntotal % 8;
        uint32_t round_ntotal = ntotal;
        if(rem != 0){
            round_ntotal = ntotal + 8 - rem;
            
        }
        round_ntotal /= 8;
        if(itotal > round_ntotal){
                itotal = round_ntotal;
        }
        
        //itotal must be lower than or equal to MAX_INODES
        if(itotal > MAX_INODES){
            itotal = MAX_INODES;
        }

        //itotal must be greater than or equal to IPB
        if(itotal < IPB){
            itotal = IPB;
        }
        
        itsize = itotal / IPB;
        dbtotal = ntotal - 1 - itsize;
        
        uint32_t bs = dbtotal/(BlockSize*8+1);
        rem = dbtotal % (BlockSize*8+1);
        if(rem == 1) itsize++;
        else if(rem != 0) bs += 1 + (rem/(BlockSize*8+1));

        dbtotal -= bs;
    }
};

