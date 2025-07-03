package me.ciruu.abyss.enums;

/*
 * Exception performing whole class analysis ignored.
 */
public enum Category {
    COMBAT("Combat", -65536),
    EXPLOIT("Exploit", -65536),
    MISC("Misc", -65536),
    MOVEMENT("Movement", -65536),
    RENDER("Render", -65536),
    HUD("Hud", -65536),
    CLIENT("Client", -65536);

    private String Field3224 = null;
    private int Field3225 = 0x0b;

    /*
     * WARNING - void declaration
     */
    private Category() {
        int var4_2 = 0x0b;
        String var3_1 = new String().toLowerCase();
        this.Field3224 = var3_1;
        this.Field3225 = var4_2;
    }

    Category(String combat, int i) {

    }

    public int getColour() {
        return this.Field3225;
    }

    public String toString() {
        return this.Field3224;
    }
}
