package fr.loggie.bys.entities;

import fr.loggie.bys.utils.Functions;
import fr.loggie.bys.utils.Sorts;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.Vector3d;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityFlying;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityAmbientCreature;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class EntitySortilege extends EntityFlying {


    public double move_x=0;
    public double move_y=0;
    public double move_z=0;
    public Sorts sort= Sorts.COUSSINAGE;
    public double speed = 0.5D;


    public EntitySortilege(World worldIn) {

        super(worldIn);
        this.setNoGravity(true);
        this.setInvisible(true);
        this.setSize(0F,0F);
    }
    public EntitySortilege(World world, Vec3d vector3d, Sorts sortIn){
        this(world);
        this.sort = sortIn;
        this.move_x = vector3d.x;
        this.move_y = vector3d.y;
        this.move_z = vector3d.z;
    }

    @Override
    public boolean attackable() {
        return false;
    }

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(source!=DamageSource.OUT_OF_WORLD){
            return false;
        }
        this.setDead();
        return true;
    }

    public EntitySortilege(World world, double x, double y , double z, Sorts sortIn){
        this(world);
        this.sort = sortIn;
        this.move_x = x;
        this.move_y = y;
        this.move_z = z;
    }
    public void setSort(Sorts sort){
        this.sort = sort;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }
    public void setMotion(Vector3d vect){
        this.move_x = vect.x;
        this.move_y = vect.y;
        this.move_z = vect.z;
    }

    public double getSpeed() {
        return speed;
    }

    public Sorts getSort() {
        return sort;
    }

    @Override
    protected void onInsideBlock(IBlockState p_191955_1_) {
        if(p_191955_1_.isFullBlock() ) {
            this.playSound(SoundEvents.BLOCK_GRAVEL_PLACE, 1.0F, 0);
            this.setDead();
        }
    }

    @Override
    protected void collideWithEntity(Entity entityIn) {
        super.collideWithEntity(entityIn);
        this.sort.doAction(this,entityIn);
        this.playSound(SoundEvents.BLOCK_ENCHANTMENT_TABLE_USE, 1.0F, 0);
        this.setDead();
    }

    @Override
    public void onEntityUpdate() {
        super.onEntityUpdate();
        this.world.spawnParticle(EnumParticleTypes.DRAGON_BREATH, this.posX, this.posY,this.posZ,0, 0,0);
        this.setPosition(this.posX+(move_x*speed),(this.move_y*speed)+this.posY,this.posZ+(this.move_z*speed));

    }



}
