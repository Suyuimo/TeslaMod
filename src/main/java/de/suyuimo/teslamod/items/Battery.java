package de.suyuimo.teslamod.items;

import de.suyuimo.teslamod.TeslaMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.energy.IEnergyStorage;

public class Battery extends Item implements IEnergyStorage {
    private int energy;
    private final int capacity;
    private final int maxTransfer;

    public Battery(Properties properties, int capacity, int maxTransfer) {
        super(properties);
        this.capacity = capacity;
        this.maxTransfer = maxTransfer;
        if(TeslaMod.coding) {
            this.energy = 500;
        }else {
            this.energy = 0;  // Start without energy
        }

    }

    // IEnergyStorage Implementierung
    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
        int energyReceived = Math.min(capacity - energy, Math.min(this.maxTransfer, maxReceive));
        if (!simulate) {
            energy += energyReceived;
        }
        return energyReceived;
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(energy, Math.min(this.maxTransfer, maxExtract));
        if (!simulate) {
            energy -= energyExtracted;
        }
        return energyExtracted;
    }

    @Override
    public int getEnergyStored() {
        return energy;
    }

    @Override
    public int getMaxEnergyStored() {
        return capacity;
    }

    @Override
    public boolean canExtract() {
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}
