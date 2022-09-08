package moose.pd.blocks.internal;

import moose.pd.blockentities.TempleReturnBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class TempleReturnBlock extends BaseEntityBlock {

    public TempleReturnBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand interactionHand, BlockHitResult blockHitResult) {

        TempleReturnBlockEntity entity = (TempleReturnBlockEntity) level.getBlockEntity(blockPos);
        entity.onRightClick(blockState, level, blockPos, player, interactionHand, blockHitResult);

        return InteractionResult.SUCCESS;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos blockPos, BlockState blockState) {
        return new TempleReturnBlockEntity(blockPos, blockState);
    }
}
