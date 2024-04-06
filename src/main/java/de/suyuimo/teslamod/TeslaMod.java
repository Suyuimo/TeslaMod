package de.suyuimo.teslamod;

import com.mojang.logging.LogUtils;
import de.suyuimo.teslamod.entitys.EntityManager;
import de.suyuimo.teslamod.items.ItemManager;
import net.minecraft.client.Minecraft;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

@Mod(TeslaMod.MODID)
public class TeslaMod {


    public static final String MODID = "teslamod";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final RegistryObject<CreativeModeTab> TESLA_TAB = CREATIVE_MODE_TABS.register("teslatab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ItemManager.RAD.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ItemManager.RAD.get()); // Add the example item to the tab. For your own tabs, this method is preferred over the event
            }).build());



    public TeslaMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        CREATIVE_MODE_TABS.register(modEventBus);
        modEventBus.addListener(this::addCreative);
        ItemManager.register(modEventBus);
        EntityManager.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);


    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(ItemManager.RAD);
    }

}