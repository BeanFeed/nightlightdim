package tk.beanfeed.nightlightdim.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FlowerBlock;
import net.minecraft.block.Material;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.beanfeed.nightlightdim.NightLightDim;

public class NLDBlockRegister {
    public static final DeathBlock DEATH_SAND = new DeathBlock(FabricBlockSettings.of(Material.SOIL).strength(5.0f).sounds(BlockSoundGroup.SAND));
    public static final Block SOUL_STONE = new Block(FabricBlockSettings.of(Material.STONE).strength(15.0f).sounds(BlockSoundGroup.STONE));
    public static final DeathBlock DEATH_STONE = new DeathBlock(FabricBlockSettings.of(Material.STONE).strength(20.0f).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final DeathBlock DEATH_BRICK = new DeathBlock(FabricBlockSettings.of(Material.STONE).strength(20.0f).sounds(BlockSoundGroup.STONE));
    public static final Block SOUL_BRICK = new Block(FabricBlockSettings.of(Material.STONE).strength(15.0f).sounds(BlockSoundGroup.STONE));
    public static final DeathPlant EXTRA_DEAD_BUSH = new DeathPlant(StatusEffects.WITHER, 100, FabricBlockSettings.copy(Blocks.DEAD_BUSH).strength(0.0f).nonOpaque().sounds(BlockSoundGroup.GRASS));
    public static final NLPortalBlock PORTAL = new NLPortalBlock(FabricBlockSettings.of(Material.PORTAL).strength(1000000.0f).sounds(BlockSoundGroup.GLASS));

    public static void register(){
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "death_sand"), DEATH_SAND);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "death_sand"), new BlockItem(DEATH_SAND, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "soul_stone"), SOUL_STONE);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "soul_stone"), new BlockItem(SOUL_STONE, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "death_stone"), DEATH_STONE);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "death_stone"), new BlockItem(DEATH_STONE, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "death_brick"), DEATH_BRICK);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "death_brick"), new BlockItem(DEATH_BRICK, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "soul_brick"), SOUL_BRICK);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "soul_brick"), new BlockItem(SOUL_BRICK, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "extra_dead_bush"), EXTRA_DEAD_BUSH);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "extra_dead_bush"), new BlockItem(EXTRA_DEAD_BUSH, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "nlportal"), PORTAL);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "nlportal"), new BlockItem(PORTAL, new FabricItemSettings()));
    }
}
