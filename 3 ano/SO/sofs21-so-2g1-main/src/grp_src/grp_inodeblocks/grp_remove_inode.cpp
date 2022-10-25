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

namespace sofs21
{
    void grpRemoveInode(uint16_t in)
    {
        soProbe(334, "%s(%d)\n", __FUNCTION__, in);

        /* replace this comment and following line with your code */
        //binRemoveInode(in);
        
		SOSuperblock *sbp = soGetSuperblockPointer();
		
		if(sbp->iqcount == DELETED_QUEUE_SIZE)
		{
			bool validation = soHideInode(in);
			if (validation){
			
				uint16_t inode = soUnqueueHiddenInode();
				int ih = soOpenInode(inode);
				
				soFreeInodeBlocks(ih, 0);
				soSaveInode(ih);
				soFreeInode(inode);
				soCloseInode(ih);
		}
			else{
				throw SOException(EINVAL,__FUNCTION__); 
			}
		}
		sbp->iqueue[sbp->iqcount-1] = in;
		sbp->iqcount += 1;
		soSaveSuperblock();

		
    }
};

