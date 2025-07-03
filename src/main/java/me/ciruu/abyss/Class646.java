package me.ciruu.abyss;

import me.ciruu.abyss.modules.client.Client;

public class Class646
extends Class163 {
    public Class646() {
        super("help", "Shows the help message", "help");
    }

    public void Method4165(String string) {
        Class547.printChatMessage("Help page for Abyss:");
        for (Object class163_ : Manager.Field1639.addSetting33()) {
            Class163 class163 = (Class163) class163_;
            String string2 = (String)((Client)Manager.moduleManager.getModuleByClass(Client.class)).cmdprefix.getValue();
            String string3 = string2 + class163.addSetting32() + "" + class163.addSetting7() + " Usage:" + string2 + class163.Method4031();
            Class547.printChatMessage(string3);
        }
    }
}
