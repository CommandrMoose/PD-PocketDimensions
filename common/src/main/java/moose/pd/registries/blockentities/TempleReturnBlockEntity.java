package moose.pd.registries.blockentities;

import moose.pd.registries.BlockEntityRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Debug;

import javax.annotation.Nullable;
import java.util.Map;

public class TempleReturnBlockEntity extends BlockEntity {

    private BlockPos externalPos;
    private ResourceKey<Level> level;
    private int direction;

    public TempleReturnBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityRegistry.TEMPLE_RETURN_BLOCK.get(), blockPos, blockState);
    }

    public void onRightClick(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {

        if (!level.isClientSide()) {
            Map<ResourceKey<Level>, ServerLevel> map = level.getServer().levels;
            @Nullable ServerLevel existingLevel = map.get(this.level);

            if (existingLevel != null) {
                ServerPlayer sp = (ServerPlayer) level.getPlayerByUUID(player.getUUID());

                System.out.println(direction);
                BlockPos outPos = externalPos;

                switch (direction) {
                    case 90:
                        outPos = outPos.east();
                        break;
                    case 180:
                        outPos = outPos.south();
                        break;

                    case 270:
                        outPos = outPos.west();
                        break;

                    case 0:
                        outPos =  outPos.north();
                        break;
                }

                sp.teleportTo(existingLevel, outPos.getX() + 0.5f, outPos.getY(), outPos.getZ() + 0.5f, 180+direction,0);
            }
        }

    }

    public void setExternalInformation(ResourceKey<Level> key, BlockPos pos, Direction direction) {
        this.level = key;
        this.externalPos = pos;
        this.direction = (int) direction.toYRot();
    }

    @Override
    public void load(CompoundTag compoundTag) {

        this.level = ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(compoundTag.getString(EXTERNAL_NAMESPACE_DATA), compoundTag.getString(EXTERNAL_UUID_DATA)));
        this.externalPos = new BlockPos(compoundTag.getFloat(EXTERNAL_POSX_DATA), compoundTag.getFloat(EXTERNAL_POSY_DATA),compoundTag.getFloat(EXTERNAL_POSZ_DATA));
        this.direction = compoundTag.getInt(EXTERNAL_ROTATION_DATA);
        super.load(compoundTag);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        compoundTag.putString(EXTERNAL_NAMESPACE_DATA, this.level.location().getNamespace());
        compoundTag.putString(EXTERNAL_UUID_DATA, this.level.location().getPath());
        compoundTag.putFloat(EXTERNAL_POSX_DATA, this.externalPos.getX());
        compoundTag.putFloat(EXTERNAL_POSY_DATA, this.externalPos.getY());
        compoundTag.putFloat(EXTERNAL_POSZ_DATA, this.externalPos.getZ());
        compoundTag.putInt(EXTERNAL_ROTATION_DATA, this.direction);

        super.saveAdditional(compoundTag);
    }


    private static String EXTERNAL_NAMESPACE_DATA = "external_namespace";
    private static String EXTERNAL_UUID_DATA = "external_uuid";
    private static String EXTERNAL_POSX_DATA = "external_posx";
    private static String EXTERNAL_ROTATION_DATA = "external_rotation";
    private static String EXTERNAL_POSY_DATA = "external_posy";
    private static String EXTERNAL_POSZ_DATA = "external_posz";
}
