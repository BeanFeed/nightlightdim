package tk.beanfeed.nightlightdim.tool;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;
import tk.beanfeed.nightlightdim.NightLightDim;

public class NLDToolRegister {
    public static final RevivingRod REVIVING_ROD = new RevivingRod(RevivingRodMat.INSTANCE, new Item.Settings().group(NightLightDim.NLD).maxCount(1).rarity(Rarity.EPIC));

    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "reviving_rod"), REVIVING_ROD);
    }
}
