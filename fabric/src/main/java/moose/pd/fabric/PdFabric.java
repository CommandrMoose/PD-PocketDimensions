package moose.pd.fabric;

import moose.pd.Pd;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;

public class PdFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        Pd.init();
    }
}