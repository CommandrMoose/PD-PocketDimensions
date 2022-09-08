package moose.pd.network.messages;

import moose.pd.network.MessageS2C;
import moose.pd.network.MessageType;
import moose.pd.network.PdNetwork;
import moose.pd.network.handler.ClientPacketHandler;
import moose.pd.blockentities.TempleShells;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class UpdateShellMessage extends MessageS2C {

    private ResourceKey<Level> level;
    private BlockPos blockPos;
    private TempleShells shell;

    public UpdateShellMessage(ResourceKey<Level> level, BlockPos pos, TempleShells shell) {
        this.level = level;
        this.shell = shell;
        this.blockPos = pos;
    }

    public UpdateShellMessage(FriendlyByteBuf buf) {
        this.level = buf.readResourceKey(Registry.DIMENSION_REGISTRY);
        this.blockPos = buf.readBlockPos();
        this.shell = TempleShells.values()[buf.readInt()];
    }

    @NotNull
    @Override
    public MessageType getType() {
        return PdNetwork.SYNC_LEVELS;
    }


    @Override
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeResourceKey(this.level);
        buf.writeBlockPos(this.blockPos);
        buf.writeInt(TempleShells.valueOf(this.shell.name()).ordinal());
    }

    @Override
    public void handle() {
        System.out.println("Sending the info:" + this.level+ " " +this.blockPos+ " " + this.shell);
        ClientPacketHandler.updateShell(this.level, this.blockPos, this.shell);
    }
}
