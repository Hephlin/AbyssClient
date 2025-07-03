package me.ciruu.abyss;

import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.lwjgl.opengl.Display;

public class Class564
extends Module {
    private final Setting Field2204 = new Setting("DefaultFPS", "", (Module)this, (Object)60, 1, 1000);
    private final Setting Field2205 = new Setting("WordLoadingFPS", "", (Module)this, (Object)0, 0, 1000);
    private final Setting Field2206 = new Setting("MenuFPS", "", (Module)this, (Object)60, 0, 1000);
    private final Setting Field2207 = new Setting("NoFocusFPS", "", (Module)this, (Object)3, 0, 1000);

    public Class564() {
        super("FPSLock", "", Category.EXPLOIT, "");
        this.addSetting(this.Field2204);
        this.addSetting(this.Field2205);
        this.addSetting(this.Field2206);
        this.addSetting(this.Field2207);
    }

    private int Method2709() {
        if ((Integer)this.Field2207.getValue() > 0 && !Display.isActive()) {
            return (Integer)this.Field2207.getValue();
        }
        if (Globals.mc.currentScreen != null) {
            return (Integer)this.Field2206.getValue() > 0 ? ((Integer)this.Field2206.getValue()).intValue() : ((Integer)this.Field2204.getValue()).intValue();
        }
        return (Integer)this.Field2205.getValue() > 0 ? ((Integer)this.Field2205.getValue()).intValue() : ((Integer)this.Field2204.getValue()).intValue();
    }

    public void Method2710() {
        super.getEnable();
        MinecraftForge.EVENT_BUS.register((Object)this);
    }

    public void Method2711() {
        super.getDisable();
        MinecraftForge.EVENT_BUS.unregister((Object)this);
        Globals.mc.gameSettings.limitFramerate = (Integer)this.Field2204.getValue();
    }

    @SubscribeEvent
    void Method2712(TickEvent.ClientTickEvent clientTickEvent) {
        switch (clientTickEvent.phase) {
            case START: {
                Globals.mc.gameSettings.limitFramerate = this.Method2709();
                break;
            }
        }
    }
}
