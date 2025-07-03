package me.ciruu.abyss;

import java.util.ArrayList;

import me.ciruu.abyss.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import net.minecraft.client.gui.Gui;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public final class Class546
implements Class70 {
    private int Field2195;
    private int Field2196;
    private int Field2197 = 16;
    private boolean Field2198;
    @Nullable
    private Setting Field2199;
    @NotNull
    private ArrayList Field2200;
    @NotNull
    private String Field2201;
    private int Field2202;
    @NotNull
    private final Class71 Field2203;

    public int Method2673() {
        return this.Field2195;
    }

    public void Method2674(int n) {
        this.Field2195 = n;
    }

    public int Method2675() {
        return this.Field2196;
    }

    public void Method2676(int n) {
        this.Field2196 = n;
    }

    public int Method2677() {
        return this.Method2678().Method157();
    }

    public int Method2679() {
        return this.Field2197;
    }

    public boolean Method2680() {
        return this.Field2198;
    }

    public void Method2681(boolean bl) {
        this.Field2198 = bl;
    }

    @Nullable
    public Setting Method2682() {
        return this.Field2199;
    }

    @NotNull
    public final ArrayList Method2583() {
        return this.Field2200;
    }

    public final void Method2683(@NotNull ArrayList arrayList) {
        this.Field2200 = arrayList;
    }

    @EventHandler
    @NotNull
    public String getSetting() {
        return this.Field2201;
    }

    public final void Method2684(@NotNull String string) {
        this.Field2201 = string;
    }

    public final int Method2685() {
        return this.Field2202;
    }

    public final void Method2686(int n) {
        this.Field2202 = n;
    }

    
    public void Method2687() {
        if (this.Method2680()) {
            return;
        }
        GL11.glLineWidth((float)1.0f);
        Gui.drawRect((int)this.Method2689(), (int)this.Method2690(), (int)(this.Method2689() + this.Method2678().Method157()), (int)(this.Method2690() + 16), (int)Class74.Field172.Method165().getRGB());
        Class36.Method63("- " + this.Field2201, this.Method2689() + 2, this.Method2690() + 4, Class74.Field172.Method171().getRGB());
    }

    public void Method2695(int n, int n2, int n3) {
        if (this.Method2696(n, n2) && this.Method2678().Method175()) {
            if (n3 == 0) {
                ++this.Field2202;
                if (this.Field2202 >= this.Field2200.size()) {
                    this.Field2202 = 0;
                }
                this.Field2201 = (String)this.Field2200.get(this.Field2202);
            }
            if (n3 == 1) {
                --this.Field2202;
                if (this.Field2202 < 0) {
                    this.Field2202 = this.Field2200.size() - 1;
                }
                this.Field2201 = (String)this.Field2200.get(this.Field2202);
            }
        }
    }

    @NotNull
    public Class71 Method2678() {
        return this.Field2203;
    }

    public Class70 Method2699() {
        return this.Method2678();
    }

    public Class546(@NotNull Class71 class71) {
        this.Field2203 = class71;
        this.Field2197 = 16;
        this.Field2200 = new ArrayList();
        this.Field2202 = this.Field2200.size();
        this.Field2201 = "Main Settings";
        this.Field2200.add("Main Settings");
    }

    public int Method2702() {
        return Class75.Method183(this);
    }

    public int Method2689() {
        return Class75.Method184(this);
    }

    public int Method2690() {
        return Class75.Method185(this);
    }

    public void Method2703(int n, int n2) {
        Class75.Method433(this, n, n2);
    }

    public void Method2704(int n, int n2, int n3) {
        Class75.Method187(this, n, n2, n3);
    }

    public void Method2705(int n, int n2, int n3, long l) {
        Class75.Method189(this, n, n2, n3, l);
    }

    public void Method2706(char c, int n) {
        Class75.Method1622(this, c, n);
    }

    public void Method2707(int n) {
        Class75.Method191(this, n);
    }

    public boolean Method2696(int n, int n2) {
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
