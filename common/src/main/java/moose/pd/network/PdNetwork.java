package moose.pd.network;

import moose.pd.Pd;
import moose.pd.network.messages.SyncLevelListMessage;
import moose.pd.network.messages.UpdateShellMessage;
import net.minecraft.resources.ResourceLocation;

public class PdNetwork {

    public static final NetworkManager NETWORK = NetworkManager.create(new ResourceLocation(Pd.MOD_ID, "channel"));

    public static MessageType SYNC_LEVELS;
    public static MessageType UPDATE_SHELL;

    public static void init() {
        SYNC_LEVELS = NETWORK.registerS2C("sync_levels", SyncLevelListMessage::new);
        UPDATE_SHELL = NETWORK.registerS2C("update_shell",  UpdateShellMessage::new);
    }

}
