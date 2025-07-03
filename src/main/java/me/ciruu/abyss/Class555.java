package me.ciruu.abyss;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.time.LocalDateTime;

public class Class555
extends Class99 {
    private String Field2139 = ChatFormatting.AQUA + "AbyssClient VoiceAssistant:" + ChatFormatting.RESET;

    public Class555(String string) {
        super(string);
    }

    public void Method2617(String[] stringArray) {
        if (stringArray[1].equalsIgnoreCase("time")) {
            Class547.printChatMessage(this.Field2139 + "It's" + LocalDateTime.now().getHour() + ":" + LocalDateTime.now().getMinute());
        } else if (stringArray[1].equalsIgnoreCase("version")) {
            Class547.printChatMessage(this.Field2139 + "You are using Abyss" + "1.6.0");
        }
    }

    public boolean Method2618(String[] stringArray) {
        return stringArray[0].equalsIgnoreCase("what");
    }
}
