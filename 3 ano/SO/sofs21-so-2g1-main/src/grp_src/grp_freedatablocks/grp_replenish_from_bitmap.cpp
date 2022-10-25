/*
 *  \author António Rui Borges - 2012-2015
 *  \authur Artur Pereira - 2016-2021
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
    void grpReplenishFromBitmap(void)
    {
        soProbe(445, "%s()\n", __FUNCTION__);

        /* replace this comment and following line with your code */
        //binReplenishFromBitmap();

        SOSuperblock* superblock_pointer = soGetSuperblockPointer();
        soOpenBitmapTable();

        //Do nothing if the retrieval cache is not empty
        if(superblock_pointer->retrieval_cache.idx == REF_CACHE_SIZE){
            int iMin;
            uint32_t bits_livres = (superblock_pointer->dbfree - superblock_pointer->insertion_cache.idx);

            if(bits_livres <= 59){
                iMin = (REF_CACHE_SIZE - superblock_pointer->dbfree);
            }
            else{ 
                iMin = 0;
            }

            for(uint32_t i = iMin; i < REF_CACHE_SIZE; i++){
                if(superblock_pointer->rbm_idx == 0){ //Checking if it is the root
                    superblock_pointer->rbm_idx = 1;
                }
                superblock_pointer->retrieval_cache.ref[i] = superblock_pointer->rbm_idx++;

                if(superblock_pointer->retrieval_cache.idx >= 60){
                    superblock_pointer->retrieval_cache.idx = 0;
                }
                superblock_pointer->retrieval_cache.idx--;
            }
            //Update the rbm and retrieval_cache index
            superblock_pointer->rbm_idx--;
            superblock_pointer->retrieval_cache.idx++; 

            uint32_t* bitmap = soGetBitmapBlockPointer(0); //Get a pointer to a bitmap block (block with index 0) of the bitmap table
            
            int index = 1;

            //Finding the first bit at 1 on bitmap
            for (uint32_t i = 0; i < superblock_pointer->dbtotal; i++){
                uint32_t* data = &bitmap[i/32];        
                uint32_t bits_livres = *data & (1 << i % 32);
                if (bits_livres != 0){
                    index = i;
                    break;
                }
                if(i == superblock_pointer->dbtotal-1){
                    superblock_pointer->rbm_idx = NullBlockReference; //if bitmap becomes empty (no bits at one), rbm_idx must be assigned NullBlockReference
                }
            }
            int data_idx = (index/32);
            for(int j = index; j < index + 60; j++){
                if ((j > 0) && (j % 32 == 0)){
                    data_idx++;
                }
                int mask = 1 << j;          //Check if the bit is 1
                bitmap[data_idx] &= ~mask; //Set the bit to 0
            }
            soSaveBitmapBlock();    //Save the bitmapblock
            soCloseBitmapTable();
            soSaveSuperblock();
            
        }else{
            return;
        }
    }
};
