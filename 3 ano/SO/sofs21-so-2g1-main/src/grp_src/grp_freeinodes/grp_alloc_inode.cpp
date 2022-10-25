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
    uint16_t grpAllocInode()
    {
        soProbe(401, "%s()\n", __FUNCTION__);

    /* replace this comment and following line with your code */
    //return binAllocInode();
    SOSuperblock *sbpointer = soGetSuperblockPointer();
    uint16_t iidx = sbpointer ->iidx; //starting at the bit position pointed to by the iidx superblock field
    uint16_t inodenumber = iidx;
   
    //returns NullBlockReference if there is no free inodes
   if (sbpointer->ifree ==0){
            return NullBlockReference;
        }
    
    do
    {
        // ibitmap must be circularly searched  
        //por isso temos de Mudar o ponteiro para o inicio no caso da pesquisa ter chegado ao final ou entao nao ter 
        //encontrado o inode livre
        if (inodenumber == MAX_INODES-1 || inodenumber >= sbpointer->itotal-1)
        {
            inodenumber=0;
        }
        else    inodenumber+=1;

        int32_t block = inodenumber/32 ;  //geting the block of bitmap
        uint32_t position =  inodenumber %32 ; // getting the posi
        uint32_t bit = 0x01 << position ;       // getting only one bit 
        
        
        //looking for the first bit at one
        //putting it at zero and returning its index, 
        //which corresponds to the inode number
        
        if( ((sbpointer->ibitmap[block] & bit) ^ bit) == 0) {  //verificar se o inode esta free => a 1
				sbpointer->ibitmap[block] = sbpointer->ibitmap[block] | bit;
				break;
			}

    } while (inodenumber!=iidx);


    //iidx e ifree updtades
    sbpointer->ifree = sbpointer->ifree-1; //update ao ifree 
    sbpointer ->iidx = inodenumber;
    
    soSaveSuperblock();
    return inodenumber;
    

    }
};

