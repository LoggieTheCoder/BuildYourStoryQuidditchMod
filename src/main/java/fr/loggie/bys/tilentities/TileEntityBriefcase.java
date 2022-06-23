package fr.loggie.bys.tilentities;



import fr.loggie.bys.blocks.BriefcaseBlock;
import fr.loggie.bys.utils.References;
import fr.loggie.bys.containers.ContainerBriefcase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemBow;
import net.minecraft.item.ItemClock;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntityLockableLoot;
import net.minecraft.util.ITickable;
import net.minecraft.util.NonNullList;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TileEntityBriefcase extends TileEntityLockableLoot implements ITickable
{

    private NonNullList<ItemStack> chestContents = NonNullList.<ItemStack>withSize(4, ItemStack.EMPTY);
    public int numPlayersUsing, ticksSinceSync;
    public float lidAngle, prevLidAngle;
    public String pos1X ="", pos1Y ="",pos1Z ="", pos2X ="",pos2Y ="",pos2Z ="";


    public NonNullList<ItemStack> getChestContents() {
        return chestContents;
    }
    public void setChestContents(NonNullList<ItemStack> chestContents){
        this.chestContents = chestContents;
    }

    @Override
    public int getSizeInventory()
    {
        return 4;
    }

    @Override
    public int getInventoryStackLimit()
    {
        return 1;
    }

    @Override
    public boolean isEmpty()
    {
        for(ItemStack stack : this.chestContents)
        {
            if(!stack.isEmpty()) return false;
        }

        return true;
    }

    @Override
    public String getName()
    {
        return this.hasCustomName() ? this.customName : "container.briefcase";
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        super.readFromNBT(compound);
        this.chestContents = NonNullList.<ItemStack>withSize(this.getSizeInventory(), ItemStack.EMPTY);
        if(!this.checkLootAndRead(compound)) ItemStackHelper.loadAllItems(compound, chestContents);
        if(compound.hasKey("CustomName", 8)) this.customName = compound.getString("CustomName");
        if(compound.hasKey("pos1X",8)) this.pos1X = compound.getString("pos1X");
        if(compound.hasKey("pos1Y",8)) this.pos1Y = compound.getString("pos1Y");
        if(compound.hasKey("pos1Z",8)) this.pos1Z = compound.getString("pos1Z");
        if(compound.hasKey("pos2X",8)) this.pos2X = compound.getString("pos2X");
        if(compound.hasKey("pos2Y",8)) this.pos2Y = compound.getString("pos2Y");
        if(compound.hasKey("pos2Z",8)) this.pos2Z = compound.getString("pos2Z");
    }
    public BlockPos[] getGuiPositions(){
        try{
            System.out.println(pos1X+"   "+pos1Y);
            return new BlockPos[]{
                    new BlockPos(Integer.parseInt(pos1X),Integer.parseInt(pos1Y),Integer.parseInt(pos1Z)),
                    new BlockPos(Integer.parseInt(pos2X),Integer.parseInt(pos2Y),Integer.parseInt(pos2Z))
            };
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        super.writeToNBT(compound);
        if(!this.checkLootAndWrite(compound)) ItemStackHelper.saveAllItems(compound, chestContents);
        if(compound.hasKey("CustomName", 8)) compound.setString("CustomName", this.customName);
         compound.setString("pos1X", this.pos1X);
         compound.setString("pos1Y", this.pos1Y);
         compound.setString("pos1Z", this.pos1Z);
         compound.setString("pos2X", this.pos2X);
         compound.setString("pos2Y", this.pos2Y);
         compound.setString("pos2Z", this.pos2Z);
        return compound;
    }

    @Override
    public Container createContainer(InventoryPlayer playerInventory, EntityPlayer playerIn)
    {
        return new ContainerBriefcase(playerInventory, this, playerIn);
    }

    @Override
    public String getGuiID()
    {
        return References.MODID + ":briefcase";
    }

    @Override
    protected NonNullList<ItemStack> getItems()
    {
        return this.chestContents;
    }

    @Override
    public boolean shouldRefresh(World world, BlockPos pos, IBlockState oldState, IBlockState newState){
        return !(newState.getBlock() instanceof BriefcaseBlock);
    }

    @Override
    public void update()
    {


        if (!this.world.isRemote && this.numPlayersUsing != 0 && (this.ticksSinceSync + pos.getX() + pos.getY() + pos.getZ()) % 200 == 0)
        {
            this.numPlayersUsing = 0;
            for (EntityPlayer entityplayer : this.world.getEntitiesWithinAABB(EntityPlayer.class, new AxisAlignedBB((double)((float)pos.getX() - 5.0F), (double)((float)pos.getY() - 5.0F), (double)((float)pos.getZ() - 5.0F), (double)((float)(pos.getX() + 1) + 5.0F), (double)((float)(pos.getY() + 1) + 5.0F), (double)((float)(pos.getZ() + 1) + 5.0F))))
            {
                if (entityplayer.openContainer instanceof ContainerBriefcase)
                {
                    if (((ContainerBriefcase)entityplayer.openContainer).getChestInventory()==this)
                    {
                        ++this.numPlayersUsing;
                    }
                }
            }
        }

        this.prevLidAngle = this.lidAngle;
        float f1 = 0.1F;

        if (this.numPlayersUsing > 0 && this.lidAngle == 0.0F)
        {
            double d1 = (double)pos.getX() + 0.5D;
            double d2 = (double)pos.getZ() + 0.5D;
            this.world.playSound((EntityPlayer)null, d1, (double)pos.getY() + 0.5D, d2, SoundEvents.BLOCK_IRON_TRAPDOOR_OPEN, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
        }

        if (this.numPlayersUsing == 0 && this.lidAngle > 0.0F || this.numPlayersUsing > 0 && this.lidAngle < 1.0F)
        {
            float f2 = this.lidAngle;

            if (this.numPlayersUsing > 0)
            {
                this.lidAngle += 0.1F;
            }
            else
            {
                this.lidAngle -= 0.1F;
            }

            if (this.lidAngle > 1.0F)
            {
                this.lidAngle = 1.0F;
            }

            float f3 = 0.5F;

            if (this.lidAngle < 0.5F && f2 >= 0.5F)
            {
                double d3 = (double)pos.getX() + 0.5D;
                double d0 = (double)pos.getZ() + 0.5D;
                this.world.playSound((EntityPlayer)null, d3, (double)pos.getY() + 0.5D, d0, SoundEvents.BLOCK_IRON_TRAPDOOR_CLOSE, SoundCategory.BLOCKS, 0.5F, this.world.rand.nextFloat() * 0.1F + 0.9F);
            }

            if (this.lidAngle < 0.0F)
            {
                this.lidAngle = 0.0F;
            }
        }
    }

    @Override
    public void openInventory(EntityPlayer player)
    {
        ++this.numPlayersUsing;
        this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
        this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
    }

    @Override
    public void closeInventory(EntityPlayer player)
    {
        --this.numPlayersUsing;
        this.world.addBlockEvent(pos, this.getBlockType(), 1, this.numPlayersUsing);
        this.world.notifyNeighborsOfStateChange(pos, this.getBlockType(), false);
    }
}