package de.suyuimo.teslamod.client.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.energy.IEnergyStorage;

public class CarEnergyGUI extends Screen {
    private final IEnergyStorage energyStorage;

    public CarEnergyGUI(IEnergyStorage energyStorage) {
        super(Component.literal("Energy Status"));
        this.energyStorage = energyStorage;
    }

    @Override
    public void render(GuiGraphics graphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(graphics);  // Hintergrund rendern
        super.render(graphics, mouseX, mouseY, partialTicks);  // Grundlegende Screen-Funktionalität rendern
        renderEnergyStatus(graphics);
    }

    private void renderEnergyStatus(GuiGraphics graphics) {
        int energy = energyStorage.getEnergyStored();
        int maxEnergy = energyStorage.getMaxEnergyStored();
        String energyText = "Energy: " + energy + " / " + maxEnergy;
        int width = this.font.width(energyText);
        int x = (this.width - width) / 2;
        int y = (this.height / 2) - 10;
        graphics.drawString(font, energyText, x, y, 0xFFFFFF);  // Text zentrieren und zeichnen
    }

    @Override
    public boolean isPauseScreen() {
        return false;
    }
}