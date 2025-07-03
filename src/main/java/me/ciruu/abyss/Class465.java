package me.ciruu.abyss;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import me.ciruu.abyss.Class162;
import me.ciruu.abyss.Class163;
import me.ciruu.abyss.Class186;
import me.ciruu.abyss.Class214;
import me.ciruu.abyss.Class356;
import me.ciruu.abyss.Class484;
import me.ciruu.abyss.Class493;
import me.ciruu.abyss.Class541;
import me.ciruu.abyss.Class603;
import me.ciruu.abyss.Class624;
import me.ciruu.abyss.Class645;
import me.ciruu.abyss.Class646;

public class Class465 {
    private ArrayList Field2938 = new ArrayList();

    public void Method2023() {
        this.Field2938.add(new Class214());
        this.Field2938.add(new Class356());
        this.Field2938.add(new Class624());
        this.Field2938.add(new Class541());
        this.Field2938.add(new Class162());
        this.Field2938.add(new Class484());
        this.Field2938.add(new Class186());
        this.Field2938.add(new Class493());
        this.Field2938.add(new Class603());
        this.Field2938.add(new Class645());
        this.Field2938.add(new Class646());
        this.Field2938.sort(Comparator.comparing(Class163::addSetting32));
    }

    public final ArrayList addSetting33() {
        return this.Field2938;
    }

    public final List addSetting34(String string) {
        return Collections.singletonList(this.Field2938.stream().filter(arg_0 -> Class465.addSetting35(string, (Class163) arg_0)).collect(Collectors.toList()));
    }

    public Class163 addSetting36(String string) {
        for (Object class163_ : this.Field2938) {
            if (class163_ instanceof Class163) {
                Class163 class163 = (Class163) class163_;
                if (!class163.addSetting32().toLowerCase().startsWith(string.toLowerCase())) continue;
                return class163;
            }
        }
        return null;
    }

    public void addSetting37() {
        this.Field2938.clear();
        this.Method2023();
    }

    private static boolean addSetting35(String string, Class163 class163) {
        return class163.addSetting32().toLowerCase().startsWith(string.toLowerCase());
    }
}
