package moose.pd.client.models.temple;// Made with Blockbench 4.3.1
// Exported for Minecraft version 1.17 - 1.18 with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class SuitcaseExternalModel extends HierarchicalModel {

	private final ModelPart root;
	private final ModelPart Lid;
	private final ModelPart Main;

	public SuitcaseExternalModel(ModelPart root) {
		this.root = root;
		this.Lid = this.root.getChild("Lid");
		this.Main = this.root.getChild("Main");
	}

	public static LayerDefinition meshLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Lid = partdefinition.addOrReplaceChild("Lid", CubeListBuilder.create().texOffs(0, 38).addBox(-10.0F, -2.0F, -14.0F, 20.0F, 2.0F, 14.0F, new CubeDeformation(0.19F))
		.texOffs(0, 54).addBox(-10.0F, -2.0F, -14.0F, 20.0F, 2.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(54, 38).addBox(-9.0F, -1.0F, -13.0F, 18.0F, 1.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 19.0F, 7.0F));

		PartDefinition Main = partdefinition.addOrReplaceChild("Main", CubeListBuilder.create().texOffs(0, 0).addBox(-10.0F, -5.0F, -7.0F, 20.0F, 5.0F, 14.0F, new CubeDeformation(0.2F))
		.texOffs(0, 19).addBox(-10.0F, -5.0F, -7.0F, 20.0F, 5.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(56, 7).addBox(-9.0F, -5.0F, -6.0F, 18.0F, 4.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition Handle = Main.addOrReplaceChild("Handle", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -4.0F, -8.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}


	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Lid.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
		Main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return root;
	}

	@Override
	public void setupAnim(Entity entity, float f, float g, float h, float i, float j) {

	}
}