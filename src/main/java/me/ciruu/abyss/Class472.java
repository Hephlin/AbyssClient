package me.ciruu.abyss;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import me.ciruu.abyss.enums.Class535;
import me.ciruu.abyss.modules.client.VoiceAssistant;

public class Class472 {
    private List Field3355 = new ArrayList();
    private String[] Field3356;

    public void Method2019() {
        this.Field3355.add(new Class457("friend"));
        this.Field3355.add(new Class527("module"));
        this.Field3355.add(new Class620(""));
        this.Field3355.add(new Class98("open"));
        this.Field3355.add(new Class555("what"));
    }

    public void Method2165(String string) {
        String string2 = string.replaceAll("[\\[\\]<>()|+*]", "");
        String[] stringArray = string2.split("");
        boolean bl = true;
        for (String string3 : stringArray) {
            if (string3 instanceof String) {
                if (string3.matches(" *") || Manager.Field255.Method257().isWord(string3)) continue;
                bl = false;
            }
        }
        if (!bl) {
            return;
        }
        this.Field3356 = ((VoiceAssistant)Manager.moduleManager.getModuleByClass(VoiceAssistant.class)).mode.getValue() == Class535.Strict ? Arrays.copyOfRange(stringArray, 1, stringArray.length) : stringArray;
        if (((Boolean)((VoiceAssistant)Manager.moduleManager.getModuleByClass(VoiceAssistant.class)).debug.getValue()).booleanValue()) {
            Class547.printChatMessage(stringArray.length + " elements");
        }
        for (Object class99_ : this.Field3355) {
            if (class99_ instanceof Class390) {
                Class99 class99 = (Class99) class99_;
                if (!class99.Method3349(this.Field3356)) continue;
                class99.Method3346(this.Field3356);
                break;
            }
        }
    }

    private Queue addSetting(Queue queue) {
        ArrayList arrayList = new ArrayList(queue);
        Collections.reverse(arrayList);
        return new LinkedList(arrayList);
    }
}
