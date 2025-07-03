package me.ciruu.abyss;

import java.util.ArrayList;
import java.util.List;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.settings.Setting;

public abstract class Class703 {
    private String string;
    private Category class11;
    private final List list = new ArrayList();

    public Class703(String string, Category class11) {
        this.string = string;
        this.class11 = class11;
    }

    public Class703(String antiHoleCamp, String s, Category class11) {
    }

    public Enum getEnum() {
        return this.class11;
    }

    public String getString() {
        return this.string;
    }

    public void getString(String string) {
        this.string = string;
    }

    public void getSetting(Setting setting) {
        this.list.add(setting);
    }

    public List getList() {
        return this.list;
    }
}
