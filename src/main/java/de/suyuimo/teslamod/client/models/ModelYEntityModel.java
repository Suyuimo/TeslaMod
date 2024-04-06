// Made with Blockbench 4.9.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package de.suyuimo.teslamod.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import de.suyuimo.teslamod.TeslaMod;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;

public class ModelYEntityModel<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(TeslaMod.MODID, "modelyentity_converted"), "main");
    private final ModelPart bone;

    public ModelYEntityModel(ModelPart root) {
        this.bone = root.getChild("bone");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone = partdefinition.addOrReplaceChild("bone", CubeListBuilder.create().texOffs(124, 27).mirror().addBox(-18.0F, -11.0F, -4.0F, 20.0F, 9.0F, 23.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(12, 0).mirror().addBox(-18.0F, -11.0F, 19.0F, 20.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(0, 66).addBox(-18.0F, -8.0F, -16.0F, 20.0F, 6.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(0, 2).addBox(-17.0F, -17.0F, 7.0F, 18.0F, 7.0F, 12.0F, new CubeDeformation(0.0F))
                .texOffs(8, 89).addBox(-1.0F, -7.0F, -11.0F, 4.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(8, 89).addBox(-19.0F, -7.0F, -11.0F, 4.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(8, 89).addBox(-1.0F, -7.0F, 20.0F, 4.0F, 7.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(8, 89).addBox(-19.0F, -7.0F, 20.0F, 4.0F, 7.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 24.0F, -8.0F));

        PartDefinition haube_r1 = bone.addOrReplaceChild("haube_r1", CubeListBuilder.create().texOffs(74, 73).addBox(-18.0F, -14.0F, -12.0F, 20.0F, 7.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(36, 0).addBox(-17.0F, -13.0F, -4.0F, 18.0F, 7.0F, 17.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.0F));

        PartDefinition backwindow_r1 = bone.addOrReplaceChild("backwindow_r1", CubeListBuilder.create().texOffs(20, 38).mirror().addBox(-17.0F, -23.0F, 11.0F, 18.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.3927F, 0.0F, 0.0F));

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