package moose.pd.client.models.temple;// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import moose.pd.registries.blockentities.TempleBlockEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class GreekExternalModel extends HierarchicalModel {

	private final ModelPart Root;
	private final ModelPart Roof;
	private final ModelPart Door;
	private final ModelPart Walls;
	private final ModelPart Pillars;
	private final ModelPart bb_main;

	public GreekExternalModel(ModelPart root) {
		this.Root = root;
		this.Roof = this.Root.getChild("Roof");
		this.Door = this.Root.getChild("Door");
		this.Walls = this.Root.getChild("Walls");
		this.Pillars = this.Root.getChild("Pillars");
		this.bb_main = this.Root.getChild("bb_main");
	}

	public static LayerDefinition meshLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Roof = partdefinition.addOrReplaceChild("Roof", CubeListBuilder.create().texOffs(76, 31).addBox(-1.0F, -51.0F, -14.0F, 2.0F, 2.0F, 28.0F, new CubeDeformation(0.0F))
		.texOffs(0, 57).addBox(-13.0F, -49.0F, -12.0F, 26.0F, 7.0F, 24.0F, new CubeDeformation(0.0F))
		.texOffs(0, 29).addBox(-13.0F, -42.0F, -13.0F, 26.0F, 2.0F, 26.0F, new CubeDeformation(0.0F))
		.texOffs(135, 0).addBox(-11.0F, -40.0F, -11.0F, 22.0F, 2.0F, 22.0F, new CubeDeformation(0.0F))
		.texOffs(108, 31).addBox(-13.0F, -38.0F, -13.0F, 26.0F, 2.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition REast_r1 = Roof.addOrReplaceChild("REast_r1", CubeListBuilder.create().texOffs(78, 3).addBox(0.0F, -2.0F, -13.0F, 15.0F, 2.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-13.0F, -42.0F, 0.0F, 0.0F, 0.0F, -0.48F));

		PartDefinition RWest_r1 = Roof.addOrReplaceChild("RWest_r1", CubeListBuilder.create().texOffs(74, 62).addBox(-15.0F, -2.0F, -13.0F, 15.0F, 2.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, -42.0F, 0.0F, 0.0F, 0.0F, 0.48F));

		PartDefinition Door = partdefinition.addOrReplaceChild("Door", CubeListBuilder.create().texOffs(114, 90).addBox(-16.0F, -32.0F, -1.0F, 16.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 21.0F, -10.0F));

		PartDefinition Walls = partdefinition.addOrReplaceChild("Walls", CubeListBuilder.create().texOffs(0, 88).addBox(-11.0F, -36.0F, -9.0F, 1.0F, 33.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(76, 90).addBox(-9.0F, -36.0F, 10.0F, 18.0F, 33.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(38, 88).addBox(10.0F, -36.0F, -9.0F, 1.0F, 33.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Doorframe = Walls.addOrReplaceChild("Doorframe", CubeListBuilder.create().texOffs(104, 124).addBox(8.0F, -35.0F, -11.0F, 1.0F, 32.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(78, 0).addBox(-9.0F, -36.0F, -11.0F, 18.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(100, 124).addBox(-9.0F, -35.0F, -11.0F, 1.0F, 32.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Pillars = partdefinition.addOrReplaceChild("Pillars", CubeListBuilder.create().texOffs(76, 124).addBox(-12.0F, -40.0F, -12.0F, 3.0F, 37.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(123, 123).addBox(-12.0F, -40.0F, 9.0F, 3.0F, 37.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(111, 123).addBox(9.0F, -40.0F, 9.0F, 3.0F, 37.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(88, 124).addBox(9.0F, -40.0F, -12.0F, 3.0F, 37.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-13.0F, -3.0F, -13.0F, 26.0F, 3.0F, 26.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 256, 256);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Roof.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Door.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Walls.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Pillars.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return root();
	}

	@Override
	public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

	}
}