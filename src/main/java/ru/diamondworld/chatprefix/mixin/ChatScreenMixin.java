package ru.diamondworld.chatprefix.mixin;

import net.minecraft.client.gui.screens.ChatScreen;
import ru.diamondworld.chatprefix.ChatPrefixClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(ChatScreen.class)
public abstract class ChatScreenMixin {
    private static final String PREFIX = "[#D31027-#6A0572] ";

    @ModifyVariable(
        method = "handleChatInput",
        at = @At("HEAD"),
        argsOnly = true,
        ordinal = 0
    )
    private String chatprefix$addPrefix(String message) {
        // When disabled, send every message exactly as typed.
        if (!ChatPrefixClient.prefixEnabled) {
            return message;
        }

        // Preserve server-specific chat syntax: ! = global chat (prefix after !),
        // @ and " = clan chat, $ and ; = trade chat, #hand/#рука = held-item tag.
        if (message == null
            || message.startsWith("/")
            || message.startsWith("\"")
            || message.startsWith("@")
            || message.startsWith("$")
            || message.startsWith(";")
            || message.startsWith("#hand")
            || message.startsWith("#рука")
            || message.startsWith(PREFIX)) {
            return message;
        }
        if (message.startsWith("!")) {
            return "!" + PREFIX + message.substring(1);
        }
        return PREFIX + message;
    }
}
