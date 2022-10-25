/*
 *  \authur Artur Pereira - 2016-2020
 */

#include "freedatablocks.h"
#include "bin_freedatablocks.h"
#include "grp_freedatablocks.h"

#include "core.h"
#include "devtools.h"
#include "daal.h"

#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <iostream>
using namespace std;

namespace sofs21
{
    void grpDeplete(void)
    {
        soProbe(444, "%s()\n", __FUNCTION__);

        SOSuperblock *sb_p = soGetSuperblockPointer(); //ponteiro super bloco
        int BB = 8 * BlockSize;
        int ant = -1;

        if(sb_p->insertion_cache.idx == REF_CACHE_SIZE){//so se a cache estiver cheia
            
            for(int i=0; i < REF_CACHE_SIZE; i++){
                uint32_t n = sb_p -> insertion_cache.ref[i]; 
                int block_id = n/(8 * BB);
                uint32_t *p = soGetBitmapBlockPointer(block_id); //ponteiro no bitmap
                int var = n % BB; 
                
                
                int l = var / 32; 
                int pos = var % 32;
                             
                p[l] |= 1 << pos; 
                sb_p -> insertion_cache.ref[sb_p->insertion_cache.idx - 1] = NullBlockReference;
                sb_p -> insertion_cache.idx --;

                if(ant != block_id){ //so se o bloco mudar
                    
                    soSaveBitmapBlock();
                    soSaveSuperblock();
                    ant = block_id;
                }

            }
        }

        
    }
};
