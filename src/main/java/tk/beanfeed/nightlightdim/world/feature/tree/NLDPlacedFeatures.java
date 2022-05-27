package tk.beanfeed.nightlightdim.world.feature.tree;

import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.minecraft.world.gen.feature.PlacedFeatures;
import net.minecraft.world.gen.placementmodifier.BiomePlacementModifier;
import net.minecraft.world.gen.placementmodifier.RarityFilterPlacementModifier;
import net.minecraft.world.gen.placementmodifier.SquarePlacementModifier;

public class NLDPlacedFeatures {
    public static final RegistryEntry<PlacedFeature> EXTRA_DEAD_BUSH =
            PlacedFeatures.register("extra_dead_bush", NLDConfiguredFeatures.EXTRA_DEAD_BUSH, RarityFilterPlacementModifier.of(4), SquarePlacementModifier.of(),PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of());
}
