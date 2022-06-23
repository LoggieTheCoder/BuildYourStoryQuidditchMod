package fr.loggie.bys.containers.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class SlotOnlyItem extends Slot {
    Item item;
    public SlotOnlyItem(IInventory inventoryIn, int index, int xPosition, int yPosition, Item item) {
        super(inventoryIn, index, xPosition, yPosition);
        this.item=item;
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem()==item;
    }
}
