package me.ciruu.abyss;

import java.awt.Color;
import java.util.function.Predicate;

import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.enums.Class171;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.util.math.MathHelper;

public class Class172
extends Module {
    private final Setting Field2739 = new Setting("Scale", "", (Module)this, (Object)Float.valueOf(3.0f), Float.valueOf(0.1f), Float.valueOf(20.0f));
    private static final double Field2740 = 1.5707963267948966;
    @EventHandler
    private Listener Field2741 = new Listener<Class35>(this::addSetting0, new Predicate[0]);

    public Class172() {
        super("Compass", "", Category.HUD, "");
        this.addSetting(this.Field2739);
    }

    private double addSetting(double d) {
        return Math.sin(d) * (double)(((Float)this.Field2739.getValue()).floatValue() * 10.0f);
    }

    private double addSetting3(double d) {
        double d2 = MathHelper.clamp((float)(Globals.mc.getRenderViewEntity().rotationPitch + 30.0f), (float)-90.0f, (float)90.0f);
        double d3 = Math.toRadians(d2);
        return Math.cos(d) * Math.sin(d3) * (double)(((Float)this.Field2739.getValue()).floatValue() * 10.0f);
    }

    private static double addSetting4(Class171 class171) {
        double d = Math.toRadians(MathHelper.wrapDegrees((float)Globals.mc.getRenderViewEntity().rotationYaw));
        int n = class171.ordinal();
        return d + (double)n * 1.5707963267948966;
    }

    private void addSetting0(Class35 class35) {
        if (Globals.mc.getRenderViewEntity() == null) {
            return;
        }
        double d = class35.addSetting5() / 2.0;
        double d2 = class35.addSetting6() * 0.8;
        for (Class171 class171 : Class171.values()) {
            if (class171 instanceof Class171) {
                double d3 = Class172.addSetting4(class171);
                Class36.Method557(class171.name(), (float) (d + this.addSetting(d3)), (float) (d2 + this.addSetting3(d3)), class171 == Class171.N ? Color.RED.getRGB() : Color.WHITE.getRGB());
            }
        }
    }
}
