package me.ciruu.abyss;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;

public class Class163 {
    private final String Field3365;
    private final String Field3366;
    private String Field3367 = "";
    protected final Minecraft Field3368 = Globals.mc;
    protected final List Field3369 = new ArrayList();
    public static String Field3370 = "*";

    public Class163(String string, String string2) {
        this.Field3365 = string;
        this.Field3366 = string2;
    }

    public Class163(String string, String string2, String string3) {
        this.Field3365 = string;
        this.Field3366 = string2;
        this.Field3367 = string3;
    }

    public String addSetting32() {
        return this.Field3365;
    }

    public String addSetting7() {
        return this.Field3366;
    }

    public void addSetting8(String string) {
    }

    protected void addSetting9(String string) {
        Class547.printChatMessage(String.format("%s[%s]: %s", ChatFormatting.AQUA, this.addSetting32(), ChatFormatting.YELLOW + string));
    }

    public List Method4030() {
        return this.Field3369;
    }

    public String Method4031() {
        return this.Field3367;
    }
}
