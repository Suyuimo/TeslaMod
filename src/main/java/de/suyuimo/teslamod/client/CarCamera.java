package de.suyuimo.teslamod.client;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

    @Mod.EventBusSubscriber(value = Dist.CLIENT)
    public class CarCamera {

        private static boolean wasRiding = false;

        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            Minecraft mc = Minecraft.getInstance();
            if (mc.player == null) return;

            // Überprüfe, ob der Spieler gerade ein Entity reitet
            if (mc.player.isPassenger() && !wasRiding) {
                // Ändere die Kameraperspektive
                changeCameraPerspective(mc);
                wasRiding = true;
            } else if (!mc.player.isPassenger() && wasRiding) {
                // Setze die Perspektive zurück oder auf eine andere gewünschte Perspektive
                changeCameraPerspectiveBack(mc);
                wasRiding = false;
            }
        }

        private static void changeCameraPerspective(Minecraft mc) {
            mc.options.setCameraType(mc.options.getCameraType().cycle());
        }

        private static void changeCameraPerspectiveBack(Minecraft mc) {
            mc.options.setCameraType(mc.options.getCameraType().cycle().cycle());
        }
    }
