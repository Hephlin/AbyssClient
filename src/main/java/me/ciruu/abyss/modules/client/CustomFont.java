package me.ciruu.abyss.modules.client;

import java.awt.Font;
import java.util.function.Predicate;
import me.ciruu.abyss.Class139;
import me.ciruu.abyss.Class197;
import me.ciruu.abyss.Class198;
import me.ciruu.abyss.Class210;
import me.ciruu.abyss.Class25;
import me.ciruu.abyss.Class36;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.enums.Class363;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;

public class CustomFont
extends Module {
    private final Setting mainwindow = new Setting("Main", "", this, new Class25("Main Settings"));
    public final Setting newfontrenderer = new Setting("NewFontRenderer", "", (Module) this, (Object) true, this.mainwindow, true);
    private final Setting size = new Setting("Size", "", this, Float.valueOf(40.0f), Float.valueOf(5.0f), Float.valueOf(60.0f), this.mainwindow, this.newfontrenderer::getValue);
    private final Setting custom = new Setting("CustomServer", "", (Module) this, (Object) false, this.mainwindow, this.newfontrenderer::getValue);
    private final Setting font = new Setting("Font", "A New Font", (Module) this, (Object) "Arial", this.mainwindow, this::custom);
    private final Setting type = new Setting("Type", "A New Type", (Module) this, (Object) Class363.PLAIN, this.mainwindow, this::custom);
    private final Setting fontsize = new Setting("FontSize", "", this, Float.valueOf(17.0f), Float.valueOf(5.0f), Float.valueOf(25.0f), this.mainwindow, this::newfontrenderer);
    private final Setting antialias = new Setting("AntiAlias", "", (Module) this, (Object) true, this.mainwindow, this::newfontrenderer);
    private final Setting fractionalmetrics = new Setting("FractionalMetrics", "", (Module) this, (Object) true, this.mainwindow, this::newfontrenderer);
    public final Setting override = new Setting("Override", "", (Module) this, (Object) false, this.mainwindow, true);
    @EventHandler
    private Listener log = new Listener<Class139>(this::log, new Predicate[0]);
    @EventHandler
    private Listener listener = new Listener<Class210>(this::fontvalue, new Predicate[0]);

    public CustomFont() {
        super("CustomFont", "", Category.CLIENT, "");
        this.addSetting(this.newfontrenderer);
        this.addSetting(this.size);
        this.addSetting(this.custom);
        this.addSetting(this.font);
        this.addSetting(this.type);
        this.addSetting(this.fontsize);
        this.addSetting(this.antialias);
        this.addSetting(this.fractionalmetrics);
        this.addSetting(this.override);
    }

    public boolean getEnable() {
        super.getEnable();
        Class36.Method552(new Class197(Class36.Field458.deriveFont(((Float) this.fontsize.getValue()).floatValue()), (Boolean) this.antialias.getValue(), (Boolean) this.fractionalmetrics.getValue()));
        if (((Boolean) this.custom.getValue()).booleanValue()) {
            try {
                Class36.Method553(new Class198(new Font((String) this.font.getValue(), ((Class363) ((Object) this.type.getValue())).Field1206, ((Float) this.size.getValue()).intValue()).deriveFont(((Float) this.size.getValue()).floatValue())));
            } catch (Exception exception) {
                Class36.Method553(new Class198(Class36.Field458.deriveFont(((Float) this.size.getValue()).floatValue())));
            }
        } else {
            Class36.Method553(new Class198(new Font((String) this.font.getValue(), ((Class363) ((Object) this.type.getValue())).Field1206, ((Float) this.size.getValue()).intValue()).deriveFont(((Float) this.size.getValue()).floatValue())));
        }
        return false;
    }

    public boolean getRenderer() {
        if (((Boolean)this.newfontrenderer.getValue()).booleanValue()) {
            if (((Boolean)this.custom.getValue()).booleanValue()) {
                try {
                    Class36.Method553(new Class198(new Font((String)this.font.getValue(), ((Class363)((Object)this.type.getValue())).Field1206, ((Float)this.size.getValue()).intValue()).deriveFont(((Float)this.size.getValue()).floatValue())));
                }
                catch (Exception exception) {
                    Class36.Method553(new Class198(Class36.Field458.deriveFont(((Float)this.size.getValue()).floatValue())));
                }
            } else {
                Class36.Method553(new Class198(Class36.Field458.deriveFont(((Float)this.size.getValue()).floatValue())));
            }
        } else {
            Class36.Method552(new Class197(Class36.Field458.deriveFont(((Float)this.fontsize.getValue()).floatValue()), (Boolean)this.antialias.getValue(), (Boolean)this.fractionalmetrics.getValue()));
        }
        return getRenderer();
    }

    private void fontvalue(Class210 class210) {
        this.getRenderer();
    }

    private void log(Class139 class139) {
        if (class139.Method1564() == this) {
            this.getRenderer();
        }
        if (this.getRenderer())log.getPriority();
    }

    private boolean newfontrenderer() {
        return (Boolean)this.newfontrenderer.getValue() == false;
    }

    private boolean custom() {
        return (Boolean)this.newfontrenderer.getValue() != false && (Boolean)this.custom.getValue() != false;
    }
}
