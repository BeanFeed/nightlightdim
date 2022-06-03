package tk.beanfeed.nightlightdim.items.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;

public class CondensedSoulArmorMaterial implements ArmorMaterial {
    @Override
    public int getDurability(EquipmentSlot slot) {
        return 10;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot slot) {
        return 10;
    }

    @Override
    public int getEnchantability() {
        return 5;
    }

    @Override
    public SoundEvent getEquipSound() {
        return null;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return null;
    }

    @Override
    public String getName() {
        return "Condensed Soul";
    }

    @Override
    public float getToughness() {
        return 5;
    }

    @Override
    public float getKnockbackResistance() {
        return 3;
    }
}
