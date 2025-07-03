package me.ciruu.abyss.util;

import java.util.ArrayList;
import java.util.List;
import me.ciruu.abyss.Class251;
import me.ciruu.abyss.Manager;
import me.ciruu.abyss.managers.CapeManager;
import me.ciruu.abyss.modules.client.Capes;
import net.minecraft.util.ResourceLocation;

/*
 * Exception performing whole class analysis ignored.
 */
public class Cape {
    private final String Field1179;
    private final String playerName;
    private final boolean Field1181;
    private int Field1182 = 20;
    private final List Field1183;

    public Cape(String string, String string2, boolean bl) {
        this.Field1179 = string;
        this.playerName = string2;
        this.Field1181 = bl;
        this.Field1183 = new ArrayList(1);
    }

    public boolean Method1543() {
        return !this.Field1183.isEmpty();
    }

    public ResourceLocation Method1544() {
        float f = ((Capes)Manager.moduleManager.getModuleByClass(Capes.class)).animated();
        float f2 = f / 20.0f * 8.0f / (float)this.Field1182;
        return (ResourceLocation)this.Field1183.get((int)f2 % this.Field1183.size());
    }
}
