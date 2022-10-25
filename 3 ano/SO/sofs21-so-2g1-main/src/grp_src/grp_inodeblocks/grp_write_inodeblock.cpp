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
    void grpWriteInodeBlock(int ih, uint32_t ibn, void *buf)
    {
        soProbe(332, "%s(%d, %u, %p)\n", __FUNCTION__, ih, ibn, buf);

        uint16_t number = soGetInodeNumber(ih);
        int inodeHandler = soOpenInode(number);
        uint32_t numberBlock = soGetInodeBlock(ih,ibn);
        SOSuperblock* pointer = soGetSuperblockPointer();

        if (inodeHandler >= pointer->itotal) 
            throw SOException(EINVAL, __FUNCTION__);
        if (numberBlock >= pointer->dbtotal)
            throw SOException(EINVAL, __FUNCTION__);

        if (numberBlock == NullBlockReference || numberBlock == NullInodeReference)
           numberBlock = soAllocInodeBlock(ih,ibn);
        soWriteDataBlock(numberBlock, buf);
    }
};

