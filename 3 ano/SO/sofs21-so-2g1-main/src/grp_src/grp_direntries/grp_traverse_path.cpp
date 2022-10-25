#include "direntries.h"

#include "core.h"
#include "devtools.h"
#include "daal.h"
#include "inodeblocks.h"
#include "direntries.h"
#include "bin_direntries.h"

#include <errno.h>
#include <string.h>
#include <libgen.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/stat.h>

namespace sofs21
{
    uint16_t grpTraversePath(char *path)
    {
        soProbe(221, "%s(%s)\n", __FUNCTION__, path);

        /* replace this comment and following line with your code */
        //return binTraversePath(path);
        if(strcmp(strndup(path,1),"/")){
            return 0;
        }
            
        char *dir = dirname(strdup(path));
        char *base = basename(strdup(path));

        if(strcmp(dir,"/") == 0)                
        {
            if(strcmp(dir,base) == 0)  
            {
                return 0;                          
            }
        }
            
        uint32_t inode = grpTraversePath(dir);
        uint32_t ih = soOpenInode(inode);
        SOInode *sip = soGetInodePointer(ih);

        if((sip->mode & S_IFDIR) != S_IFDIR){
            throw SOException(ENOTDIR, __FUNCTION__);
        }
            

        if(!soCheckInodeAccess(ih, X_OK)){
            throw SOException(EACCES, __FUNCTION__);
        }
            
        
        uint32_t inumber = soGetDirentry(ih,base);


        soCloseInode(ih);
        return inumber;
    }
};

