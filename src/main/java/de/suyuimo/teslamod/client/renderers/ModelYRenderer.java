package de.suyuimo.teslamod.client.renderers;

import de.suyuimo.teslamod.TeslaMod;
import de.suyuimo.teslamod.entitys.ModelYEntity;
import de.suyuimo.teslamod.client.models.ModelYEntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import com.mojang.blaze3d.vertex.PoseStack;


public class ModelYRenderer extends EntityRenderer<ModelYEntity> {
    private final ModelYEntityModel<ModelYEntity> model;



    public ModelYRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.model = new ModelYEntityModel<>(context.bakeLayer(TeslaMod.MODEL_Y_LAYER));
    }

    @Override
    public void render(ModelYEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        poseStack.pushPose();
        // Hier würdest du die Skalierung und Rotation anpassen, um dein Modell korrekt zu positionieren
        model.renderToBuffer(poseStack, bufferSource.getBuffer(model.renderType(getTextureLocation(entity))), packedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
       // poseStack.mulPose(Axis.YP.rotationDegrees(-entity.getYRot()));
        poseStack.popPose();
    }

    @Override
    public ResourceLocation getTextureLocation(ModelYEntity entity) {
        return new ResourceLocation("teslamod", "textures/entity/tesla.png");
    }
}
