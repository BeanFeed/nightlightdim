package tk.beanfeed.nightlightdim.blocks;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.block.sapling.AcaciaSaplingGenerator;
import net.minecraft.block.sapling.DarkOakSaplingGenerator;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.beanfeed.nightlightdim.NightLightDim;
import tk.beanfeed.nightlightdim.world.feature.tree.DeadSaplingGenerator;

public class NLDBlockRegister {
    public static final DeathBlock DEATH_SAND = new DeathBlock(FabricBlockSettings.of(Material.SOIL).strength(1.0f).sounds(BlockSoundGroup.SAND));
    public static final Block SOUL_STONE = new Block(FabricBlockSettings.of(Material.STONE).strength(10.0f).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final DeathBlock DEATH_STONE = new DeathBlock(FabricBlockSettings.of(Material.STONE).strength(12.0f).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final DeathBlock DEATH_BRICK = new DeathBlock(FabricBlockSettings.of(Material.STONE).strength(12.0f).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final Block SOUL_BRICK = new Block(FabricBlockSettings.of(Material.STONE).strength(10.0f).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final DeathPlant EXTRA_DEAD_BUSH = new DeathPlant(StatusEffects.WITHER, 100, FabricBlockSettings.copy(Blocks.DEAD_BUSH).strength(0.0f).nonOpaque().sounds(BlockSoundGroup.GRASS));
    public static final NLPortalBlock PORTAL = new NLPortalBlock(FabricBlockSettings.of(Material.PORTAL).strength(10000000000.0f).sounds(BlockSoundGroup.GLASS));
    public static final NLPortalInitiator PORTAL_INITIATOR = new NLPortalInitiator(FabricBlockSettings.of(Material.STONE).strength(10000000000.0f).sounds(BlockSoundGroup.STONE));
    public static final DeathBlock EMERALD_ORE_DS = new DeathBlock(FabricBlockSettings.of(Material.STONE).strength(15.0f).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final DeathBlock REDSTONE_ORE_DS = new DeathBlock(FabricBlockSettings.of(Material.STONE).strength(15.0f).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final DeathBlock CONDENSE_SOUL_ORE_DS = new DeathBlock(FabricBlockSettings.of(Material.STONE).strength(20.0f).sounds(BlockSoundGroup.STONE).requiresTool());
    public static final PillarBlock DEAD_TREE_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_LOG).strength(4.0f).sounds(BlockSoundGroup.WOOD));
    public static final PillarBlock STRIPPED_DEAD_TREE_LOG = new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_LOG).strength(4.0f).sounds(BlockSoundGroup.WOOD));
    public static final PillarBlock DEAD_TREE_WOOD = new PillarBlock(FabricBlockSettings.copy(Blocks.OAK_WOOD).strength(4.0f).sounds(BlockSoundGroup.WOOD));
    public static final PillarBlock STRIPPED_DEAD_TREE_WOOD = new PillarBlock(FabricBlockSettings.copy(Blocks.STRIPPED_OAK_WOOD).strength(4.0f).sounds(BlockSoundGroup.WOOD));
    public static final Block DEAD_PLANKS = new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS).strength(4.0f).sounds(BlockSoundGroup.WOOD));
    public static final LeavesBlock DEAD_LEAVES = new LeavesBlock(FabricBlockSettings.copy(Blocks.OAK_LEAVES).strength(0.2f).sounds(BlockSoundGroup.GRASS).nonOpaque());
    public static final NLDSaplingBlock DEAD_SAPLING = new NLDSaplingBlock(new DeadSaplingGenerator(), FabricBlockSettings.copy(Blocks.OAK_SAPLING).strength(0.0f).sounds(BlockSoundGroup.GRASS));
    public static final CropBlock SOUL_WEED = new SoulfulWeed(FabricBlockSettings.copy(Blocks.CARROTS).strength(0.0f).sounds(BlockSoundGroup.CROP));

    private static void registerFlammableBlocks(){
        FlammableBlockRegistry instance = FlammableBlockRegistry.getDefaultInstance();
        instance.add(DEAD_TREE_LOG, 5, 5);
        instance.add(DEAD_TREE_WOOD, 5, 5);
        instance.add(DEAD_PLANKS, 5, 20);
        instance.add(DEAD_LEAVES, 20, 60);
        instance.add(STRIPPED_DEAD_TREE_LOG, 5, 5);
        instance.add(STRIPPED_DEAD_TREE_WOOD, 5, 5);
    }

    private static void registerStrippableLogs(){
        StrippableBlockRegistry.register(DEAD_TREE_LOG, STRIPPED_DEAD_TREE_LOG);
        StrippableBlockRegistry.register(DEAD_TREE_WOOD, STRIPPED_DEAD_TREE_WOOD);
    }
    private static void registerFuelBlocks(){
        FuelRegistry.INSTANCE.add(DEAD_TREE_LOG, 300);
        FuelRegistry.INSTANCE.add(DEAD_TREE_WOOD, 300);
        FuelRegistry.INSTANCE.add(DEAD_PLANKS, 300);
        FuelRegistry.INSTANCE.add(STRIPPED_DEAD_TREE_LOG, 300);
        FuelRegistry.INSTANCE.add(STRIPPED_DEAD_TREE_WOOD, 300);
    }
    public static void register(){
        registerFlammableBlocks();
        registerStrippableLogs();
        registerFuelBlocks();
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
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "nlportalinitiator"), PORTAL_INITIATOR);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "nlportalinitiator"), new BlockItem(PORTAL_INITIATOR, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "deathstone_emerald_ore"), EMERALD_ORE_DS);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "deathstone_emerald_ore"), new BlockItem(EMERALD_ORE_DS, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "deathstone_redstone_ore"), REDSTONE_ORE_DS);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "deathstone_redstone_ore"), new BlockItem(REDSTONE_ORE_DS, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "deathstone_condensed_soul_ore"), CONDENSE_SOUL_ORE_DS);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "deathstone_condensed_soul_ore"), new BlockItem(CONDENSE_SOUL_ORE_DS, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "dead_log"), DEAD_TREE_LOG);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "dead_log"), new BlockItem(DEAD_TREE_LOG, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "stripped_dead_log"), STRIPPED_DEAD_TREE_LOG);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "stripped_dead_log"), new BlockItem(STRIPPED_DEAD_TREE_LOG, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "dead_wood"), DEAD_TREE_WOOD);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "dead_wood"), new BlockItem(DEAD_TREE_WOOD, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "stripped_dead_wood"), STRIPPED_DEAD_TREE_WOOD);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "stripped_dead_wood"), new BlockItem(STRIPPED_DEAD_TREE_WOOD, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "dead_planks"), DEAD_PLANKS);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "dead_planks"), new BlockItem(DEAD_PLANKS, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "dead_leaves"), DEAD_LEAVES);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "dead_leaves"), new BlockItem(DEAD_LEAVES, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "dead_sapling"), DEAD_SAPLING);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "dead_sapling"), new BlockItem(DEAD_SAPLING, new FabricItemSettings().group(NightLightDim.NLD)));
        Registry.register(Registry.BLOCK, new Identifier(NightLightDim.MOD_ID, "soul_weed"), SOUL_WEED);
    }
}
