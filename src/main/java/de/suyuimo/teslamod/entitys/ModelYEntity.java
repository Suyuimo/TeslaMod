package de.suyuimo.teslamod.entitys;

import de.suyuimo.teslamod.TeslaMod;
import de.suyuimo.teslamod.client.gui.CarEnergyGUI;
import de.suyuimo.teslamod.items.Battery;
import de.suyuimo.teslamod.items.ItemManager;
import net.minecraft.client.Minecraft;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

public class ModelYEntity extends Boat implements IEnergyStorage {

    private final EnergyStorage energyStorage;
    private boolean isBoosting = false;
    private double boost = 1.7;

    public ModelYEntity(EntityType<? extends Boat> type, Level world) {
        super(type, world);
        this.energyStorage = new EnergyStorage(1000);
    }

    private void adjustSpeedBasedOnBoost() {
        if (this.isBoosting) {
            if (this.getDeltaMovement().x < boost && this.getDeltaMovement().z < boost) {
                this.setDeltaMovement(this.getDeltaMovement().x * boost,0,this.getDeltaMovement().z * boost);
            }

            isBoosting = false;
        } else {
            this.setDeltaMovement(0, 0, 0);
        }
    }


    @Override
    public void tick() {
        super.tick();
        adjustSpeedBasedOnBoost();
    }

    public void setBoosting(boolean isBoosting) {
        this.isBoosting = isBoosting;
    }

    public boolean getBoosting() {
        return this.isBoosting;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        System.out.println("Hand Max:" + this.getMaxEnergyStored() + "Aktuell:" + this.getEnergyStored());
        if(player.isCrouching()) {
            Minecraft.getInstance().setScreen(new CarEnergyGUI(this));
        } else {
            ItemStack stack = player.getItemInHand(hand);
            if (stack.getItem() instanceof Battery) {
                IEnergyStorage battery = (IEnergyStorage) stack.getItem();
                if (this.getEnergyStored() < this.getMaxEnergyStored()) {
                    int energyNeeded = this.getMaxEnergyStored() - this.getEnergyStored();
                    System.out.println("Mit Battery Max:" + this.getMaxEnergyStored() + "Aktuell:" + this.getEnergyStored());
                    int energyTransferred = battery.extractEnergy(energyNeeded, false);
                    this.receiveEnergy(energyTransferred, false);
                }
            } else {

                // Code dass der Schlüssel iregendwo im Inventar sein darf
                ItemStack carkeyininventory = new ItemStack(ItemManager.CARKEY.get());

                if (player.getInventory().contains(carkeyininventory)) {
                    if (getEnergyStored() > 50) {
                    //    this.extractEnergy(10, false); // Verbraucht 50 RF pro Bewegungsaktion
                        player.startRiding(this);
                    }
                }
            }

        }

        /*
        //Code dass der Schlüssel in der Hand gehalten werden muss
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() == ItemManager.CARKEY.get()) {

        }
         */
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
/*
    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Energy", energyStorage.getEnergyStored());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("Energy", 99)) { // 99 steht für den NBT-Typ für Integer
            energyStorage.receiveEnergy(compound.getInt("Energy"),false);
        }
    }
 */



}
