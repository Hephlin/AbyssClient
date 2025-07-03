package me.ciruu.abyss;

import me.ciruu.abyss.modules.combat.AntiHoleCamp;

public class Class620
extends Class99 {
    public Class620(String string) {
        super(string);
    }

    public void Method3316(String[] stringArray) {
        if (stringArray[1].equalsIgnoreCase("camp")) {
            ((AntiHoleCamp)Manager.moduleManager.getModuleByClass(AntiHoleCamp.class)).onError();
        }
    }

    public boolean Method3317(String[] stringArray) {
        return stringArray[0].equalsIgnoreCase("anti");
    }
}
