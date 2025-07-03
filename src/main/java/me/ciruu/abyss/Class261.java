package me.ciruu.abyss;

import me.ciruu.abyss.events.MinecraftEvent;

import java.lang.reflect.Field;

public class Class261
extends MinecraftEvent {
    public static Object getPrivateField(Object instance, String name, String srgName) {
        try {
            Field field = null;
            try {
                field = instance.getClass().getDeclaredField(name);
            } catch (NoSuchFieldException e) {
                field = instance.getClass().getDeclaredField(srgName);
            }
            field.setAccessible(true);
            return field.get(instance);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
