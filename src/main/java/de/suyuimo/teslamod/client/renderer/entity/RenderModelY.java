package de.suyuimo.teslamod.client.renderer.entity;


import de.suyuimo.teslamod.client.model.ModelModelY;
import de.suyuimo.teslamod.entitys.ModelY;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderModelY extends Render<ModelY> {

    private static final ResourceLocation CUSTOM_BOAT_TEXTURES = new ResourceLocation("teslamod", "textures/entity/modely_block.png");
    private final ModelBase model = new ModelModelY();

    public RenderModelY(RenderManager renderManager) {
        super(renderManager);
        this.shadowSize = 0.5F; // Schatten-Größe anpassen
    }

    @Override
    protected ResourceLocation getEntityTexture(ModelY entity) {
        return CUSTOM_BOAT_TEXTURES;
    }

    @Override
    public void doRender(ModelY entity, double x, double y, double z, float entityYaw, float partialTicks) {
        // Hier kommt die Logik, um das Model zu rendern. Dies kann je nach Entity-Typ variieren.
    }
}