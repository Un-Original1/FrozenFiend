package com.unoriginal.iceologer.init;


import com.unoriginal.iceologer.Main;
import com.unoriginal.iceologer.entity.Entity.EntityIceCube;
import com.unoriginal.iceologer.entity.Entity.EntityIceologer;
import com.unoriginal.iceologer.entity.render.RenderIceCube;
import com.unoriginal.iceologer.entity.render.RenderIceologer;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.init.Biomes;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


public class ModEntities
{
    public static void init() {
            int id = 1;
            if(ModConfig.isIceologerEnabled == true) {
                EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID, "Iceologer"), EntityIceologer.class, "Iceologer", id++, Main.instance, 70, 3, true, 128, 14078143);
            }
            EntityRegistry.addSpawn(EntityIceologer.class, ModConfig.iceologerSpawnProbability, 1, 1, EnumCreatureType.MONSTER, Biomes.FROZEN_RIVER, Biomes.FROZEN_OCEAN, Biomes.ICE_MOUNTAINS, Biomes.ICE_PLAINS, Biomes.MUTATED_ICE_FLATS, Biomes.EXTREME_HILLS, Biomes.EXTREME_HILLS_WITH_TREES, Biomes.MUTATED_EXTREME_HILLS, Biomes.MUTATED_EXTREME_HILLS_WITH_TREES, Biomes.MUTATED_TAIGA_COLD, Biomes.COLD_TAIGA, Biomes.COLD_TAIGA_HILLS, Biomes.COLD_BEACH);
            EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID,"IceCube"), EntityIceCube.class, "IceCube", id++, Main.instance, 70, 3,true);

    }
    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityIceologer.class, RenderIceologer.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityIceCube.class, RenderIceCube.FACTORY);

    }
}
