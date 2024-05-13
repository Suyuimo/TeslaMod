package de.suyuimo.teslamod.entitys;


import de.suyuimo.teslamod.items.ItemManager;
import net.minecraft.network.chat.Component;
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

    private EnergyStorage energyStorage;

    private boolean isBoosting = false;
    private double boost = 1.7;
    private static final int ENERGY_PER_TICK = 100; // Energieverbrauch pro Tick beim Fahren

    public ModelYEntity(EntityType<? extends Boat> type, Level world) {
        super(type, world);
        this.energyStorage = new EnergyStorage(1000);
    }

    private void adjustSpeedBasedOnBoost() {
        if (this.isBoosting && this.energyStorage.getEnergyStored() > ENERGY_PER_TICK) {
            if (this.getDeltaMovement().x < boost && this.getDeltaMovement().z < boost) {
                this.setDeltaMovement(this.getDeltaMovement().x * boost, 0, this.getDeltaMovement().z * boost);
                this.energyStorage.extractEnergy(ENERGY_PER_TICK, false);
            }

            isBoosting = false;
        } else {
            if (this.energyStorage.getEnergyStored() > ENERGY_PER_TICK) {
                this.setDeltaMovement(0, 0, 0);
                this.energyStorage.extractEnergy(ENERGY_PER_TICK, false);
            }
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
        // Code dass der Schlüssel iregendwo im Inventar sein darf
        ItemStack carkeyininventory = new ItemStack(ItemManager.CARKEY.get());
        ItemStack itemInHand = player.getItemInHand(hand);

        if (player.isCrouching()) {
            if (itemInHand.isEmpty()) { // Der Spieler sollte nichts in der Hand halten
                // Zeige den aktuellen Ladezustand der Batterie an
                Component energyMessage = Component.literal("Current Energy: " + this.getEnergyStored() + " FE");
                player.displayClientMessage(energyMessage, true);
            } else {
                if (player.getInventory().contains(carkeyininventory) && this.energyStorage.getEnergyStored() > ENERGY_PER_TICK) {
                    player.startRiding(this);

                            /*
        //Code dass der Schlüssel in der Hand gehalten werden muss
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() == ItemManager.CARKEY.get()) {
                    player.startRiding(this);
        }
         */

                } else {
                    player.displayClientMessage(Component.literal("Not enough energy to drive."), true);
                }
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
        return true;
    }

    @Override
    public boolean canReceive() {
        return true;
    }
}
