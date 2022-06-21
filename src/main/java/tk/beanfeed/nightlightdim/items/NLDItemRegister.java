package tk.beanfeed.nightlightdim.items;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.beanfeed.nightlightdim.NightLightDim;
import tk.beanfeed.nightlightdim.blocks.NLDBlockRegister;
import tk.beanfeed.nightlightdim.entity.NLDEntityRegister;
import tk.beanfeed.nightlightdim.items.armor.NLDArmorRegister;

public class NLDItemRegister {
    public static final Item DEAD_STICK = new Item(new Item.Settings().group(NightLightDim.NLD));
    public static final Item SOUL_BERRIES = new AliasedBlockItem(NLDBlockRegister.SOUL_WEED, new Item.Settings().group(NightLightDim.NLD).food(NLDFoodComponents.SOUL_BERRY));
    public static final Item DEAD_MOSS = new Item(new Item.Settings().group(NightLightDim.NLD));

    public static final Item CONDENSED_SOUL_INGOT = new Item(new Item.Settings().group(NightLightDim.NLD));

    public static final Item REAPER_SPAWN_EGG = new SpawnEggItem(NLDEntityRegister.REAPER, 1842212, 13297119, new Item.Settings().group(NightLightDim.NLD));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "dead_stick"), DEAD_STICK);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "soul_berries"), SOUL_BERRIES);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "dead_moss"), DEAD_MOSS);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "reaper_spawn_egg"), REAPER_SPAWN_EGG);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "condensed_soul_ingot"), CONDENSED_SOUL_INGOT);
        NLDArmorRegister.register();
    }
}
