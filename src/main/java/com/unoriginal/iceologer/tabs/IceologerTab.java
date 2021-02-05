package com.unoriginal.iceologer.tabs;


import com.unoriginal.iceologer.init.ModItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class IceologerTab extends CreativeTabs {
    public IceologerTab(String label) {
        super("iceologertab");
        this.setBackgroundImageName("iceologer.png");
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(ModItems.ICE_WAND);
    }
}
