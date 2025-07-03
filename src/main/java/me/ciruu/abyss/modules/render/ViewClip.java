package me.ciruu.abyss.modules.render;

import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;

public class ViewClip
extends Module {
    public final Setting distance = new Setting("Distance", "", (Module)this, (Object)Float.valueOf(3.5f), Float.valueOf(0.0f), Float.valueOf(10.0f));

    public ViewClip() {
        super("ViewClip", "Change the distance in third person.", Category.RENDER, "");
        this.addSetting(this.distance);
    }
}
