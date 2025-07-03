package me.ciruu.abyss;

import java.lang.reflect.Field;
import java.lang.reflect.ReflectPermission;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;

import me.ciruu.abyss.modules.misc.PacketLogNew;
import net.minecraft.util.StringUtils;

/*
 * Exception performing whole class analysis ignored.
 */
public class Class700
implements PrivilegedExceptionAction {
    final Field Field6;
    final Class700 Field7;
    final Class Field8;
    final PacketLogNew Field9;

    Class700(PacketLogNew class6, Field field, Class700 packet, Class clazz) {
        this.Field9 = class6;
        this.Field6 = field;
        this.Field7 = packet;
        this.Field8 = clazz;
    }

    public Object run() throws Exception {
        Object[] objectArray;
        try {
            objectArray = new ReflectPermission[]{new ReflectPermission("suppressAccessChecks")};
            System.out.println("Permission granted");
        }
        catch (SecurityException securityException) {
            System.out.println("Permission denied");
        }
        if (this.Field6.getType().getSimpleName().equals(this.Field7)) {
            PacketLogNew.getPacketByClassString(this.Field9, StringUtils.stripControlCodes((String)("" + this.Field6.getType().getSimpleName() + "" + this.Field6.getName())));
            System.out.println(StringUtils.stripControlCodes((String)("" + this.Field6.getType().getSimpleName() + "" + this.Field6.getName())));
            return null;
        }
        if (this.Field6.getType() == this.Field8) {
            PacketLogNew.getPacketByClassString(this.Field9, StringUtils.stripControlCodes((String)("" + this.Field6.getType().getSimpleName() + "" + this.Field6.getName())));
            System.out.println(StringUtils.stripControlCodes((String)("" + this.Field6.getType().getSimpleName() + "" + this.Field6.getName())));
            return null;
        }
        if (!this.Field6.isAccessible()) {
            this.Field6.setAccessible(true);
        }
        if (this.Field6.get(this.Field7) instanceof String[]) {
            objectArray = (String[])this.Field6.get(this.Field7);
            PacketLogNew.getPacketByClassString(this.Field9, StringUtils.stripControlCodes((String)("" + this.Field6.getType().getSimpleName() + "" + this.Field6.getName() + " =" + Arrays.toString(objectArray))));
            System.out.println(StringUtils.stripControlCodes((String)("" + this.Field6.getType().getSimpleName() + "" + this.Field6.getName() + " =" + Arrays.toString(objectArray))));
            return null;
        }
        if (this.Field6.get(this.Field7) instanceof int[]) {
            objectArray = (Object[]) this.Field6.get(this.Field7);
            PacketLogNew.getPacketByClassString(this.Field9, StringUtils.stripControlCodes((String)("" + this.Field6.getType().getSimpleName() + "" + this.Field6.getName() + " =" + Arrays.toString((Object[])objectArray))));
            System.out.println(StringUtils.stripControlCodes((String)("" + this.Field6.getType().getSimpleName() + "" + this.Field6.getName() + " =" + Arrays.toString((Object[])objectArray))));
            return null;
        }
        PacketLogNew.getPacketByClassString(this.Field9, StringUtils.stripControlCodes((String)("" + this.Field6.getType().getSimpleName() + "" + this.Field6.getName() + " =" + this.Field6.get(this.Field7))));
        System.out.println(StringUtils.stripControlCodes((String)("" + this.Field6.getType().getSimpleName() + "" + this.Field6.getName() + " =" + this.Field6.get(this.Field7))));
        return null;
    }
}
