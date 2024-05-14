package de.suyuimo.teslamod.entitys;


import de.suyuimo.teslamod.items.ItemManager;
import de.suyuimo.teslamod.net.EnergySyncMessage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import de.suyuimo.teslamod.net.ModNetwork;
import net.minecraftforge.network.PacketDistributor;


public class ModelYEntity extends Boat implements IEnergyStorage {

    private EnergyStorage energyStorage;

    private Player lastInteractingPlayer;

    private boolean isBoosting = false;
    private double boost = 1.7;

    private int battery;
    private static final int ENERGY_PER_TICK = 1; // Energieverbrauch pro Tick beim Fahren

    public ModelYEntity(EntityType<? extends Boat> type, Level world) {
        super(type, world);
        this.energyStorage = new EnergyStorage(1000);
    }

    private void adjustSpeedBasedOnBoost() {
        if (this.isBoosting) {
            if (this.getDeltaMovement().x < boost && this.getDeltaMovement().z < boost) {
                this.setDeltaMovement(this.getDeltaMovement().x * boost,0,this.getDeltaMovement().z * boost);
                this.energyStorage.extractEnergy(ENERGY_PER_TICK * 3, false);
                forceSyncEnergy();
            }

            isBoosting = false;
        } else {
            if (this.isVehicle()) {

                this.energyStorage.extractEnergy(ENERGY_PER_TICK, false);
                forceSyncEnergy();
            }
        }
    }

    private void batteryEmptyCheck() {
        if (this.energyStorage.getEnergyStored() > ENERGY_PER_TICK) {

        } else {
            this.setDeltaMovement(0,0,0);
        }
    }

    private void updateBattery() {
        battery = this.energyStorage.getEnergyStored();
        System.out.println(battery);
    }

    @Override
    public void tick() {
        super.tick();
        adjustSpeedBasedOnBoost();
        //batteryEmptyCheck();
        updateBattery();
    }

    public void setBoosting(boolean isBoosting) {
        this.isBoosting = isBoosting;
    }

    public void forceSyncEnergy() {
        if (!this.level().isClientSide) { // Sicherstellen, dass dies nur auf dem Server ausgeführt wird
            ModNetwork.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> this), new EnergySyncMessage(this.getEnergyStored()));
        }
    }

    public boolean getBoosting() {
        return this.isBoosting;
    }

    // Beispiele für Setter-Methoden
    public void setLastInteractingPlayer(Player player) {
        this.lastInteractingPlayer = player;
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        // Code dass der Schlüssel iregendwo im Inventar sein darf
        ItemStack carkeyininventory = new ItemStack(ItemManager.CARKEY.get());
        ItemStack itemInHand = player.getItemInHand(hand);
        this.setLastInteractingPlayer(player);

        if (player.isCrouching() && itemInHand.isEmpty()) {
            // Der Spieler sollte nichts in der Hand halten
            // Zeige den aktuellen Ladezustand der Batterie an
            forceSyncEnergy();
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
        return InteractionResult.PASS;
    }

    @Override
    public int receiveEnergy(int maxReceive, boolean simulate) {
    //    if (!simulate && lastInteractingPlayer != null) {
   //         ModNetwork.INSTANCE.send(PacketDistributor.PLAYER.with(() -> (ServerPlayer) lastInteractingPlayer), new EnergySyncMessage(maxReceive));
 //       }
        forceSyncEnergy();
        return energyStorage.receiveEnergy(maxReceive, simulate);
    }

    @Override
    public int extractEnergy(int maxExtract, boolean simulate) {
        forceSyncEnergy();
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


}
