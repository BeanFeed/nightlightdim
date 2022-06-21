package tk.beanfeed.nightlightdim.sounds;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.beanfeed.nightlightdim.NightLightDim;

public class NLDSoundRegister {
    public static final Identifier rGroan = new Identifier("nightlightdim", "reaper_groan");
    public static SoundEvent REAPER_GROAN = new SoundEvent(rGroan);
    public static final Identifier rAmb = new Identifier(NightLightDim.MOD_ID, "reaper_ambient");
    public static SoundEvent REAPER_AMBIENT = new SoundEvent(rAmb);

    public static void register(){
        Registry.register(Registry.SOUND_EVENT, rGroan, REAPER_GROAN);
        Registry.register(Registry.SOUND_EVENT, rAmb, REAPER_AMBIENT);
    }
}
