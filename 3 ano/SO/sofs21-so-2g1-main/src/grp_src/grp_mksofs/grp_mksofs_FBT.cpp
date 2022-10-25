#include "grp_mksofs.h"

#include "rawdisk.h"
#include "core.h"
#include "devtools.h"
#include "bin_mksofs.h"

#include <inttypes.h>
#include <string.h>

namespace sofs21
{
    void grpFillInBitmapTable(uint32_t ntotal, uint16_t itsize, uint32_t dbtotal)
    {
        soProbe(605, "%s(%u, %u, %u)\n", __FUNCTION__, ntotal, itsize, dbtotal);

        u_int32_t bitmapTable[RPB];
        memset(bitmapTable, 0xff, RPB*4);
        int bitmap_dimension = ntotal - dbtotal - itsize - 1;
        int bitmap_indice = 1 + itsize;
        int exceed_bits = dbtotal % 32;
        int essential_words = (dbtotal + (32-1))/ 32;
        
        for(int i = 0; i < bitmap_dimension; i++){
            fprintf(stderr, "essential_words: %d", essential_words);
            if(i == 0){
                bitmapTable[0] = bitmapTable[0] << 1;
            }
            if (i == bitmap_dimension - 1){
                essential_words = essential_words - ((bitmap_dimension - 1) * 256 );
            
                if (exceed_bits != 0){
                    bitmapTable[essential_words - 1] = bitmapTable[essential_words] >> (32 - exceed_bits);
                }
                for(uint32_t k = essential_words; k < RPB; k++){
                   bitmapTable[k]=0;
                }
            }
            soWriteRawBlock(bitmap_indice + i, bitmapTable);
        }
    }
};

