package com.unoriginal.iceologer.config;

import com.unoriginal.iceologer.Main;
import com.unoriginal.iceologer.proxy.ServerProxy;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

@Config(modid = Main.MODID)
@Config.LangKey("iceologer.config.title")
public class ModConfig {

    public static boolean isIceologerEnabled = true;
    public static boolean isIceWandEnabled = true;
    public static int iceologerSpawnProbability = 5;
    public static int iceologerMinSpawnGroup= 1;
    public static int iceologerMaxSpawnGroup= 1;

    private static final String CATEGORY_GENERAL = "general";
    public static void readConfig() {
        Configuration cfg = ServerProxy.config;
        try {
            cfg.load();
            initGeneralConfig(cfg);
        } catch (Exception e1) {
            Main.logger.log(Level.ERROR, "Problem loading config file!", e1);
        } finally {
            if (cfg.hasChanged()) {
                cfg.save();
            }
        }
    }

    private static void initGeneralConfig(Configuration cfg) {
        cfg.addCustomCategoryComment(CATEGORY_GENERAL, "General configuration");
        // cfg.getBoolean() will get the value in the config if it is already specified there. If not it will create the value.
        isIceologerEnabled = cfg.getBoolean("isIceologerEnabled", CATEGORY_GENERAL, isIceologerEnabled, "Set to false if you want to disable iceologer from the mod");
        isIceWandEnabled = cfg.getBoolean("isIceWandEnabled", CATEGORY_GENERAL, isIceWandEnabled, "Set to false if you want to disable Ice wand from the mod");
        iceologerSpawnProbability = cfg.getInt("iceologerSpawnProbability", CATEGORY_GENERAL, iceologerSpawnProbability, 0, 100, "Change the spawn rate of iceologer, 0 to disable it and 100 to make them as common as a zombie");
        iceologerMinSpawnGroup = cfg.getInt("iceologerMinSpawnGroup", CATEGORY_GENERAL, iceologerMinSpawnGroup, 1, 3, "Change the minimum of iceologers spawned in a group");
        iceologerMaxSpawnGroup = cfg.getInt("iceologerMaxSpawnGroup", CATEGORY_GENERAL, iceologerMaxSpawnGroup, 1, 3, "Change the maximum of iceologers spawned in a group");
    }
}
