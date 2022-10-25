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
    uint16_t grpDeleteDirentry(int pih, const char *name)
    {
        soProbe(203, "%s(%d, %s)\n", __FUNCTION__, pih, name);

        uint32_t result = 0;
        SODirectorySlot ref[DPB];
        SOInode* inode_pointer = soGetInodePointer(pih);
        uint32_t numero_blocks = ((inode_pointer->size) / BlockSize);
        uint32_t i = 0;
        
        if (!strncmp(name, "", FILENAME_MAXLEN)){
            throw SOException(EINVAL,__FUNCTION__);
        }
        if (strlen(name) > FILENAME_MAXLEN){
            throw SOException(ENAMETOOLONG, __FUNCTION__);
        }
        
        while (i < numero_blocks){ 
            soReadInodeBlock(pih, i, ref);
            for(uint32_t k = 0; k < DPB; k++) {
                if(strncmp(ref[k].nameBuffer, name, FILENAME_MAXLEN) == 0){
                    memset(ref[k].nameBuffer, '\0', FILENAME_MAXLEN);
                    ref[k].in = NullInodeReference;
                    soWriteInodeBlock(pih, i, ref);
                    return result;
                }
            }
            i++;
        }
        throw SOException(ENOENT, __FUNCTION__);
    }
};

