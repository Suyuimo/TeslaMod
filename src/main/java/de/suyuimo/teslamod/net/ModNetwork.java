package de.suyuimo.teslamod.net;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

public class ModNetwork {
    private static final String PROTOCOL_VERSION = "1";
    public static SimpleChannel INSTANCE;

    public static void registerNetworkChannel() {
        INSTANCE = NetworkRegistry.newSimpleChannel(
                new ResourceLocation("teslamod", "main"),
                () -> PROTOCOL_VERSION,
                PROTOCOL_VERSION::equals,
                PROTOCOL_VERSION::equals
        );

        // Register messages
        INSTANCE.registerMessage(0, EnergySyncMessage.class, EnergySyncMessage::encode, EnergySyncMessage::decode, EnergySyncMessage::handle);
    }
}
