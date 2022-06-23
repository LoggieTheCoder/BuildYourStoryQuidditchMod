package fr.loggie.bys.entities;


import fr.loggie.bys.BYS;
import fr.loggie.bys.entities.FlyingBroomEntities.*;
import fr.loggie.bys.init.ModItems;

import fr.loggie.bys.utils.Functions;
import fr.loggie.bys.utils.References;
import fr.loggie.bys.utils.Sorts;
import fr.loggie.bys.utils.id.EntitiesID;
import fr.loggie.bys.utils.packets.ExpulsePlayerFromBroom;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.passive.EntityBat;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.ArrayList;
import java.util.List;


public abstract class EntityFlyingBroom extends EntityLiving{
    public List<Integer> malefices = new ArrayList<>();
    public float prevFlyingBroomPitch, prevFlyingBroomYaw, flyingBroomPitch, flyingBroomYaw,turnLefting=0F,beginTurn=0F;
    protected double prevX, prevY, prevZ, MAX_SPEED, ACCELERATION, speed, s_z=0, s_y=0, s_x=0,freinage_next_speed;
    public static final float WIDTH=0.5F, HEIGHT=0.5F,DEGREES_BY_SALTO_TICK = 9F, CATAPULTAGE_PUISSANCE=1.8F;
    public boolean client_only_doNextTick;
    public long lastMounted;
    public EntityPlayer client_player= null ;
    public EntityFlyingBroom(World worldIn) {
        super(worldIn);
        this.setSize(getWidth(), getHeight());
        this.setEntityBoundingBox(new AxisAlignedBB(0,0,0,3,1,1));
        this.setNoGravity(true);
        this.freinage_next_speed = -1;
        this.lastMounted = -1;
        this.client_only_doNextTick = false;
        this.ACCELERATION = FlyingBrooms.DEFAULT.getAcceleration();
        this.MAX_SPEED = FlyingBrooms.DEFAULT.getMaxSpeed();
        this.flyingBroomPitch = this.rotationPitch;
        this.flyingBroomYaw = this.rotationYaw;

    }
    public float getWidth(){

        return WIDTH;
    }

    public float getHeight(){
        return HEIGHT;
    }
    public void setSpeed(double speed){
        this.speed = speed;
    }

    public double getSpeed(){return speed;}

    public double getAcceleration() {
        return ACCELERATION;
    }

    public double getMaxSpeed() {return MAX_SPEED;}
    public ItemStack getDataLoot(){
        ItemStack stack = getLoot();
        NBTTagCompound nbt = new NBTTagCompound();
        nbt.setBoolean("hascatapultage",this.hasSort(Sorts.CATAPULTAGE.getID()));
        stack.setTagCompound(nbt);
        return stack;
    }
    public ItemStack getLoot(){
        return new ItemStack(Items.AIR);
    }
    public ResourceLocation getTexture(){
        return null;
    }
    public ModelBase getModel(){
        return null;
    }

    @Override
    protected boolean canBeRidden(Entity entityIn) {
        return entityIn instanceof EntityPlayer;
    }

    @Override
    protected boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (!Minecraft.getMinecraft().gameSettings.keyBindSneak.isKeyDown() && !player.isRiding() && this.canBeRidden(player) && !this.isBeingRidden()) {
            ItemStack is = player.getHeldItem(hand);
            if(!(is.getItem().getRegistryName().toString().equals(ModItems.MAGIC_QUIDDITCH_WAND.getRegistryName().toString()))){
                lastMounted = Functions.getUnixTime();
                player.startRiding(this);
            }
            return true;
        }
        return false;
    }
    public void addSpeed(double toAdd){
        if(speed+toAdd>0 && speed+toAdd<=this.MAX_SPEED) {
            if(toAdd>0 && hasSort(Sorts.COUSSINAGE.getID())){
                this.speed +=toAdd;
            }
            this.speed += toAdd;
        }else if(speed+toAdd<=0){
            this.speed = 0;
        }else if(speed+toAdd>this.MAX_SPEED){
            this.speed = this.MAX_SPEED;
        }
    }
    public void startFreinage(){freinage_next_speed = this.speed;}
    public void doFreinage(){
        if(freinage_next_speed-MAX_SPEED/40>0){
            freinage_next_speed -= MAX_SPEED/40;
            this.speed = freinage_next_speed;
        }else {
            if(freinage_next_speed != -1) {
                this.speed = 0;
            }
            freinage_next_speed = -1;
        }
    }
    public void accelerate(){this.addSpeed(this.getAcceleration());}
    public void decelerate(){this.addSpeed(-1*this.getAcceleration());}

    @Override
    public boolean attackEntityFrom(DamageSource source, float amount) {
        if(!world.isRemote) {
            world.spawnEntity(new EntityItem(this.world, this.posX, this.posY, this.posZ,this.getDataLoot()));
            this.setDead();
            return true;
        }
        return false;
    }
    @Override
    public boolean canBeCollidedWith() {return this.isDead;}
    @Override
    public boolean canBePushed() {return true;}
    @Override
    public void onLivingUpdate() {

        boolean b = true;
        super.onLivingUpdate();
        if(client_only_doNextTick && world.isRemote){


            client_player.dismountRidingEntity();
            this.removePassengers();
            doCatapultageof(client_player);
            client_only_doNextTick = false;
        }

        if(this.lastMounted+References.CATAPULTAGE_DELAY<=Functions.getUnixTime()) {
            if (this.getPassengers().size() > 0) {
                if (!world.isRemote) {
                    if(hasSort(Sorts.CATAPULTAGE.getID())) {
                        doCatapultageof((EntityPlayer) this.getPassengers().get(0));
                        BYS.PACKETS_CHANNEL.sendTo(new ExpulsePlayerFromBroom(this.getEntityId()), (EntityPlayerMP) this.getPassengers().get(0));
                        this.getPassengers().get(0).dismountRidingEntity();
                        this.removePassengers();
                        removeSort(Sorts.CATAPULTAGE.getID());
                    }
                }
            }
        }

        this.prevFlyingBroomPitch = this.flyingBroomPitch;
        this.prevFlyingBroomYaw = this.flyingBroomYaw;
        if(getPassengers().size()>0){

            if(turnLefting == 0) {

                this.flyingBroomPitch = getPassengers().get(0).rotationPitch;
                this.flyingBroomYaw = getPassengers().get(0).getRotationYawHead()+180;
                s_x = this.getPassengers().get(0).getLookVec().x * speed;
                s_y = this.getPassengers().get(0).getLookVec().y * speed;
                s_z = this.getPassengers().get(0).getLookVec().z * speed;
            }
            doSalto();

        }


        if(!world.isRemote) {
            this.doFreinage();
            this.rotationPitch = this.flyingBroomPitch;
            this.rotationYaw = this.flyingBroomYaw;
            if(isBeingRidden() && getPassengers().get(0) instanceof  EntityPlayer){
                EntityPlayer player = (EntityPlayer) this.getPassengers().get(0);
                player.rotationPitch = this.flyingBroomPitch;
            }
        }
        if(!world.isRemote && getPassengers().size()==1){
            s_x/=(1+this.ACCELERATION/this.MAX_SPEED);
            s_y/=(1+this.ACCELERATION/this.MAX_SPEED);
            s_z/=(1+this.ACCELERATION/this.MAX_SPEED);
            b=false;
        }
        if(!world.isRemote){
            if(b) {
                s_x /= 1.005;
                s_y /= 1.005;
                s_z /= 1.005;
            }
            this.prevX = this.posX;
            this.prevY = this.posY;
            this.prevZ = this.posZ;
            this.addPosition(s_x, s_y, s_z);
        }
        this.doBlockCollisions();

    }

    public boolean addPosition(double x, double y , double z){
        if(!world.getBlockState(new BlockPos(posX+x,posY+y,posZ+z)).isFullBlock()) {
            this.setPosition(this.posX + x, this.posY + y, this.posZ + z);
            return true;
        }
        return false;
    }
    public void setSalto(){
        this.turnLefting = 360;
        this.beginTurn = this.rotationPitch;
    }
    public void doRotate(float yaw, float pitch){
        this.flyingBroomPitch = pitch+this.flyingBroomPitch;
        this.flyingBroomYaw = yaw+this.flyingBroomYaw;
    }
    public float getFixedPitch(){
        return (this.flyingBroomPitch+this.prevFlyingBroomPitch)/2;
    }
    public float getFixedYaw(){
        return (this.flyingBroomYaw+this.prevFlyingBroomYaw)/2;
    }
    public void doSalto(){
        if(this.isBeingRidden() && this.getPassengers().get(0) instanceof EntityPlayer) {
            if (turnLefting > 0) {
                if (turnLefting > DEGREES_BY_SALTO_TICK) {
                    this.doRotate(0, -1*DEGREES_BY_SALTO_TICK);
                    turnLefting -= DEGREES_BY_SALTO_TICK;
                } else {
                    this.doRotate(0, -1*turnLefting);
                    turnLefting = 0;
                }
            } else {
                turnLefting = 0;
            }
        }else {
            turnLefting = 0;
        }
    }
    private static double getRealSpeed(double km_h){
        return Functions.getTickSpeedFromKmH(km_h)* References.BROOMS_REAL_SPEED_MODIFIER;
    }
    public enum FlyingBrooms{
        DEFAULT(20D,0.01,"default", EntityVarapidosBroom.class),
        BLUE_BOMB(5D,0.09,"bluebottle", EntityBlueBombBroom.class),
        VARAPIDOS(15D,0.1,"varapidos",EntityVarapidosBroom.class),
        BROSSDUR_1(10D,0.09,"brossdur1", EntityBrossdur1Broom.class),
        BROSSDUR_3(10D,0.09,"brossdur3", EntityBrossdur3Broom.class),
        BROSSDUR_5(10D,0.09,"brossdur5", EntityBrossdur5Broom.class),
        BROSSDUR_6(10D,0.09,"brossdur6", EntityBrossdur6Broom.class),
        BROSSDUR_7(10D,0.09,"brossdur7", EntityBrossdur7Broom.class),
        BROSSDUR_11(10D,0.09,"brossdur11", EntityBrossdur11Broom.class),
        COMETE_140(10D,0.09,"comet140", EntityComete140Broom.class),
        COMETE_180(10D,0.09,"comet180", EntityComete180Broom.class),
        COMETE_260(10D,0.09,"comet260", EntityComete260Broom.class),
        COMETE_290(10D,0.09,"comet290", EntityComete290Broom.class),
        FIREBOLT(240D,3,"firebolt",EntityFireboltBroom.class),
        FRISELUNE(10D,0.09,"moontrimmer",EntityFriseluneBroom.class),
        NIMBUS_1000(10D,0.09,"nimbus1000",EntityNimbus1000Broom.class),
        NIMBUS_1001(10D,0.09,"nimbus1001",EntityNimbus1001Broom.class),
        NIMBUS_1500(10D,0.09,"nimbus1500",EntityNimbus1500Broom.class),
        NIMBUS_1700(10D,0.09,"nimbus1700",EntityNimbus1700Broom.class),
        NIMBUS_2000(10D,0.09,"nimbus2000",EntityNimbus2000Broom.class),
        NIMBUS_2001(10D,0.09,"nimbus2001",EntityNimbus2001Broom.class),
        LANCECHENE_79(10D,0.09,"oakshaft79",EntityLancechene79Broom.class),
        ETOILE_FILANTE(10D,0.09,"shootingstar",EntityEtoileFilanteBroom.class),
        STEEL_ARROW_BROOM(110D,0.09,"silver_arrow",EntitySteelArrowBroom.class),
        MANCHEVIF(10D,0.09,"swiftstick",EntityManchevifBroom.class),
        FIRETOPOWDERS(10D,0.09,"tinderblast",EntityFiretopowdersBroom.class),
        MARGOTIN_90(10D,0.09,"twigger90",EntityMargotin90Broom.class),
        BALETOILE_XXI(10D,0.09,"baletoile_xxi", EntityBaletoileXXIBroom.class),
        YAJIRUSHI(10D,0.09,"yajirushi",EntityYajirushiBroom.class),
        FIREBOLT_SUPREME(10D,0.09,"firebolt_supreme",EntityFireboltSupremeBroom.class),
        BABY_BROOM(10D,0.5D,"baby_broom",EntityBabyBroom.class)
        ;

        final double MAX_SPEED;
        final double ACCELERATION;
        final String NAME;
        final Class<? extends EntityFlyingBroom> ENTITY_CLASS;

        FlyingBrooms(final double MAX_SPEED,final double ACCELERATION,final String name, final Class<? extends EntityFlyingBroom> entity_class){
            this.ACCELERATION = ACCELERATION;
            this.MAX_SPEED = MAX_SPEED;
            this.NAME = name;
            this.ENTITY_CLASS = entity_class;
        }
        public String getCustomName(){
            return NAME;
        }
        public Class<? extends EntityFlyingBroom> getEntityClass(){
            return ENTITY_CLASS;
        }
        public double getAcceleration() {
            return getRealSpeed(ACCELERATION);
        }

        public double getMaxSpeed() {
            return getRealSpeed(MAX_SPEED);
        }
        public int getID(){
            int i =-1;
            for (FlyingBrooms value : FlyingBrooms.values()) {
                if(this.equals(value)){
                    return (EntitiesID.BROOMS_BASE +i);
                }
                i++;
            }
            return (EntitiesID.BROOMS_BASE +i);
        }
        public static FlyingBrooms getElement(int e){
            return FlyingBrooms.values()[e];
        }
        public static int getLenght(){
            return FlyingBrooms.values().length;
        }
    }
    public int[] getIntList(){
        int[] fin = new int[malefices.size()];
        for (int i = 0;i<malefices.size();i++) {
            fin[i]=malefices.get(i);
        }
        return fin;
    }
    public List<Integer> getIntegerArray(int[] list){
        List<Integer> fin = new ArrayList<>();
        for (int i : list) {
            fin.add(i);
        }
        return fin;
    }
    @Override
    public void writeEntityToNBT(NBTTagCompound compound) {
        compound.setIntArray("malefices",getIntList());
        super.writeEntityToNBT(compound);
    }

    @Override
    public void readEntityFromNBT(NBTTagCompound compound) {
        malefices = getIntegerArray(compound.getIntArray("malefices"));
        super.readEntityFromNBT(compound);
    }
    public boolean hasSort(int ID){
        for (Integer malefice : this.malefices) {
            if(malefice == ID){
                return true;
            }
        }
        return false;
    }
    public void addSort(Sorts sort){
        if(!this.hasSort(sort.getID())) {
            malefices.add(sort.getID());
        }
        if(sort.equals(Sorts.CATAPULTAGE)){
            this.lastMounted = Functions.getUnixTime();
        }
    }
    public void removeSort(int i){
        malefices.removeIf(malefice -> i == malefice);
    }

    public void doCatapultageof(EntityPlayer player){

        double v_x = player.getLookVec().x;
        double v_z = player.getLookVec().z;
        double t = Math.abs(v_x) + Math.abs(v_z);
        v_x /= t;
        v_z /= t;
        player.setVelocity(v_x*3,CATAPULTAGE_PUISSANCE,v_z*3);
        removeSort(Sorts.CATAPULTAGE.getID());

    }
    public void doRiderCatapultage(){
        if(this.getPassengers().size() ==1 && this.getPassengers().get(0) instanceof EntityPlayer){
            doCatapultageof((EntityPlayer) this.getPassengers().get(0));
        }
    }
}
