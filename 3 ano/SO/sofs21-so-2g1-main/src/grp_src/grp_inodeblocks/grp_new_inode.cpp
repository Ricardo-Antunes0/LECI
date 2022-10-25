/*
 *  \authur Artur Pereira - 2016-2021
 */

#include "inodeblocks.h"
#include "bin_inodeblocks.h"
#include "grp_inodeblocks.h"

#include "daal.h"
#include "core.h"
#include "devtools.h"

#include <string.h>
#include <inttypes.h>
#include <time.h>

namespace sofs21
{
    uint16_t grpNewInode(uint16_t type, uint16_t perm)
    {
        soProbe(333, "%s(%04x, %9o)\n", __FUNCTION__, type, perm);
        /* replace this comment and following line with your code */
        if(!(type == S_IFREG || type == S_IFDIR || type == S_IFLNK)){
            throw SOException(EINVAL,__FUNCTION__);
        }

        if(!(perm>=0 || perm <= 0777)){
            throw SOException(EINVAL,__FUNCTION__);
        }

        uint16_t inode =soAllocInode();
        uint16_t node = type | perm;

        if(inode == NullInodeReference){

            uint16_t n = soUnqueueHiddenInode();

            if(n==NullInodeReference){
                throw SOException(ENOSPC,__FUNCTION__);
            }
            int handler =soOpenInode(n);
            SOInode *pointer = soGetInodePointer(handler);
            soFreeInodeBlocks(handler,0);

            pointer -> mode = node;
            pointer -> owner =geteuid();
            pointer -> group = getgid();

            __time_t current_time= time(NULL);
            pointer -> atime = current_time;
            pointer ->ctime=current_time;
            pointer ->mtime=current_time;

            
            soSaveInode(handler);
            soCloseInode(handler);

        }

    }
};

