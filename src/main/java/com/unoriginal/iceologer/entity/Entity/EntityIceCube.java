package com.unoriginal.iceologer.entity.Entity;


import com.unoriginal.iceologer.init.ModSounds;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.*;
import net.minecraft.init.Blocks;
import net.minecraft.init.MobEffects;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class EntityIceCube extends Entity{
    private int floatTicks = ModConfig.iceCubeFloatTicks;
    public int fallTime = 0;
    private float fallHurtAmount = ModConfig.iceCubeDamage;
    private EntityLivingBase caster;
    private UUID casterUuid;
    private EntityLivingBase target;
    private UUID targetUUID;
    private double heightAboveTarget = 3.0D;
    private double heightAdjustment = (1.0F - this.height) / 2.0F;

   public EntityIceCube(World worldIn)
    {
        super(worldIn);
        this.setSize(2.0F, 1.5F);
    }

   public EntityIceCube(World worldIn, EntityLivingBase casterIn, EntityLivingBase targetIn)
    {
        this(worldIn);
        this.preventEntitySpawning = true;
        this.setSize(2.0F, 1.5F);
        this.setCaster(casterIn);
        this.setTarget(targetIn);
        this.setPosition(targetIn.posX, targetIn.posY + 1.0D + this.heightAboveTarget + heightAdjustment, targetIn.posZ);
        this.motionX = 0.0D;
        this.motionY = 0.0D;
        this.motionZ = 0.0D;
        this.prevPosX = targetIn.posX;
        this.prevPosY = targetIn.posY + 1.0D + heightAboveTarget + heightAdjustment;
        this.prevPosZ = targetIn.posZ;
    }

    protected void entityInit() {

    }

    private void tryToFloatAboveTarget(EntityLivingBase targetIn) {
        List<EntityIceCube> nearbyIceClouds = this.world.getEntitiesWithinAABB( EntityIceCube.class, this.getEntityBoundingBox().grow(0.2, 0, 0.2), (nearbyEntity) -> nearbyEntity != this);

        if(nearbyIceClouds.isEmpty()){
            this.setPosition(targetIn.posX, targetIn.posY + 1.0D+ this.heightAboveTarget + heightAdjustment, targetIn.posZ);
        }
    }

    public void setCaster(@Nullable EntityLivingBase caster) {
        this.caster = caster;
        this.casterUuid = caster == null ? null : caster.getUniqueID();
    }

    @Nullable
    public EntityLivingBase getCaster() {
        if (this.caster == null && this.casterUuid != null && this.world instanceof WorldServer) {
            Entity entity = ((WorldServer)this.world).getEntityFromUuid(this.casterUuid);
            if (entity instanceof EntityLivingBase) {
                this.caster = (EntityLivingBase)entity;
            }
        }

        return this.caster;
    }

    public void setTarget(@Nullable EntityLivingBase target) {
        this.target = target;
        this.targetUUID = target == null ? null : target.getUniqueID();
    }

    @Nullable
    public EntityLivingBase getTarget() {
        if (this.target == null && this.targetUUID != null && this.world instanceof WorldServer) {
            Entity entity = ((WorldServer)this.world).getEntityFromUuid(this.targetUUID);
            if (entity instanceof EntityLiving) {
                this.target = (EntityLiving)entity;
            }
        }
        return this.caster;
    }
    @Override
    public void onUpdate() {
        if (floatTicks == 20){
            this.playSound(ModSounds.ICE_CUBE_AMBIENT, 1.0F, 1.0F);
        }
        if(this.floatTicks > 0){
            this.floatTicks--;
            if(this.target != null && !this.world.isRemote){
                this.tryToFloatAboveTarget(this.target);
            }
        }
        else{
            if (this.fallTime < 0) {
                if (!this.world.isRemote) {
                    this.setDead();
                    return;
                }
            }
            this.fallTime++;

            if (!this.hasNoGravity()) {
                this.motionY -= 0.04D * 8.0D;
            }

            this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
            if (!this.world.isRemote) {
                BlockPos iceCloudPosition = this.getPosition();

                if (!this.onGround) {
                    if (!this.world.isRemote && (this.fallTime > 100 && (iceCloudPosition.getY() < 1 || iceCloudPosition.getY() > 256) || this.fallTime > 600)) {
                        this.setDead();
                    }
                } else {
                    IBlockState blockstate = this.world.getBlockState(iceCloudPosition);
                    this.motionX *= 0.7D;
                    this.motionZ *= 0.7D;
                    this.motionY *= -0.5D;
                    if (blockstate.getBlock() != Blocks.PISTON_EXTENSION) {
                        this.spawnIceExplosionCloud();
                        this.setDead();
                    }
                }
            }
            this.motionX *= 0.98D;
            this.motionY *= 0.98D;
            this.motionZ *= 0.98D;
        }
    }
    public void fall(float distance, float damageMultiplier) {
        int distanceFallen = MathHelper.ceil(distance - 1.0F);
        if (distanceFallen > 0) {
            List<Entity> list = this.world.getEntitiesWithinAABBExcludingEntity(this, this.getEntityBoundingBox());
            for(Entity entity : list) {
                if(entity instanceof EntityLivingBase){
                    EntityLivingBase livingEntity = (EntityLivingBase) entity;
                    damage(livingEntity, distanceFallen);
                }
            }
        }
    }
    public void spawnIceExplosionCloud(){
        EntityAreaEffectCloud areaeffectcloudentity = new EntityAreaEffectCloud(this.world, this.posX, this.posY, this.posZ);
        areaeffectcloudentity.setParticle(EnumParticleTypes.EXPLOSION_HUGE);
        areaeffectcloudentity.setRadius(3.0F);
        areaeffectcloudentity.setDuration(0);
        this.playSound(ModSounds.ICE_CUBE_HIT, 1.0F, 1.0F);
        this.world.spawnEntity(areaeffectcloudentity);
    }

    private void damage(EntityLivingBase targetEntity, int distanceFallen) {
        EntityLivingBase caster = this.getCaster();
        float damageAmount = (float) MathHelper.floor((float)distanceFallen * this.fallHurtAmount);
        if (targetEntity.isEntityAlive() && !targetEntity.getIsInvulnerable() && targetEntity != caster) {
            if (caster == null) {
                targetEntity.attackEntityFrom(DamageSource.MAGIC, damageAmount);
                targetEntity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 4));
                targetEntity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 4));
            } else {
                if (caster.isOnSameTeam(targetEntity)) {
                    return;
                }
                targetEntity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this, this.caster), damageAmount);
                targetEntity.addPotionEffect(new PotionEffect(MobEffects.NAUSEA, 100, 4));
                targetEntity.addPotionEffect(new PotionEffect(MobEffects.SLOWNESS, 100, 4));
            }

        }
    }


    public boolean canBeAttackedWithItem() {
        return false;
    }

    @Override
    protected boolean canTriggerWalking() {
        return false;
    }

    @Override
    public boolean canBeCollidedWith() {
        return !this.isDead;
    }

    @Override
    protected void readEntityFromNBT(NBTTagCompound compound) {
        this.fallTime = compound.getInteger("time");
        this.fallHurtAmount = compound.getFloat("fallDamageAmount");
        this.setFloatTicks(compound.getInteger("floatTicks"));
        if (compound.hasUniqueId("owner")){
            this.casterUuid = compound.getUniqueId("owner");
        }
        if(compound.hasUniqueId("target")){
            this.targetUUID = compound.getUniqueId("target");
        }
    }

    @Override
    protected void writeEntityToNBT(NBTTagCompound compound) {
        compound.setInteger("time", this.fallTime);
        compound.setFloat("fallDamageAmount", this.fallHurtAmount);
        compound.setInteger("floatTicks", this.getFloatTicks());
        if(this.casterUuid != null){
            compound.setUniqueId("owner", this.casterUuid);
        }
        if(this.targetUUID != null)
        {
            compound.setUniqueId("target", this.targetUUID);
        }

    }
    public int getFloatTicks() {
        return this.floatTicks;
    }
    public void setFloatTicks(int floatTicksIn){
        this.floatTicks = floatTicksIn;
    }

    public boolean canRenderOnFire() {
        return false;
    }
}
