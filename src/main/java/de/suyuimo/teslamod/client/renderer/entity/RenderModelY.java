package de.suyuimo.teslamod.client.renderer.entity;


import de.suyuimo.teslamod.entitys.ModelY;
import net.minecraft.client.model.ModelBoat;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderModelY extends Render<ModelY> {

    public static final ResourceLocation TEXTURES = new ResourceLocation("yourmod:textures/entity/custom_boat.png");

    protected ModelBoat model = new ModelBoat();

    public RenderModelY(RenderManager renderManager) {
        super(renderManager);
        this.shadowSize = 0.5F;
    }

    @Override
    protected ResourceLocation getEntityTexture(ModelY entity) {
        return TEXTURES;
    }

    // Hier render-Methoden implementieren
}
