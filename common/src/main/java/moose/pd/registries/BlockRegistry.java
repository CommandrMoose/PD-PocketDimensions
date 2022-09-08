package moose.pd.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import moose.pd.blocks.internal.TempleReturnBlock;
import moose.pd.blocks.temple.BaseTempleBlock;
import net.minecraft.core.Registry;
import moose.pd.Pd;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Supplier;


public class BlockRegistry {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(Pd.MOD_ID, Registry.BLOCK_REGISTRY);

    public static final RegistrySupplier<Block> TEMPLE_BLOCK = register("temple_block", () -> new BaseTempleBlock(BlockBehaviour.Properties.copy(Blocks.STONE).noOcclusion()), true);
    public static final RegistrySupplier<Block> TEMPLE_RETURN_BLOCK = register("temple_return_block", () -> new TempleReturnBlock(BlockBehaviour.Properties.copy(Blocks.BARRIER)), false);

    private static <T extends Block> RegistrySupplier<T> register(String id, Supplier<T> blockSupplier, boolean buildItem) {
        RegistrySupplier<T> registryObj = BLOCKS.register(id, blockSupplier);
        if (buildItem) {
            ItemRegistry.ITEMS.register(id, () -> new BlockItem(registryObj.get(), new Item.Properties()));
        }

        return registryObj;
    }

}
