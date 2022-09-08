package moose.pd.registries;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import moose.pd.Pd;
import moose.pd.blockentities.TempleBlockEntity;
import moose.pd.blockentities.TempleReturnBlockEntity;
import net.minecraft.core.Registry;
import net.minecraft.world.level.block.entity.BlockEntityType;

public class BlockEntityRegistry {

    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(Pd.MOD_ID, Registry.BLOCK_ENTITY_TYPE_REGISTRY);

    public static final RegistrySupplier<BlockEntityType<TempleBlockEntity>> TEMPLE_BLOCK = BLOCK_ENTITY_TYPES.register("temple", () -> BlockEntityType.Builder.of(TempleBlockEntity::new, BlockRegistry.TEMPLE_BLOCK.get()).build(null));
    public static final RegistrySupplier<BlockEntityType<TempleReturnBlockEntity>> TEMPLE_RETURN_BLOCK = BLOCK_ENTITY_TYPES.register("temple_return", () -> BlockEntityType.Builder.of(TempleReturnBlockEntity::new, BlockRegistry.TEMPLE_RETURN_BLOCK.get()).build(null));



}
