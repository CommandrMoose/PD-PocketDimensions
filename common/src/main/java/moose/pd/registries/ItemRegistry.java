package moose.pd.registries;

import dev.architectury.registry.registries.DeferredRegister;
import moose.pd.Pd;
import net.minecraft.core.Registry;
import net.minecraft.world.item.Item;

public class ItemRegistry {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(Pd.MOD_ID, Registry.ITEM_REGISTRY);

}
