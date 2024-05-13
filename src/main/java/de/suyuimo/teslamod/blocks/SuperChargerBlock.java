package de.suyuimo.teslamod.blocks;

import de.suyuimo.teslamod.entitys.ModelYEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import java.util.List;

public class SuperChargerBlock extends Block {

    public SuperChargerBlock(Properties properties) {
        super(properties);
    }

    @Override
    public void onPlace(BlockState state, Level world, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!world.isClientSide) {
            world.scheduleTick(pos, this, 20);  // Schedule a tick every second
        }
    }

    @Override
    public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random) {
        super.tick(state, world, pos, random);
        if (!world.isClientSide()) {
            chargeNearbyModelYEntities(world, pos);
            world.scheduleTick(pos, this, 20);  // Continue ticking
        }
    }

    private void chargeNearbyModelYEntities(ServerLevel world, BlockPos pos) {
        List<Boat> entities = world.getEntitiesOfClass(Boat.class, new AABB(pos.offset(-1, -1, -1), pos.offset(1, 2, 1)));
        for (Boat entity : entities) {
            if (entity instanceof ModelYEntity) {
                ModelYEntity modelY = (ModelYEntity) entity;
                modelY.receiveEnergy(100, false); // Charge the ModelYEntity, amount is just an example
            }
        }
    }
}
