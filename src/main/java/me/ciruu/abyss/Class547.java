package me.ciruu.abyss;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mojang.realmsclient.gui.ChatFormatting;
import kotlin.collections.CollectionsKt;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.modules.client.ClickGUI;
import me.ciruu.abyss.modules.hud.ChatWatermark;
import me.ciruu.abyss.settings.Setting;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.event.HoverEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Class547
implements Class70 {
    @NotNull
    private ArrayList Field2596;
    private boolean Field2597;
    private int Field2598;
    private int Field2599;
    private int Field2600;
    private int Field2601;
    private boolean Field2602;
    @Nullable
    private Setting Field2603;
    private final int Field2604;
    private boolean Field2605;
    private int Field2606;
    private int Field2607;
    @NotNull
    private Color Field2608;
    @NotNull
    private Category Field2609;

    public static int getScaleFactor() {
        int n = 0;
        int n2 = Globals.mc.gameSettings.guiScale;
        if (n2 == 0) {
            n2 = 1000;
        }
        while (n < n2 && Globals.mc.displayWidth / (n + 1) >= 320 && Globals.mc.displayHeight / (n + 1) >= 240) {
            ++n;
        }
        if (n == 0) {
            n = 1;
        }
        return n;
    }

    public static void printChatMessage(String string) {
        if (Globals.mc.ingameGUI != null || Globals.mc.player != null) {
            String string2 = Manager.moduleManager.isModuleEnabled(ChatWatermark.class) ? "?+[Abyss]" + ChatFormatting.WHITE + string : ChatFormatting.DARK_PURPLE + "[Abyss]" + ChatFormatting.WHITE + string;
            Globals.mc.ingameGUI.getChatGUI().printChatMessage((ITextComponent)new TextComponentString(string2));
        }
    }

    public static void printNewChatMessage(String string) {
        if (Globals.mc.player != null) {
            String string2 = Manager.moduleManager.isModuleEnabled(ChatWatermark.class) ? "?+[Abyss]" + string : ChatFormatting.DARK_PURPLE + "[Abyss]" + string;
            ITextComponent iTextComponent = new TextComponentString(string2).setStyle(new Style().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, (ITextComponent)new TextComponentString("You found biggest Abyss secret ;)"))));
            Globals.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion(iTextComponent, 5936);
        }
    }

    @NotNull
    public final ArrayList Method3098() {
        return this.Field2596;
    }

    public final void Method3099(@NotNull ArrayList arrayList) {
        this.Field2596 = arrayList;
    }

    public final boolean Method3100() {
        return this.Field2597;
    }

    public final void Method3101(boolean bl) {
        this.Field2597 = bl;
    }

    @Nullable
    public Class70 Method3102() {
        return null;
    }

    public int Method2567() {
        return this.Field2598;
    }

    public void Method3103(int n) {
        this.Field2598 = n;
    }

    public int Method3104() {
        return this.Field2599;
    }

    public void Method3105(int n) {
        this.Field2599 = n;
    }

    public int Method3106() {
        return this.Field2600;
    }

    public void Method3107(int n) {
        this.Field2600 = n;
    }

    public int Method3108() {
        return this.Field2601;
    }

    public void Method3109(int n) {
        this.Field2601 = n;
    }

    public boolean Method3110() {
        return this.Field2602;
    }

    public void Method3111(boolean bl) {
        this.Field2602 = bl;
    }

    @Nullable
    public Setting Method3112() {
        return this.Field2603;
    }

    public final int Method3113() {
        return this.Field2606;
    }

    public final void Method3114(int n) {
        this.Field2606 = n;
    }

    public final int Method3115() {
        return this.Field2607;
    }

    public final void Method3116(int n) {
        this.Field2607 = n;
    }

    @NotNull
    public final Color Method3117() {
        return this.Field2608;
    }

    public final void Method3118(@NotNull Color color) {
        this.Field2608 = color;
    }

    public final void Method3134(boolean bl) {
        this.Field2605 = bl;
    }

    public final void Method3135(int n, int n2) {
        if (this.Field2605) {
            this.Method3107(n - this.Field2606);
            this.Method3105(n2 - this.Field2607);
        }
    }

    public final void Method3119() {
        Class50.Method764(this.Method3106(), this.Method3104(), this.Method2567(), this.Field2604, Class74.Field172.Method165().getRGB());
        Class36.Method557(this.Field2609.name(), this.Method3106() + this.Method2567() / 2, this.Method3104() + 6, Class74.Field172.Method171().getRGB());

        if (this.Field2597) {
            if (!this.Field2596.isEmpty()) {
                List<Integer> collection2 = new ArrayList<>();
                for (Object class71_ : this.Field2596) {
                    if (class71_ instanceof Class71) {
                        Class71 class71 = (Class71) class71_;
                        collection2.add(class71.Method2568());
                    }
                }

                Integer n5 = collection2.stream().max(Integer::compareTo).orElse(0);
                int n;
                if (!((Boolean) ((ClickGUI) Manager.moduleManager.getModuleByClass(ClickGUI.class)).autowidth.getValue())) {
                    n = ((Number) ((ClickGUI) Manager.moduleManager.getModuleByClass(ClickGUI.class)).width.getValue()).intValue();
                } else {
                    n = Math.max(88, n5);
                }

                this.Method3103(n);

                int n8 = 16;
                int n2 = 1;
                for (Object class71_ : this.Field2596) {
                    if (class71_ instanceof Class71) {
                        Class71 class71 = (Class71) class71_;
                        class71.Method2549(n8);
                        n8 += class71.Method2553();
                        class71.Method2576(this.Field2608);
                        class71.Method2578(this.Method3133(n2 * 300));
                        if (n2 == 1) {
                            class71.Method2576(class71.Method2577());
                        }
                        this.Field2608 = class71.Method2577();
                        class71.Method2589();
                        ++n2;
                    }
                }
            }
        }
    }


    public final boolean Method3136(int n, int n2) {
        return n >= this.Method3106() && n <= this.Method3106() + this.Method2567() && n2 >= this.Method3104() && n2 <= this.Method3104() + this.Field2604;
    }

    public final void Method2601() {
        int n = this.Field2604;
        for (Object class71_ : this.Field2596) {
            if (class71_ instanceof Class71) {
                Class71 class71 = (Class71) class71_;
                class71.Method2612(n);
                n += class71.Method2553();
            }
        }
        this.Method3109(n);
    }

    @NotNull
    public final Color Method3133(int n) {
        Float f = (Float)((ClickGUI)Manager.moduleManager.getModuleByClass(ClickGUI.class)).rsatenabled.getValue();
        Float f2 = (Float)((ClickGUI)Manager.moduleManager.getModuleByClass(ClickGUI.class)).rbrightenabled.getValue();
        double d = Math.ceil((double)(System.currentTimeMillis() + (long)n) / (double)(((Number)((ClickGUI)Manager.moduleManager.getModuleByClass(ClickGUI.class)).enabledoffset.getValue()).floatValue() * (float)2 + (float)2));
        return Color.getHSBColor((float)((d %= 360.0) / (double)360.0f), f.floatValue(), f2.floatValue());
    }

    @NotNull
    public final Category Method3142() {
        return this.Field2609;
    }

    public final void Method3143(@NotNull Category class11) {
        this.Field2609 = class11;
    }

    public Class547(@NotNull Category category) {
        this.Field2609 = category;
        this.Field2596 = new ArrayList<>();
        this.Field2598 = 88;
        this.Field2599 = 5;
        this.Field2600 = 5;
        this.Field2608 = Class74.Field172.Method413();
        this.Field2604 = 16;
        this.Field2606 = 0;
        this.Field2597 = true;
        this.Field2605 = false;

        Collection<?> moduleValues = Manager.moduleManager.getModules().values();
        List<Module> modules = new ArrayList<>();

        if (moduleValues == null || moduleValues.isEmpty()) {
            System.err.println("[Class547] No modules found in ModuleManager.");
        } else {
            for (Object obj : moduleValues) {
                if (obj instanceof Module) {
                    modules.add((Module) obj);
                }
            }
        }

        List<Object> sortedModules;
        try {
            sortedModules = CollectionsKt.sortedWith(modules, new Class220());
        } catch (Exception e) {
            e.printStackTrace();
            sortedModules = new ArrayList<>(modules); // fallback
        }

        for (Object mod : sortedModules) {
            if (mod instanceof Module) {
                Module module = (Module) mod;
                if (module.getCategory() == this.Field2609) {
                    Class71 element = new Class71(module, this);
                    this.Field2596.add(element);
                }
            }
        }

        List<Integer> widths = new ArrayList<>();
        for (Object el : this.Field2596) {
            if (el instanceof ArrayList) {
                if (el instanceof Class71) {
                    widths.add(((Class71) el).Method2568());
                }
            }
        }

        Integer maxWidth = widths.stream().max(Integer::compareTo).orElse(0);
        int computedWidth = Math.max(88, maxWidth);
        System.out.println("[Class547] Computed Width: " + computedWidth);

        this.Method3103(computedWidth);
        this.Method2601();
    }



    public int Method3150() {
        return Class75.Method183(this);
    }

    public int Method3151() {
        return Class75.Method184(this);
    }

    public int Method3152() {
        return Class75.Method185(this);
    }

    public void Method3153() {
        Class75.Method2713(this);
    }

    public void Method3154(int n, int n2) {
        Class75.Method433(this, n, n2);
    }

    public void Method3155(int n, int n2, int n3) {
        Class75.Method1618(this, n, n2, n3);
    }

    public void Method3156(int n, int n2, int n3) {
        Class75.Method187(this, n, n2, n3);
    }

    public void Method3157(int n, int n2, int n3, long l) {
        Class75.Method189(this, n, n2, n3, l);
    }

    public void Method3158(char c, int n) {
        Class75.Method1622(this, c, n);
    }

    public void Method3159(int n) {
        Class75.Method191(this, n);
    }

    public boolean Method3160(int n, int n2) {
        return Class75.Method192(this, n, n2);
    }

    @Nullable
    @Override
    public Class70 Method2231() {
        return null;
    }

    @Nullable
    @Override
    public Setting Method2232() {
        return null;
    }

    @Override
    public int Method2233() {
        return 0;
    }

    @Override
    public void Method2234(int var1) {

    }

    @Override
    public int Method2235() {
        return 0;
    }

    @Override
    public void Method2236(int var1) {

    }

    @Override
    public int Method2237() {
        return 0;
    }

    @Override
    public int Method2238() {
        return 0;
    }

    @Override
    public boolean Method2239() {
        return false;
    }

    @Override
    public void Method2240(boolean var1) {

    }

    @Override
    public int Method2241() {
        return 0;
    }

    @Override
    public int Method2242() {
        return 0;
    }

    @Override
    public int Method2243() {
        return 0;
    }

    @Override
    public void Method2244() {

    }

    @Override
    public void Method2245(int var1, int var2) {

    }

    @Override
    public void Method2246(int var1, int var2, int var3) {

    }

    @Override
    public void Method2247(int var1, int var2, int var3) {

    }

    @Override
    public void Method2248(int var1, int var2, int var3, long var4) {

    }

    @Override
    public void Method2249(char var1, int var2) {

    }

    @Override
    public void Method2250(int var1) {

    }

    @Override
    public boolean Method2251(int var1, int var2) {
        return false;
    }
}
