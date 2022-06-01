package tk.beanfeed.nightlightdim.world.feature.tree;

import net.fabricmc.fabric.api.event.registry.DynamicRegistrySetupCallback;
import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.fabricmc.fabric.api.event.registry.RegistryEntryAddedCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import tk.beanfeed.nightlightdim.NightLightDim;

import javax.annotation.Nullable;
import java.util.Random;

public class DeadSaplingGenerator extends LargeTreeSaplingGenerator {
    public static Identifier DEAD_TREE = new Identifier(NightLightDim.MOD_ID,"dead_tree");
    public static  RegistryKey<ConfiguredFeature<?,?>> DEAD_TREE_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, DEAD_TREE);
    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getLargeTreeFeature(Random random) {
        return null;
    }
    @Nullable
    @Override
    protected RegistryEntry<? extends ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return null;
    }
    //RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry = world.getRegistryManager().get(Registry.CONFIGURED_FEATURE_KEY).getEntry(DEAD_TREE_KEY).orElse(null);
    @Override
    public boolean generateLargeTree(ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random random, int x, int z) {
        RegistryEntry<? extends ConfiguredFeature<?, ?>> registryEntry = world.getRegistryManager().get(Registry.CONFIGURED_FEATURE_KEY).getEntry(DEAD_TREE_KEY).orElse(null);
        if (registryEntry == null) {
            return false;
        } else {
            ConfiguredFeature<?, ?> configuredFeature = (ConfiguredFeature)registryEntry.value();
            BlockState blockState = Blocks.AIR.getDefaultState();
            world.setBlockState(pos.add(x, 0, z), blockState, 4);
            world.setBlockState(pos.add(x + 1, 0, z), blockState, 4);
            world.setBlockState(pos.add(x, 0, z + 1), blockState, 4);
            world.setBlockState(pos.add(x + 1, 0, z + 1), blockState, 4);
            if (configuredFeature.generate(world, chunkGenerator, random, pos.add(x, 0, z))) {
                return true;
            } else {
                world.setBlockState(pos.add(x, 0, z), state, 4);
                world.setBlockState(pos.add(x + 1, 0, z), state, 4);
                world.setBlockState(pos.add(x, 0, z + 1), state, 4);
                world.setBlockState(pos.add(x + 1, 0, z + 1), state, 4);
                return false;
            }
        }
    }


}
