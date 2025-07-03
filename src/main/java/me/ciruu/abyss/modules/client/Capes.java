package me.ciruu.abyss.modules.client;

import me.ciruu.abyss.Manager;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;

public class Capes
extends Module {
    private final Setting animated = new Setting("Animated", "Enables animations on capes", this, true);
    private final Setting delay = new Setting("Delay", "Delay for the animation in ticks", (Module)this, (Object)20, 1, 200);
    private final Setting preload = new Setting("Preload at startup", "Load the capes when starting the game", this, false);
    private final Setting reload = new Setting("Reload the GUI", "", (Module)this, false);
    private float animtime = 0.0f;

    public Capes() {
        super("Capes", "Enables abyss capes on the client", Category.CLIENT, "");
        this.addSetting(this.animated);
        this.addSetting(this.delay);
        this.addSetting(this.reload);
    }

    public boolean getEnable() {
        super.getEnable();
        if (this.animated.getValue()) {
            this.animtime();
            this.animated();
        }
        if (this.preload.getValue() !=null) {
            preload();
        }
        if (this.reload.getValue())reloadCapes();
        return false;
    }

    public void getDisable() {
        this.moduleDisabled();
    }

    public void reloadCapes() {
        Manager.capeManager.reload();
    }

    public void animtime() {
        this.animtime += 20.0f / (float)((Integer)this.delay.getValue()).intValue();
    }

    public boolean preload() {
        return (Boolean)this.preload.getValue();
    }

    public float animated() {
        return (Boolean)this.animated.getValue() != false ? this.animtime : 0.0f;
    }
}
