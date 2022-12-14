package moose.pd.network.handler;

import moose.pd.blocks.temple.BaseTempleBlock;
import moose.pd.blockentities.TempleBlockEntity;
import moose.pd.blockentities.TempleShells;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;
import java.util.Set;

public class ClientPacketHandler {

    public static void handleDimSyncPacket(ResourceKey<Level> level, boolean add) {

        if (Minecraft.getInstance().player == null || Minecraft.getInstance().player.connection.levels() == null)
            return;

        Set<ResourceKey<Level>> levels = Minecraft.getInstance().player.connection.levels();
        //If this player knows about this dimension
        if (levels.contains(level)) {
            //If remove
            if (!add) {
                levels.remove(level);
            }
        }
        //If player does not know about this dim and we're trying to add it
        else if (add) {
            levels.add(level);
        }
    }

}
