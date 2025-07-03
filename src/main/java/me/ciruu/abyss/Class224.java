package me.ciruu.abyss;

import java.awt.Color;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.client.renderer.GlStateManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public final class Class224 {
    private GuiTextField Field2224;
    private boolean Field2225;
    private boolean Field2226;
    @Nullable
    private Function1 Field2227;
    @Nullable
    private Function1 Field2228;
    @Nullable
    private Function1 Field2229;
    @NotNull
    private final Class137 Field2230;

    @NotNull
    public final String Method700() {
        return this.Field2224.getText();
    }

    public final void Method1716(@NotNull String string) {
        this.Field2224.setText(string);
    }

    public final int Method2829() {
        return this.Field2224.getMaxStringLength();
    }

    public final void Method1741(int n) {
        this.Field2224.setMaxStringLength(n);
    }

    public final boolean Method2830() {
        return this.Field2224.isFocused();
    }

    public final void Method2831(boolean bl) {
        if (bl) {
            this.Field2224.setFocused(true);
        } else {
            this.Field2224.setFocused(false);
        }
    }

    public final boolean Method2832() {
        return this.Field2225;
    }

    public final void Method1687(boolean bl) {
        this.Field2225 = bl;
    }

    public final boolean Method2833() {
        return this.Field2226;
    }

    public final void Method2834(boolean bl) {
        this.Field2226 = bl;
    }

    @Nullable
    public final Function1 Method2835() {
        return this.Field2227;
    }

    public final void Method2836(@Nullable Function1 function1) {
        this.Field2227 = function1;
    }

    @Nullable
    public final Function1 Method2837() {
        return this.Field2228;
    }

    public final void Method1739(@Nullable Function1 function1) {
        this.Field2228 = function1;
    }

    @Nullable
    public final Function1 Method2838() {
        return this.Field2229;
    }

    public final void Method1740(@Nullable Function1 function1) {
        this.Field2229 = function1;
    }

    public final void Method1689() {
        if (!this.Field2225) return;

        this.Field2224.x = this.Field2230.Method1677();
        this.Field2224.y = this.Field2230.Method1678();
        this.Field2224.width = this.Field2230.Method1679() - 8;
        this.Field2224.height = this.Field2230.Method1676();
        this.Field2224.setEnabled(this.Field2226);
        this.Field2224.setEnableBackgroundDrawing(false);

        int borderColor = this.Method2830() ? Color.GRAY.brighter().getRGB() : Color.GRAY.getRGB();
        Class140.Method608(this.Field2230.Method1677() - 1, this.Field2230.Method1678() - 1,
                this.Field2230.Method1679() + 2, this.Field2230.Method1676() + 2, borderColor);
        Class140.Method608(this.Field2230.Method1677(), this.Field2230.Method1678(),
                this.Field2230.Method1679(), this.Field2230.Method1676(),
                Class74.Field172.Method165().getRGB());

        GlStateManager.pushMatrix();
        GlStateManager.translate(1.0f, 2.0f, 0.0f);
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        this.Field2224.drawTextBox();
        GlStateManager.popMatrix();
    }

    public final void Method1726(char c, int keyCode) {
        if (!this.Field2225) return;

        if (this.Field2226 || keyCode == 203 || keyCode == 205 || keyCode == 199 || keyCode == 207) {
            String before = this.Method700();
            this.Field2224.textboxKeyTyped(this.Field2226 ? c : '\0', keyCode);

            if (!Intrinsics.areEqual(this.Method700(), before)) {
                if (this.Field2227 != null) {
                    this.Field2227.invoke(this);
                }
            }

            if (keyCode == 28 && this.Method2830()) {
                if (this.Field2229 != null) {
                    this.Field2229.invoke(this);
                }
                if (this.Field2224.isFocused()) {
                    this.Method2831(false);
                    this.Method2849();
                }
            }
        }
    }

    public final void Method1732(int mouseX, int mouseY, int mouseButton) {
        boolean wasFocused = this.Field2224.isFocused();

        if (this.Field2225 && this.Field2226) {
            this.Field2224.mouseClicked(mouseX, mouseY, mouseButton);
        } else {
            this.Field2224.setFocused(false);
        }

        if (this.Field2224.isFocused() != wasFocused) {
            this.Method2849();
        }
    }

    private final void Method2849() {
        if (!this.Method2830() && this.Field2224.getSelectionEnd() != this.Field2224.getCursorPosition()) {
            this.Field2224.setSelectionPos(this.Field2224.getCursorPosition());
        }
        if (this.Field2228 != null) {
            this.Field2228.invoke(this);
        }
    }

    @NotNull
    public final Class137 Method1688() {
        return this.Field2230;
    }

    public Class224(@NotNull Class137 class137) {
        this.Field2230 = class137;
        GuiTextField textField = new GuiTextField(0, Class36.Method551(), 0, 0, 0, 0);
        textField.setMaxStringLength(10000);
        this.Field2224 = textField;
        this.Field2225 = true;
        this.Field2226 = true;
    }

    public <DefaultConstructorMarker> Class224(Class137 class137, int mask, DefaultConstructorMarker marker) {
        this((mask & 1) != 0 ? new Class137(0, 0, 0, 0, 15, null) : class137);
    }

    public Class224() {
        this(null, 1, null);
    }
}
