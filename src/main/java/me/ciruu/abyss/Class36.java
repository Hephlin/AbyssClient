package me.ciruu.abyss;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.io.IOException;
import me.ciruu.abyss.Class197;
import me.ciruu.abyss.Class198;
import me.ciruu.abyss.Globals;
import me.ciruu.abyss.Manager;
import me.ciruu.abyss.modules.client.CustomFont;
import net.minecraft.client.gui.FontRenderer;

public class Class36 {
    public static Font Field458;
    private static Font Field459;
    private static Class197 Field460;
    private static Class197 Field461;
    private static Class198 Field462;
    private static Class197 Field463;
    private static FontRenderer Field464;
    public static Class197 Field465;
    public static Class198 Field466;

    public static void Method546() {
        Class36.Method547();
    }

    public static Class197 Method548() {
        return Field460;
    }

    public static Class197 Method549() {
        return Field461;
    }

    public static Class198 Method550() {
        return Field462;
    }

    public static FontRenderer Method551() {
        return Globals.mc.fontRenderer;
    }

    private static void Method547() {
        try {
            Field458 = Font.createFont(Font.TRUETYPE_FONT, Class197.class.getResourceAsStream("/assets/Quicksand-Medium.ttf"));
            Field459 = Font.createFont(Font.TRUETYPE_FONT, Class197.class.getResourceAsStream("/assets/Dash-Horizon-Demo.otf"));

            Field460 = new Class197(Field458.deriveFont(17.0f), true, true);
            Field461 = new Class197(Field458.deriveFont(30.0f), true, true);
            Field462 = new Class198(Field458.deriveFont(43.0f));
            Field466 = new Class198(Field459.deriveFont(80.0f));
        } catch (Exception e) {
            System.err.println("Failed to load custom fonts: " + e.getMessage());
            e.printStackTrace();

            // Fallback to Arial
            Field460 = Field463; // already set in static block
            Field462 = new Class198(new Font("Arial", Font.PLAIN, 43));
        }
    }

    public static void Method552(Class197 class197) {
        Field460 = class197;
    }

    public static void Method553(Class198 class198) {
        Field462 = class198;
    }

    public static void Method63(String string, float f, float f2, int n) {
        if (Manager.moduleManager.isModuleEnabled(CustomFont.class)) {
            CustomFont customFont = (CustomFont)Manager.moduleManager.getModuleByClass(CustomFont.class);
            if ((Boolean)customFont.newfontrenderer.getValue()) {
                if (Field462 != null) {
                    Field462.drawStringWithShadow(string, f, f2, n);
                } else {
                    Globals.mc.fontRenderer.drawStringWithShadow(string, f, f2, n);
                }
            } else if (Field460 != null) {
                Field460.Method554(string, f, f2, n);
            } else {
                Globals.mc.fontRenderer.drawStringWithShadow(string, f, f2, n);
            }
        } else {
            Globals.mc.fontRenderer.drawStringWithShadow(string, f, f2, n);
        }
    }

    public static void Method555(String string, float f, float f2, int n) {
        if (Manager.moduleManager.isModuleEnabled(CustomFont.class)) {
            CustomFont customFont = (CustomFont)Manager.moduleManager.getModuleByClass(CustomFont.class);
            if ((Boolean)customFont.newfontrenderer.getValue()) {
                if (Field462 != null) {
                    Field462.drawString(string, f, f2, n);
                } else {
                    Globals.mc.fontRenderer.drawString(string, f, f2, n, false);
                }
            } else if (Field460 != null) {
                Field460.Method556(string, f, f2, n);
            } else {
                Globals.mc.fontRenderer.drawString(string, f, f2, n, false);
            }
        } else {
            Globals.mc.fontRenderer.drawString(string, f, f2, n, false);
        }
    }


    public static void Method557(String string, float f, float f2, int n) {
        if (Manager.moduleManager.isModuleEnabled(CustomFont.class)) {
            if (((Boolean)((CustomFont)Manager.moduleManager.getModuleByClass(CustomFont.class)).newfontrenderer.getValue()).booleanValue()) {
                Field462.drawCenteredString(string, f, f2, n, true);
            } else {
                Field460.Method558(string, f, f2, n);
            }
        } else {
            Globals.mc.fontRenderer.drawStringWithShadow(string, f - (float)(Globals.mc.fontRenderer.getStringWidth(string) / 2), f2, n);
        }
    }

    public static void Method559(String string, float f, float f2, int n) {
        if (Manager.moduleManager.isModuleEnabled(CustomFont.class)) {
            if (((Boolean)((CustomFont)Manager.moduleManager.getModuleByClass(CustomFont.class)).newfontrenderer.getValue()).booleanValue()) {
                Field462.drawCenteredString(string, f, f2, n, false);
            } else {
                Field460.Method560(string, f, f2, n);
            }
        } else {
            Globals.mc.fontRenderer.drawString(string, f - (float)(Globals.mc.fontRenderer.getStringWidth(string) / 2), f2, n, false);
        }
    }

    public static void Method561(String string, int n) {
        if (Manager.moduleManager.isModuleEnabled(CustomFont.class)) {
            if (((Boolean)((CustomFont)Manager.moduleManager.getModuleByClass(CustomFont.class)).newfontrenderer.getValue()).booleanValue()) {
                Field462.trimStringToWidth(string, n);
            } else {
                Field460.Method562(string, n);
            }
        } else {
            Globals.mc.fontRenderer.trimStringToWidth(string, n);
        }
    }

    public static int Method259(String string) {
        if (Manager.moduleManager.isModuleEnabled(CustomFont.class)) {
            if (((Boolean)((CustomFont)Manager.moduleManager.getModuleByClass(CustomFont.class)).newfontrenderer.getValue()).booleanValue()) {
                return Field462.getStringWidth(string);
            }
            return Field460.Method563(string);
        }
        return Globals.mc.fontRenderer.getStringWidth(string);
    }

    public static void Method62(String string, float f, float f2, float f3, float f4, float f5, float f6, boolean bl, float f7) {
        char[] cArray = string.toCharArray();
        int n = 1;
        float f8 = 0.0f;
        for (char c : cArray) {
            if (Manager.moduleManager.isModuleEnabled(CustomFont.class)) {
                if (((Boolean)((CustomFont)Manager.moduleManager.getModuleByClass(CustomFont.class)).newfontrenderer.getValue()).booleanValue()) {
                    if (bl) {
                        Field462.drawStringWithShadow(String.valueOf(c), f + 0.0f + f8, f2, Class36.Method564(f3, f4, f5, (float)n * f6));
                    } else {
                        Field462.drawString(String.valueOf(c), f + 0.0f + f8, f2, Class36.Method564(f3, f4, f5, (float)n * f6));
                    }
                    f8 += (float)Field462.getStringWidth(String.valueOf(c)) + f7;
                } else {
                    if (bl) {
                        Field460.Method554(String.valueOf(c), f + 0.0f + f8, f2, Class36.Method564(f3, f4, f5, (float)n * f6));
                    } else {
                        Field460.Method556(String.valueOf(c), f + 0.0f + f8, f2, Class36.Method564(f3, f4, f5, (float)n * f6));
                    }
                    f8 += (float)Field460.Method563(String.valueOf(c)) + f7;
                }
            } else {
                if (bl) {
                    Globals.mc.fontRenderer.drawStringWithShadow(String.valueOf(c), f + 0.0f + f8, f2, Class36.Method564(f3, f4, f5, (float)n * f6));
                } else {
                    Globals.mc.fontRenderer.drawString(String.valueOf(c), f + 0.0f + f8, f2, Class36.Method564(f3, f4, f5, (float)n * f6), false);
                }
                f8 += (float)Globals.mc.fontRenderer.getStringWidth(String.valueOf(c));
            }
            ++n;
        }
    }

    public static void Method565(String string, float f, float f2, Color color, float f3, boolean bl) {
        Class36.Method566(string, f, f2, color, f3, bl, 0.0f);
    }

    public static void Method566(String string, float f, float f2, Color color, float f3, boolean shadow, float spacing) {
        char[] chars = string.toCharArray();
        int index = 1;
        float offsetX = 0.0f;
        StringBuilder colorCode = new StringBuilder();
        boolean readingColorCode = false;

        for (char c : chars) {
            if (c == '\u00a7') {
                readingColorCode = true;
                colorCode.append(c);
                continue;
            }

            if (readingColorCode) {
                colorCode.append(c);
                readingColorCode = false;
                continue;
            }

            int rgb = Method567(color, index * f3);
            String charStr = String.valueOf(c);
            float currentX = f + offsetX;

            if (Manager.moduleManager.isModuleEnabled(CustomFont.class)) {
                CustomFont customFont = (CustomFont)Manager.moduleManager.getModuleByClass(CustomFont.class);
                boolean newFont = (Boolean)customFont.newfontrenderer.getValue();

                if (newFont && Field462 != null) {
                    if (shadow) {
                        Field462.drawStringWithShadow(charStr, currentX, f2, rgb);
                    } else {
                        Field462.drawString(charStr, currentX, f2, rgb);
                    }
                    offsetX += Field462.getStringWidth(charStr) + spacing;
                } else if (Field460 != null) {
                    if (shadow) {
                        Field460.Method554(charStr, currentX, f2, rgb);
                    } else {
                        Field460.Method556(charStr, currentX, f2, rgb);
                    }
                    offsetX += Field460.Method563(charStr) + spacing;
                } else {
                    offsetX += Globals.mc.fontRenderer.getStringWidth(charStr);
                }
            } else {
                if (shadow) {
                    Globals.mc.fontRenderer.drawStringWithShadow(charStr, currentX, f2, rgb);
                } else {
                    Globals.mc.fontRenderer.drawString(charStr, currentX, f2, rgb, false);
                }
                offsetX += Globals.mc.fontRenderer.getStringWidth(charStr);
            }

            index++;
        }
    }


    public static int Method260() {
        if (Manager.moduleManager.isModuleEnabled(CustomFont.class)) {
            return (Boolean)((CustomFont)Manager.moduleManager.getModuleByClass(CustomFont.class)).newfontrenderer.getValue() != false ? Class36.Field462.FONT_HEIGHT : Field460.Field313.getGlTextureId();
        }
        return Globals.mc.fontRenderer.FONT_HEIGHT;
    }

    private static int Method567(Color color, float f) {
        float[] fArray = new float[3];
        int n = color.getRed();
        int n2 = color.getGreen();
        int n3 = color.getBlue();
        Color.RGBtoHSB(n, n2, n3, fArray);
        return Color.HSBtoRGB(fArray[0] + f, fArray[1], fArray[2]);
    }

    private static int Method564(float f, float f2, float f3, float f4) {
        double d = Math.ceil((double)System.currentTimeMillis() / (20.0 * (double)f));
        return Color.getHSBColor((float)((d %= 360.0) / 360.0) + f4, f2, f3).getRGB();
    }

    static {
        Field463 = new Class197(new Font("Arial", 0, 18), true, false);
        Field464 = Globals.mc.fontRenderer;
        try {
            Field465 = new Class197(Font.createFont(0, Class197.class.getResourceAsStream("/assets/Quicksand-Medium.ttf")).deriveFont(20.0f), true, false);
        }
        catch (FontFormatException | IOException exception) {
            exception.printStackTrace();
        }
    }
}
