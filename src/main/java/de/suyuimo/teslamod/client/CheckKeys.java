package de.suyuimo.teslamod.client;

import de.suyuimo.teslamod.entitys.ModelYEntity;
import net.minecraft.client.Minecraft;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class CheckKeys {

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.START) {
            checkBoostKey();
        }
    }

    private static void checkBoostKey() {
        if (KeyBindings.BOOST_KEY.isDown()) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player != null && mc.player.getVehicle() instanceof ModelYEntity) {
                if (((ModelYEntity) mc.player.getVehicle()).getBoosting() == false) {
                    ((ModelYEntity) mc.player.getVehicle()).setBoosting(true);
                } else {
                    ((ModelYEntity) mc.player.getVehicle()).setBoosting(false);
                }
            }
        }
    }
}
