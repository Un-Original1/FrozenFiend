package com.unoriginal.iceologer.proxy;

import com.unoriginal.iceologer.init.ModEntities;
import com.unoriginal.iceologer.init.ModItems;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod.EventBusSubscriber
public class ServerProxy
{
    public void preInit(FMLPreInitializationEvent e)
    {
        ModEntities.init();
        ModItems.init();
    }
    public void init(FMLInitializationEvent e) {
    }
    public void postInit(FMLPostInitializationEvent e) {
    }
}
