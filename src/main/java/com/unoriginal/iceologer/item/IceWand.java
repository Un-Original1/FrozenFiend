package com.unoriginal.iceologer.item;

import com.unoriginal.iceologer.entity.Entity.EntityIceCube;
import com.unoriginal.iceologer.init.ModSounds;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;

public class IceWand extends Item {
    public IceWand(String name) {
        setRegistryName(name);
        setTranslationKey(name);
        setCreativeTab(CreativeTabs.COMBAT);
        this.setMaxDamage(64);
        this.setMaxStackSize(1);
    }
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand) {
        if(target != null){
            World world = player.getEntityWorld();
            EntityIceCube iceCube = new EntityIceCube(world, player, target);
            if (!world.isRemote && !player.getCooldownTracker().hasCooldown(this)) {
                world.spawnEntity(iceCube);
                player.getCooldownTracker().setCooldown(this, 400);
                stack.damageItem(1, player);
                player.swingArm(hand);
                world.playSound((EntityPlayer)null, player.posX, player.posY, player.posZ, ModSounds.ICEOLOGER_PREPARE_SPELL, SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        }
        return super.itemInteractionForEntity(stack, player, target, hand);
    }
    @Override
    public EnumRarity getRarity(ItemStack stack) {
        return EnumRarity.RARE;
    }
}
