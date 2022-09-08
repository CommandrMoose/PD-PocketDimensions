package moose.pd.forge;

import dev.architectury.platform.forge.EventBuses;
import moose.pd.Pd;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Pd.MOD_ID)
public class PdForge {
    public PdForge() {
        // Submit our event bus to let architectury register our content on the right time
        EventBuses.registerModEventBus(Pd.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        Pd.init();
    }


}