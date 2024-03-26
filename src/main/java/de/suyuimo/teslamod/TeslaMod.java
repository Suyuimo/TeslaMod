package de.suyuimo.teslamod;

import de.suyuimo.teslamod.blocks.ModelYBlock;
import de.suyuimo.teslamod.client.renderer.entity.RenderModelY;
import de.suyuimo.teslamod.entitys.ModelY;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

@Mod(modid = TeslaMod.MODID, version = TeslaMod.VERSION)
public class TeslaMod {
    public static final String MODID = "teslamod";
    public static final String NAME = "Tesla Mod";
    public static final String VERSION = "1.0";

    // Define the custom block
    public static Block ModelYBlock;


    @Mod.Instance
    public static TeslaMod instance;

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
       //  Entity-Registrierung
        EntityRegistry.registerModEntity(new ResourceLocation("teslamod:modely"), ModelY.class, "modely", 1, TeslaMod.instance, 80, 3, true);

       //  Renderer-Registrierung (nur auf dem Client)
        if (event.getSide() == Side.CLIENT) {
            RenderingRegistry.registerEntityRenderingHandler(ModelY.class, RenderModelY::new);

        }
    }

        @EventHandler
        public void init (FMLInitializationEvent event){

        }
    }