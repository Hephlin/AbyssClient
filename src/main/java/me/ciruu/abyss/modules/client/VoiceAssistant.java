package me.ciruu.abyss.modules.client;

import java.util.function.Predicate;
import me.ciruu.abyss.Class207;
import me.ciruu.abyss.Class26;
import me.ciruu.abyss.Manager;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.enums.Class535;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class VoiceAssistant
extends Module {
    public final Setting mode = new Setting("Mode", "", this, (Object)Class535.NonStrict);
    public final Setting pushToTalkKey = new Setting("PushToTalkKey", "", this, new Class207(0));
    public final Setting tickBuffer = new Setting("TickBuffer", "Minecraft ticks of the recording buffer", (Module)this, (Object)20, 1, 200);
    public final Setting debug = new Setting("Debug", "", this, false);
    @EventHandler
    private Listener listen1 = new Listener<Class26>(VoiceAssistant::getStatus, new Predicate[0]);

    public VoiceAssistant() {
        super("VoiceAssistant", "", Category.CLIENT, "");
        this.addSetting(this.debug);
        this.addSetting(this.pushToTalkKey);
        this.addSetting(this.tickBuffer);
    }

    public boolean getEnable() {
        super.getEnable();
        try {
            if (isEnabled() == true) {
                Manager.Field255.Method257().setEnabled(true);
            } else if (isEnabled() == false) {
                System.out.println("error: isEnabled() == false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public void getDisable() {
        super.getDisable();
        try {
            if (isEnabled() == true) {
                Manager.Field255.Method257().setEnabled(true);
            } else if (isEnabled() == false) {
                System.out.println("error: isEnabled() == false");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void getStatus(Class26 class26) {
        Manager.Field255.Method2164();
    }
}
