package tk.beanfeed.nightlightdim.items.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.beanfeed.nightlightdim.NightLightDim;



public class NLDArmorRegister {
    public static final ArmorItem DAMNED_HELMET = new ArmorItem(NLDArmorMaterials.DAMNED, EquipmentSlot.HEAD, new Item.Settings().group(NightLightDim.NLD));
    public static final ArmorItem DAMNED_CHESTPLATE = new ArmorItem(NLDArmorMaterials.DAMNED, EquipmentSlot.CHEST, new Item.Settings().group(NightLightDim.NLD));
    public static final ArmorItem DAMNED_LEGS = new ArmorItem(NLDArmorMaterials.DAMNED, EquipmentSlot.LEGS, new Item.Settings().group(NightLightDim.NLD));
    public static final ArmorItem DAMNED_BOOTS = new ArmorItem(NLDArmorMaterials.DAMNED, EquipmentSlot.FEET, new Item.Settings().group(NightLightDim.NLD));

    public static void register(){
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "damned_helmet"), DAMNED_HELMET);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "damned_chestplate"), DAMNED_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "damned_leggings"), DAMNED_LEGS);
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "damned_boots"), DAMNED_BOOTS);
    }
}
