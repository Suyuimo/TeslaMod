package de.suyuimo.teslamod.entitys;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.horse.Horse;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class ModelYEntityTest extends Horse {

    private Level level;

    public ModelYEntityTest(EntityType<? extends Horse> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        if (!this.level.isClientSide()) {
                // Erlaube dem Spieler, das Pferd zu reiten
                player.startRiding(this);
                return InteractionResult.sidedSuccess(this.level.isClientSide);
            }
        return InteractionResult.PASS;
    }
}