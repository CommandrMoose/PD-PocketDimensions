package moose.pd.forge.handlers;

import moose.pd.Pd;
import moose.pd.client.models.ModelRegistry;
import moose.pd.client.models.forge.ModelRegistryImpl;
import moose.pd.client.render.blockentity.TempleExternalRenderer;
import moose.pd.registries.BlockEntityRegistry;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = Pd.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModBus {

    @SubscribeEvent
    public static void event(EntityRenderersEvent.RegisterLayerDefinitions event) {
        ModelRegistry.init();
        ModelRegistryImpl.register(event);
    }

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        BlockEntityRenderers.register(BlockEntityRegistry.TEMPLE_BLOCK.get(), TempleExternalRenderer::new);
    }

}
