package moose.pd.blockentities;

import moose.pd.Pd;
import moose.pd.blocks.temple.BaseTempleBlock;
import moose.pd.dimension.DimensionBuilder;
import moose.pd.registries.BlockEntityRegistry;
import moose.pd.registries.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.ShulkerBoxBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.ShulkerBoxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.phys.BlockHitResult;

import java.util.Random;
import java.util.UUID;

public class TempleBlockEntity extends BlockEntity implements BlockEntityTicker<TempleBlockEntity> {

    private boolean activated = false;
    private String uuid = "";
    private int currentSet = 0;
    private boolean hasLoadedForSession = false;

    public TempleBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityRegistry.TEMPLE_BLOCK.get(), blockPos, blockState);
    }


    public void sendUpdates() {
        if (level != null && getBlockState() != null && getBlockState().getBlock() != null) {
            this.getLevel().updateNeighbourForOutputSignal(this.getBlockPos(), getBlockState().getBlock());
            this.getLevel().sendBlockUpdated(worldPosition, this.getLevel().getBlockState(worldPosition), this.getLevel().getBlockState(worldPosition), 3);
        }
        setChanged();
    }

    public void onRightClick(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {
        if (activated) {
            if (!level.isClientSide()) {
                if (player.getMainHandItem().getItem() != Items.INK_SAC) {

                    ServerLevel temple = level.getServer().getLevel(ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(Pd.MOD_ID, uuid)));

                    if (temple != null) {
                        rebuildReturnBlock(temple, blockState.getValue(BaseTempleBlock.FACING));
                        ServerPlayer sp = (ServerPlayer) level.getPlayerByUUID(player.getUUID());
                        sp.teleportTo(temple, 0.5, 66, 0.5, 180, 0);
                    }
                } else {
                    this.currentSet++;
                    if (this.currentSet >= TempleShells.values().length) {
                        this.currentSet = 0;
                    }
                    level.setBlock(blockPos, blockState.setValue(BaseTempleBlock.SHELL_ID, this.currentSet), 3);
                    sendUpdates();
                }
            } else {

                if (player.getMainHandItem().getItem() == Items.INK_SAC) {
                    BlockPos center = new BlockPos(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                    for (int i = 0; i < 150; i++) {
                        Random random = new Random();
                        level.addParticle(ParticleTypes.HAPPY_VILLAGER, center.getX() + ((random.nextInt(300) -10) * 0.0075) -0.5, center.getY() + ((random.nextInt(300) -10) * 0.0075), center.getZ() + ((random.nextInt(300) -10) * 0.0075) -0.5,0, level.getRandom().nextInt(50), 0);
                    }
                }

            }
        } else {

            ItemStack stack = player.getMainHandItem();

            if (!level.isClientSide()) {
                if (stack.getItem() == Items.NETHER_STAR) {
                    activated = true;
                    level.playSound(null, blockPos, SoundEvents.BEACON_ACTIVATE, SoundSource.BLOCKS, 1, 1);
                    stack.setCount(stack.getCount() - 1);
                    uuid = UUID.randomUUID().toString();

                    ServerLevel temple = DimensionBuilder.getOrBuildDimension(level, ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(Pd.MOD_ID, uuid)));

                } else {
                    level.playSound(null, blockPos, SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1, 1);

                }
            }
        }

        sendUpdates();

    }

    private void rebuildReturnBlock(ServerLevel temple, Direction direction) {
        BlockPos exit = new BlockPos(0, 66, 2);
        temple.setBlock(exit, BlockRegistry.TEMPLE_RETURN_BLOCK.get().defaultBlockState(), 3);
        TempleReturnBlockEntity trbe = (TempleReturnBlockEntity) temple.getBlockEntity(exit);
        trbe.setExternalInformation(getLevel().dimension(), getBlockPos().above(), direction);
    }

    public CompoundTag writeToItem() {
        CompoundTag tag = new CompoundTag();
        tag.putBoolean(ACTIVATED_DATA, this.activated);
        tag.putString(UUID_DATA, this.uuid);
        tag.putInt(SHELL_DATA, this.currentSet);
        return tag;
    }

    @Override
    public void load(CompoundTag compoundTag) {
        this.activated = compoundTag.getBoolean(ACTIVATED_DATA);
        this.uuid = compoundTag.getString(UUID_DATA);
        int id = (compoundTag.getInt(SHELL_DATA) != -1) ? compoundTag.getInt(SHELL_DATA) : 0;
        this.currentSet = id;
        sendUpdates();
        super.load(compoundTag);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        compoundTag.putBoolean(ACTIVATED_DATA, this.activated);
        compoundTag.putString(UUID_DATA, this.uuid);
        compoundTag.putInt(SHELL_DATA, this.currentSet);
        super.saveAdditional(compoundTag);
    }

    // Data
    private static String ACTIVATED_DATA = "activated";
    private static String UUID_DATA = "uuid";
    private static String SHELL_DATA = "shell";

    @Override
    public void tick(Level level, BlockPos blockPos, BlockState blockState, TempleBlockEntity blockEntity) {

        if (!level.isClientSide()) {
            level.setBlock(blockPos, blockState.setValue(BaseTempleBlock.SHELL_ID, this.currentSet), 3);
        }

    }
}

