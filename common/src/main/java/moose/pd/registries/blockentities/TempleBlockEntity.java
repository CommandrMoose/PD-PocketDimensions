package moose.pd.registries.blockentities;

import moose.pd.Pd;
import moose.pd.blocks.temple.BaseTempleBlock;
import moose.pd.helper.DimensionHelper;
import moose.pd.network.messages.SyncLevelListMessage;
import moose.pd.network.messages.UpdateShellMessage;
import moose.pd.registries.BlockEntityRegistry;
import moose.pd.registries.BlockRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
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
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.phys.BlockHitResult;

import java.util.UUID;

public class TempleBlockEntity extends BlockEntity {

    private boolean activated = false;
    private String uuid = "";
    private String exteriorID = "";
    private int currentSet = 0;


    public TempleBlockEntity(BlockPos blockPos, BlockState blockState) {
        super(BlockEntityRegistry.TEMPLE_BLOCK.get(), blockPos, blockState);
        this.exteriorID = TempleShells.BLUE_BOX.id;
    }


    public String getExteriorID() {
        return exteriorID;
    }

    public void setExteriorID(String id) {
        this.exteriorID = id;
    }

    public void onRightClick(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {

        if (activated) {

            if (!level.isClientSide()) {

                if (player.getMainHandItem().getItem() == Items.INK_SAC) {
                    currentSet++;
                    if (currentSet >= TempleShells.values().length) {
                        currentSet = 0;
                    }

                    blockState.setValue(BaseTempleBlock.SHELL_ID, currentSet);

                    System.out.println(currentSet);
                    System.out.println(TempleShells.values()[currentSet]);

                   // this.exteriorID = TempleShells.values()[currentSet].id;
                    new UpdateShellMessage(level.dimension(), blockPos, TempleShells.values()[currentSet]).sendToAll((ServerLevel) level);


                } else {
                    ServerLevel temple = DimensionHelper.getOrBuildDimension(level, ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(Pd.MOD_ID, uuid)));
                    if (temple != null) {
                        rebuildReturnBlock(temple, blockState.getValue(BaseTempleBlock.FACING));
                        ServerPlayer sp = (ServerPlayer) level.getPlayerByUUID(player.getUUID());
                        sp.teleportTo(temple, 0.5, 66, 0.5, 180, 0);

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

                    ServerLevel temple = DimensionHelper.getOrBuildDimension(level, ResourceKey.create(Registry.DIMENSION_REGISTRY, new ResourceLocation(Pd.MOD_ID, uuid)));
                    buildInternal(temple, blockHitResult.getDirection());

                } else {
                    level.playSound(null, blockPos, SoundEvents.ITEM_BREAK, SoundSource.BLOCKS, 1, 1);

                }
            }
        }

        // if (blockHitResult.getDirection().toYRot() == blockState.getValue(BaseTempleBlock.FACING).toYRot() + 180) {



    }

    private void buildInternal(ServerLevel temple, Direction direction) {
        ServerLevelAccessor templeAccessor = temple;

        BlockPos position = new BlockPos(-16, 58, -56);

        temple.getStructureManager().getOrCreate(new ResourceLocation(Pd.MOD_ID, "temple_starter_room")).placeInWorld(templeAccessor, position, position, new StructurePlaceSettings(), temple.getRandom(), 1);

        rebuildReturnBlock(temple, direction);
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
        tag.putString(SHELL_DATA, this.exteriorID);
        return tag;
    }

    @Override
    public void load(CompoundTag compoundTag) {
        this.activated = compoundTag.getBoolean(ACTIVATED_DATA);
        this.uuid = compoundTag.getString(UUID_DATA);
        this.exteriorID = compoundTag.getString(SHELL_DATA);
        super.load(compoundTag);
    }

    @Override
    protected void saveAdditional(CompoundTag compoundTag) {
        compoundTag.putBoolean(ACTIVATED_DATA, this.activated);
        compoundTag.putString(UUID_DATA, this.uuid);
        compoundTag.putString(SHELL_DATA, this.exteriorID);
        super.saveAdditional(compoundTag);
    }

    // Data
    private static String ACTIVATED_DATA = "activated";
    private static String UUID_DATA = "uuid";
    private static String SHELL_DATA = "shell";

}

