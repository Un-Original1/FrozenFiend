package com.unoriginal.iceologer.entity.Entity;


import com.unoriginal.iceologer.Main;
import com.unoriginal.iceologer.init.ModSounds;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.*;
import net.minecraft.entity.monster.*;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EntityIceologer extends EntitySpellcasterIllager{
    public static final ResourceLocation LOOT = new ResourceLocation(Main.MODID, "entities/Iceologer");


    public EntityIceologer(World worldIn)
    {
        super(worldIn);
        this.setSize(0.6F, 1.95F);
        this.experienceValue = 10;
    }

    @Override
    protected SoundEvent getSpellSound() {
        return ModSounds.ICEOLOGER_CAST_SPELL;
    }

    protected void initEntityAI()
    {
        super.initEntityAI();
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(1, new AICastingSpell());
        this.tasks.addTask(2, new EntityAIAvoidEntity(this, EntityPlayer.class, 8.0F, 0.6D, 1.0D));
        this.tasks.addTask(8, new EntityAIWander(this, 0.6D));
        this.tasks.addTask(9, new EntityAIWatchClosest(this, EntityPlayer.class, 3.0F, 1.0F));
        this.tasks.addTask(10, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
        this.tasks.addTask(4, new AISummonIceBlock());
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityIceologer.class}));
        this.targetTasks.addTask(2, (new EntityAINearestAttackableTarget(this, EntityPlayer.class, true)).setUnseenMemoryTicks(300));
        this.targetTasks.addTask(3, (new EntityAINearestAttackableTarget(this, EntityVillager.class, false)).setUnseenMemoryTicks(300));
        this.targetTasks.addTask(3, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, false));
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
        this.getEntityAttribute(SharedMonsterAttributes.FOLLOW_RANGE).setBaseValue(12.0D);
        this.getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(24.0D);
    }
    @Override
    public EnumCreatureAttribute getCreatureAttribute() {
        return EnumCreatureAttribute.ILLAGER;
    }

    protected void entityInit()
    {
        super.entityInit();
    }

    public boolean isOnSameTeam(Entity entityIn)
    {
        if (entityIn == null)
        {
            return false;
        }
        else if (entityIn == this)
        {
            return true;
        }
        else if (super.isOnSameTeam(entityIn))
        {
            return true;
        }
        else if (entityIn instanceof EntityVex)
        {
            return this.isOnSameTeam(((EntityVex)entityIn).getOwner());
        }
        else if (entityIn instanceof EntityLivingBase && ((EntityLivingBase)entityIn).getCreatureAttribute() == EnumCreatureAttribute.ILLAGER)
        {
            return this.getTeam() == null && entityIn.getTeam() == null;
        }
        else
        {
            return false;
        }
    }

    protected SoundEvent getAmbientSound()
    {
        return ModSounds.ICEOLOGER_AMBIENT;
    }

    protected SoundEvent getDeathSound()
    {
        return ModSounds.ICEOLOGER_DEATH;
    }

    protected SoundEvent getHurtSound(DamageSource damageSourceIn)
    {
        return ModSounds.ICEOLOGER_HURT;
    }

    protected void updateAITasks()
    {
        super.updateAITasks();
    }

    class AICastingSpell extends EntitySpellcasterIllager.AICastingApell {
        private AICastingSpell() {
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void updateTask() {
            if (EntityIceologer.this.getAttackTarget() != null) {
                EntityIceologer.this.getLookHelper().setLookPositionWithEntity(EntityIceologer.this.getAttackTarget(), (float) EntityIceologer.this.getHorizontalFaceSpeed(), (float) EntityIceologer.this.getVerticalFaceSpeed());
            }

        }
    }

    class AISummonIceBlock extends  EntitySpellcasterIllager.AIUseSpell
    {

        @Override
        protected void castSpell() {
            EntityLivingBase target = EntityIceologer.this.getAttackTarget();
                summonIceCube(target);
        }

        private void summonIceCube(EntityLivingBase entityLiving)
        {
            EntityIceCube iceCube = new EntityIceCube(world, EntityIceologer.this, entityLiving);
            world.spawnEntity(iceCube);
        }

        @Override
        protected int getCastingTime() {
            return 40;
        }

        @Override
        protected int getCastingInterval() {
            return 100;
        }

        @Override
        protected SoundEvent getSpellPrepareSound() {
            return ModSounds.ICEOLOGER_PREPARE_SPELL;
        }

        @Override
        protected SpellType getSpellType() {
            return SpellType.SUMMON_VEX;
        }
        @Override
        public void resetTask() {
            super.resetTask();
            EntityIceologer.this.setSpellType(SpellType.NONE);
        }
    }
    @Override
    public void onUpdate() {
        super.onUpdate();
        List<EntityVillager> villagers = this.world.getEntitiesWithinAABB(EntityVillager.class, this.getEntityBoundingBox().grow(20d));
        List<EntityAIBase> list = new ArrayList<EntityAIBase>();
        for (EntityVillager villager : villagers) {
            Set<EntityAITasks.EntityAITaskEntry> test = villager.tasks.taskEntries;
            List<EntityAITasks.EntityAITaskEntry> list2 = new ArrayList<EntityAITasks.EntityAITaskEntry>(test);
            for (EntityAITasks.EntityAITaskEntry name : list2) {
                list.add(name.action);
            }
            if (!list.contains(new EntityAIAvoidEntity(villager, EntityIceologer.class, 12.0F, 0.8D, 0.8D))) {
                villager.tasks.addTask(1, new EntityAIAvoidEntity(villager,EntityIceologer.class, 12.0F, 0.8D, 0.8D));
            }
            if (!list.contains(new EntityAIAvoidEntity(villager, EntityIceCube.class, 8.0F, 0.6D, 0.6D))) {
                villager.tasks.addTask(1, new EntityAIAvoidEntity(villager,EntityIceCube.class, 8.0F, 0.6D, 0.6D));
            }
        }
    }

    @Override
    @Nullable
    protected ResourceLocation getLootTable() {
        return LOOT;
    }

}
