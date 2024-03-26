package de.suyuimo.teslamod.registers;

import de.suyuimo.teslamod.blocks.ModelYBlock;
import net.minecraft.block.Block;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

@Mod.EventBusSubscriber
public class RegisterBlocks {
    // Erstellen Sie eine öffentliche statische Instanz Ihres Blocks
    public static Block ModelYBlock;

    @SubscribeEvent
    public static void registerBlocks(RegistryEvent.Register<Block> event) {
        ModelYBlock = new ModelYBlock();
        event.getRegistry().register(ModelYBlock);
    }
}
