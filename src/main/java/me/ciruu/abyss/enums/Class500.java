package me.ciruu.abyss.enums;

/*
 * Exception performing whole class analysis ignored.
 */
public enum Class500 {
    LEFT(0),
    MIDDLE(1),
    RIGHT(2);

    private int Field3576;

    /*
     * WARNING - Possible parameter corruption
     * WARNING - void declaration
     */
    private Class500() {
        int var3_1 = 0x0b;
        this.Field3576 = var3_1;
    }

    Class500(int i) {
    }

    private int getIndex() {
        return this.Field3576;
    }

    public static int Method2191(Class500 class500) {
        return class500.getIndex();
    }
}
