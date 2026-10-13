package ru.diamondworld.chatprefix;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import com.mojang.blaze3d.platform.InputConstants;
import org.lwjgl.glfw.GLFW;

public final class ChatPrefixClient implements ClientModInitializer {
    public static boolean prefixEnabled = true;

    private static final KeyMapping TOGGLE_PREFIX_KEY = KeyMappingHelper.registerKeyMapping(
        new KeyMapping(
            "key.chat_prefix.toggle",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_F8,
            KeyMapping.Category.MISC
        )
    );

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            while (TOGGLE_PREFIX_KEY.consumeClick()) {
                prefixEnabled = !prefixEnabled;

            }
        });
    }
}
