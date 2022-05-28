package tk.beanfeed.nightlightdim.items;

import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import tk.beanfeed.nightlightdim.NightLightDim;

public class NLDItemRegister {
    public static final Item DEAD_STICK = new Item(new Item.Settings().group(NightLightDim.NLD));
    public static void register() {
        Registry.register(Registry.ITEM, new Identifier(NightLightDim.MOD_ID, "dead_stick"), DEAD_STICK);
    }
}
