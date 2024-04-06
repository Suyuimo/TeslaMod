package de.suyuimo.teslamod.blocks;


import de.suyuimo.teslamod.TeslaMod;
import de.suyuimo.teslamod.items.ItemManager;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockManager {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TeslaMod.MODID);

    // Blocks erstellen
    // Model Y Block
    public static final RegistryObject<Block> MODEL_Y_BLOCK = registerBlock("modelyblock",
            () -> new ModelYBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));



    private static <T extends Block> RegistryObject<T> registerBlock(String blockId, Supplier<T> block) {
        RegistryObject<T> blockToReturn = BLOCKS.register(blockId, block);
        registerBlockItem(blockId, blockToReturn);
        return blockToReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String blockId, RegistryObject<T> block) {
        return ItemManager.ITEMS.register(blockId,
                () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}