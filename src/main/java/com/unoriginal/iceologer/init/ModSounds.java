package com.unoriginal.iceologer.init;
import com.unoriginal.iceologer.Main;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@SuppressWarnings("MemberVisibilityCanBePrivate")
public class ModSounds
{
    public static final SoundEvent ICEOLOGER_AMBIENT = new SoundEvent(new ResourceLocation(Main.MODID, "iceologer_ambient")).setRegistryName("iceologer_ambient");
    public static final SoundEvent ICEOLOGER_HURT = new SoundEvent(new ResourceLocation(Main.MODID, "iceologer_hurt")).setRegistryName("iceologer_hurt");
    public static final SoundEvent ICEOLOGER_DEATH = new SoundEvent(new ResourceLocation(Main.MODID, "iceologer_death")).setRegistryName("iceologer_death");
    public static final SoundEvent ICEOLOGER_PREPARE_SPELL = new SoundEvent(new ResourceLocation(Main.MODID, "iceologer_prepare_spell")).setRegistryName("iceologer_prepare_spell");
    public static final SoundEvent ICEOLOGER_CAST_SPELL = new SoundEvent(new ResourceLocation(Main.MODID, "iceologer_cast_spell")).setRegistryName("iceologer_cast_spell");
    public static final SoundEvent ICE_CUBE_HIT  = new SoundEvent(new ResourceLocation(Main.MODID, "ice_cube_hit")).setRegistryName("ice_cube_hit");
    public static final SoundEvent ICE_CUBE_AMBIENT = new SoundEvent(new ResourceLocation(Main.MODID, "ice_cube_ambient")).setRegistryName("ice_cube_ambient");

    @Mod.EventBusSubscriber
    public static class RegistrationHandler {

        @SubscribeEvent
        public static void registerSounds(final RegistryEvent.Register<SoundEvent> event) {
            event.getRegistry().register(ICEOLOGER_AMBIENT);
            event.getRegistry().register(ICEOLOGER_HURT);
            event.getRegistry().register(ICEOLOGER_DEATH);
            event.getRegistry().register(ICEOLOGER_PREPARE_SPELL);
            event.getRegistry().register(ICEOLOGER_CAST_SPELL);
            event.getRegistry().register(ICE_CUBE_HIT);
            event.getRegistry().register(ICE_CUBE_AMBIENT);
        }

    }
}

