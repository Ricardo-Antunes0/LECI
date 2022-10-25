#include "inodeblocks.h"
#include "bin_inodeblocks.h"
#include "grp_inodeblocks.h"

#include "freedatablocks.h"
#include "daal.h"
#include "core.h"
#include "devtools.h"

#include <errno.h>

#include <iostream>

namespace sofs21
{

#if true
    static uint32_t grpAllocIndirectInodeBlock(int ih, uint32_t & i1, uint32_t idx);
    static uint32_t grpAllocDoubleIndirectInodeBlock(int ih, uint32_t & i2, uint32_t idx);
#endif

    /* ********************************************************* */

    uint32_t grpAllocInodeBlock(int ih, uint32_t ibn)
    {
        soProbe(302, "%s(%d, %u)\n", __FUNCTION__, ih, ibn);

    
        
        SOInode *inode = soGetInodePointer(ih);     //ponteiro para o inode

        uint32_t aloc1;                        //variavel para o bloco alocado 
        
        uint32_t N_INDIRECT1 = RPB + N_DIRECT - 1;                       
        uint32_t N_DOUBLE_INDIRECT1 = N_INDIRECT1 + (RPB * RPB);      

        if(ibn < 0 || ibn > N_DOUBLE_INDIRECT1)
        {
            throw SOException(EINVAL,__FUNCTION__);                         
        }

        if(ibn < N_DIRECT)                              
        {
            if(inode->d[ibn] != NullBlockReference)     
            {
                throw SOException(ESTALE,__FUNCTION__);
            }
            aloc1 = soAllocDataBlock();            //alocamos um datablock para d[ibn]
            inode->blkcnt++;                            //blockcount incrementa
            inode->d[ibn] = aloc1;                 //bloco alocado
        }else if(ibn > N_DIRECT && ibn < N_INDIRECT1)     
        {
            aloc1 = grpAllocIndirectInodeBlock(ih,inode->i1,ibn-N_DIRECT);     //funçao implementada em baixo
        }else
        {
            aloc1 = grpAllocDoubleIndirectInodeBlock(ih,inode->i2,ibn-N_INDIRECT1);   //função implementada em baixo
        }
        soSaveInode(ih);       
        return aloc1;      
    }

    /* ********************************************************* */

#if true
    /*
    */
    static uint32_t grpAllocIndirectInodeBlock(int ih, uint32_t & i1, uint32_t idx)
    {
        soProbe(302, "%s(%d, %u, %u)\n", __FUNCTION__, ih, i1, idx);

       
        
        SOInode *inode = soGetInodePointer(ih);     //pointeiro inode
        uint32_t idx1 = idx % RPB;          
        uint32_t ref[RPB];                   
        uint32_t aloc2;                     

        if(i1 == NullBlockReference)                
        {
            uint32_t refBlock = soAllocDataBlock(); 
            for(uint32_t i=0 ; i < RPB ; i++)
            {
                ref[i] = NullBlockReference; //referencias nulas
            }
            soWriteDataBlock(refBlock,ref);  //
            i1 = refBlock;                          //atribuir bloco alocado a i1
            inode->blkcnt++;                        //atualizar blknt
        }else       //se i1 ja tem bloco alocado
        {
            soReadDataBlock(i1,ref);    
        }
        aloc2 = soAllocDataBlock();       //alocar o bloco
        ref[idx1] = aloc2; 
        soWriteDataBlock(i1,ref);      
        inode->blkcnt++;                        //atualizar blkcnt do inode
        return aloc2;                    
    }
#endif

    /* ********************************************************* */

#if true
    /*
    */
    static uint32_t grpAllocDoubleIndirectInodeBlock(int ih, uint32_t & i2, uint32_t idx)
    {
        soProbe(302, "%s(%d, %u, %u)\n", __FUNCTION__, ih, i2, idx);

        

        SOInode *inode = soGetInodePointer(ih);         //ponteiro para o para inode
        uint32_t aloc3;                           
        uint32_t ref[RPB];                       
        uint32_t aux = idx / RPB;     

        if(i2 == NullBlockReference)                    //se i2 nao tiver  o bloco alocado
        {
            aloc3 = soAllocDataBlock();           
            for(uint32_t i=0 ; i<RPB ; i++)
            {
                ref[i] = NullBlockReference;    //referencias nulas
            }
            soWriteDataBlock(aloc3,ref);   
            i2 = aloc3;                           
            inode->blkcnt++;                            //atualizamos o blkcnt
        }else         
        {
            soReadDataBlock(i2,ref);             
        }
        return grpAllocIndirectInodeBlock(ih,ref[aux],idx);     
    }
#endif
};