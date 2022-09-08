package moose.pd.client.render.blockentity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import moose.pd.Pd;
import moose.pd.client.models.temple.BlueBoxExternalModel;
import moose.pd.client.models.temple.GreekExternalModel;
import moose.pd.registries.blockentities.TempleBlockEntity;
import moose.pd.blocks.temple.BaseTempleBlock;
import moose.pd.client.models.ModelRegistry;
import moose.pd.client.models.temple.TempleExternalModel;
import moose.pd.registries.blockentities.TempleShells;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class TempleExternalRenderer implements BlockEntityRenderer<TempleBlockEntity>, BlockEntityRendererProvider<TempleBlockEntity> {

    private static TempleExternalModel templeModel;
    private static GreekExternalModel greekModel;
    private static BlueBoxExternalModel blueBoxModel;
    private static ResourceLocation templeTexture = new ResourceLocation(Pd.MOD_ID, "textures/shell/shell_temple.png");
    private static ResourceLocation greekTexture = new ResourceLocation(Pd.MOD_ID, "textures/shell/shell_greek.png");
    private static ResourceLocation blueBoxTexture = new ResourceLocation(Pd.MOD_ID, "textures/shell/shell_blue_box.png");


    public TempleExternalRenderer(BlockEntityRendererProvider.Context context) {
        templeModel = new TempleExternalModel(context.bakeLayer(ModelRegistry.TEMPLE_EXTERNAL));
        greekModel = new GreekExternalModel(context.bakeLayer(ModelRegistry.GREEK_EXTERNAL));
        blueBoxModel = new BlueBoxExternalModel(context.bakeLayer(ModelRegistry.BLUE_BOX_EXTERNAL));

    }

    @Override
    public void render(TempleBlockEntity templeBlockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j) {

        poseStack.pushPose();
        BlockState blockstate = templeBlockEntity.getBlockState();
        poseStack.translate(0.5F, 1.425F, 0.5F);
        poseStack.mulPose(Vector3f.ZP.rotationDegrees(180F));
        poseStack.scale(0.95f,0.95f,0.95f);

        float rotation = ((Direction)blockstate.getValue(BaseTempleBlock.FACING)).toYRot();
        poseStack.mulPose(Vector3f.YP.rotationDegrees(rotation));

        System.out.println(blockstate.getValue(BaseTempleBlock.SHELL_ID));


        if (blockstate.getValue(BaseTempleBlock.SHELL_ID) == TempleShells.TEMPLE.ordinal()) {
            this.templeModel.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.entityTranslucent(templeTexture)), i, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
        }

        if (blockstate.getValue(BaseTempleBlock.SHELL_ID) == TempleShells.GREEK.ordinal()) {
            this.greekModel.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.entityTranslucent(greekTexture)), i, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
        }

        if (blockstate.getValue(BaseTempleBlock.SHELL_ID) == TempleShells.BLUE_BOX.ordinal()) {
            this.blueBoxModel.renderToBuffer(poseStack, multiBufferSource.getBuffer(RenderType.entityTranslucent(blueBoxTexture)), i, OverlayTexture.NO_OVERLAY, 1F, 1F, 1F, 1F);
        }


        poseStack.popPose();
    }

    @Override
    public BlockEntityRenderer<TempleBlockEntity> create(Context context) {
        return new TempleExternalRenderer(context);
    }
}
