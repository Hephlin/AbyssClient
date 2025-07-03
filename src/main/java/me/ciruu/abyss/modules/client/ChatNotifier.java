package me.ciruu.abyss.modules.client;

import me.ciruu.abyss.Globals;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;
import me.ciruu.abyss.util.Timer;

public class ChatNotifier
extends Module {

    public Setting autoQMain = new Setting("AutoQMain", "", this);
    public Timer timer;

    public ChatNotifier() {
        super("ChatNotifier", "Just a Queue Module!", Category.CLIENT, "");
    }

    public boolean getEnable() {
        onLoad();
        return false;
    }

    public void getDisable() {

    }

    public void onLoad() {
        timer.reset();
        if (timer.get10_0(10)) {
            if (((Boolean) this.autoQMain.getValue()).booleanValue())Globals.mc.player.
                    sendChatMessage("/queue main");
                timer.reset();
        }
    }
}
