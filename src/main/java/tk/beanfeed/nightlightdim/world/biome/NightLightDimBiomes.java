package tk.beanfeed.nightlightdim.world.biome;


import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import tk.beanfeed.nightlightdim.NightLightDim;

public class NightLightDimBiomes {

    private static RegistryKey<Biome> registryKey(String name){
        return RegistryKey.of(Registry.BIOME_KEY, new Identifier(NightLightDim.MOD_ID, name));
    }

    private static final RegistryKey<Biome> DEATH_PLAINS = registryKey("death_plains");
    private static final RegistryKey<Biome> SOUL_SAVANNA = registryKey("soul_savanna");

    public static void loadBiomes(){
        Registry.register(BuiltinRegistries.BIOME, DEATH_PLAINS.getValue(), Death_Plains.create());
        Registry.register(BuiltinRegistries.BIOME, SOUL_SAVANNA.getValue(), Soul_Savanna.create());
    }

    public static void init(){}
}
