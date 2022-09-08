package moose.pd.dimension;

import dev.architectury.registry.registries.DeferredRegister;
import dev.architectury.registry.registries.RegistrySupplier;
import moose.pd.Pd;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.dimension.BuiltinDimensionTypes;
import net.minecraft.world.level.dimension.DimensionType;

public class DimensionTypes {

    public static ResourceKey<DimensionType> TEMPLE;

    public static void register() {
        TEMPLE = ResourceKey.create(Registry.DIMENSION_TYPE_REGISTRY, new ResourceLocation(Pd.MOD_ID, "temple"));
    }
}
