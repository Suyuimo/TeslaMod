// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package de.suyuimo.teslamod.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;


public class ModelYEntityModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "teslamodely_converted"), "main");
    private final ModelPart bone;

    public ModelYEntityModel(ModelPart root) {
        this.bone = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(0, 64).addBox(-3.0F, -12.0F, -6.0F, 22.0F, 10.0F, 26.0F, new CubeDeformation(0.0F))
                .texOffs(0, 42).addBox(-3.0F, -12.0F, 20.0F, 22.0F, 10.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(60, 88).mirror().addBox(-3.0F, -10.0F, -17.0F, 22.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(64, 32).mirror().addBox(-2.0F, -19.0F, 7.0F, 20.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 112).mirror().addBox(-4.0F, -8.0F, -11.0F, 6.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 112).mirror().addBox(14.0F, -8.0F, -11.0F, 6.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 112).mirror().addBox(-4.0F, -8.0F, 20.0F, 6.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 112).mirror().addBox(14.0F, -8.0F, 20.0F, 6.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-8.0F, 5.0F, 7.0F, -3.1416F, 0.0F, 0.0F));

        PartDefinition haube_r1 = bone.addOrReplaceChild("haube_r1", CubeListBuilder.create().texOffs(28, 108).mirror().addBox(-3.0F, -16.0F, -12.0F, 22.0F, 8.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition backwindow_r1 = bone.addOrReplaceChild("backwindow_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-2.0F, -25.0F, 10.0F, 20.0F, 8.0F, 16.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

        PartDefinition windscrean_r1 = bone.addOrReplaceChild("windscrean_r1", CubeListBuilder.create().texOffs(52, 6).mirror().addBox(-2.0F, 7.0F, -14.0F, 20.0F, 8.0F, 18.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -2.7489F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bone.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}