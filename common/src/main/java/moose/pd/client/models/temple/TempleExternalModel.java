package moose.pd.client.models.temple;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class TempleExternalModel extends HierarchicalModel {
    private final ModelPart root;
    private final ModelPart Roof;
    private final ModelPart Door;
    private final ModelPart Walls;
    private final ModelPart Pillars;
    private final ModelPart bb_main;


    public TempleExternalModel(ModelPart root) {
        this.root = root;
        this.Roof = root.getChild("Roof");
        this.Door = root.getChild("Door");
        this.Walls = root.getChild("Walls");
        this.Pillars = root.getChild("Pillars");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition meshLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Roof = partdefinition.addOrReplaceChild("Roof", CubeListBuilder.create().texOffs(76, 31).addBox(-1.0F, -47.0F, -14.0F, 2.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
                .texOffs(0, 57).addBox(-13.0F, -45.0F, -12.0F, 26.0F, 7.0F, 24.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-13.0F, -38.0F, -13.0F, 26.0F, 2.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition REast_r1 = Roof.addOrReplaceChild("REast_r1", CubeListBuilder.create().texOffs(78, 3).addBox(0.0F, -2.0F, -13.0F, 15.0F, 2.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, -38.0F, 0.0F, 0.0F, 0.0F, -0.48F));

        PartDefinition RWest_r1 = Roof.addOrReplaceChild("RWest_r1", CubeListBuilder.create().texOffs(74, 62).addBox(-15.0F, -2.0F, -13.0F, 15.0F, 2.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, -38.0F, 0.0F, 0.0F, 0.0F, 0.48F));

        PartDefinition Door = partdefinition.addOrReplaceChild("Door", CubeListBuilder.create().texOffs(114, 90).addBox(-16.0F, -32.0F, -1.0F, 16.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 21.0F, -10.0F));

        PartDefinition Walls = partdefinition.addOrReplaceChild("Walls", CubeListBuilder.create().texOffs(0, 88).addBox(-11.0F, -36.0F, -9.0F, 1.0F, 33.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(76, 90).addBox(-9.0F, -36.0F, 10.0F, 18.0F, 33.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 88).addBox(10.0F, -36.0F, -9.0F, 1.0F, 33.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition Doorframe = Walls.addOrReplaceChild("Doorframe", CubeListBuilder.create().texOffs(104, 124).addBox(8.0F, -35.0F, -11.0F, 1.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(78, 0).addBox(-9.0F, -36.0F, -11.0F, 18.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(100, 124).addBox(-9.0F, -35.0F, -11.0F, 1.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Pillars = partdefinition.addOrReplaceChild("Pillars", CubeListBuilder.create().texOffs(76, 124).addBox(-12.0F, -36.0F, -12.0F, 3.0F, 33.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(123, 123).addBox(-12.0F, -36.0F, 9.0F, 3.0F, 33.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(111, 123).addBox(9.0F, -36.0F, 9.0F, 3.0F, 33.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(88, 124).addBox(9.0F, -36.0F, -12.0F, 3.0F, 33.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-13.0F, -3.0F, -13.0F, 26.0F, 3.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 256, 256);
    }

    @Override
    public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return root;
    }
}
