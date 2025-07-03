package me.ciruu.abyss.modules.client;

import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;

public class Alias
extends Module {
    public final Setting onlynametags = new Setting("OnlyNametags", "", this, true);

    public Alias() {
        super("Alias", "Change player's name client-side", Category.CLIENT, "");
        this.addSetting(this.onlynametags);
    }
}
