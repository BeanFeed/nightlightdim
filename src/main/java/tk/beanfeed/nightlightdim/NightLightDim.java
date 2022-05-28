package tk.beanfeed.nightlightdim;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModification;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.beanfeed.nightlightdim.blocks.NLDBlockRegister;
import tk.beanfeed.nightlightdim.items.NLDItemRegister;
import tk.beanfeed.nightlightdim.structure.NLDStructuresRegistry;
import tk.beanfeed.nightlightdim.tool.NLDToolRegister;
import tk.beanfeed.nightlightdim.world.biome.NightLightDimBiomes;

public class NightLightDim implements ModInitializer{
    public static String MOD_ID = "nightlightdim";
    public static Identifier NIGHTLIGHTID = new Identifier(MOD_ID,"nightlight");
    public static RegistryKey<World> NIGHTLIGHT = RegistryKey.of(Registry.WORLD_KEY, NIGHTLIGHTID);
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
    //adds Item Group
    public static final ItemGroup NLD = FabricItemGroupBuilder.build(
            new Identifier(MOD_ID, "nld"),
            () -> new ItemStack(NLDBlockRegister.DEATH_STONE)
    );


    @Override
    public void onInitialize() {
        NLDBlockRegister.register();
        NightLightDimBiomes.loadBiomes();
        NLDToolRegister.register();
        NLDStructuresRegistry.registerStructures();
        NLDItemRegister.register();
    }

}
