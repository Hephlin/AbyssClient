package me.ciruu.abyss;

import com.mojang.realmsclient.gui.ChatFormatting;
import kotlin.text.StringsKt;
import me.ciruu.abyss.settings.Setting;
import net.minecraft.client.gui.Gui;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

public final class Class69
implements Class70 {
    private boolean Field164;
    private boolean Field165;
    private int Field166;
    private int Field167;
    private boolean Field168;
    private boolean Field169;
    @NotNull
    private final Class71 Field170;
    @Nullable
    private final Setting Field171;

    public int Method148() {
        return this.Field166;
    }

    public void Method149(int n) {
        this.Field166 = n;
    }

    public int Method150() {
        return this.Field167;
    }

    public void Method151(int n) {
        this.Field167 = n;
    }

    public boolean Method152() {
        return this.Field168;
    }

    public void Method153(boolean bl) {
        this.Field168 = bl;
    }

    public int Method154() {
        return 16;
    }

    public int Method155() {
        return this.Method156().Method157();
    }

    
    public void Method158() {
        if (this.Method152()) {
            return;
        }
        if (StringsKt.startsWith(this.Method156().Method159().getContext(), "MOUSE", false)) {
            this.Field169 = true;
        }
        GL11.glLineWidth((float)1.0f);
        Gui.drawRect((int)this.Method163(), (int)this.Method164(), (int)(this.Method163() + this.Method156().Method157()), (int)(this.Method164() + 16), (int)Class74.Field172.Method165().getRGB());
        Class36.Method63(this.Field165 ? "> Press a key..." : "> Key: " + ChatFormatting.GRAY + this.Method156().Method159().getContext(), this.Method163() + 2, this.Method164() + 4, Class74.Field172.Method171().getRGB());
    }

    public void Method172(int n, int n2) {
        this.Field164 = this.Method173(n, n2);
    }

    public void Method174(int n, int n2, int n3) {
        if (this.Method173(n, n2) && this.Method156().Method175()) {
            if (this.Field165 && n3 != 2 && n3 != 0) {
                this.Field169 = true;
                this.Method156().Method159().moduleContext(Mouse.getButtonName((int)n3));
                this.Field165 = false;
            }
            if (this.Field165 && n3 == 2) {
                this.Field165 = false;
                this.Method156().Method159().moduleContext(Keyboard.getKeyName((int)0));
                this.Field169 = false;
            } else if (n3 == 0) {
                this.Field165 = !this.Field165;
            } else if (this.Field165) {
                this.Method156().Method159().moduleContext(Mouse.getButtonName((int)n3));
                this.Field169 = true;
                this.Field165 = false;
            }
        }
    }

    public void Method179(char c, int n) {
        if (this.Field165) {
            if (n == 211) {
                this.Method156().Method159().moduleContext(Keyboard.getKeyName((int)0));
            } else {
                this.Method156().Method159().moduleContext(Keyboard.getKeyName((int)n));
            }
            this.Field165 = false;
            this.Field169 = false;
        }
    }

    @NotNull
    public Class71 Method156() {
        return this.Field170;
    }

    public Class70 Method180() {
        return this.Method156();
    }

    @Nullable
    public Setting Method181() {
        return this.Field171;
    }

    public Class69(@NotNull Class71 class71, @Nullable Setting setting) {
        this.Field170 = class71;
        this.Field171 = setting;
    }

    public int Method182() {
        return Class75.Method183(this);
    }

    public int Method163() {
        return Class75.Method184(this);
    }

    public int Method164() {
        return Class75.Method185(this);
    }

    public void Method186(int n, int n2, int n3) {
        Class75.Method187(this, n, n2, n3);
    }

    public void Method188(int n, int n2, int n3, long l) {
        Class75.Method189(this, n, n2, n3, l);
    }

    public void Method190(int n) {
        Class75.Method191(this, n);
    }

    public boolean Method173(int n, int n2) {
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
