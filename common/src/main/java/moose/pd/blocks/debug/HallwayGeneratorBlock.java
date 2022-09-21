package moose.pd.blocks.debug;


import moose.pd.Pd;
import moose.pd.world.hallway.HallwayGenerator;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

import java.sql.Date;
import java.sql.Time;
import java.util.UUID;

import static com.ibm.icu.impl.ValidIdentifiers.Datatype.x;

public class HallwayGeneratorBlock extends Block {
    public HallwayGeneratorBlock(Properties properties) {
        super(properties);
    }


    @Override
    public InteractionResult use(BlockState blockState, Level level, BlockPos blockPos, Player player, InteractionHand p_60507_, BlockHitResult result) {

        if (!level.isClientSide()) {

            if (player.isCrouching()) {
                player.displayClientMessage(Component.translatable("Attempting to generate structure."), false);
                HallwayGenerator.generateFromPosition(level, blockPos, blockPos);
            } else {

                UUID uuid = UUID.randomUUID();
                String name = "hallway_sets/" + uuid.toString();
                StructureTemplateManager manager = level.getServer().getStructureManager();
                StructureTemplate template = manager.getOrCreate(new ResourceLocation(Pd.MOD_ID,name));
                template.fillFromWorld(level, blockPos.above(), new BlockPos(48, 48, 48), false, null);
                template.setAuthor("");
                manager.save(new ResourceLocation(Pd.MOD_ID,name));

                System.out.println(level.getBlockState(blockPos.above()).getValues().toString());

                player.displayClientMessage(Component.translatable("Generated structure at: " + name), false);
            }


        }

        return super.use(blockState, level, blockPos, player, p_60507_, result);
    }

}
