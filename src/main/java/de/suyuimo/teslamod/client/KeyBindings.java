package de.suyuimo.teslamod.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeyBindings {

    public static final String CATEGORY = "de.suyuimo.teslamod.keys";
    public static final KeyMapping BOOST_KEY = new KeyMapping("key.teslamod.carboost", InputConstants.Type.KEYSYM, InputConstants.KEY_R, CATEGORY);

    @SubscribeEvent
    public static void onKeyMapping(RegisterKeyMappingsEvent event) {
        event.register(BOOST_KEY);
    }
}
