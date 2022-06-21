package tk.beanfeed.nightlightdim.items.tool;

import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import tk.beanfeed.nightlightdim.blocks.NLDBlockRegister;

public class SoulSwordMat implements ToolMaterial {
    public static final SoulSwordMat INSTANCE = new SoulSwordMat();
    @Override
    public int getDurability() {
        return 600;
    }
    @Override
    public float getMiningSpeedMultiplier() {
        return 1.0F;
    }
    @Override
    public float getAttackDamage() {
        return 0.0F;
    }
    @Override
    public int getMiningLevel() {
        return 0;
    }
    @Override
    public int getEnchantability() {
        return 15;
    }
    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(NLDBlockRegister.EXTRA_DEAD_BUSH);
    }
}
