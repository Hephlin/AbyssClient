package me.ciruu.abyss;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;

public class Class484
extends Class163 {
    public Minecraft mc = Globals.mc;
    public Class484() {
        super("Profile", "Allows you to switch profiles", "profile <load/create> name");
        mc.player.sendChatMessage("<load> <name>");
        mc.player.sendChatMessage("<create> <name>");
    }

    public void Method2114(String string) {
        String[] stringArray = string.split("");
        if (stringArray == null || stringArray.length <= 1) {
            mc.player.sendChatMessage("Invalid Input");
            return;
        }
        String string2 = stringArray[1];
        if (string2 != null) {
            if (string2.equalsIgnoreCase("reload")) {
                Class208.Method2116();
                return;
            }
            if (string2.equalsIgnoreCase("list")) {
                Class208.Method2117();
                return;
            }
            String string3 = stringArray[2];
            if (string3 != null) {
                if (string2.equalsIgnoreCase("load")) {
                    Class208.Method2118(string3);
                    return;
                }
                if (string2.equalsIgnoreCase("add")) {
                    Class208.mkDirs(string3);
                    return;
                }
                if (string2.equalsIgnoreCase("del")) {
                    Class208.Method2120(string3);
                    return;
                }
                Class547.printChatMessage(ChatFormatting.RED + "Invalid name!");
            } else {
                Class547.printChatMessage(ChatFormatting.RED + "Invalid name!");
            }
        } else {
            Class547.printChatMessage(ChatFormatting.RED + "Invalid action");
        }
    }
}
