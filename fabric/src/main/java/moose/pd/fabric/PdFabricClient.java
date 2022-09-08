package moose.pd.fabric;

import dev.architectury.registry.client.rendering.BlockEntityRendererRegistry;
import moose.pd.client.models.ModelRegistry;
import moose.pd.client.render.blockentity.TempleExternalRenderer;
import moose.pd.registries.BlockEntityRegistry;
import net.fabricmc.api.ClientModInitializer;

public class PdFabricClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        //ModelRegistry.init();
        establishBlockEntityRenderers();
    }

    private void establishBlockEntityRenderers() {
        BlockEntityRendererRegistry.register(BlockEntityRegistry.TEMPLE_BLOCK.get(), TempleExternalRenderer::new);
    }
}
