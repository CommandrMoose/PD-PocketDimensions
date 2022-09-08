package moose.pd;

import moose.pd.client.models.ModelRegistry;
import moose.pd.dimension.DimensionTypes;
import moose.pd.network.PdNetwork;
import moose.pd.registries.BlockEntityRegistry;
import moose.pd.registries.BlockRegistry;
import moose.pd.registries.ItemRegistry;
import moose.pd.world.ChunkGenerators;

public class Pd {
    public static final String MOD_ID = "pd";

    public static void init() {
        BlockRegistry.BLOCKS.register();
        ItemRegistry.ITEMS.register();
        ModelRegistry.init();
        BlockEntityRegistry.BLOCK_ENTITY_TYPES.register();
        ChunkGenerators.CHUNK_GENERATORS.register();
        DimensionTypes.register();
        PdNetwork.init();
    }
}