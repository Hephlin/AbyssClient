package me.ciruu.abyss;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.enums.Class83;
import me.ciruu.abyss.events.player.EventPlayerUpdate;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class Class82
extends Module {
    private final Setting Field184 = new Setting("Radious", "", (Module)this, (Object)10, 0, 30);
    private final Setting Field185 = new Setting("HideOwn", "", this, false);
    private final Setting Field186 = new Setting("RenderOnlyInFOV", "", this, false);
    public Setting Field187 = new Setting("RenderBox", "RenderBox", this, (Object)Class83.CustomBox);
    private final Setting Field188 = new Setting("Obsidian color", "", this, new Color(255, 255, 0, 125));
    private final Setting Field189 = new Setting("Box", "Box", this, true);
    private final Setting Field190 = new Setting("Outline", "Outline", this, true);
    private final Setting Field191 = new Setting("LineWidth", "LineWidth", (Module)this, (Object)Float.valueOf(1.5f), Float.valueOf(0.1f), Float.valueOf(5.0f));
    private final Setting Field192 = new Setting("Bedrock color", "", this, new Color(0, 255, 0, 125));
    private final Setting Field193 = new Setting("Box", "Box", this, true);
    private final Setting Field194 = new Setting("Outline", "Outline", this, true);
    private final Setting Field195 = new Setting("LineWidth", "LineWidth", (Module)this, (Object)Float.valueOf(1.5f), Float.valueOf(0.1f), Float.valueOf(5.0f));
    public static List Field196 = new ArrayList();
    @EventHandler
    private Listener Field197 = new Listener<EventPlayerUpdate>(this::Method202, new Predicate[0]);
    @EventHandler
    private Listener Field198 = new Listener<Class66>(this::addSetting, new Predicate[0]);

    public Class82() {
        super("HoleESP", "HoleESP aka SmashESP", Category.RENDER, "");
        this.addSetting(this.Field187);
        this.addSetting(this.Field185);
        this.addSetting(this.Field186);
        this.addSetting(this.Field184);
        this.addSetting(this.Field188);
        this.addSetting(this.Field189);
        this.addSetting(this.Field190);
        this.addSetting(this.Field191);
        this.addSetting(this.Field192);
        this.addSetting(this.Field193);
        this.addSetting(this.Field194);
        this.addSetting(this.Field195);
    }

    private void addSetting(Class66 class66) {
        for (Object blockPos420 : Field196) {
            if (blockPos420 instanceof List) {
                BlockPos blockPos = (BlockPos) blockPos420;
                if (((Boolean) this.Field185.getValue()).booleanValue() && blockPos.equals((Object) new BlockPos(Globals.mc.player.posX, Globals.mc.player.posY, Globals.mc.player.posZ)) || ((Boolean) this.Field186.getValue()).booleanValue() && !Class84.Method205(blockPos))
                    continue;
                if (Class85.Method206(blockPos)) {
                    if (this.Field187.getValue() == Class83.FullBox) {
                        Class50.Method137(blockPos, (Color) this.Field192.getValue(), true, (Color) this.Field192.getValue(), ((Float) this.Field195.getValue()).floatValue(), (Boolean) this.Field194.getValue(), (Boolean) this.Field193.getValue(), ((Color) this.Field192.getValue()).getAlpha(), true);
                        continue;
                    }
                    if (this.Field187.getValue() != Class83.CustomBox) continue;
                    Class50.Method207(blockPos, (Color) this.Field192.getValue(), true, (Color) this.Field192.getValue(), ((Float) this.Field195.getValue()).floatValue(), (Boolean) this.Field194.getValue(), (Boolean) this.Field193.getValue(), ((Color) this.Field192.getValue()).getAlpha(), true);
                    continue;
                }
                if (!Class85.Method208(blockPos)) continue;
                if (this.Field187.getValue() == Class83.FullBox) {
                    Class50.Method137(blockPos, (Color) this.Field188.getValue(), true, (Color) this.Field188.getValue(), ((Float) this.Field191.getValue()).floatValue(), (Boolean) this.Field190.getValue(), (Boolean) this.Field189.getValue(), ((Color) this.Field188.getValue()).getAlpha(), true);
                    continue;
                }
                if (this.Field187.getValue() != Class83.CustomBox) continue;
                Class50.Method207(blockPos, (Color) this.Field188.getValue(), true, (Color) this.Field188.getValue(), ((Float) this.Field191.getValue()).floatValue(), (Boolean) this.Field190.getValue(), (Boolean) this.Field189.getValue(), ((Color) this.Field188.getValue()).getAlpha(), true);
            }
        }
    }

    private void Method202(EventPlayerUpdate eventPlayerUpdate) {
        Field196 = (List) Class31.Method210(Class30.Method209((EntityPlayer)Globals.mc.player), ((Integer)this.Field184.getValue()).intValue(), (Integer)this.Field184.getValue(), false, true, 0).stream().filter(Class82::Method211).collect(Collectors.toList());
    }

    private static boolean Method211(Object o) {
        BlockPos blockPos = null;
        o = (Object)Method211(blockPos);
        return Method211(o);
    }

    private static boolean Method211(BlockPos blockPos) {
        return Class85.Method208(blockPos) && Globals.mc.world.getBlockState(blockPos).getBlock() == Blocks.AIR && Globals.mc.world.getBlockState(blockPos.up(1)).getBlock() == Blocks.AIR && Globals.mc.world.getBlockState(blockPos.up(2)).getBlock() == Blocks.AIR;
    }
}
