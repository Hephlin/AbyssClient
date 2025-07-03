package me.ciruu.abyss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import kotlin.jvm.internal.Intrinsics;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.modules.client.BubbleGUI;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Mouse;

public final class Class111
extends GuiScreen {
    @NotNull
    private Class113 Field271 = new Class113(0L, 0.0f, 0.0f);
    @NotNull
    private Class113 Field272 = new Class113(0L, 0.0f, 0.0f);
    private boolean Field273;
    private static boolean Field274;
    @NotNull
    private static ArrayList Field275;
    @NotNull
    private static ScaledResolution Field276;
    @NotNull
    private static Class113 Field277;
    private static boolean Field278;
    public static final Class112 Field279;

    @NotNull
    public final Class113 getAnim() {
        return this.Field271;
    }

    public final void setAnim(@NotNull Class113 class113) {
        this.Field271 = class113;
    }

    @NotNull
    public final Class113 getModuleAnimation() {
        return this.Field272;
    }

    public final void setModuleAnimation(@NotNull Class113 class113) {
        this.Field272 = class113;
    }

    public final boolean getCloseGUI() {
        return this.Field273;
    }

    public final void setCloseGUI(boolean bl) {
        this.Field273 = bl;
    }
    public Class219 class116 = null;


    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        if (Manager.moduleManager.isModuleEnabled(BubbleGUI.class) && Field277.Method261() && this.Field273) {
            ((BubbleGUI)Manager.moduleManager.getModuleByClass(BubbleGUI.class)).disable();
            this.mc.displayGuiScreen(null);
            return;
        }

        float yOffset = this.Field273 ? Field277.Method264() : this.Field271.Method264();
        Field276 = new ScaledResolution(Globals.mc);
        this.checkMouseWheel(mouseX);

        for (Object class116 : Field275) {
            if (class116 instanceof Class116) {
                Class219 category = (Class219) class116;
                category.drawScreen(mouseX, mouseY, partialTicks); // Ensures GUI is actually rendered
                yOffset += 60.0f;

                int selectedModule = this.getSelectedModule();
                float moduleY = (float) Field276.getScaledHeight() / 2.0f - 11 - (22 * selectedModule);

                for (Class117 module : category.Method271()) {
                    if (module instanceof Class117) {
                        module.Method272(moduleY); // Sets Y position
                        moduleY += 22.0f;
                    }
                }
            }
        }
    }

    protected void keyTyped(char c, int n) {
        if (n == 1) {
            if (Field274) {
                Field277 = new Class113(1000L, this.getFirstY(), (float)Field276.getScaledHeight() + 400.0f);
            }
            this.Field273 = true;
        }
        for (Object class116_ : Field275) {
                if (class116_ instanceof Class116) {
                    Class219 class116 = (Class219) class116_;
                    class116.Method273(c, n);
            }
        }
    }

    protected void mouseClicked(int n, int n2, int n3) throws IOException {
        Iterable iterable = Field275;
        boolean bl = false;
        for (Object t : iterable) {
            if (t instanceof Iterable) {
                Class219 class116 = (Class219) t;
                boolean bl2 = false;
                class116.Method275(n, n2, n3);
            }
        }
    }

    protected void mouseReleased(int n, int n2, int n3) {
        for (Object class116_ : Field275) {
            if (class116_ instanceof Class116) {
                    Class219 class116 = (Class219) class116_;
                    try {
                        class116.Method276(n, n2, n3);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
            }
        }
    }

    protected void mouseClickMove(int n, int n2, int n3, long l) {
        for (Object class116_ : Field275) {
            if (class116_ instanceof Class116) {
                Class219 class116 = (Class219) class116_;
                class116.Method277(n, n2, n3, l);
            }
        }
    }

    public boolean doesGuiPauseGame() {
        return false;
    }

    public void initGui() {
        this.Field273 = false;
        Field276 = new ScaledResolution(Globals.mc);
        Field274 = ((BubbleGUI) Manager.moduleManager.getModuleByClass(BubbleGUI.class)).animation.getValue();

        if (!Field278) {
            Field278 = true;

            for (Object class116 : Field275) {
                if (class116 instanceof Class116) {
                    Class219 category = (Class219) class116;
                    float moduleY = (float) Field276.getScaledHeight() / 2.0f - 11;

                    for (Class117 module : category.Method271()) {
                        if (module instanceof Class117) {
                            module.Method272(moduleY);
                            moduleY += 22.0f;
                        }
                    }
                }
            }
        }

        if (Field274) {
            this.hideButtons(null); // Ensure only one category is open

            float yOffset = (float) Field276.getScaledHeight() / 2.0f - 180;
            this.Field271 = new Class113(500L, -500.0f, yOffset);

            float mainX = this.getMainX();
            for (Object class116 : Field275) {
                if (class116 instanceof Class116) {
                    Class219 category = (Class219) class116;
                    for (Class117 module : category.Method271()) {
                        if (module instanceof Class117) {
                            module.Method279(mainX + 50); // Ensure module X is set
                        }
                    }
                }
            }
        }
    }


    public final float getMainX() {
        return (float)Field276.getScaledWidth() / 4.0f;
    }

    public final float getFirstY() {
        return ((Class219)Field275.get(0)).height;
    }

    public static void hideButtons(@Nullable Class219 selected) {
        for (Object class116 : Field275) {
            if (class116 instanceof Class116) {
                Class219 category = (Class219) class116;
                category.Method285(!Intrinsics.areEqual(category, selected));
            }
        }
    }



    public final float getMiddleY() {
        Class219 class116;
        int n = 0;
        Iterator iterator = Field275.iterator();
        while (iterator.hasNext() && (class116 = (Class219)iterator.next()).allowUserInput) {
            ++n;
        }
        return (float)Field276.getScaledHeight() / (float)2 - (float)(60 * n);
    }

    private final void checkMouseWheel(int n) {
        int n2 = Mouse.getDWheel();
        if (n2 == 0) {
            return;
        }
        this.checkCategoryWheel(n2, n);
        this.checkModuleWheel(n2, n);
    }

    private final void checkModuleWheel(int n, int n2) {
        if ((float)n2 > this.getMainX() + (float)150 || (float)n2 < this.getMainX() + (float)50) {
            return;
        }
        int n3 = -1;
        int n4 = this.getSelectedModule();
        int n5 = this.getModuleListSize();
        if (n > 0) {
            n3 = n4 <= 0 ? 0 : n4 - 1;
        }
        if (n < 0) {
            n3 = n4 == n5 - 1 ? n4 : n4 + 1;
        }
        if (n3 >= 0) {
            this.selectModule(n3);
        }
    }

    private final void selectModule(int n) {
        int n2 = 0;
        for (Object class116_ : Field275) {
            if (class116_ instanceof Class116) {
                Class219 class116 = (Class219) class116_;
                if (class116.allowUserInput) continue;
                for (Class117 class117 : class116.Method271()) {
                    if (class117 instanceof Class117) {
                        class117.Method288(n2 == n);
                        ++n2;
                    }
                }
            }
        }
    }

    private final int getModuleListSize() {
        for (Object class116_ : Field275) {
            if (class116_ instanceof Class116) {
                Class219 class116 = (Class219) class116_;
                if (class116.allowUserInput) continue;
                return class116.Method271().length;
            }
        }
        return -1;
    }

    private final int getSelectedModule() {
        int n = 0;
        for (Object class116_ : Field275) {
            if (class116_ instanceof Class116) {
                Class219 class116 = (Class219) class116_;
                if (class116.allowUserInput) continue;
                for (Class117 class117 : class116.Method271()) {
                    if (class117 instanceof Class117) {
                        if (class117.Method290()) {
                            return n;
                        }
                        ++n;
                    }
                }
            }
        }
        return n;
    }

    private final float getFirstModuleY() {
        for (Object class116_ : Field275) {
            if (class116_ instanceof Class116) {
                Class219 class116 = (Class219) class116_;
                if (class116.allowUserInput) continue;
                return Arrays.stream(class116.Method271())
                        .map(obj -> (Class117) obj)
                        .findFirst()
                        .map(Class117::Method291)
                        .orElse(0.0f);
            }
        }
        return 0.0f;
    }


    private final float getMiddleYModule() {
        int n = 0;
        for (Object class116_ : Field275) {
            if (class116_ instanceof Class116) {
                Class219 class116 = (Class219) class116_;
                Class117 class117;
                if (class116.allowUserInput) continue;
                Iterator iterator = Arrays.stream(class116.Method271()).iterator();
                while (iterator.hasNext() && !(class117 = (Class117) iterator.next()).Method290()) {
                    ++n;
                }
                break;
            }
        }
        return (float)Field276.getScaledHeight() / 2.0f - (float)11 - (float)(20 * (n - 1) + 10 + 2 * (n - 1));
    }

    private final void checkCategoryWheel(int n, int n2) {
        if ((float)n2 > this.getMainX() + (float)30 || (float)n2 < this.getMainX() - (float)30) {
            return;
        }
        int n3 = -1;
        int n4 = this.getUnhiddenCategory();
        if (n > 0) {
            n3 = n4 == -1 ? 3 : (n4 == 0 ? 0 : n4 - 1);
        }
        if (n < 0) {
            n3 = n4 == -1 ? 3 : (n4 == Field275.size() - 1 ? n4 : n4 + 1);
        }
        if (n3 >= 0 && n3 < Field275.size()) {
            this.hideButtons((Class219)Field275.get(n3));
            this.Field271 = new Class113(200L, this.getFirstY(), this.getMiddleY());
        }
    }

    private final int getUnhiddenCategory() {
        int n = 0;
        for (Object class116_ : Field275) {
            if (class116_ instanceof Class116) {
                Class219 class116 = (Class219) class116_;
                if (!class116.allowUserInput) {
                    return n;
                }
                ++n;
            }
        }
        return -1;
    }

    public Class111() {
        for (Category class11 : Category.values()) {
            if (class11 instanceof Category) {
                Class219 class116 = new Class219(class11);
                Field275.add(class116);
            }
        }
    }

    static {
        Field279 = new Class112(null);
        Field274 = true;
        Field275 = new ArrayList<>();
        Field277 = new Class113(0L, 0.0f, 0.0f);

        if (Globals.mc != null) {
            Field276 = new ScaledResolution(Globals.mc);
        } else {
            System.out.println("[(cannot load scaledresolution)]");
        }
    }

    public static final boolean Method294() {
        return Field274;
    }

    public static final void Method295(boolean bl) {
        Field274 = bl;
    }

    public static final ArrayList Method296() {
        return Field275;
    }

    public static final void Method297(ArrayList arrayList) {
        Field275 = arrayList;
    }

    public static final ScaledResolution Method298() {
        return Field276;
    }

    public static final void Method299(ScaledResolution scaledResolution) {
        Field276 = scaledResolution;
    }

    public static final Class113 Method300() {
        return Field277;
    }

    public static final void Method301(Class113 class113) {
        Field277 = class113;
    }

    public static final boolean Method302() {
        return Field278;
    }

    public static final void Method303(boolean bl) {
        Field278 = bl;
    }
}
