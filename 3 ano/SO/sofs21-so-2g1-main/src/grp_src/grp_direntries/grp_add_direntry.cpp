#include "direntries.h"

#include "core.h"
#include "devtools.h"
#include "daal.h"
#include "inodeblocks.h"
#include "bin_direntries.h"

#include <errno.h>
#include <string.h>
#include <sys/stat.h>

namespace sofs21
{
    void grpAddDirentry(int pih, const char *name, uint16_t cin)
    {
        soProbe(202, "%s(%d, %s, %u)\n", __FUNCTION__, pih, name, cin);


        SOInode* pointer = soGetInodePointer(pih);
        uint32_t block_idx = NullBlockReference;        
        uint32_t dir_entries_idx = 0;         
        

        for(uint32_t i = 0;i < pointer->size/BlockSize;i++){
            SODirectorySlot dir_entries[DPB];
            soReadInodeBlock(pih,i,dir_entries);

            for (unsigned int j = 0; j < DPB; j++){
                if(strcmp(name,dir_entries[j].nameBuffer) == 0){
                    throw SOException(EEXIST,__FUNCTION__);
                }

                if(block_idx == NullBlockReference){
                    if(strlen(name) > DIRECTORY_SLOT){
                        if(dir_entries[j].nameBuffer[0] == '\0' && dir_entries[j+1].nameBuffer[0] == '\0') {
                            block_idx = i;
                            dir_entries_idx = j;
                        }
                    }
                    else{
                        if(dir_entries[j].nameBuffer[0] == '\0'){
                            block_idx = i;
                            dir_entries_idx = j;
                        }
                    }
                }
                
            }
        } 

        SODirectorySlot dir_entries[DPB];  
        soReadInodeBlock(pih,block_idx,dir_entries);    

        if(strlen(name) > DIRECTORY_SLOT) {
            strncpy(dir_entries[dir_entries_idx].nameBuffer,name,DIRECTORY_SLOT);
            dir_entries[dir_entries_idx].in = NullInodeReference;
            strncpy(dir_entries[dir_entries_idx+1].nameBuffer,name+DIRECTORY_SLOT,DIRECTORY_SLOT); 
            dir_entries[dir_entries_idx+1].in = cin;
        }  
        else{
            strncpy(dir_entries[dir_entries_idx].nameBuffer,name,DIRECTORY_SLOT);
            dir_entries[dir_entries_idx].in = cin;
        }
        soWriteInodeBlock(pih,block_idx,dir_entries);

    }
};
