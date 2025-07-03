package me.ciruu.abyss;

import java.awt.Color;
import java.util.*;
import java.util.stream.Collectors;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.managers.ModuleManager;
import me.ciruu.abyss.modules.Module;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Class116 implements Class101 {
    private float x;
    private float y;
    private float width = 20.0f;
    private boolean extended = true;
    @Nullable
    private ResourceLocation icon;
    private boolean hovered;
    @NotNull
    private ArrayList<Class117> buttons = new ArrayList<>();
    @NotNull
    private Category category;
    public Class219 class116;
    public Class111 guiInstance;

    public Class116(@NotNull Category category) {
        this.category = category;
        this.icon = getIconForCategory(category);

        Collection<Module> allModules = Manager.moduleManager.getModules().values();

        List<Class117> moduleButtons = allModules
                .stream().filter(m -> m.getCategory() == category)
                .sorted(Comparator.comparing(Module::moduleName))
                .map(m -> new Class117(m, this.class116))
                .collect(Collectors.toList());

        if (!moduleButtons.isEmpty()) {
            moduleButtons.get(0).Method288(true); // Activate the first button
        }

        buttons.addAll(moduleButtons);
    }

    @Nullable
    private ResourceLocation getIconForCategory(Category category) {
        switch (category) {
            case COMBAT: return Class234.Field1811;
            case HUD: return Class234.Field1813;
            case EXPLOIT: return Class234.Field1812;
            case MISC: return Class234.Field1814;
            case MOVEMENT: return Class234.Field1815;
            case RENDER: return Class234.Field1816;
            case CLIENT: return Class234.Field1810;
            default: return null;
        }
    }

    public void draw(int mouseX, int mouseY) {
        this.hovered = isMouseOver(mouseX, mouseY);
        drawBackground();
        buttons.forEach(Class117::Method3565);
    }

    /** @Class116
             Is This Correct?

     */
    public void handleClick(int mouseX, int mouseY, int button) {
        if (button == 0 && isMouseOver(mouseX, mouseY)) {
            Class111.hideButtons(this.class116);
            guiInstance.hideButtons(this.class116);
            guiInstance.setAnim(new Class113(200L, guiInstance.getFirstY(), guiInstance.getMiddleY()));

            Manager.Field280.setAnim(new Class113(200L, Manager.Field280.getFirstY(), Manager.Field280.getMiddleY()));
        }
        buttons.forEach(btn -> btn.Method3566(mouseX, mouseY, button));
    }

    private void drawBackground() {
        int bgColor = extended ? Class232.Field570.Method721().getRGB() : Class232.Field570.Method720().getRGB();
        int lineColor = !extended || hovered ? Class232.Field570.Method720().getRGB() : Class232.Field570.Method721().getRGB();

        Class423.Method2778((int)x, (int)y, width, bgColor, 90);
        drawIcon();
        Class423.Method2800((int)x, (int)y, (int)(x + width + 3), lineColor, 2.0f);
    }

    private void drawIcon() {
        if (icon != null) {
            Class118.Field282.Method304();
            Globals.mc.renderEngine.bindTexture(icon);
            float iconSize = (width - 2) * 2;
            Class118.Field282.Method320(x - width + 2, y - width + 2, 0.0f, 0.0f, 64, 64, iconSize, iconSize, 64.0f, 64.0f, extended ? Class232.Field570.Method723() : Color.WHITE);
            Class118.Field282.Method313();
        }
    }

    public boolean isMouseOver(int mouseX, int mouseY) {
        return mouseX >= x && mouseX <= x + width && mouseY >= y && mouseY <= y + width;
    }

    // --- Getters & Setters ---

    public float getX() { return x; }
    public void setX(float x) { this.x = x; }

    public float getY() { return y; }
    public void setY(float y) { this.y = y; }

    public float getWidth() { return width; }

    public boolean isExtended() { return extended; }
    public void setExtended(boolean extended) { this.extended = extended; }

    @Nullable
    public ResourceLocation getIcon() { return icon; }
    public void setIcon(@Nullable ResourceLocation icon) { this.icon = icon; }

    public boolean isHovered() { return hovered; }
    public void setHovered(boolean hovered) { this.hovered = hovered; }

    @NotNull
    public ArrayList<Class117> getButtons() { return buttons; }
    public void setButtons(@NotNull ArrayList<Class117> buttons) { this.buttons = buttons; }

    // --- Interface Methods (likely GUI interface stubs) ---

    @Override public float Method240() { return 0; }
    @Override public void Method241(float var1) {}
    @Override public float Method242() { return 0; }
    @Override public void Method243(float var1) {}
    @Override public float Method244() { return 0; }
    @Override public boolean Method245() { return false; }
    @Override public void Method246(boolean var1) {}
    @Override public void Method247(int var1, int var2) {}
    @Override public void Method248(int var1, int var2) {}
    @Override public void Method249(int var1, int var2, int var3) {}
    @Override public void Method250(int var1, int var2, int var3) {}
    @Override public void Method251(int var1, int var2, int var3, long var4) {}
    @Override public void Method252(char var1, int var2) {}
    @Override public void Method253(int var1) {}
    @Override public boolean Method254(int var1, int var2) { return false; }

    public List<String> getNamesInCategory() {
        List<String> names = new ArrayList<>();

        for (Object module_ : ModuleManager.modulecollection) {
            if (module_ instanceof Module) {
                Module module = (Module) module_;
                if (module != null && module.getCategory() == this.category) {
                    names.add(module.getCategory().name());
                }
            }
        }
        return names;
    }

    public static Set<Category> getAllUsedCategories() {
        Set<Category> categories = new HashSet<>();

        for (Object module_ : ModuleManager.modulecollection) {
            if (module_ instanceof Module) {
                Module module = (Module) module_;
                if (module != null) {
                    categories.add(module.getCategory());
                }
            }
        }
        return categories;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return this.category != null ? this.category.name() : "Unknown";
    }
}