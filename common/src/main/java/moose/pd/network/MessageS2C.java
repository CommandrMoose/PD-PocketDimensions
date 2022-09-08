package moose.pd.network;


import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import org.apache.logging.log4j.core.jmx.Server;

import java.util.List;
import java.util.function.Predicate;

public abstract class MessageS2C extends Message {

    public void send(ServerPlayer player) {
        this.getType().getNetworkManager().sendToPlayer(player, this);
    }

    public void sendToDimension(Level level) {
        this.getType().getNetworkManager().sendToDimension(level, this);
    }

    public void sendToAll(ServerLevel level) {
        MinecraftServer server = level.getServer();
        List<ServerPlayer> players = server.getPlayerList().getPlayers();
        players.forEach(this::send);
    }

}
