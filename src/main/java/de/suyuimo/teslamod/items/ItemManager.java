package de.suyuimo.teslamod.items;

import de.suyuimo.teslamod.TeslaMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemManager {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TeslaMod.MODID);

    // Items erstellen
    public static final RegistryObject<Item> WHEEL = ITEMS.register("wheel",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> ELECTRICENGINE = ITEMS.register("electricengine",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> BODY = ITEMS.register("body",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> CHARGINGPORT = ITEMS.register("chargingport",
            () -> new Item(new Item.Properties()));



    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
