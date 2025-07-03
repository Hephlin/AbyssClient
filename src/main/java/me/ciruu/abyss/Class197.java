package me.ciruu.abyss;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import me.ciruu.abyss.Class133;
import me.ciruu.abyss.Class134;
import me.ciruu.abyss.Manager;
import me.ciruu.abyss.modules.client.NameChanger;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.texture.DynamicTexture;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.opengl.GL11;

public class Class197
        extends Class133 {
    private DynamicTexture Field2622;
    protected Class134[] Field2610 = new Class134[256];
    protected Class134[] Field2611 = new Class134[256];
    protected Class134[] Field2612 = new Class134[256];
    protected Class134[] Field2621 = new Class134[256];
    private final int[] Field2613 = new int[32];
    private final String Field2614 = "0123456789abcdefklmnor";
    private int Field2623 = 2; // or 1, 2, 3, depending on how tight/loose spacing you want
    String Field2615;
    int Field2616;
    public Font Field2624;
    public boolean Field2625;
    public boolean Field2626;
    public @NotNull Class134[] glyphDataArray;
    protected DynamicTexture Field2617;
    protected DynamicTexture Field2618;
    protected DynamicTexture Field2619;
    private void Method3206(Class134[] glyphs, char c, float x, float y) {

        if (c < 0 || c >= glyphs.length || glyphs[c] == null) return;

        Class134 glyph = glyphs[c];

        int charWidth = glyph.Field314;
        int charHeight = glyph.Field315;

        float texSize = 512.0f;
        int charsPerRow = (int) (texSize / 16); // 16 px per char slot?

        int index = c;
        int col = index % charsPerRow;
        int row = index / charsPerRow;

        float u1 = (col * 16.0f) / texSize;
        float v1 = (row * 16.0f) / texSize;
        float u2 = ((col * 16.0f) + charWidth) / texSize;
        float v2 = ((row * 16.0f) + charHeight) / texSize;

        GL11.glTexCoord2f(u1, v1);
        GL11.glVertex2f(x, y);

        GL11.glTexCoord2f(u1, v2);
        GL11.glVertex2f(x, y + charHeight);

        GL11.glTexCoord2f(u2, v2);
        GL11.glVertex2f(x + charWidth, y + charHeight);

        GL11.glTexCoord2f(u2, v1);
        GL11.glVertex2f(x + charWidth, y);

    }


    public Class197(Font font, boolean bl, boolean bl2) {
        super(font, bl, bl2);
        this.Method3181();
        this.Method3182();
    }

    public String Method3183() {
        return this.Field2615;
    }
    @Nullable
    public int Method3184() {
        return this.Field2616;
    }

    @Nullable
    public void Method3185(String string) {
        this.Field2615 = string;
    }

    @Nullable
    public void Method3186(int n) {
        this.Field2616 = n;
    }

    @Nullable
    public float Method554(String string, double d, double d2, int n) {
        float f = this.Method3187(string, d + 1.0, d2 + 1.0, n, true);
        return Math.max(f, this.Method3187(string, d, d2, n, false));
    }

    @Nullable
    public float Method556(String string, float f, float f2, int n) {
        return this.Method3187(string, f, f2, n, false);
    }

    @Nullable
    public float Method558(String string, float f, float f2, int n) {
        return this.Method554(string, f - (float)(this.Method563(string) / 2), f2, n);
    }

    @Nullable
    public float Method560(String string, float f, float f2, int n) {
        return this.Method556(string, f - (float)(this.Method563(string) / 2), f2, n);
    }

    @Nullable
    public float Method3187(String string, double d, double d2, int n, boolean bl) {
        d2 -= 2.0;
        if (string == null) {
            return 0.0f;
        }
        if (n == 0x20FFFFFF) {
            n = 0xFFFFFF;
        }
        if ((n & 0xFC000000) == 0) {
            n |= 0xFF000000;
        }
        if (bl) {
            n = (n & 0xFCFCFC) >> 2 | n & 0xFF000000;
        }
        if (Manager.moduleManager.getModuleByClass(NameChanger.class) != null && Manager.moduleManager.isModuleEnabled(NameChanger.class)) {
            string = string.replace(Minecraft.getMinecraft().getSession().getUsername(), (CharSequence)((NameChanger)Manager.moduleManager.getModuleByClass(NameChanger.class)).newname.getValue());
        }
        Class134[] class134Array = this.Field2621;
        float f = (float)(n >> 24 & 0xFF) / 255.0f;
        boolean bl2 = false;
        boolean bl3 = false;
        boolean bl4 = false;
        boolean bl5 = false;
        boolean bl6 = false;
        boolean bl7 = true;
        d *= 2.0;
        d2 *= 2.0;
        if (bl7) {
            GL11.glPushMatrix();
            GlStateManager.scale((double)0.5, (double)0.5, (double)0.5);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc((int)770, (int)771);
            GlStateManager.color((float)((float)(n >> 16 & 0xFF) / 255.0f), (float)((float)(n >> 8 & 0xFF) / 255.0f), (float)((float)(n & 0xFF) / 255.0f), (float)f);
            int n2 = string.length();
            GlStateManager.enableTexture2D();
            GlStateManager.bindTexture((int)this.Field2622.getGlTextureId());
            GL11.glBindTexture((int)3553, (int)this.Field2622.getGlTextureId());
            for (int i = 0; i < n2; ++i) {
                char c = string.charAt(i);
                if (c == '\u00a7' && i < n2) {
                    int n3 = 21;
                    try {
                        n3 = "0123456789abcdefklmnor".indexOf(string.charAt(i + 1));
                    }
                    catch (Exception exception) {
                        // empty catch block
                    }
                    if (n3 < 16) {
                        bl3 = false;
                        bl4 = false;
                        bl2 = false;
                        bl6 = false;
                        bl5 = false;
                        GlStateManager.bindTexture((int)this.Field2622.getGlTextureId());
                        class134Array = this.Field2621;
                        if (n3 < 0 || n3 > 15) {
                            n3 = 15;
                        }
                        if (bl) {
                            n3 += 16;
                        }
                        int n4 = this.Field2613[n3];
                        GlStateManager.color((float)((float)(n4 >> 16 & 0xFF) / 255.0f), (float)((float)(n4 >> 8 & 0xFF) / 255.0f), (float)((float)(n4 & 0xFF) / 255.0f), (float)f);
                    } else if (n3 == 16) {
                        bl2 = true;
                    } else if (n3 == 17) {
                        bl3 = true;
                        if (bl4) {
                            GlStateManager.bindTexture((int)this.Field2619.getGlTextureId());
                            class134Array = this.Field2612;
                        } else {
                            GlStateManager.bindTexture((int)this.Field2617.getGlTextureId());
                            class134Array = this.Field2610;
                        }
                    } else if (n3 == 18) {
                        bl5 = true;
                    } else if (n3 == 19) {
                        bl6 = true;
                    } else if (n3 == 20) {
                        bl4 = true;
                        if (bl3) {
                            GlStateManager.bindTexture((int)this.Field2619.getGlTextureId());
                            class134Array = this.Field2612;
                        } else {
                            GlStateManager.bindTexture((int)this.Field2618.getGlTextureId());
                            class134Array = this.Field2611;
                        }
                    } else if (n3 == 21) {
                        bl3 = false;
                        bl4 = false;
                        bl2 = false;
                        bl6 = false;
                        bl5 = false;
                        GlStateManager.color((float)((float)(n >> 16 & 0xFF) / 255.0f), (float)((float)(n >> 8 & 0xFF) / 255.0f), (float)((float)(n & 0xFF) / 255.0f), (float)f);
                        GlStateManager.bindTexture((int)this.Field2622.getGlTextureId());
                        class134Array = this.Field2621;
                    }
                    ++i;
                    continue;
                }
                if (c >= class134Array.length || c < '\u0000') continue;
                GL11.glBegin((int)4);
                this.Method3206(class134Array, c, (float)d, (float)d2);
                GL11.glEnd();
                if (bl5) {
                    this.Method3208(d, d2 + (double)(class134Array[c].Field315 / 2), d + (double)class134Array[c].Field314 - 8.0, d2 + (double)(class134Array[c].Field315 / 2), 1.0f);
                }
                if (bl6) {
                    this.Method3208(d, d2 + (double)class134Array[c].Field315 - 2.0, d + (double)class134Array[c].Field314 - 8.0, d2 + (double)class134Array[c].Field315 - 2.0, 1.0f);
                }

                d += (double)(class134Array[c].Field314 - 8 + this.Field2623);
            }
            GL11.glHint((int)3155, (int)4352);
            GL11.glPopMatrix();
        }
        return (float)d / 2.0f;
    }

    @Nullable
    private DynamicTexture Method3215(Font font, boolean antiAlias, boolean fractionalMetrics, Class134[] glyphDataArray) {
        BufferedImage bufferedImage = new BufferedImage(512, 512, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = (Graphics2D) bufferedImage.getGraphics();
        g.setFont(font);
        g.setColor(new Color(255, 255, 255, 0));
        g.fillRect(0, 0, 512, 512);
        g.setColor(Color.WHITE);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, antiAlias ? RenderingHints.VALUE_ANTIALIAS_ON : RenderingHints.VALUE_ANTIALIAS_OFF);
        g.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, fractionalMetrics ? RenderingHints.VALUE_FRACTIONALMETRICS_ON : RenderingHints.VALUE_FRACTIONALMETRICS_OFF);

        FontMetrics fontMetrics = g.getFontMetrics();

        int charX = 0, charY = 0;
        for (char c = 0; c < glyphDataArray.length; c++) {
            char currentChar = (char)c;
            Rectangle2D bounds = fontMetrics.getStringBounds(String.valueOf(currentChar), g);

            int charWidth = (int) bounds.getWidth() + 2;
            int charHeight = (int) bounds.getHeight() + 2;

            if (charX + charWidth >= 512) {
                charX = 0;
                charY += charHeight;
            }

            g.drawString(String.valueOf(currentChar), charX, charY + fontMetrics.getAscent());

            glyphDataArray[c] = new Class134(charWidth, charHeight, (float)charX / 512f, (float)charY / 512f,
                    (float)(charX + charWidth) / 512f, (float)(charY + charHeight) / 512f);

            charX += charWidth;
        }

        g.dispose();
        return new DynamicTexture(bufferedImage);
    }

    @Nullable
    public int Method563(String string) {
        if (string == null) {
            return 0;
        }
        int n = 0;
        this.Field2610 = new Class134[256]; // Initialize array with 256 elements
        this.Field2610 = new Class134[256];
        for (int i = 0; i < 256; i++) {
            this.Field2610[i] = new Class134(null); // Ensure valid Class134 objects are used
        }
        Class134[] class134Array = this.Field2621;
        boolean bl = false;
        boolean bl2 = false;
        int n2 = string.length();
        for (int i = 0; i < n2; ++i) {
            char c = string.charAt(i);
            if (c == '\u00a7' && i < n2) {
                int n3 = "0123456789abcdefklmnor".indexOf(c);
                if (n3 < 16) {
                    bl = false;
                    bl2 = false;
                } else if (n3 == 17) {
                    bl = true;
                    class134Array = bl2 ? this.Field2612 : this.Field2610;
                } else if (n3 == 20) {
                    bl2 = true;
                    class134Array = bl ? this.Field2612 : this.Field2611;
                } else if (n3 == 21) {
                    bl = false;
                    bl2 = false;
                    class134Array = this.Field2621;
                }
                ++i;
                continue;
            }
            if (c >= class134Array.length || c < '\u0000') continue;
            n += class134Array[c].Field314 - 8 + this.Field2623;
        }
        return n / 2;
    }

    public void Method3211(Font font) {
        super.Method374(font);
        this.Method3182();
    }

    public void Method3212(boolean bl) {
        super.Method370(bl);
        this.Method3182();
    }

    public void Method3213(boolean bl) {
        super.Method372(bl);
        this.Method3182();
    }

    // 17:48:14.404
    //at me.ciruu.abyss.Class197.Method3187(Class197.java:147)
    //17:48:14.405
    //at me.ciruu.abyss.Class197.Method556(Class197.java:100)
    private void Method3182() {
        this.Field2617 = this.Method3215(this.Field2624.deriveFont(1), this.Field2625, this.Field2626, this.Field2610);
        this.Field2618 = this.Method3215(this.Field2624.deriveFont(2), this.Field2625, this.Field2626, this.Field2611);
        this.Field2619 = this.Method3215(this.Field2624.deriveFont(3), this.Field2625, this.Field2626, this.Field2612);
    }

    private void Method3208(double d, double d2, double d3, double d4, float f) {
        GL11.glDisable((int)3553);
        GL11.glLineWidth((float)f);
        GL11.glBegin((int)1);
        GL11.glVertex2d((double)d, (double)d2);
        GL11.glVertex2d((double)d3, (double)d4);
        GL11.glEnd();
        GL11.glEnable((int)3553);
    }

    public List Method3220(String string, double d) {
        ArrayList<String> arrayList = new ArrayList<String>();
        if ((double)this.Method563(string) > d) {
            String[] stringArray = string.split(" ");
            String string2 = "";
            char c = '\uffff';
            for (String string3 : stringArray) {
                if (string3 instanceof String) {
                    for (int i = 0; i < string3.toCharArray().length; ++i) {
                        char c2 = string3.toCharArray()[i];
                        if (c2 != '\u00a7' || i >= string3.toCharArray().length - 1) continue;
                        c = string3.toCharArray()[i + 1];
                    }
                    StringBuilder stringBuilder = new StringBuilder();
                    if ((double) this.Method563(stringBuilder.append(string2).append(string3).append(" ").toString()) < d) {
                        string2 = string2 + string3 + " ";
                        continue;
                    }
                    arrayList.add(string2);
                    string2 = "\u00a7" + c + string3 + " ";
                }
            }
            if (string2.length() > 0) {
                if ((double)this.Method563(string2) < d) {
                    arrayList.add("\u00a7" + c + string2 + " ");
                    string2 = "";
                } else {
                    for (Object string4_ : this.Method3228(string2, d)) {
                        String string4 = (String)string4_;
                        arrayList.add(string4);
                    }
                }
            }
        } else {
            arrayList.add(string);
        }
        return arrayList;
    }

    public List Method3228(String string, double d) {
        ArrayList<String> arrayList = new ArrayList<String>();
        String string2 = "";
        char c = '\uffff';
        char[] cArray = string.toCharArray();
        for (int i = 0; i < cArray.length; ++i) {
            char c2 = cArray[i];
            if (c2 == '\u00a7' && i < cArray.length - 1) {
                c = cArray[i + 1];
            }
            StringBuilder stringBuilder = new StringBuilder();
            if ((double)this.Method563(stringBuilder.append(string2).append(c2).toString()) < d) {
                string2 = string2 + c2;
                continue;
            }
            arrayList.add(string2);
            string2 = "\u00a7" + c + String.valueOf(c2);
        }
        if (string2.length() > 0) {
            arrayList.add(string2);
        }
        return arrayList;
    }

    private void Method3181() {
        for (int i = 0; i < 32; ++i) {
            int n = (i >> 3 & 1) * 85;
            int n2 = (i >> 2 & 1) * 170 + n;
            int n3 = (i >> 1 & 1) * 170 + n;
            int n4 = (i >> 0 & 1) * 170 + n;
            if (i == 6) {
                n2 += 85;
            }
            if (i >= 16) {
                n2 /= 4;
                n3 /= 4;
                n4 /= 4;
            }
            this.Field2613[i] = (n2 & 0xFF) << 16 | (n3 & 0xFF) << 8 | n4 & 0xFF;
        }
    }

    public String Method562(String string, int n) {
        return this.Method3232(string, n, false);
    }

    public String Method3232(String string, int n, boolean bl) {
        StringBuilder stringBuilder = new StringBuilder();
        int n2 = 0;
        int n3 = bl ? string.length() - 1 : 0;
        int n4 = bl ? -1 : 1;
        boolean bl2 = false;
        boolean bl3 = false;
        for (int i = n3; i >= 0 && i < string.length() && n2 < n; i += n4) {
            char c = string.charAt(i);
            float f = this.Method563(string);
            if (bl2) {
                bl2 = false;
                if (c != 'l' && c != 'L') {
                    if (c == 'r' || c == 'R') {
                        bl3 = false;
                    }
                } else {
                    bl3 = true;
                }
            } else if (f < 0.0f) {
                bl2 = true;
            } else {
                n2 = (int)((float)n2 + f);
                if (bl3) {
                    ++n2;
                }
            }
            if (n2 > n) break;
            if (bl) {
                stringBuilder.insert(0, c);
                continue;
            }
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }
}