package tk.beanfeed.nightlightdim.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import tk.beanfeed.nightlightdim.world.feature.tree.NLDPlacedFeatures;


public class Death_Plains {
    public static Biome create(){
        return new Biome.Builder()
                .generationSettings(createGenerationSettings())
                .spawnSettings(createSpawnSettings())
                .precipitation(Biome.Precipitation.NONE)
                .category(Biome.Category.PLAINS)
                .temperature(0.8f)
                .downfall(0.0f)
                .effects(new BiomeEffects.Builder()
                        .fogColor(2894892)
                        .waterColor(328965)
                        .waterFogColor(7237230)
                        .foliageColor(0)
                        .skyColor(4671303)
                        .grassColor(0)
                        .build()
                )
                .build();
    }

    private static GenerationSettings createGenerationSettings(){
        GenerationSettings.Builder builder = new GenerationSettings.Builder();
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, NLDPlacedFeatures.EXTRA_DEAD_BUSH);
        return builder.build();
    }

    private static SpawnSettings createSpawnSettings(){
        SpawnSettings.Builder builder = new SpawnSettings.Builder();

        return builder.build();
    }

}
