package me.ciruu.abyss.enums;

public enum Class600 {
    BLACK("BLACK", '0'),
    DARK_BLUE("DARK_BLUE", '1'),
    DARK_GREEN("DARK_GREEN", '2'),
    DARK_AQUA("DARK_AQUA", '3'),
    DARK_RED("DARK_RED", '4'),
    DARK_PURPLE("DARK_PURPLE", '5'),
    GOLD("GOLD", '6'),
    GRAY("GRAY", '7'),
    DARK_GRAY("DARK_GRAY", '8'),
    BLUE("BLUE", '9'),
    GREEN("GREEN", 'a'),
    AQUA("AQUA", 'b'),
    RED("RED", 'c'),
    LIGHT_PURPLE("LIGHT_PURPLE", 'd'),
    YELLOW("YELLOW", 'e'),
    WHITE("WHITE", 'f');

    public String Field2513;
    private String Field2514;

    /*
     * WARNING - void declaration
     */
    private Class600() {
        String var4_2 = new String();
        String var3_1 = new String();
        this.Field2513 = var3_1;
        this.Field2514 = "?" + (String)var4_2;
    }

    Class600(String black, char c) {
    }

    public String toString() {
        return this.Field2514;
    }
}
