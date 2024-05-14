package de.suyuimo.teslamod.net;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class EnergySyncMessage {
    private final int energy;

    public EnergySyncMessage(int energy) {
        this.energy = energy;
    }

    public static void encode(EnergySyncMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.energy);
    }

    public static EnergySyncMessage decode(FriendlyByteBuf buffer) {
        return new EnergySyncMessage(buffer.readInt());
    }

    public static void handle(EnergySyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            // Handle message on the receiving side
            // For example, update the energy storage of a vehicle if applicable
        });
        context.setPacketHandled(true);
    }
}
