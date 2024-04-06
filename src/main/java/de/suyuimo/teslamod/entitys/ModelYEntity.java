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

public class ModelYEntity extends Boat {

    private Level level;

    public ModelYEntity(EntityType<? extends Boat> type, Level world) {
        super(type, world);
        // Weitere Initialisierungen hier
    }

    @Override
    public InteractionResult interact(Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        if (itemStack.getItem() == ItemManager.CARKEY.get()) {
            // Logik zum Einsteigen ins Fahrzeug, wenn der Spieler den Schlüssel hat
            // Beispiel:
                player.startRiding(this);
        }
        return InteractionResult.PASS;
    }
}
