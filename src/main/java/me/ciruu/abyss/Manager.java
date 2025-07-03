package me.ciruu.abyss;
import java.io.File;

import me.ciruu.abyss.managers.CapeManager;
import me.ciruu.abyss.managers.ModuleManager;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.modules.client.Client;
import me.ciruu.abyss.modules.misc.DiscordRPC;
import me.ciruu.abyss.util.SplashProgress;
import net.minecraft.client.audio.ISound;
import net.minecraft.client.audio.PositionedSoundRecord;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.lwjgl.opengl.Display;

public class Manager {
    public static boolean Field1634 = true;
    public static boolean beta;
    public static Manager INSTANCE;
    public static final Logger logger;
    public static ModuleManager moduleManager;
    public static Class464 Field1638;
    public static Class92 Field223;
    public static boolean unload;
    public static Class465 Field1639;
    public static Class466 Field1640;
    public static CapeManager capeManager;
    public static Class79 Field489;
    public static Class195 Field456;
    public static Class467 Field1642;
    public static Class125 Field298;
    public static Class177 Field1643;
    public static Class468 Field1644;
    public static Class381 Field1255;
    public static Class469 Field1645;
    public static Class470 Field1646;
    public static Class127 Field424;
    public static File Field1647;
    public static Class471 Field1648;
    public static Class472 Field1649;
    public static Class105 Field255;
    public static Class219 GUI;
    public static Class111 Field280;
    public static Class176 Field375;
    public static long Field377;

    public Manager() {
        INSTANCE = this;
        beta = DRM.isBeta();
    }

    public void preinit(FMLPreInitializationEvent fMLPreInitializationEvent) {
        SplashProgress.setProgress(6, "Pre initializing...");
        Field1646 = new Class470();
        MinecraftForge.EVENT_BUS.register((Object)Field1646);
        Field1647 = fMLPreInitializationEvent.getSourceFile();
    }

    public void init(FMLInitializationEvent fMLInitializationEvent) {
        Class475.Method2015();
        Class36.Method546();
        Class208.Method2016();
        Field1640.Method2017();
        Field255 = new Class105();
        Field255.Method2018();
        Field1649 = new Class472();
        Field1649.Method2019();
        Field1648 = new Class471();
        Field1648.Method2020();
        moduleManager.mods();
        Field1638.Method2022();
        Field223.Method1759();
        Field1639.Method2023();
        Field489.Method193();
        Field1645 = new Class469();
        Field298 = new Class125();
        Field1643 = new Class177();
        Field424 = new Class127();
        GUI = new Class219(null);
        Field280 = new Class111();
        Module.onProfileSettings();
        Field1644.Method2024();
        Field298.Method2025();
        Field1255.Method2026();
        if (moduleManager.isModuleEnabled(DiscordRPC.class)) {
            me.ciruu.abyss.modules.client.DiscordRPC.start();
        }
    }

    public void postinit(FMLPostInitializationEvent fMLPostInitializationEvent) {
        Display.setTitle((String)"Abyss 1.6.0");
        SplashProgress.setProgress(7, "Loading completed...");
        if (moduleManager.getModuleByClass(Client.class) != null && ((Boolean)((Client)Manager.moduleManager.getModuleByClass(Client.class)).startupsound.getValue()).booleanValue()) {
            Globals.mc.soundHandler.playSound((ISound)PositionedSoundRecord.getRecord((SoundEvent)Class470.Field1651, (float)1.0f, (float)1.0f));
        }
    }

    static {
        INSTANCE = AbyssMod.manager;
        logger = LogManager.getLogger((String)"Abyss");
        moduleManager = new ModuleManager();
        Field1638 = new Class464();
        Field223 = new Class92();
        Field1639 = new Class465();
        Field1640 = new Class466();
        capeManager = new CapeManager();
        Field489 = new Class79();
        Field456 = new Class195();
        Field1642 = new Class467();
        Field1644 = new Class468(null, 20);
        Field1255 = new Class381();
    }

    public void unload() {

    }

}