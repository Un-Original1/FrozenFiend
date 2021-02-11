package com.unoriginal.iceologer.init;


import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.unoriginal.iceologer.Main;
import com.unoriginal.iceologer.config.ModConfig;
import com.unoriginal.iceologer.entity.Entity.EntityIceCube;
import com.unoriginal.iceologer.entity.Entity.EntityIceologer;
import com.unoriginal.iceologer.entity.render.RenderIceCube;
import com.unoriginal.iceologer.entity.render.RenderIceologer;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.ResourceLocation;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
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
        Multimap<BiomeDictionary.Type, Biome> biomesAndTypes = HashMultimap.create();
            for (Biome b : Biome.REGISTRY)
            {
            Set<BiomeDictionary.Type> types = BiomeDictionary.getTypes(b);
            for (BiomeDictionary.Type t : types)
                {
                    biomesAndTypes.put(t, b);
                }
            }
         EntityRegistry.addSpawn(EntityIceologer.class, ModConfig.iceologerSpawnProbability, ModConfig.iceologerMinSpawnGroup, odConfig.iceologerMaxSpawnGroup, EnumCreatureType.MONSTER, biomesAndTypes.get(BiomeDictionary.Type.SNOWY).toArray(new Biome[biomesAndTypes.get(BiomeDictionary.Type.SNOWY).size()]));
         EntityRegistry.registerModEntity(new ResourceLocation(Main.MODID,"IceCube"), EntityIceCube.class, "IceCube", id++, Main.instance, 70, 3,true);

    }
    @SideOnly(Side.CLIENT)
    public static void initModels() {
        RenderingRegistry.registerEntityRenderingHandler(EntityIceologer.class, RenderIceologer.FACTORY);
        RenderingRegistry.registerEntityRenderingHandler(EntityIceCube.class, RenderIceCube.FACTORY);

    }
}
