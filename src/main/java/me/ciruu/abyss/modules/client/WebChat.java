package me.ciruu.abyss.modules.client;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.time.Instant;
import java.util.*;

import me.ciruu.abyss.*;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.enums.Class184;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class WebChat
extends Module {
    private final Setting openchat = new Setting("OpenChat", "", this, new Class207(0));
    private final Setting sound = new Setting("Sound", "", this, true);
    private final Setting color = new Setting("Color", "", this, (Object) Class184.WHITE);

    @EventHandler
    private Listener listen1;

    @EventHandler
    private Listener listen2;

    public WebChat() {
        super("WebChat", "", Category.CLIENT, "");
        this.getWebChat();
        this.addSetting(this.openchat);
        this.addSetting(this.sound);
        this.addSetting(this.color);
    }

    public boolean getEnable() {
        this.getWebChat();
        super.getEnable();
        return false;
    }

    public void getDisable() {
        super.getDisable();
    }

    public static final Setting openchat(WebChat webChat) {
        return webChat.openchat;
    }

    public final void getWebChat(@NotNull String string, @NotNull String string2, @Nullable Instant instant) {
    }

    public final void clearMembers(@NotNull List list, @NotNull List list2) {
    }

    public final void getDisconnected() {
        Class547.printNewChatMessage(
                Class636.Method3480(
                        Class636.Method3479(
                                Class636.Method3480(
                                        Class636.Method3479(
                                                Class636.Method3482(), ChatFormatting.GOLD),
                                        null),  ChatFormatting.RED ), " Disconnected").toString());
    }

    @NotNull
    public final Map getUpdate() {
        return getUpdate();
    }

    public void getWebChat() {
        System.out.println(ChatFormatting.GOLD + " WebChat Enabled!");
    }

    public final void Method3485(@NotNull String string) {
        Class636.Method3485(null, string);
    }

    public final void Method3486(@NotNull String string) {
        Class636.Method3486(null, string);
    }

}
