package me.ciruu.abyss;

import com.mojang.realmsclient.gui.ChatFormatting;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.NotNull;
import me.ciruu.abyss.modules.Module;
import java.awt.Color;

public final class Class117
implements Class631 {
    private float Field3396;
    private float Field3397;
    private boolean Field3398;
    @NotNull
    private final Module Field3399;
    @NotNull
    private final Class219 Field3400;
    public Class219 parent;

    public float Method4058() {
        return this.Field3396;
    }

    public void Method279(float f) {
        this.Field3396 = f;
    }

    public float Method291() {
        return this.Field3397;
    }

    public void Method272(float f) {
        this.Field3397 = f;
    }

    public boolean Method290() {
        return this.Field3398;
    }

    public void Method288(boolean bl) {
        this.Field3398 = bl;
    }

    public float Method4059() {
        return 20.0f;
    }

    public float Method4060() {
        return 100.0f;
    }

    public void Method3565() {
        if (this.Method4061().allowUserInput || Manager.Field280.getCloseGUI()) {
            return;
        }
        this.Method4062();
    }

    public void Method3566(int n, int n2, int n3) {
        if (!this.Method4061().allowUserInput && this.Method4063(n, n2)) {
            if (n3 == 0) {
                this.Field3399.moduleStatus();
            } else if (n3 == 2) {
                this.Field3399.Method585(!this.Field3399.Method492());
                ChatFormatting chatFormatting = ChatFormatting.GREEN;
                ChatFormatting chatFormatting2 = ChatFormatting.RED;
                Class547.printChatMessage(this.Field3399 + " visibility:" + (!this.Field3399.Method492() ? chatFormatting + " ON" : chatFormatting2 + " OFF"));
            }
        }
    }

    public void Method4067(int n, int n2, int n3) {
    }

    public void Method4068(int n, int n2, int n3, long l) {
    }

    public void Method4069(char c, int n) {
    }

    private final void Method4062() {
        Class423.Method2815(this.Method4058(), this.Method291(), this.Method4058() + this.Method4060(), this.Method291() + this.Method4059(), this.Field3399.getStatus() ? Class232.Field570.Method720() : Class232.Field570.Method721());
        Class36.Method63(String.valueOf(this.Field3399), this.Method4058() + (float)3, this.Method291() + this.Method4059() / 3.0f, this.Field3399.getStatus() ? Color.WHITE.getRGB() : Class232.Field570.Method722().getRGB());
        if (this.Method290()) {
            float f = this.Method291() + this.Method4059() / (float)2;
            float f2 = this.Method4058() - 13.0f;
            float f3 = f;
            float f4 = this.Method4058();
            float f5 = f + 6.0f;
            float f6 = this.Method4058();
            float f7 = f - 6.0f;
            Class423.Method2828(f2, f3, f4, f5, f6, f7, this.Field3399.getStatus() ? Class232.Field570.Method720().getRGB() : Class232.Field570.Method721().getRGB());
        }
    }

    @NotNull
    public final Module Method4071() {
        return this.Field3399;
    }

    @NotNull
    public Class219 Method4061() {
        return this.Field3400;
    }

    public Class219 Method4072() {
        return this.Method4061();
    }

    public Class117(@NotNull Module module, @NotNull Class219 class116) {
        this.Field3399 = module;
        this.Field3400 = class116;
    }

    public void Method4073(int n, int n2) {
        Class632.Method3843(this, n, n2);
    }

    public void Method4074(int n) {
        Class632.Method3848(this, n);
    }

    public boolean Method4063(int n, int n2) {
        return Class632.Method3849(this, n, n2);
    }

    @Nullable
    @Override
    public Class101 Method3391() {
        return null;
    }

    @Override
    public float Method3392() {
        return 0;
    }

    @Override
    public void Method3393(float var1) {

    }

    @Override
    public float Method3394() {
        return 0;
    }

    @Override
    public void Method3395(float var1) {

    }

    @Override
    public float Method3396() {
        return 0;
    }

    @Override
    public float Method3397() {
        return 0;
    }

    @Override
    public boolean Method3398() {
        return false;
    }

    @Override
    public void Method3399(boolean var1) {

    }

    @Override
    public void Method3400() {

    }

    @Override
    public void Method3401(int var1, int var2) {

    }

    @Override
    public void Method3402(int var1, int var2, int var3) {

    }

    @Override
    public void Method3403(int var1, int var2, int var3) {

    }

    @Override
    public void Method3404(int var1, int var2, int var3, long var4) {

    }

    @Override
    public void Method3405(char var1, int var2) {

    }

    @Override
    public void Method3406(int var1) {

    }

    @Override
    public boolean Method3407(int var1, int var2) {
        return false;
    }
}
