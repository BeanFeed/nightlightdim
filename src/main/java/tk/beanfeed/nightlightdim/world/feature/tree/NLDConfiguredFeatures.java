package tk.beanfeed.nightlightdim.world.feature.tree;

import net.minecraft.block.Blocks;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import tk.beanfeed.nightlightdim.blocks.NLDBlockRegister;

public class NLDConfiguredFeatures {
    private static RandomPatchFeatureConfig createRandomPatchFeatureConfig(BlockStateProvider block, int tries) {
        return ConfiguredFeatures.createRandomPatchFeatureConfig(tries, PlacedFeatures.createEntry(Feature.SIMPLE_BLOCK, new SimpleBlockFeatureConfig(block)));
    }

    public static final RegistryEntry<ConfiguredFeature<RandomPatchFeatureConfig, ?>> EXTRA_DEAD_BUSH =
            ConfiguredFeatures.register("extra_dead_bush", Feature.RANDOM_PATCH, createRandomPatchFeatureConfig(BlockStateProvider.of(NLDBlockRegister.EXTRA_DEAD_BUSH), 4));
}
