package moose.pd.network.messages;

import moose.pd.network.MessageS2C;
import moose.pd.network.MessageType;
import moose.pd.network.PdNetwork;
import moose.pd.network.handler.ClientPacketHandler;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class SyncLevelListMessage extends MessageS2C {

    public ResourceKey<Level> level;
    public boolean add = true;

    public SyncLevelListMessage(ResourceKey<Level> level, boolean add) {
        this.level = level;
        this.add = add;
    }

    @NotNull
    @Override
    public MessageType getType() {
        return PdNetwork.SYNC_LEVELS;
    }

    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceKey(this.level);
        buf.writeBoolean(this.add);
    }

    public SyncLevelListMessage(FriendlyByteBuf buf) {
        this.level = buf.readResourceKey(Registry.DIMENSION_REGISTRY);
        this.add = buf.readBoolean();
    }

    @Override
    public void handle() {
        ClientPacketHandler.handleDimSyncPacket(this.level, this.add);
    }
}
