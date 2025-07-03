package me.ciruu.abyss.modules.client;

import me.ciruu.abyss.Globals;
import me.ciruu.abyss.Manager;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;
import net.minecraft.client.gui.GuiScreen;

public class BubbleGUI
extends Module {
    public final Setting animation = new Setting("Animation", "", this, true);

    public BubbleGUI() {
        super("BubbleGUI", "Bubble GUI (Not SAO)", Category.CLIENT, "");
        this.addSetting(this.animation);
    }

    public boolean moduleEnabled() {
        if (Module.isEnabled()) {
            this.getEnable();
        }
        return (boolean) this.isEnabled(true);
    }

    public void getDisable() {
        this.endScreen();
    }


    public boolean getEnable() {
        super.getEnable();
        Globals.mc.displayGuiScreen((GuiScreen) Manager.Field280);
        return false;
    }

    public void endScreen() {
        super.getDisable();
        Globals.mc.displayGuiScreen((GuiScreen) Manager.Field280);
    }
}
