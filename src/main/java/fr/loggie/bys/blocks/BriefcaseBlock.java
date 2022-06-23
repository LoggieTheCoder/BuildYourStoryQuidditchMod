package fr.loggie.bys.blocks;

import fr.loggie.bys.BYS;
import fr.loggie.bys.entities.EntityBall;
import fr.loggie.bys.init.ModBlocks;
import fr.loggie.bys.items.abstract_items.SpawnBall;
import fr.loggie.bys.tilentities.*;
import fr.loggie.bys.utils.Functions;
import fr.loggie.bys.utils.id.GuiID;

import fr.loggie.bys.utils.packets.SpawnBriefcaseContentPacket;
import net.minecraft.block.BlockHorizontal;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraft.init.Items;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.Random;

public class BriefcaseBlock extends BYSBlockContainer {
    //public static final AxisAlignedBB BRIEFCASE_AABB_NORTH = new AxisAlignedBB(0.0625,0,0,1.9375,0.5625,1);
    //public static final AxisAlignedBB BRIEFCASE_AABB_EAST = new AxisAlignedBB(0,0,0.0625,1,0.5625,1.9375);
    //public static final AxisAlignedBB BRIEFCASE_AABB_SOUTH = new AxisAlignedBB(-0.9375,0,0,0.9375,0.5625,1);
    //public static final AxisAlignedBB BRIEFCASE_AABB_WEST = new AxisAlignedBB(0,0,-0.9375,1,0.5625,0.9375);
    public static final AxisAlignedBB BRIEFCASE_AABB_OPEN_NORTH = Functions.getAABBSixteenths(-12,0,-6,18,30,22);
    public static final AxisAlignedBB BRIEFCASE_AABB_OPEN_EAST = Functions.getAABBSixteenths(-6,0,-12,22,30,18);
    public static final AxisAlignedBB BRIEFCASE_AABB_OPEN_SOUTH = Functions.getAABBSixteenths(-2,0,-6,28,30,22);
    public static final AxisAlignedBB BRIEFCASE_AABB_OPEN_WEST = Functions.getAABBSixteenths(-6,0,-2,22,30,28);
    public static final AxisAlignedBB BRIEFCASE_AABB_CLOSE_NORTH = Functions.getAABBSixteenths(-2,0,-6,18,18,22);
    public static final AxisAlignedBB BRIEFCASE_AABB_CLOSE_EAST = Functions.getAABBSixteenths(-6,0,-2,22,18,18);
    public static final AxisAlignedBB BRIEFCASE_AABB_CLOSE_SOUTH = BRIEFCASE_AABB_CLOSE_NORTH;
    public static final AxisAlignedBB BRIEFCASE_AABB_CLOSE_WEST =BRIEFCASE_AABB_CLOSE_EAST;
    public static final PropertyDirection FACING = BlockHorizontal.FACING;
    public static final PropertyBool OPEN = PropertyBool.create("open");
    public static final PropertyBool GOLDEN_SNITCH = PropertyBool.create("golden_snitch");
    public static final PropertyBool QUAFFLE = PropertyBool.create("quaffle");
    public static final PropertyBool BLUDGER1 = PropertyBool.create("bludger1");
    public static final PropertyBool BLUDGER2 = PropertyBool.create("bludger2");

    public BriefcaseBlock(String name, Material materialIn, float hardness, float resistance, CreativeTabs creativetab) {
        super(name, materialIn, hardness, resistance,creativetab);
        this.setSoundType(SoundType.WOOD);
        this.setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.NORTH)
                .withProperty(OPEN,false)
                .withProperty(GOLDEN_SNITCH,false)
                .withProperty(QUAFFLE,false)
                .withProperty(BLUDGER1,false)
                .withProperty(BLUDGER2,false));
    }

    @Override
    @Nonnull
    @SuppressWarnings("deprecated")
    public AxisAlignedBB getBoundingBox(IBlockState state,@Nonnull IBlockAccess source,@Nonnull BlockPos pos) {

        if(state.getValue(FACING)==EnumFacing.NORTH){
            if(isOpen(state)) {
                return BRIEFCASE_AABB_OPEN_NORTH;
            }else {
                return BRIEFCASE_AABB_CLOSE_NORTH;
            }
        }
        if(state.getValue(FACING)==EnumFacing.EAST){
            if(isOpen(state)) {
                return BRIEFCASE_AABB_OPEN_EAST;
            }else {
                return BRIEFCASE_AABB_CLOSE_EAST;
            }
        }
        if(state.getValue(FACING)==EnumFacing.SOUTH){
            if(isOpen(state)) {
                return BRIEFCASE_AABB_OPEN_SOUTH;
            }else {
                return BRIEFCASE_AABB_CLOSE_SOUTH;
            }
        }
        if(state.getValue(FACING)==EnumFacing.WEST){
            if(isOpen(state)) {
                return BRIEFCASE_AABB_OPEN_WEST;
            }else {
                return BRIEFCASE_AABB_CLOSE_WEST;
            }
        }
        return BRIEFCASE_AABB_OPEN_NORTH;
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return Item.getItemFromBlock(ModBlocks.BRIEFCASE);
    }

    @Override
    public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
        return new ItemStack(ModBlocks.BRIEFCASE);
    }

    @Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {

            if(playerIn.getHeldItem(hand).getItem()== Items.AIR) {
                if(!worldIn.isRemote) {
                    if (playerIn.isSneaking()) {
                        changeOpen(worldIn, pos);
                    } else {

                        if (this.isOpen(state)) {
                            playerIn.openGui(BYS.instance, GuiID.GUI_BRIEFCASE, worldIn, pos.getX(), pos.getY(), pos.getZ());
                        }
                    }
                }
            }
        return true;
    }

    @Override
    public boolean isFullBlock(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public void onBlockAdded(World worldIn, BlockPos pos, IBlockState state) {
        if (!worldIn.isRemote)
        {
            IBlockState north = worldIn.getBlockState(pos.north());
            IBlockState south = worldIn.getBlockState(pos.south());
            IBlockState west = worldIn.getBlockState(pos.west());
            IBlockState east = worldIn.getBlockState(pos.east());
            EnumFacing face = state.getValue(FACING);
            /**
            if (face == EnumFacing.NORTH && north.isFullBlock() && !south.isFullBlock()){ face = EnumFacing.SOUTH;//worldIn.setBlockState(pos.east(), ModBlocks.BRIEFCASE_SIDE.getDefaultState(),2);
                }
            else if (face == EnumFacing.SOUTH && south.isFullBlock() && !north.isFullBlock()){ face = EnumFacing.NORTH;//worldIn.setBlockState(pos.west(), ModBlocks.BRIEFCASE_SIDE.getDefaultState(),2);
                }
            else if (face == EnumFacing.WEST && west.isFullBlock() && !east.isFullBlock()){ face = EnumFacing.EAST;//worldIn.setBlockState(pos.south(), ModBlocks.BRIEFCASE_SIDE.getDefaultState(),2);
                }
            else if (face == EnumFacing.EAST && east.isFullBlock() && !west.isFullBlock()){ face = EnumFacing.WEST;//worldIn.setBlockState(pos.north(), ModBlocks.BRIEFCASE_SIDE.getDefaultState(),2);
                }
            **/
            worldIn.setBlockState(pos, state.withProperty(FACING, face), 2);
        }
    }


    @Nullable
    @Override
    public TileEntity createTileEntity(World world, IBlockState state) {
        return new TileEntityBriefcase();
    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer, EnumHand hand) {
        if(placer.getHorizontalFacing() == EnumFacing.NORTH) {
            return this.getDefaultState().withProperty(FACING, EnumFacing.EAST);
        }
        if(placer.getHorizontalFacing() == EnumFacing.EAST) {
            return this.getDefaultState().withProperty(FACING, EnumFacing.SOUTH);
        }
        if(placer.getHorizontalFacing() == EnumFacing.SOUTH) {
            return this.getDefaultState().withProperty(FACING, EnumFacing.WEST);
        }
        return this.getDefaultState().withProperty(FACING, EnumFacing.NORTH);

    }

    @Override
    public void onBlockPlacedBy(World worldIn, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        worldIn.setBlockState(pos,this.getDefaultState().withProperty(FACING,placer.getHorizontalFacing().getOpposite()),2);

        /** EnumFacing facing = worldIn.getBlockState(pos).getValue(FACING);
        if(facing==EnumFacing.NORTH)worldIn.setBlockState(pos.east(), ModBlocks.BRIEFCASE_SIDE.getDefaultState(),2);
        else if(facing==EnumFacing.EAST)worldIn.setBlockState(pos.south(), ModBlocks.BRIEFCASE_SIDE.getDefaultState(),2);
        else if(facing==EnumFacing.SOUTH)worldIn.setBlockState(pos.west(), ModBlocks.BRIEFCASE_SIDE.getDefaultState(),2);
        else if(facing==EnumFacing.WEST)worldIn.setBlockState(pos.north(), ModBlocks.BRIEFCASE_SIDE.getDefaultState(),2);
        **/
    }

    @Override
    public boolean hasTileEntity(IBlockState state) {
        return true;
    }

    @Override
    public void breakBlock(World worldIn, BlockPos pos, IBlockState state) {
        TileEntityBriefcase te = (TileEntityBriefcase) worldIn.getTileEntity(pos);
        InventoryHelper.dropInventoryItems(worldIn,pos, (IInventory) te);
        /**EnumFacing facing = state.getValue(FACING);
        if(facing==EnumFacing.NORTH){
            worldIn.setBlockState(pos.east(), Blocks.AIR.getDefaultState());
        }else if(facing==EnumFacing.EAST){
            worldIn.setBlockState(pos.south(), Blocks.AIR.getDefaultState());
        }else if(facing==EnumFacing.SOUTH){
            worldIn.setBlockState(pos.west(), Blocks.AIR.getDefaultState());
        }else if(facing==EnumFacing.WEST){
            worldIn.setBlockState(pos.north(), Blocks.AIR.getDefaultState());
        }**/
        super.breakBlock(worldIn,pos,state);
    }
    @Override
    public EnumBlockRenderType getRenderType(IBlockState state) {
        return EnumBlockRenderType.MODEL;
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING,rot.rotate(state.getValue(FACING)));
    }
    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn)
    {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {

        return new BlockStateContainer(this, OPEN,GOLDEN_SNITCH,QUAFFLE,BLUDGER1,BLUDGER2,FACING);
    }

    @Override
    public IBlockState getStateFromMeta(int meta)
    {
        EnumFacing facing = EnumFacing.getFront(meta);
        if(facing.getAxis() == EnumFacing.Axis.Y) facing = EnumFacing.NORTH;
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state)
    {
        return (state.getValue(FACING)).getIndex();
    }

    @Nullable
    @Override
    public TileEntity createNewTileEntity(World worldIn, int meta) {
        return new TileEntityBriefcase();
    }
    public void setOpen(World world,BlockPos pos,boolean open){
        IBlockState state = world.getBlockState(pos);
        world.setBlockState(pos, ModBlocks.BRIEFCASE.getDefaultState().withProperty(OPEN,open)
                .withProperty(GOLDEN_SNITCH,state.getValue(GOLDEN_SNITCH))
                .withProperty(BLUDGER2,state.getValue(BLUDGER2))
                .withProperty(BLUDGER1,state.getValue(BLUDGER1))
                .withProperty(QUAFFLE,state.getValue(QUAFFLE))
                .withProperty(FACING,state.getValue(FACING)));
    }
    public boolean isOpen(IBlockState state){
        return state.getValue(OPEN);
    }
    public void open(World world,BlockPos pos){
        setOpen(world,pos,true);
    }
    public void close(World world,BlockPos pos){
        setOpen(world,pos,false);
    }
    public void changeOpen(World world,BlockPos pos){
        setOpen(world,pos,!world.getBlockState(pos).getValue(OPEN));
    }

}

