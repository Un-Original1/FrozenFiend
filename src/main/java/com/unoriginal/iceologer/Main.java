package com.unoriginal.iceologer;

import com.unoriginal.iceologer.proxy.ServerProxy;
import com.unoriginal.iceologer.tabs.IceologerTab;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import org.apache.logging.log4j.Logger;

@Mod(modid = Main.MODID, name = Main.NAME, version = Main.VERSION)
public class Main {
    @Mod.Instance
    public static Main instance;

    public static final String MODID = "iceologer";
    public static final String NAME = "Frozen Fiend";
    public static final String VERSION = "1.1";

    public static final CreativeTabs iceologertab = new IceologerTab("iceologertab");

    private static Logger logger;
    @SidedProxy(serverSide = "com.unoriginal.iceologer.proxy.ServerProxy", clientSide = "com.unoriginal.iceologer.proxy.ClientProxy")
    public static ServerProxy commonProxy;

   @Mod.EventHandler
   public void preInit(FMLPreInitializationEvent event) {
       logger = event.getModLog();
       commonProxy.preInit(event);
   }
    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        commonProxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        commonProxy.postInit(e);
    }
}
