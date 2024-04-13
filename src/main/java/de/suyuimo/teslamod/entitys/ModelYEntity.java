package de.suyuimo.teslamod.entitys;

import com.mojang.math.Axis;
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

public class ModelYEntity extends Boat implements IEnergyStorage {

    private final EnergyStorage energyStorage;
    private Level level;
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

        ItemStack carkeyininventory = new ItemStack(ItemManager.CARKEY.get());



        if(player.getInventory().contains(carkeyininventory) == true) {

            player.startRiding(this);
        }

        /*
        //old Code
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() == ItemManager.CARKEY.get()) {
        //    if (getEnergyStored() > 0) {
         //       this.extractEnergy(10, false); // Verbraucht 50 RF pro Bewegungsaktion
                player.startRiding(this);
       //     }
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


}
