package tk.beanfeed.nightlightdim.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;


public class Soul_Savanna {
    public static Biome create(){
        return new Biome.Builder()
                .generationSettings(createGenerationSettings())
                .spawnSettings(createSpawnSettings())
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.PLAINS)
                .temperature(0.8f)
                .downfall(0.0f)
                .effects(new BiomeEffects.Builder()
                        .fogColor(3940879)
                        .waterColor(2705956)
                        .waterFogColor(13572)
                        .foliageColor(5125144)
                        .skyColor(4733190)
                        .grassColor(5125144)
                        .build()
                )
                .build();
    }

    private static GenerationSettings createGenerationSettings(){
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        return builder.build();
    }

    private static SpawnSettings createSpawnSettings(){
        SpawnSettings.Builder builder = new SpawnSettings.Builder();

        return builder.build();
    }
}
