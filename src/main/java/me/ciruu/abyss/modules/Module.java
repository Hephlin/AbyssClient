package me.ciruu.abyss.modules;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;
import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import me.ciruu.abyss.*;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.enums.Class128;
import me.ciruu.abyss.managers.RunnableManager;
import me.ciruu.abyss.modules.render.HoleESP;
import me.ciruu.abyss.modules.render.PearlTracker;
import me.ciruu.abyss.settings.Setting;
import me.zero.alpine.listener.Listenable;
import net.minecraft.client.Minecraft;
import me.ciruu.abyss.Manager;
import me.ciruu.abyss.modules.client.Alias;
import me.ciruu.abyss.modules.client.BubbleGUI;
import me.ciruu.abyss.modules.client.Capes;
import me.ciruu.abyss.modules.client.ChatNotifier;
import me.ciruu.abyss.modules.client.ClickGUI;
import me.ciruu.abyss.modules.client.Client;
import me.ciruu.abyss.modules.client.CustomFont;
import me.ciruu.abyss.modules.client.HUDColorSync;
import me.ciruu.abyss.modules.client.NameChanger;
import me.ciruu.abyss.modules.client.Particles;
import me.ciruu.abyss.modules.client.ScreenShaders;
import me.ciruu.abyss.modules.client.VoiceAssistant;
import me.ciruu.abyss.modules.client.WebChat;
import me.ciruu.abyss.modules.combat.AntiHoleCamp;
import me.ciruu.abyss.modules.combat.AntiRegear;
import me.ciruu.abyss.modules.combat.AutoArmor;
import me.ciruu.abyss.modules.combat.AutoBed;
import me.ciruu.abyss.modules.combat.AutoCity;
import me.ciruu.abyss.modules.combat.AutoCrystal;
import me.ciruu.abyss.modules.combat.AutoMinecart;
import me.ciruu.abyss.modules.combat.AutoTrap;
import me.ciruu.abyss.modules.combat.AutoWeb;
import me.ciruu.abyss.modules.combat.BowSpam;
import me.ciruu.abyss.modules.combat.Burrow;
import me.ciruu.abyss.modules.combat.Criticals;
import me.ciruu.abyss.modules.combat.HoleFillRewrite;
import me.ciruu.abyss.modules.combat.HoleFiller;
import me.ciruu.abyss.modules.combat.KillAura;
import me.ciruu.abyss.modules.combat.MultiTask;
import me.ciruu.abyss.modules.combat.OffHand;
import me.ciruu.abyss.modules.combat.Predict;
import me.ciruu.abyss.modules.combat.SelfTrap;
import me.ciruu.abyss.modules.combat.Surround;
import me.ciruu.abyss.modules.combat.Trigger;
import me.ciruu.abyss.modules.combat.WebFill;
import me.ciruu.abyss.modules.exploit.*;
import me.ciruu.abyss.modules.hud.ArmourHUD;
import me.ciruu.abyss.modules.hud.BindList;
import me.ciruu.abyss.modules.hud.ChatWatermark;
import me.ciruu.abyss.modules.hud.CombatInfo;
import me.ciruu.abyss.modules.hud.Coordinates;
import me.ciruu.abyss.modules.hud.CrystalCounter;
import me.ciruu.abyss.modules.hud.CustomIngameGUI;
import me.ciruu.abyss.modules.hud.DVDLogo;
import me.ciruu.abyss.modules.hud.Greeter;
import me.ciruu.abyss.modules.hud.HitCrosshair;
import me.ciruu.abyss.modules.hud.InfoList;
import me.ciruu.abyss.modules.hud.InvViewer;
import me.ciruu.abyss.modules.hud.Logo;
import me.ciruu.abyss.modules.hud.Notifications;
import me.ciruu.abyss.modules.hud.PlayerViewer;
import me.ciruu.abyss.modules.hud.Radar;
import me.ciruu.abyss.modules.hud.TargetHUD;
import me.ciruu.abyss.modules.hud.TotemCounter;
import me.ciruu.abyss.modules.hud.VoiceResult;
import me.ciruu.abyss.modules.hud.Watermark;
import me.ciruu.abyss.modules.hud.WatermarkCool;
import me.ciruu.abyss.modules.hud.WebChatList;
import me.ciruu.abyss.modules.misc.AimBot;
import me.ciruu.abyss.modules.misc.AntiAFK;
import me.ciruu.abyss.modules.misc.AutoClicker;
import me.ciruu.abyss.modules.misc.AutoDisconnect;
import me.ciruu.abyss.modules.misc.AutoEz;
import me.ciruu.abyss.modules.misc.AutoFish;
import me.ciruu.abyss.modules.misc.AutoGap;
import me.ciruu.abyss.modules.misc.AutoRespawn;
import me.ciruu.abyss.modules.misc.BuildHeight;
import me.ciruu.abyss.modules.misc.ChatSuffix;
import me.ciruu.abyss.modules.misc.ChestInteract;
import me.ciruu.abyss.modules.misc.CrackedUtils;
import me.ciruu.abyss.modules.misc.DisableFriends;
import me.ciruu.abyss.modules.misc.DiscordRPC;
import me.ciruu.abyss.modules.misc.FakePlayer;
import me.ciruu.abyss.modules.misc.FastPlace;
import me.ciruu.abyss.modules.misc.HotbarRefill;
import me.ciruu.abyss.modules.misc.MCFriends;
import me.ciruu.abyss.modules.misc.MCPearl;
import me.ciruu.abyss.modules.misc.NoGlitchBlocks;
import me.ciruu.abyss.modules.misc.NoRotate;
import me.ciruu.abyss.modules.misc.NoSoundLag;
import me.ciruu.abyss.modules.misc.PacketLogger;
import me.ciruu.abyss.modules.misc.Spammer;
import me.ciruu.abyss.modules.misc.SpeedMine;
import me.ciruu.abyss.modules.misc.Timer;
import me.ciruu.abyss.modules.misc.TotemPopCount;
import me.ciruu.abyss.modules.misc.XCarry;
import me.ciruu.abyss.modules.misc.YawLock;
import me.ciruu.abyss.modules.movement.Anchor;
import me.ciruu.abyss.modules.movement.AntiVoid;
import me.ciruu.abyss.modules.movement.AutoWalk;
import me.ciruu.abyss.modules.movement.BoatFly;
import me.ciruu.abyss.modules.movement.EntitySpeed;
import me.ciruu.abyss.modules.movement.FastSwim;
import me.ciruu.abyss.modules.movement.Flight;
import me.ciruu.abyss.modules.movement.HighJump;
import me.ciruu.abyss.modules.movement.HoleTP;
import me.ciruu.abyss.modules.movement.LongJump;
import me.ciruu.abyss.modules.movement.NoSlowDown;
import me.ciruu.abyss.modules.movement.ReverseStep;
import me.ciruu.abyss.modules.movement.Scaffold;
import me.ciruu.abyss.modules.movement.Speed;
import me.ciruu.abyss.modules.movement.Sprint;
import me.ciruu.abyss.modules.movement.Step;
import me.ciruu.abyss.modules.movement.Velocity;
import me.ciruu.abyss.modules.movement.WebBypass;
import me.ciruu.abyss.modules.render.Aspect;
import me.ciruu.abyss.modules.render.BlockHighlight;
import me.ciruu.abyss.modules.render.BreakESP;
import me.ciruu.abyss.modules.render.BurrowESP;
import me.ciruu.abyss.modules.render.Chams;
import me.ciruu.abyss.modules.render.CityESP;
import me.ciruu.abyss.modules.render.CrystalESP;
import me.ciruu.abyss.modules.render.EnchantColor;
import me.ciruu.abyss.modules.render.Freecam;
import me.ciruu.abyss.modules.render.FullBright;
import me.ciruu.abyss.modules.render.ImageESP;
import me.ciruu.abyss.modules.render.ItemPhysics;
import me.ciruu.abyss.modules.render.ItemViewModel;
import me.ciruu.abyss.modules.render.LogoutSpot;
import me.ciruu.abyss.modules.render.NameTags;
import me.ciruu.abyss.modules.render.NoRender;
import me.ciruu.abyss.modules.render.OffhandSwing;
import me.ciruu.abyss.modules.render.OldAnimations;
import me.ciruu.abyss.modules.render.Skeleton;
import me.ciruu.abyss.modules.render.SkyColor;
import me.ciruu.abyss.modules.render.StorageESP;
import me.ciruu.abyss.modules.render.TabFriends;
import me.ciruu.abyss.modules.render.Tooltips;
import me.ciruu.abyss.modules.render.Tracers;
import me.ciruu.abyss.modules.render.Trajectories;
import me.ciruu.abyss.modules.render.ViewClip;
import me.ciruu.abyss.modules.render.VoidESP;
import me.ciruu.abyss.modules.render.WallHack;
import me.ciruu.abyss.modules.render.WebESP;

import static me.ciruu.abyss.managers.ModuleManager.module_val;

public class Module
extends Class703
implements Class205,
Listenable {
    public static final Object module = new Object();
    private final String description;
    private boolean status;
    private String context;
    private float Field484 = 0.0f;
    public Setting setting;
    private static final List<Setting> settings = new ArrayList<>();
    private Category category;

    public boolean disabled = this.moduleDisabled();
    public boolean enabled = this.moduleEnabled();

    public boolean moduleEnabled() {
        this.getEnable();
        return moduleEnabled();
    }

    public boolean moduleDisabled() {
        this.getDisable();
        return this.moduleDisabled();
    }
    public Module(String name, String description, Category category, Boolean status) {
        super(name, category);
        this.description = description;
        this.status = status;
        this.context = "NONE";
        this.enabled = false;
    }

    public Module(String name, String description, Category category, String string3) {
        super(name, category);
        this.description = description;
        this.context = string3;
        this.enabled = false;
    }

    public String moduleName() {
        if (Module.module instanceof Module) {
            return ((Module) Module.module).moduleName();
        } else {
            return moduleName();
        }
    }


    public void moduleStatus() {
        this.status = !this.status;
        this.isEnabled(this.status);
    }

    public boolean getEnable() {
        this.Field484 = 0.0f;
        AbyssMod.EVENT_BUS.subscribe(this);
        if (Manager.moduleManager.getModuleByClass(Notifications.class) != null && ((Boolean) ((Notifications) Manager.moduleManager.getModuleByClass(Notifications.class)).toggleFeature.getValue()).booleanValue()) {
            Manager.Field424.Method342(Class128.Info, ChatFormatting.WHITE + "" + ChatFormatting.GREEN + " enabled!");
        } else {
            new Notifications().getDisable();
        }
        if (Manager.moduleManager.getModuleByClass(ChatNotifier.class) != null && Manager.moduleManager.isModuleEnabled(ChatNotifier.class)) {
            Class547.printNewChatMessage(ChatFormatting.WHITE + "" + ChatFormatting.GREEN + " ON");
        } else {
            new ChatNotifier().getDisable();
        }
        return false;
    }

    public void getDisable() {
        AbyssMod.EVENT_BUS.unsubscribe(this);
        if (Manager.moduleManager.getModuleByClass(Notifications.class) != null && ((Boolean) ((Notifications) Manager.moduleManager.getModuleByClass(Notifications.class)).toggleFeature.getValue()).booleanValue()) {
            Manager.Field424.Method342(Class128.Info, ChatFormatting.WHITE + "" + ChatFormatting.RED + " disabled!");
        } else {
            new Notifications().getDisable();
        }
        if (Manager.moduleManager.getModuleByClass(ChatNotifier.class) != null && Manager.moduleManager.isModuleEnabled(ChatNotifier.class)) {
            Class547.printNewChatMessage(ChatFormatting.WHITE + "" + ChatFormatting.RED + " OFF");
        } else {
            new ChatNotifier().getDisable();
        }
    }

    private JsonObject getLoad() {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("enabled", Boolean.valueOf(this.getContext()));
        jsonObject.addProperty("display", this.getContext());
        jsonObject.addProperty("keybind", this.getContext());
        jsonObject.addProperty("hidden", Boolean.valueOf(this.Method492()));
        JsonObject jsonObject2 = new JsonObject();
        for (Setting setting : getSettings()) {
            if (setting instanceof Setting) {
                JsonPrimitive jsonPrimitive;
                String string;
                Object object = new Object();
                if (object instanceof Object) {
                    object = setting.getValue();
                    if (object instanceof Class25) continue;
                    JsonObject jsonObject3 = new JsonObject();
                    try {
                        string = object.getClass().getSimpleName();
                    } catch (InternalError internalError) {
                        string = object.getClass().getName();
                    }
                    if (object instanceof Integer) {
                        jsonPrimitive = new JsonPrimitive(String.valueOf(object));
                    } else if (object instanceof String) {
                        jsonPrimitive = new JsonPrimitive((String) object);
                    } else if (object instanceof Boolean) {
                        jsonPrimitive = new JsonPrimitive((Boolean) object);
                    } else if (object instanceof Float) {
                        jsonPrimitive = new JsonPrimitive(((Float) object));
                    } else if (object instanceof Double) {
                        jsonPrimitive = new JsonPrimitive(String.valueOf(object));
                    } else if (object instanceof Enum) {
                        string = "Enum";
                        jsonPrimitive = new JsonPrimitive(((Enum) object).ordinal());
                    } else if (object instanceof Color) {
                        JsonArray jsonArray = new JsonArray();
                        jsonArray.add(((Color) object).getRed());
                        jsonArray.add(((Color) object).getGreen());
                        jsonArray.add(((Color) object).getBlue());
                        jsonArray.add(((Color) object).getAlpha());
                        jsonPrimitive = jsonArray.getAsJsonPrimitive();
                    } else if (object instanceof Class207) {
                        string = "Bind";
                        jsonPrimitive = new JsonPrimitive(((Class207) object).Method592());
                    } else {
                        // Objectively Called setting.getValue()
                        throw new IllegalStateException("No se puede guardar" + object.getClass() + ", valor:" + ((Setting) object).getValue());
                    }
                    jsonObject3.addProperty("type", string);
                    jsonObject3.add("value", jsonPrimitive);
                    jsonObject2.add(setting.Method396(), jsonObject3);
                } else {
                    object = setting.getValue();
            }
            } else {

            }
        }
        return jsonObject;
    }

    private void getUnload(JsonObject jsonObject) {
        this.isDisabled(jsonObject.get("enabled").getAsBoolean());
        this.moduleContext(jsonObject.get("keybind").getAsString());
        this.Method585(jsonObject.get("hidden").getAsBoolean());
        this.createJson(jsonObject.get("modules").getAsJsonArray().getAsJsonObject());
        if (!jsonObject.has("settings")) {
            return;
        }
        JsonObject jsonObject2 = jsonObject.get("settings").getAsJsonObject();
        JsonObject jsonObject3 = this.getLoad().getAsJsonObject("settings");
        for (Object jsonObject4 : jsonObject2.entrySet()) {
            if (jsonObject4 instanceof JsonObject) {
                if (!jsonObject2.has(setting.Method396())) continue;
                try {
                    jsonObject4 = jsonObject3.get(setting.Method396()).getAsJsonObject();
                    JsonObject jsonObject5 = jsonObject2.get(setting.Method396()).getAsJsonObject();
                    String string = ((JsonObject)jsonObject4).get("type").getAsString();
                    String string2 = jsonObject5.get("type").getAsString();
                    JsonElement jsonElement = jsonObject5.get("value");
                    if (!string.equals(string2)) {
                        System.err.println("Setting type changed from" + string2 + " to" + string + ", reverting to default (" + setting.Method396() + ")");
                        setting.setValue(setting.Method595());
                        continue;
                    }
                    switch (string2) {
                        case "Integer": {
                            setting.setValue(jsonElement.getAsNumber().intValue());
                            break;
                        }
                        case "Boolean": {
                            setting.setValue(jsonElement.getAsBoolean());
                            break;
                        }
                        case "String": {
                            setting.setValue(jsonElement.getAsString());
                            break;
                        }
                        case "Float": {
                            setting.setValue(Float.valueOf(jsonElement.getAsNumber().floatValue()));
                            break;
                        }
                        case "Double": {
                            setting.setValue(jsonElement.getAsNumber().doubleValue());
                            break;
                        }
                        case "Enum": {
                            Object[] objArray = setting.getValue().getClass().getEnumConstants();
                            setting.setValue(objArray[jsonElement.getAsInt()]);
                            break;
                        }
                        case "Color": {
                            JsonArray jsonArray = jsonElement.getAsJsonArray();
                            setting.setValue(new Color(jsonArray.get(0).getAsInt(), jsonArray.get(1).getAsInt(), jsonArray.get(2).getAsInt(), jsonArray.get(3).getAsInt()));
                            break;
                        }
                        case "Bind": {
                            setting.setValue(new Class207(jsonElement.getAsInt()));
                        }
                    }
                } catch (Exception exception) {
                    System.err.println("Corrupted value for" + setting.Method396() + ", ignoring");
                    setting.setValue(setting.Method595());
                }
            }
        }
    }

    public void getFeatures() {
        File file = new File("Abyss/Features/" + Class208.Field486 + "/" + ".json");
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        JsonObject jsonObject = this.getLoad();
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(file.toPath());
            gson.toJson(jsonObject, bufferedWriter);
            bufferedWriter.close();
        } catch (IOException iOException) {
            iOException.printStackTrace();
        }
    }

    public static void getSaveSettings() {
        System.out.println("Saving all settings...");
        try {
            RunnableManager.runRunnable(Module::modulesMain);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Saved all settings!");
    }

    public void settingTask() {
        JsonObject jsonObject;
        File file = new File("Abyss/Features/" + Class208.Field486 + "/" + ".json");
        if (!file.exists()) {
            return;
        }
        try {
            BufferedReader bufferedReader = Files.newBufferedReader(file.toPath());
            jsonObject = new JsonParser().parse(bufferedReader).getAsJsonObject();
            bufferedReader.close();
        } catch (Exception exception) {
            exception.printStackTrace();
            return;
        }
        Minecraft.getMinecraft().addScheduledTask(() -> this.createJson(jsonObject));
    }

    public static void onProfileSettings() {
        RunnableManager.runRunnable(Module::settingsMain);
    }

    private void createJson(JsonObject jsonObject) {
        this.getUnload(jsonObject);
    }

    private static boolean modulesMain() {
        synchronized (module) {
            for (Object mod_ : Manager.moduleManager.getModules().values()) {
                if (mod_ instanceof Module) {
                    mod_ = module;
                    Module mod = (Module) mod_;
                    mod.settingTask();
                }
            }
        }
        return modulesMain();
    }

    private static Object settingsMain() {
        synchronized ((Setting)Module.settings) {
            for (Setting settings : getSettings()) {
                if (settings instanceof Setting) {
                    settings = (Setting) Module.settings;
                    if (Manager.moduleManager.getModules().values() != null) {
                        if (Module.module instanceof Module)
                            ((Module) module).settingTask();
                    }
                }
            }
        }
        System.out.println("+----(Loading all settings...)----+");
        System.out.println("Module:\n" + getSettings() + "\n\n");
        return settingsMain();
    }


    public static void addSetting(Setting setting) {
        settings.add(setting);
    }

    public static List<Setting> getSettings() {
        return settings;
    }

    public static void addModule(Module module) {
        module_val.put(module.getClass(), module);
    }

    
    public static void setup() {
        addModule(new Particles());
        addModule(new BubbleGUI());
        addModule(new ClickGUI());
        addModule(new HUDColorSync());
        addModule(new ChatWatermark());
        addModule(new NameChanger());
        addModule(new Alias());
        addModule(new ChatNotifier());
        addModule(new Client());
        addModule(new Capes());
        addModule(new CustomFont());
        addModule(new ScreenShaders());
        System.out.println("[(" + "\n\n Loaded Required Modules \n\n" + ")]");

        addModule(new VoiceAssistant());
        if (Manager.beta) {
            addModule(new WebChat());
        }
        addModule(new ChestExploit());
        addModule(new AntiHoleCamp());
        addModule(new AntiRegear());
        addModule(new AutoArmor());
        addModule(new AutoBed());
        addModule(new AutoCity());
        addModule(new AutoCrystal());
        addModule(new AutoMinecart());
        addModule(new AutoTrap());
        addModule(new AutoWeb());
        addModule(new BowSpam());
        addModule(new Burrow());
        addModule(new Criticals());
        addModule(new HoleFiller());
        addModule(new HoleFillRewrite());
        addModule(new KillAura());
        addModule(new MultiTask());
        addModule(new OffHand());
        if (Manager.beta) {
            addModule(new Predict());
        }
        addModule(new SelfTrap());
        addModule(new Surround());
        if (Manager.beta) {
        }
        addModule(new Trigger());
        addModule(new WebFill());
        addModule(new AutoMountDupe());
        addModule(new Blink());
        if (Manager.beta) {
            addModule(new ChunkLogger());
        }
        addModule(new EntityControl());
        addModule(new FrameDupe());
        addModule(new HandMine());
        addModule(new InstantMine());
        addModule(new Jesus());
        addModule(new LiquidInteract());
        addModule(new MountBypass());
        addModule(new NoBreakAnimation());
        addModule(new NoEntityTrace());
        addModule(new NoFall());
        addModule(new NoHandShake());
        addModule(new NoHunger());
        addModule(new NoSwing());
        addModule(new PacketFly());
        if (Manager.beta) {
            addModule(new PacketPhase());
        }
        addModule(new Phase());
        addModule(new PhaseTrap());
        addModule(new PortalGodMode());
        addModule(new Reach());
        if (Manager.beta) {
            addModule(new SuperKnockback());
        }
        if (Manager.beta) {
            addModule(new TickShift());
        }
        addModule(new ArmourHUD());
        addModule(new BindList());
        if (Manager.beta) {
            addModule(new CombatInfo());
        }
        addModule(new Coordinates());
        addModule(new CrystalCounter());
        if (Manager.beta) {
            addModule(new CustomIngameGUI());
        }
        addModule(new DVDLogo());
        addModule(new Greeter());
        addModule(new HitCrosshair());
        addModule(new InfoList());
        addModule(new InvViewer());
        addModule(new Logo());
        addModule(new me.ciruu.abyss.modules.hud.ArrayList());
        addModule(new Notifications());
        addModule(new PlayerViewer());
        addModule(new Radar());
        addModule(new TargetHUD());
        addModule(new TotemCounter());
        addModule(new VoiceResult());
        addModule(new Watermark());
        addModule(new WatermarkCool());
        if (Manager.beta) {
            addModule(new WebChatList());
        }
        addModule(new AimBot());
        addModule(new AntiAFK());
        addModule(new AutoClicker());
        addModule(new AutoDisconnect());
        addModule(new AutoEz());
        addModule(new AutoFish());
        addModule(new AutoGap());
        addModule(new AutoRespawn());
        addModule(new BuildHeight());
        addModule(new ChatSuffix());
        addModule(new ChestInteract());
        addModule(new CrackedUtils());
        addModule(new DisableFriends());
        addModule(new DiscordRPC());
        addModule(new FakePlayer());
        addModule(new FastPlace());
        addModule(new HotbarRefill());
        addModule(new MCFriends());
        addModule(new MCPearl());
        addModule(new NoGlitchBlocks());
        addModule(new NoRotate());
        addModule(new NoSoundLag());
        addModule(new PacketLogger());
        addModule(new Spammer());
        addModule(new SpeedMine());
        addModule(new Timer());
        addModule(new TotemPopCount());
        addModule(new XCarry());
        addModule(new YawLock());
        addModule(new Anchor());
        addModule(new AntiVoid());
        addModule(new AutoWalk());
        addModule(new BoatFly());
        addModule(new EntitySpeed());
        addModule(new FastSwim());
        addModule(new Flight());
        addModule(new HighJump());
        addModule(new HoleTP());
        addModule(new LongJump());
        addModule(new NoSlowDown());
        addModule(new ReverseStep());
        addModule(new Scaffold());
        addModule(new Speed());
        addModule(new Sprint());
        addModule(new Step());
        addModule(new Velocity());
        addModule(new WebBypass());
        addModule(new Aspect());
        addModule(new BlockHighlight());
        addModule(new BreakESP());
        addModule(new BurrowESP());
        addModule(new Chams());
        addModule(new CityESP());
        addModule(new EnchantColor());
        addModule(new CrystalESP());
        addModule(new Freecam());
        addModule(new FullBright());
        addModule(new HoleESP());
        addModule(new ImageESP());
        addModule(new ItemPhysics());
        addModule(new ItemViewModel());
        addModule(new LogoutSpot());
        addModule(new NameTags());
        addModule(new OldAnimations());
        addModule(new NoRender());
        addModule(new OffhandSwing());
        addModule(new PearlTracker());
        addModule(new Skeleton());
        addModule(new SkyColor());
        addModule(new StorageESP());
        addModule(new TabFriends());
        addModule(new Tooltips());
        addModule(new Tracers());
        addModule(new Trajectories());
        addModule(new ViewClip());
        addModule(new VoidESP());
        addModule(new WallHack());
        addModule(new WebESP());
    }

    public void Method684(String keyName) {
        this.moduleContext(keyName);
    }

    @SuppressWarnings("deprecation")
    public void enable() {
        this.moduleEnabled();
        this.getEnable();
    }

    @SuppressWarnings("deprecation")
    public void disable() {
        this.moduleDisabled();
        this.getDisable();
    }

    public static boolean isEnabled() {
        return true;
    }

    @Override
    public void Method1048() {

    }

    @Override
    public void Method1049() {

    }

    @Override
    public void Method1050() {

    }

    @Override
    public void Method1051(boolean var1) {

    }

    public Object isEnabled(boolean bl) {
        if (bl) {
            getEnable();
        } else {
            getDisable();
        }
        return null;
    }

    public void isDisabled(boolean bl) {
        if (!bl) {
            this.getEnable();
        } else {
            this.getDisable();
        }
    }

    public Category getCategory() {
        this.category = category;
        return this.category;
    }

    public boolean getStatus() {
        this.status = status;
        return this.status;
    }

    public void Method585(boolean bl) {
        this.status = bl;
    }

    public boolean Method492() {
        return this.status;
    }

    public void moduleContext(String string) {
        this.context = string;
    }

    public String getContext() {
        this.updateContext();
        return this.context;
    }



    public String Method586() {
        return this.description;
    }

    public void updateContext() {
        this.context = this.isEnabled() ? "enabled" : "disabled";
    }


    public String Method587() {
        return this.description;
    }

    public float Method588() {
        return this.Field484;
    }

    public void Method589(float f) {
        this.Field484 = f;
    }
}
