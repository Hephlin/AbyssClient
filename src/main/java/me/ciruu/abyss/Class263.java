package me.ciruu.abyss;

import me.ciruu.abyss.enums.Class264;
import java.lang.Enum;

public final class Class263 {
    public static final int[] Field706 = new int[Class264.values().length];
    public static final int[] Field707;
    static {
        Class263.Field706[Class264.HEX.hashCode()] = 1;
        Class263.Field706[Class264.RGBA.hashCode()] = 2;
        Class263.Field706[Class264.HSL.hashCode()] = 3;
        Field707 = new int[Class264.values().length];
        Class263.Field707[Class264.HEX.hashCode()] = 1;
        Class263.Field707[Class264.RGBA.hashCode()] = 2;
        Class263.Field707[Class264.HSL.hashCode()] = 3;
    }

    /**
     * Sole constructor.  Programmers cannot invoke this constructor.
     * It is for use by code emitted by the compiler in response to
     * enum type declarations.
     *
     * @param name    - The name of this enum constant, which is the identifier
     *                used to declare it.
     * @param ordinal - The ordinal of this enumeration constant (its position
     *                in the enum declaration, where the initial constant is assigned
     *                an ordinal of zero).
     */
    Class263(String name, int ordinal) {
        super();
    }
}
