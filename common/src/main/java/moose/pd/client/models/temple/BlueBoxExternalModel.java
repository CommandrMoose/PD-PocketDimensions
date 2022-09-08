package moose.pd.client.models.temple;// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class BlueBoxExternalModel extends HierarchicalModel {

	private final ModelPart root;
	private final ModelPart Lamp;
	private final ModelPart Roof;
	private final ModelPart Signs;
	private final ModelPart Doors;
	private final ModelPart Panels;
	private final ModelPart Posts;
	private final ModelPart bb_main;

	public BlueBoxExternalModel(ModelPart root) {
		this.root = root;
		this.Lamp = root.getChild("Lamp");
		this.Roof = root.getChild("Roof");
		this.Signs = root.getChild("Signs");
		this.Doors = root.getChild("Doors");
		this.Panels = root.getChild("Panels");
		this.Posts = root.getChild("Posts");
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition meshLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Lamp = partdefinition.addOrReplaceChild("Lamp", CubeListBuilder.create().texOffs(28, 6).addBox(-1.0F, -76.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.2F))
				.texOffs(18, 12).addBox(-2.0F, -75.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.2F))
				.texOffs(0, 12).addBox(-0.5F, -73.5F, -3.0F, 1.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
				.texOffs(20, 27).addBox(-2.0F, -71.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.2F))
				.texOffs(20, 19).addBox(-2.0F, -73.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(-0.2F))
				.texOffs(21, 0).addBox(-2.0F, -69.0F, -2.0F, 4.0F, 2.0F, 4.0F, new CubeDeformation(0.2F))
				.texOffs(0, 23).addBox(-3.0F, -67.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Braces_r1 = Lamp.addOrReplaceChild("Braces_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-3.5F, -2.5F, -3.5F, 7.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -71.0F, 0.0F, 0.0F, 0.7854F, 0.0F));

		PartDefinition Braces_r2 = Lamp.addOrReplaceChild("Braces_r2", CubeListBuilder.create().texOffs(0, 12).addBox(-0.5F, -2.5F, -3.0F, 1.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -71.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

		PartDefinition Roof = partdefinition.addOrReplaceChild("Roof", CubeListBuilder.create().texOffs(110, 85).addBox(-15.0F, -66.0F, -15.0F, 30.0F, 2.0F, 30.0F, new CubeDeformation(0.0F))
				.texOffs(106, 42).addBox(-17.0F, -64.0F, -17.0F, 34.0F, 3.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Signs = partdefinition.addOrReplaceChild("Signs", CubeListBuilder.create().texOffs(0, 42).addBox(-17.0F, -61.0F, -19.0F, 34.0F, 5.0F, 38.0F, new CubeDeformation(0.0F))
				.texOffs(0, 85).addBox(-19.0F, -61.0F, -17.0F, 38.0F, 5.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Doors = partdefinition.addOrReplaceChild("Doors", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition RDoor = Doors.addOrReplaceChild("RDoor", CubeListBuilder.create().texOffs(174, 169).addBox(0.0F, -55.0F, -16.0F, 13.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(3, 3).addBox(1.0F, -33.0F, -17.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition LDoor = Doors.addOrReplaceChild("LDoor", CubeListBuilder.create().texOffs(174, 117).addBox(-13.0F, -55.0F, -16.0F, 13.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(0, 0).addBox(-12.0F, -37.0F, -17.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Panels = partdefinition.addOrReplaceChild("Panels", CubeListBuilder.create().texOffs(116, 117).addBox(-16.0F, -56.0F, -14.0F, 1.0F, 52.0F, 28.0F, new CubeDeformation(0.0F))
				.texOffs(0, 124).addBox(15.0F, -56.0F, -14.0F, 1.0F, 52.0F, 28.0F, new CubeDeformation(0.0F))
				.texOffs(58, 124).addBox(-14.0F, -56.0F, 15.0F, 28.0F, 52.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Doorframe = Panels.addOrReplaceChild("Doorframe", CubeListBuilder.create().texOffs(126, 197).addBox(13.0F, -55.0F, -16.0F, 1.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(122, 197).addBox(-14.0F, -55.0F, -16.0F, 1.0F, 51.0F, 1.0F, new CubeDeformation(0.0F))
				.texOffs(114, 0).addBox(-14.0F, -56.0F, -16.0F, 28.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition Posts = partdefinition.addOrReplaceChild("Posts", CubeListBuilder.create().texOffs(90, 177).addBox(-18.0F, -62.0F, -18.0F, 4.0F, 58.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(74, 177).addBox(-18.0F, -62.0F, 14.0F, 4.0F, 58.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(58, 177).addBox(14.0F, -62.0F, 14.0F, 4.0F, 58.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(106, 197).addBox(14.0F, -62.0F, -18.0F, 4.0F, 58.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(0, 0).addBox(-19.0F, -4.0F, -19.0F, 38.0F, 4.0F, 38.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 272, 272);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Lamp.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Roof.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Signs.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Doors.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Panels.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Posts.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

	}
}