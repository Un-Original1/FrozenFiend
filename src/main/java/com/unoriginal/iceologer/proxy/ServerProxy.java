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
  public static Configuration config;

    public void preInit(FMLPreInitializationEvent e)
    {
        ModEntities.init();
        ModItems.init();
        File directory = e.getModConfigurationDirectory();
        config = new Configuration(new File(directory.getPath(), "iceologer.cfg"));
        ModConfig.readConfig();
    }
    public void init(FMLInitializationEvent e) {
    }
    public void postInit(FMLPostInitializationEvent e) {
        if (config.hasChanged()) {
            config.save();
        }
    }
}
