package tk.beanfeed.nightlightdim.structure;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.structure.PoolStructurePiece;
import net.minecraft.structure.PostPlacementProcessor;
import net.minecraft.structure.StructureGeneratorFactory;
import net.minecraft.structure.StructurePiecesGenerator;
import net.minecraft.structure.StructureSetKeys;
import net.minecraft.structure.pool.StructurePool;
import net.minecraft.structure.pool.StructurePoolBasedGenerator;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.feature.StructureFeature;
import net.minecraft.world.gen.feature.StructurePoolFeatureConfig;
import tk.beanfeed.nightlightdim.NightLightDim;

import java.util.Optional;

public class NL_Well extends StructureFeature<StructurePoolFeatureConfig> {


    public static final Codec<StructurePoolFeatureConfig> CODEC = RecordCodecBuilder.create(
            instance -> instance.group(
                            StructurePool.REGISTRY_CODEC.fieldOf("nl_well/nl_welltop").forGetter(StructurePoolFeatureConfig::getStartPool),
                            Codec.intRange(0, 30).fieldOf("size").forGetter(StructurePoolFeatureConfig::getSize)
                    )
                    .apply(instance, StructurePoolFeatureConfig::new)
    );

    public NL_Well() {

        super(CODEC, NL_Well::createPiecesGenerator, PostPlacementProcessor.EMPTY);
    }


    private static boolean isFeatureChunk(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {

        ChunkPos chunkpos = context.chunkPos();

        return !context.chunkGenerator().method_41053(StructureSetKeys.VILLAGES, context.seed(), chunkpos.x, chunkpos.z, 10);
    }

    public static Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> createPiecesGenerator(StructureGeneratorFactory.Context<StructurePoolFeatureConfig> context) {

        if (!NL_Well.isFeatureChunk(context)) {
            return Optional.empty();
        }

        BlockPos blockpos = context.chunkPos().getCenterAtY(0);

        blockpos = blockpos.up(60);

        Optional<StructurePiecesGenerator<StructurePoolFeatureConfig>> structurePiecesGenerator =
                StructurePoolBasedGenerator.generate(
                        context,
                        PoolStructurePiece::new,
                        blockpos,
                        false,

                        true

                );



        if(structurePiecesGenerator.isPresent()) {

            NightLightDim.LOGGER.debug("Well at {}", blockpos);
        }


        return structurePiecesGenerator;
    }
}