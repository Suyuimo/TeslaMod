package de.suyuimo.teslamod;

import com.mojang.logging.LogUtils;
import de.suyuimo.teslamod.blocks.BlockManager;
import de.suyuimo.teslamod.client.models.ModelYEntityModel;
import de.suyuimo.teslamod.client.renderers.ModelYRenderer;
import de.suyuimo.teslamod.entitys.EntityManager;
import de.suyuimo.teslamod.items.ItemManager;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
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
    public static final Boolean coding = true;
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);
    public static final ModelLayerLocation MODEL_Y_LAYER = new ModelLayerLocation(new ResourceLocation(MODID, "modely"), "main");


    public static final RegistryObject<CreativeModeTab> TESLA_TAB = CREATIVE_MODE_TABS.register("teslatab", () -> CreativeModeTab.builder()
            .withTabsBefore(CreativeModeTabs.COMBAT)
            .icon(() -> ItemManager.WHEEL.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ItemManager.WHEEL.get());
            }).build());



    public TeslaMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        CREATIVE_MODE_TABS.register(modEventBus);
        modEventBus.addListener(this::addCreative);
        ItemManager.register(modEventBus);
        EntityManager.register(modEventBus);
        BlockManager.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);


    }

    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        if (event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS)
            event.accept(ItemManager.WHEEL);
            event.accept(ItemManager.BATTERY);
            event.accept(ItemManager.BODY);
            event.accept(ItemManager.ELECTRICENGINE);
            event.accept(BlockManager.DOOR);
            event.accept(BlockManager.MODEL_Y_SPAWN_BLOCK);
            event.accept(BlockManager.SUPERCHARGER);
            event.accept(ItemManager.CARKEY);
    }

    @Mod.EventBusSubscriber(modid = TeslaMod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public class ClientModEvents {

        @SubscribeEvent
        public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
            //event.registerEntityRenderer(EntityManager.MODELY.get(), ModelYRendererTest::new);    // Rendert das Entity als Boot
        }

        @SubscribeEvent
        public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
            event.registerLayerDefinition(TeslaMod.MODEL_Y_LAYER, ModelYEntityModel::createBodyLayer);
        }

        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            EntityRenderers.register(EntityManager.MODELY.get(), context -> new ModelYRenderer(context));
        }

    }

}