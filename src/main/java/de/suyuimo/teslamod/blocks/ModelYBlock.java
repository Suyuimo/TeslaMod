package de.suyuimo.teslamod.blocks;

import de.suyuimo.teslamod.TeslaMod;
import de.suyuimo.teslamod.entitys.EntityManager;
import de.suyuimo.teslamod.entitys.ModelYEntity;
import de.suyuimo.teslamod.items.ItemManager;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class ModelYBlock extends Block {
    public ModelYBlock(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (!world.isClientSide) {
            // Entferne den Block
            player.getInventory().add(new ItemStack(ItemManager.CARKEY.get()));
            world.removeBlock(pos, false);

            // Spawne das Entity
            ModelYEntity modelYEntity = new ModelYEntity(EntityManager.MODELY.get(), world);
            modelYEntity.setPos(pos.getX(), pos.getY(), pos.getZ());
            modelYEntity.setYRot(player.getYRot()); // Richtung anpassen
            world.addFreshEntity(modelYEntity);

            return InteractionResult.CONSUME;
        }
        return InteractionResult.SUCCESS;
    }
}
