package me.ciruu.abyss;

import com.mojang.realmsclient.gui.ChatFormatting;
import net.minecraft.client.Minecraft;

public class Class493
extends Class163 {
    public Minecraft mc = Globals.mc;
    public Class493() {
        super("Item", "List with items for InventoryInteract", "");
    }

    public void Method2140(String string) {
        String[] stringArray = string.split("");
        if (stringArray == null || stringArray.length <= 1) {
            mc.player.sendChatMessage("Invalid Input");
            return;
        }
        if (stringArray[1] != null && stringArray[2] != null) {
            if (stringArray[1].toLowerCase().startsWith("w")) {
                if (stringArray[2].toLowerCase().startsWith("a")) {
                    Manager.Field1255.Method2142(stringArray[3], false);
                } else if (stringArray[2].toLowerCase().startsWith("d")) {
                    Manager.Field1255.Method2143(stringArray[3], false);
                } else if (stringArray[2].toLowerCase().startsWith("s")) {
                    Manager.Field1255.Method2144(false);
                } else {
                    Class547.printChatMessage(ChatFormatting.RED + "Invalid action!");
                }
            } else if (stringArray[1].toLowerCase().startsWith("b")) {
                if (stringArray[2].toLowerCase().startsWith("a")) {
                    Manager.Field1255.Method2142(stringArray[3], true);
                } else if (stringArray[2].toLowerCase().startsWith("d")) {
                    Manager.Field1255.Method2143(stringArray[3], true);
                } else if (stringArray[2].toLowerCase().startsWith("s")) {
                    Manager.Field1255.Method2144(true);
                } else {
                    Class547.printChatMessage(ChatFormatting.RED + "Invalid action!");
                }
            } else {
                Class547.printChatMessage(ChatFormatting.RED + "Invalid mode!");
            }
        } else {
            Class547.printChatMessage(ChatFormatting.RED + "Invalid arguments!");
        }
    }
}
