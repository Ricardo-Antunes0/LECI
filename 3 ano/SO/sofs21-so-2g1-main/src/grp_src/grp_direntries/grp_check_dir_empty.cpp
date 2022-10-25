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
    bool grpCheckDirEmpty(int ih)
    {
        soProbe(205, "%s(%d)\n", __FUNCTION__, ih);

        SOInode* inode_pointer = soGetInodePointer(ih); 
        uint32_t inode_pointer_amount = inode_pointer -> size / BlockSize; //gives how many blocks the dir occupies

        SODirectorySlot dir[DPB];
        soReadInodeBlock(ih,0,dir);

        
        for (uint32_t i = 2; i < DPB; i++){
            if (!strcmp(dir[i].nameBuffer, "\0")){ // if entry != of \0, means it's not empty
                return false;
            } 
        }

        for (uint32_t j = 1; j < inode_pointer_amount; j++){
            soReadInodeBlock(ih,j,dir);
            for (uint32_t k = 0; k < DPB; k++){
                if (!strcmp(dir[k].nameBuffer, "\0")){
                return false;
                } 
            } 
        }
        return true;
    }
};


