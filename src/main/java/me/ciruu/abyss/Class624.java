package me.ciruu.abyss;

import me.ciruu.abyss.modules.Module;
import net.minecraft.client.Minecraft;

import static me.ciruu.abyss.Globals.mc;

public class Class624
extends Class163 {
    public Class624() {
        super("Bind", "Allows you to bind a mod to a key", "bind module key");
        Minecraft mc = null;
        mc.player.sendChatMessage("<module>");
        mc.player.sendChatMessage("<module> <key>");
    }

    public void Method3340(String string) {
        String[] stringArray = string.split("");
        if (stringArray == null || stringArray.length <= 1) {
            Minecraft mc = null;
            mc.player.sendChatMessage("Invalid Input");
            return;
        }
        Module module = Manager.moduleManager.getModuleByName(stringArray[1]);
        if (module != null) {
            if (stringArray.length <= 2) {
                mc.player.sendChatMessage(String.format("The key of %s is %s", module.moduleName(), module.getContext()));
                return;
            }
            module.moduleContext(stringArray[2].toUpperCase());
            mc.player.sendChatMessage(String.format("Set the key of %s to %s", module.moduleName(), module.getContext()));
        } else {
            mc.player.sendChatMessage(String.format("Could not find the module named %s", stringArray[1]));
        }
    }

    public String Method3343() {
        return "Allows you to Bind a mod";
    }


}
