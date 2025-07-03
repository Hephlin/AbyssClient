package me.ciruu.abyss;

import com.mojang.realmsclient.gui.ChatFormatting;
import me.ciruu.abyss.Class163;
import net.minecraft.client.Minecraft;

public class Class541
extends Class163 {
    public Minecraft mc = Globals.mc;
    public Class541() {
        super("clip", "Allows you to clip certain blocks", "<h/v>clip distance");
        mc.player.sendChatMessage("hclip <distance>");
        mc.player.sendChatMessage("vclip <distance>");
    }

    public void Method2497(String string) {
        String[] stringArray = string.split("");
        if (stringArray == null || stringArray.length <= 1) {
            mc.player.sendChatMessage("Invalid Input");
            return;
        }
        if (stringArray[1].toLowerCase().startsWith("h")) {
            if (stringArray.length > 1) {
                try {
                    int n = Integer.parseInt(stringArray[2]);
                    double d = Math.cos(Math.toRadians(mc.player.rotationYaw + 90.0f));
                    double d2 = Math.sin(Math.toRadians(mc.player.rotationYaw + 90.0f));
                    mc.player.setPosition(mc.player.posX + (1.0 * (double)n * d + 0.0 * (double)n * d2), mc.player.posY, mc.player.posZ + (1.0 * (double)n * d2 - 0.0 * (double)n * d));
                    if (mc.player.isRiding()) {
                        mc.player.getRidingEntity().setPosition(mc.player.getRidingEntity().posX + (1.0 * (double)n * d + 0.0 * (double)n * d2), mc.player.getRidingEntity().posY, mc.player.getRidingEntity().posZ + (1.0 * (double)n * d2 - 0.0 * (double)n * d));
                    }
                }
                catch (Exception exception) {
                    mc.player.sendChatMessage(ChatFormatting.RED + "INVALID COMMAND");
                }
                return;
            }
            mc.player.sendChatMessage("hclip <distance>");
            return;
        }
        if (stringArray[1].toLowerCase().startsWith("v")) {
            if (stringArray.length > 1) {
                try {
                    int n = Integer.parseInt(stringArray[2]);
                    mc.player.setPosition(mc.player.posX, mc.player.posY + (double)n, mc.player.posZ);
                    if (mc.player.isRiding()) {
                        mc.player.getRidingEntity().setPosition(mc.player.getRidingEntity().posX, mc.player.getRidingEntity().posY + (double)n, mc.player.getRidingEntity().posZ);
                    }
                }
                catch (Exception exception) {
                    mc.player.sendChatMessage(ChatFormatting.RED + "INVALID COMMAND");
                }
            } else {
                mc.player.sendChatMessage("Usage: vclip <distance>");
                return;
            }
        }
    }
}
