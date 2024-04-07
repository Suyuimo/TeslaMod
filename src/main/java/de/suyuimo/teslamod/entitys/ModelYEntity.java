package de.suyuimo.teslamod.entitys;

import de.suyuimo.teslamod.items.ItemManager;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public class ModelYEntity extends Boat implements IEnergyStorage, ICapabilityProvider {

    private final EnergyStorage energyStorage;
    private Level level;

    public ModelYEntity(EntityType<? extends Boat> type, Level world) {
        super(type, world);
        this.energyStorage = new EnergyStorage(1000);
        // Weitere Initialisierungen hier
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() == ItemManager.CARKEY.get()) {
            // Logik zum Einsteigen ins Fahrzeug, wenn der Spieler den Schlüssel hat
            // Beispiel:
            if (getEnergyStored() > 0) {
                // Logik zur Bewegung des Entitys
                // Zum Beispiel, verbrauche Energie bei Bewegung:
                this.extractEnergy(10, false); // Verbraucht 50 RF pro Bewegungsaktion
                player.startRiding(this);
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        return energyStorage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        return energyStorage.extractEnergy(maxExtract, simulate);
    }

    @Override
    public int getEnergyStored() {
        return energyStorage.getEnergyStored();
    }

    @Override
    public int getMaxEnergyStored() {
        return energyStorage.getMaxEnergyStored();
    }

    @Override
    public boolean canExtract() {
        return false;
    }

    @Override
    public boolean canReceive() {
        return true;
    }

}
