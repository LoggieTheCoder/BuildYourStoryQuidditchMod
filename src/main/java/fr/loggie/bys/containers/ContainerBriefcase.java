package fr.loggie.bys.containers;


import fr.loggie.bys.blocks.BriefcaseBlock;
import fr.loggie.bys.containers.slots.SlotOnlyItem;
import fr.loggie.bys.init.ModBlocks;
import fr.loggie.bys.init.ModItems;
import fr.loggie.bys.tilentities.TileEntityBriefcase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ContainerBriefcase extends Container
{
    private final int slotsByRows=4;
    private final int numRows;
    private final TileEntityBriefcase chestInventory;

    public ContainerBriefcase(InventoryPlayer playerInv, TileEntityBriefcase tileEntityBriefcase, EntityPlayer player)
    {
        this.chestInventory = tileEntityBriefcase;
        this.numRows = tileEntityBriefcase.getSizeInventory() / slotsByRows;
        tileEntityBriefcase.openInventory(player);
        this.addSlotToContainer(new SlotOnlyItem(tileEntityBriefcase, 0, 8+18, 6*18, ModItems.BLUDGER_ITEM));
        this.addSlotToContainer(new SlotOnlyItem(tileEntityBriefcase, 1, 8+3*18, 6*18,ModItems.QUAFFLE_ITEM));
        this.addSlotToContainer(new SlotOnlyItem(tileEntityBriefcase, 2, 8+5*18, 6*18,ModItems.GOLDEN_SNITCH_ITEM));
        this.addSlotToContainer(new SlotOnlyItem(tileEntityBriefcase, 3, 8+7*18, 6*18,ModItems.BLUDGER_ITEM));


        for(int y = 0; y < 3; y++)
        {
            for(int x = 0; x < 9; x++)
            {
                this.addSlotToContainer(new Slot(playerInv, x + y*9 + 9, 8 + x*18, 175 + y*18));
            }
        }

        for(int x = 0; x < 9; x++)
        {
            this.addSlotToContainer(new Slot(playerInv, x, 8 + x*18, 233));
        }
    }
    public Slot getSlot(String name){
        switch (name) {
            case "bludger1":
                return this.inventorySlots.get(0);
            case "quaffle":
                return this.inventorySlots.get(1);
            case "golden_snitch":
                return this.inventorySlots.get(2);
            case "bludger2":
                return this.inventorySlots.get(3);
        }
        return this.inventorySlots.get(0);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn)
    {
        return this.chestInventory.isUsableByPlayer(playerIn);
    }

    @Override
    public void onContainerClosed(EntityPlayer playerIn)
    {
        super.onContainerClosed(playerIn);
        updateBlock(this.chestInventory.getWorld());
        chestInventory.closeInventory(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index)
    {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index < this.numRows * slotsByRows)
            {
                if (!this.mergeItemStack(itemstack1, this.numRows * slotsByRows, this.inventorySlots.size(), true))
                {
                    return ItemStack.EMPTY;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 0, this.numRows * slotsByRows, false))
            {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty())
            {
                slot.putStack(ItemStack.EMPTY);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return itemstack;
    }

    public TileEntityBriefcase getChestInventory()
    {
        return this.chestInventory;
    }


    public void updateBlock(World world){
        if(!world.isRemote){
            ItemStack bluger1 = getSlot("bludger1").getStack();
            ItemStack quaffle = getSlot("quaffle").getStack();
            ItemStack golden_snitch = getSlot("golden_snitch").getStack();
            ItemStack bluger2 = getSlot("bludger2").getStack();
            boolean b1 = !bluger1.isEmpty();
            boolean q = !quaffle.isEmpty();
            boolean gs = !golden_snitch.isEmpty();
            boolean b2 = !bluger2.isEmpty();
            IBlockState state = world.getBlockState(this.chestInventory.getPos());
            if(state.getBlock() instanceof BriefcaseBlock) {
                IBlockState state2 = ModBlocks.BRIEFCASE.getDefaultState().withProperty(BriefcaseBlock.BLUDGER1, b1)
                        .withProperty(BriefcaseBlock.BLUDGER2, b2)
                        .withProperty(BriefcaseBlock.GOLDEN_SNITCH, gs)
                        .withProperty(BriefcaseBlock.QUAFFLE, q)
                        .withProperty(BriefcaseBlock.OPEN, state.getValue(BriefcaseBlock.OPEN))
                        .withProperty(BriefcaseBlock.FACING, state.getValue(BriefcaseBlock.FACING));
                world.setBlockState(this.chestInventory.getPos(), state2);
            }
        }
    }
}