package de.suyuimo.teslamod.client.renderers;

import de.suyuimo.teslamod.TeslaMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.resources.ResourceLocation;

public class ModelYRendererTest extends BoatRenderer {
    private static final ResourceLocation TEXTURE = new ResourceLocation(TeslaMod.MODID, "textures/entity/rideable_block.png");

    public ModelYRendererTest(EntityRendererProvider.Context context) {
        super(context, false);
    }
}
