package de.suyuimo.teslamod.client.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;

public class ModelModelY extends ModelBase {
    // Beispiel: Definieren Sie einen Teil des Modells
    public ModelRenderer boatPart;

    public ModelModelY() {
        // Texturgröße festlegen, z.B. 64x32
        this.textureWidth = 64;
        this.textureHeight = 32;

        // Initialisieren Sie boatPart mit Texturkoordinaten (x, y)
        this.boatPart = new ModelRenderer(this, 0, 0);
        this.boatPart.addBox(-14.0F, -4.0F, -3.0F, 28, 6, 3); // Größe und Position
        this.boatPart.setRotationPoint(0.0F, 22.0F, 1.0F); // Drehpunkt
    }

    @Override
    public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        this.boatPart.render(f5);
        // Hier können weitere Teile gerendert werden
    }

    // Weitere Methoden zur Anpassung des Modells können hier hinzugefügt werden
}
