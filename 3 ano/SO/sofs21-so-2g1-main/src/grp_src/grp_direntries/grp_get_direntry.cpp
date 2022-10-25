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
    uint16_t grpGetDirentry(int pih, const char *name)
    {
        soProbe(201, "%s(%d, %s)\n", __FUNCTION__, pih, name);

        for(unsigned i = 0; name[i]!='\0';i++){
            if(name[i]=='/'){
                throw SOException(EINVAL, __FUNCTION__);
            }
        }

     
        SOInode* pointer= soGetInodePointer(pih);     
        uint16_t blckcount = pointer->blkcnt;
        for(unsigned i=0;i<blckcount;i++){
            SODirectorySlot slot[DPB];
            soReadDataBlock(i,slot);
            
            for(unsigned j=0;j<DPB;j++){
                char *nameDirentry = slot[j].nameBuffer;
                if(nameDirentry[0] != '\0' && slot[j].in == NullInodeReference){
                    char * nameDirentry2 =slot[j+1].nameBuffer;
                    char * fullName = strcat(nameDirentry,nameDirentry2);
                }
                if(strcmp(slot[j].nameBuffer, name)==0){
                    return slot[j+1].in;
                }

                if(strcmp(nameDirentry,name)==0){
                    return slot[j].in;
                }
            
            }
            
        }
        return NullInodeReference;
        
    }
};

