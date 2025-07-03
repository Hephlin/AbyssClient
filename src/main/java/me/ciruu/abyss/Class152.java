package me.ciruu.abyss;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.enums.Class151;
import me.ciruu.abyss.enums.Class235;
import me.ciruu.abyss.enums.Class260;
import me.ciruu.abyss.enums.Class279;
import me.ciruu.abyss.enums.Class339;
import me.ciruu.abyss.enums.Class340;
import me.ciruu.abyss.enums.Class341;
import me.ciruu.abyss.enums.Class342;
import me.ciruu.abyss.enums.Class343;
import me.ciruu.abyss.enums.Class344;
import me.ciruu.abyss.enums.Class345;
import me.ciruu.abyss.enums.Class346;
import me.ciruu.abyss.enums.Class347;
import me.ciruu.abyss.enums.Class348;
import me.ciruu.abyss.enums.Class349;
import me.ciruu.abyss.enums.Class53;
import me.ciruu.abyss.events.network.EventNetworkPostPacketEvent;
import me.ciruu.abyss.events.network.EventNetworkPrePacketEvent;
import me.ciruu.abyss.events.player.EventPlayerUpdate;
import me.ciruu.abyss.events.player.EventPlayerUpdateWalking;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.modules.combat.HoleFiller;
import me.ciruu.abyss.modules.misc.AutoEz;
import me.ciruu.abyss.settings.Setting;
import me.ciruu.abyss.util.Timer;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemAppleGold;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemTool;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.network.play.server.SPacketSoundEffect;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.event.entity.living.LivingDeathEvent;

import static me.ciruu.abyss.Globals.mc;

public class Class152
extends Module {
    public static final Object Field975 = new Object();
    public static final Class338 Field976 = new Class338();
    private final Setting Field977 = new Setting("Event Mode", "Event Mode", this, Class343.ClientTick);
    private final Setting Field978 = new Setting("Logic", "Logic", this, Class260.BreakPlace);
    private final Setting Field979 = new Setting("", "", this, new Class25("Place Settings"));
    private final Setting Field980 = new Setting("Place", "Place crystals", this, true, this.Field979, Class152::Method1339);
    private final Setting Field981 = new Setting("Place Range", "Place Range", this, Float.valueOf(6.0f), Float.valueOf(0.0f), Float.valueOf(6.0f), this.Field979, this.Field980::getValue);
    private final Setting Field982 = new Setting("Multiplace", "Multiplace", this, 3, 1, 5, this.Field979, this.Field980::getValue);
    private final Setting Field983 = new Setting("MultiplaceMinDmg", "MultiplaceMinDmg", this, false, this.Field979, this::Method1340);
    private final Setting Field984 = new Setting("", "", this, new Class25("Break Settings"));
    private final Setting Field985 = new Setting("Break", "Break crystals", this, true, this.Field984, Class152::Method1341);
    private final Setting Field986 = new Setting("Break Range", "Break Range", this, Float.valueOf(6.0f), Float.valueOf(0.0f), Float.valueOf(6.0f), this.Field984, this.Field985::getValue);
    private final Setting Field987 = new Setting("Break Mode", "Break Mode", this, Class339.Efficient, this.Field984, this.Field985::getValue);
    private final Setting Field988 = new Setting("InstaBreak", "", this, true, this.Field984, this.Field985::getValue);
    private final Setting Field989 = new Setting("", "", this, new Class25("WallsRange Settings"));
    private final Setting Field990 = new Setting("WallRange", "", this, Class341.Full, this.Field989, Class152::Method1342);
    private final Setting Field991 = new Setting("WallRangePlace", "", this, Float.valueOf(3.5f), Float.valueOf(0.0f), Float.valueOf(10.0f), this.Field989, this::Method1343);
    private final Setting Field992 = new Setting("WallRangeBreak", "", this, Float.valueOf(3.5f), Float.valueOf(0.0f), Float.valueOf(10.0f), this.Field989, this::Method1344);
    private final Setting Field993 = new Setting("", "", this, new Class25("Predict Settings"));
    private final Setting Field994 = new Setting("Predict Mode", "", this, Class348.Manual, this.Field993, Class152::Method1345);
    public final Setting Field995 = new Setting("Predict", "", this, false, this.Field993, Class152::Method1346);
    private final Setting Field996 = new Setting("Toggle", "", this, false, this.Field993, Class152::Method1347);
    private final Setting Field997 = new Setting("PredictBind", "", this, new Class207(0), this.Field993, Class152::Method1348);
    private final Setting Field998 = new Setting("DisableOnStop", "", this, false, this.Field993, Class152::Method1349);
    private final Setting Field999 = new Setting("FirstOffset", "", this, 0, -100, 100, this.Field993, Class152::Method1350);
    private final Setting Field1000 = new Setting("Step", "", this, 1, 1, 100, this.Field993, Class152::Method1351);
    private final Setting Field1001 = new Setting("Packets", "", this, 5, 1, 50, this.Field993, Class152::Method1352);
    private final Setting Field1002 = new Setting("PacketLimit", "", this, 200, 1, 200, this.Field993, Class152::Method1353);
    private final Setting Field1003 = new Setting("Offset", "", this, 1, 1, 100, this.Field993, Class152::Method1354);
    private final Setting Field1004 = new Setting("Samples", "", this, 10, 1, 200, this.Field993, Class152::Method1355);
    private final Setting Field1005 = new Setting("Moda", "", this, false, this.Field993, Class152::Method1356);
    private final Setting Field1006 = new Setting("PredictCooldown", "", this, true, this.Field993, Class152::Method1357);
    private final Setting Field1007 = new Setting("Reliability", "", this, 100, 0, 1000, this.Field993, Class152::Method1358);
    private final Setting Field1008 = new Setting("BranchPrediction", "", this, false, this.Field993, Class152::Method1359);
    private final Setting Field1009 = new Setting("Anti kick", "", this, false, this.Field993, Class152::Method1360);
    private final Setting Field1010 = new Setting("AntiKickTimer", "Delay (MS)", this, Float.valueOf(500.0f), Float.valueOf(0.0f), Float.valueOf(1000.0f), this.Field993, this.Field1009::getValue);
    private final Setting Field1011 = new Setting("SafeDisable", "", this, true, this.Field993, Class152::Method1361);
    public final Setting Field1012 = new Setting("PredictListener", "", this, false, this.Field993, Class152::Method1362);
    private final Setting Field1013 = new Setting("Predict Mode", "", this, Class349.Timer, this.Field993, Class152::Method1363);
    public final Setting Field1014 = new Setting("PredictListenerTimer", "", this, 1, 0, 500, this.Field993, this::Method1364);
    public final Setting Field1015 = new Setting("PredictListenerDelay", "", this, 0, 0, 20, this.Field993, this::Method1365);
    private final Setting Field1016 = new Setting("PredictListenerBind", "", this, new Class207(0), this.Field993, Class152::Method1366);
    private final Setting Field1017 = new Setting("PredictListenerPackets", "", this, 1, 1, 20, this.Field993, Class152::Method1367);
    private final Setting Field1018 = new Setting("RecalcID", "", this, true, this.Field993, this::Method1368);
    public final Setting Field1019 = new Setting("PlaceAfter", "", this, false, this.Field993, Class152::Method1369);
    private final Setting Field1020 = new Setting("", "", this, new Class25("Delay Settings"));
    private final Setting Field1021 = new Setting("Delay Mode", "", this, Class347.Timer, this.Field1020, Class152::Method1370);
    private final Setting Field1022 = new Setting("Place Timer", "Place Delay (MS)", this, Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1000.0f), this.Field1020, this::Method1371);
    private final Setting Field1023 = new Setting("Break Timer", "Break Delay (MS)", this, Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(1000.0f), this.Field1020, this::Method1372);
    private final Setting Field1024 = new Setting("Place Ticks", "", this, 0, 0, 10, this.Field1020, this::Method1373);
    private final Setting Field1025 = new Setting("Break Ticks", "", this, 0, 0, 10, this.Field1020, this::Method1374);
    private final Setting Field1026 = new Setting("", "", this, new Class25("Target Settings"));
    private final Setting Field1027 = new Setting("Target", "Target", this, Class235.Closest, this.Field1026, Class152::Method1375);
    private final Setting Field1028 = new Setting("Target Range", " Target Range for Crystals and Players", this, Float.valueOf(12.0f), Float.valueOf(0.1f), Float.valueOf(20.0f), this.Field1026, Class152::Method1376);
    private final Setting Field1029 = new Setting("FacePlace", "FacePlace", this, Float.valueOf(8.0f), Float.valueOf(0.1f), Float.valueOf(37.0f), this.Field1026, Class152::Method1377);
    private final Setting Field1030 = new Setting("FacePlaceMinDMG", "FacePlace minimum damage", this, Float.valueOf(1.5f), Float.valueOf(1.0f), Float.valueOf(2.4f), this.Field1026, Class152::Method1378);
    private final Setting Field1031 = new Setting("ArmorBreaker", "", this, 0, 0, 125, this.Field1026, Class152::Method1379);
    private final Setting Field1032 = new Setting("PrioritizeArmor", "", this, false, this.Field1026, this::Method1380);
    private final Setting Field1033 = new Setting("", "", this, new Class25("Damage Settings"));
    private final Setting Field1034 = new Setting("MinDamage", "MinDamage", this, Float.valueOf(4.0f), Float.valueOf(0.1f), Float.valueOf(20.0f), this.Field1033, Class152::Method1381);
    private final Setting Field1035 = new Setting("IgnoreSelfDMGBreak", "Ignore self damage when breaking crystals", this, false, this.Field1033, Class152::Method1382);
    private final Setting Field1036 = new Setting("IgnoreSelfDMGPlace", "Ignore self damage when placing crystals", this, false, this.Field1033, Class152::Method1383);
    private final Setting Field1037 = new Setting("MaxSelfDMGPlace", "Maximum self damage when placing", this, Float.valueOf(9.0f), Float.valueOf(0.0f), Float.valueOf(20.0f), this.Field1033, this::Method1384);
    private final Setting Field1038 = new Setting("MaxSelfDMGBreak", "Maximum self damage when breaking", this, Float.valueOf(10.0f), Float.valueOf(0.0f), Float.valueOf(20.0f), this.Field1033, this::Method1385);
    private final Setting Field1039 = new Setting("CheckResistance", "Check resistance damage", this, false, this.Field1033, Class152::Method1386);
    private final Setting Field1040 = new Setting("", "", this, new Class25("Rotation Settings"));
    private final Setting Field1041 = new Setting("Rotate", "Rotate", this, Class151.Off, this.Field1040, Class152::Method1387);
    private final Setting Field1042 = new Setting("RotateMode", "Rotate mode", this, Class345.Default, this.Field1040, this::Method1388);
    public final Setting Field942 = new Setting("Strict", "Strict mode for 2B2T", this, false, this.Field1040, this::Method1389);
    private final Setting Field1043 = new Setting("", "", this, new Class25("Switch Settings"));
    private final Setting Field1044 = new Setting("Switch Mode", "Switch Mode", this, Class279.Target, this.Field1043, Class152::Method1390);
    private final Setting Field1045 = new Setting("Cooldown", "Cooldown (MS)", this, 0, 0, 1000, this.Field1043, Class152::Method1391);
    private final Setting Field1046 = new Setting("Switch", "Switch", this, Class340.Always, this.Field1043, Class152::Method1392);
    private final Setting Field1047 = new Setting("SwitchBind", "SwitchBind", this, new Class207(11), this.Field1043, this::Method1393);
    private final Setting Field1048 = new Setting("", "", this, new Class25("Pause Settings"));
    private final Setting Field1049 = new Setting("PauseIfHPBelow", "Pauses the CrystalAura if health below", this, Float.valueOf(0.0f), Float.valueOf(0.0f), Float.valueOf(36.0f), this.Field1048, Class152::Method1394);
    private final Setting Field1050 = new Setting("PauseEating", "Pause While Eating", this, false, this.Field1048, Class152::Method1395);
    private final Setting Field1051 = new Setting("PauseHittingBlock", "Pause while the player is hitting a block with a tool", this, false, this.Field1048, Class152::Method1396);
    private final Setting Field1052 = new Setting("", "", this, new Class25("Misc Settings"));
    private final Setting Field1053 = new Setting("Luigi's mansion", "Luigi's mansion", this, Class344.Off, this.Field1052, Class152::Method1397);
    public final Setting Field940 = new Setting("1.13", "1.13 mode", this, false, this.Field1052, Class152::Method1398);
    private final Setting Field1054 = new Setting("WebAttack", "WebAttack", this, true, this.Field1052, Class152::Method1399);
    private final Setting Field1055 = new Setting("FastMode", "Fast mode", this, true, this.Field1052, Class152::Method1400);
    private final Setting Field1056 = new Setting("FullCalc", "FullCalc", this, false, this.Field1052, Class152::Method1401);
    private final Setting Field1057 = new Setting("MinSelfDmg", "MinSelfDmg", this, false, this.Field1052, Class152::Method1402);
    private final Setting Field1058 = new Setting("AttackSync", "AttackSync", this, true, this.Field1052, Class152::Method1403);
    private final Setting Field1059 = new Setting("No Sound Desync", "No Sound Desync", this, false, this.Field1052, Class152::Method1404);
    private final Setting Field1060 = new Setting("ResetTargetDisable", "Reset target on disable", this, false, this.Field1052, Class152::Method1405);
    private final Setting Field1061 = new Setting("", "", this, new Class25("Render Settings"));
    public Setting Field1062 = new Setting("Animation", "Animation", this, Class342.OffHand, this.Field1061, Class152::Method1406);
    private final Setting Field1063 = new Setting("Render", "Render", this, true, this.Field1061, Class152::Method1407);
    public Setting Field1064 = new Setting("RenderBox", "RenderBox", this, Class346.FullBox, this.Field1061, this::Method1408);
    private final Setting Field1065 = new Setting("Box", "Box", this, true, this.Field1061, this::Method1409);
    private final Setting Field1066 = new Setting("Outline", "Outline", this, true, this.Field1061, this::Method1410);
    private final Setting Field1067 = new Setting("Text", "Text", this, false, this.Field1061, this::Method1411);
    private final Setting Field1068 = new Setting("Box color", "Box color", this, new Color(0, 0, 0, 125), this.Field1061, this::Method1412);
    private final Setting Field1069 = new Setting("LineWidth", "LineWidth", this, Float.valueOf(1.5f), Float.valueOf(0.1f), Float.valueOf(5.0f), this.Field1061, this::Method1413);
    private final Setting Field1070 = new Setting("CustomLine", "CustomLine", this, true, this.Field1061, this::Method1414);
    private final Setting Field1071 = new Setting("OutLine color", "OutLine color", this, new Color(0, 0, 0, 255), this.Field1061, this::Method1415);
    private final Setting Field1072 = new Setting("Rainbow", "", this, true, this.Field1061, this.Field1063::getValue);
    private final Setting Field1073 = new Setting("RainbowSpeed", "", this, Float.valueOf(7.0f), Float.valueOf(0.0f), Float.valueOf(10.0f), this.Field1061, this::Method1416);
    private final Setting Field1074 = new Setting("RainbowSaturation", "", this, Float.valueOf(0.5f), Float.valueOf(0.0f), Float.valueOf(1.0f), this.Field1061, this::Method1417);
    private final Setting Field1075 = new Setting("RainbowBrightness", "", this, Float.valueOf(1.0f), Float.valueOf(0.0f), Float.valueOf(1.0f), this.Field1061, this::Method1418);
    private final Queue Field1076 = new ConcurrentLinkedQueue();
    private final Map Field1077 = new HashMap();
    private final Timer Field1078 = new Timer();
    private final Timer Field1079 = new Timer();
    private final Timer Field1080 = new Timer();
    public static EntityPlayer Field1081 = null;
    private Entity Field1082 = null;
    private double Field1083 = 0.0;
    private double Field1084 = 0.0;
    private double Field1085 = 0.0;
    public static boolean Field1086 = false;
    private BlockPos Field1087 = null;
    private BlockPos Field1088 = null;
    private boolean Field1089 = false;
    private boolean Field1090 = false;
    private boolean Field1091 = false;
    private int Field1092 = 0;
    private int Field1093 = 0;
    private int Field1094 = -1;
    private float Field1095 = 0.0f;
    private float Field1096 = 0.0f;
    private BlockPos Field1097 = null;
    private Timer Field1098 = new Timer();
    private BlockPos Field1099 = null;
    public static ArrayList Field1100 = new ArrayList();
    private boolean Field1101 = false;
    private int Field1102 = 0;
    private int Field1103 = 0;
    public static boolean Field848 = false;
    public static boolean Field1104 = false;
    public static boolean Field1105 = false;
    private double[] Field1106 = new double[2];
    private final Timer Field1107 = new Timer();
    private boolean Field1108 = false;
    private final Vec3d Field1109 = null;
    public int Field1110;
    public int Field1111;
    private int Field1112 = 0;
    private final ArrayDeque Field1113 = new ArrayDeque();
    private final ArrayDeque Field1114 = new ArrayDeque();
    private final ArrayDeque Field1115 = new ArrayDeque();
    private final ArrayDeque Field1116 = new ArrayDeque();
    private final ArrayDeque Field1117 = new ArrayDeque();
    private final ArrayDeque Field1118 = new ArrayDeque();
    public int Field1119 = 0;
    public int Field1120 = 0;
    public int Field1121 = 0;
    public int Field1122 = 0;
    private long Field1123 = System.currentTimeMillis();
    private int Field1124;
    public boolean Field1125 = false;
    private final Timer Field1126 = new Timer();
    private boolean Field1127 = false;
    private static final Timer Field1128 = new Timer();
    private final Timer Field1129 = new Timer();
    private boolean Field1130 = true;
    private int Field1131 = 0;
    public Vec3d Field941 = null;
    @EventHandler
    private final Listener Field1132 = new Listener<EventNetworkPrePacketEvent>(this::Method1419, 300);
    @EventHandler
    private final Listener Field1133 = new Listener<Class26>(this::Method1420);
    @EventHandler
    private final Listener Field1134 = new Listener<EventPlayerUpdateWalking>(this::Method1421);
    @EventHandler
    private final Listener Field1135 = new Listener<EventPlayerUpdate>(this::Method1422);
    @EventHandler
    private final Listener Field1136 = new Listener<Class66>(this::Method1423);
    @EventHandler
    private final Listener Field1137 = new Listener<EventNetworkPostPacketEvent>(this::Method1424);
    @EventHandler
    private final Listener Field1138 = new Listener<EventNetworkPrePacketEvent>(this::Method1425);
    @EventHandler
    private final Listener Field1139 = new Listener<Class350>(this::Method1426);
    @EventHandler
    private final Listener Field1140 = new Listener<EventPlayerUpdateWalking>(this::Method1427);
    @EventHandler
    private final Listener Field1141 = new Listener<LivingDeathEvent>(Class152::Method1428, 500);
    @EventHandler
    private final Listener Field1142 = new Listener<Class27>(Class152::Method1429, 600);

    public Class152() {
        super("AutoCrystalOld", "Attacks nearby players by placing end crystals", Category.COMBAT, "");
        this.addSetting(this.Field977);
        this.addSetting(this.Field978);
        this.addSetting(this.Field979);
        this.addSetting(this.Field980);
        this.addSetting(this.Field981);
        this.addSetting(this.Field982);
        this.addSetting(this.Field983);
        this.addSetting(this.Field984);
        this.addSetting(this.Field985);
        this.addSetting(this.Field986);
        this.addSetting(this.Field987);
        this.addSetting(this.Field988);
        this.addSetting(this.Field989);
        this.addSetting(this.Field990);
        this.addSetting(this.Field991);
        this.addSetting(this.Field992);
        this.addSetting(this.Field993);
        this.addSetting(this.Field994);
        this.addSetting(this.Field995);
        this.addSetting(this.Field996);
        this.addSetting(this.Field997);
        this.addSetting(this.Field998);
        this.addSetting(this.Field999);
        this.addSetting(this.Field1000);
        this.addSetting(this.Field1001);
        this.addSetting(this.Field1002);
        this.addSetting(this.Field1003);
        this.addSetting(this.Field1004);
        this.addSetting(this.Field1005);
        this.addSetting(this.Field1006);
        this.addSetting(this.Field1008);
        this.addSetting(this.Field1007);
        this.addSetting(this.Field1009);
        this.addSetting(this.Field1010);
        this.addSetting(this.Field1011);
        this.addSetting(this.Field1012);
        this.addSetting(this.Field1013);
        this.addSetting(this.Field1014);
        this.addSetting(this.Field1015);
        this.addSetting(this.Field1016);
        this.addSetting(this.Field1017);
        this.addSetting(this.Field1018);
        this.addSetting(this.Field1019);
        this.addSetting(this.Field1020);
        this.addSetting(this.Field1021);
        this.addSetting(this.Field1022);
        this.addSetting(this.Field1023);
        this.addSetting(this.Field1024);
        this.addSetting(this.Field1025);
        this.addSetting(this.Field1026);
        this.addSetting(this.Field1027);
        this.addSetting(this.Field1028);
        this.addSetting(this.Field1029);
        this.addSetting(this.Field1030);
        this.addSetting(this.Field1031);
        this.addSetting(this.Field1032);
        this.addSetting(this.Field1033);
        this.addSetting(this.Field1034);
        this.addSetting(this.Field1036);
        this.addSetting(this.Field1035);
        this.addSetting(this.Field1037);
        this.addSetting(this.Field1038);
        this.addSetting(this.Field1039);
        this.addSetting(this.Field1040);
        this.addSetting(this.Field1041);
        this.addSetting(this.Field1042);
        this.addSetting(this.Field942);
        this.addSetting(this.Field1043);
        this.addSetting(this.Field1046);
        this.addSetting(this.Field1047);
        this.addSetting(this.Field1044);
        this.addSetting(this.Field1045);
        this.addSetting(this.Field1048);
        this.addSetting(this.Field1049);
        this.addSetting(this.Field1050);
        this.addSetting(this.Field1051);
        this.addSetting(this.Field1052);
        this.addSetting(this.Field940);
        this.addSetting(this.Field1053);
        this.addSetting(this.Field1054);
        this.addSetting(this.Field1055);
        this.addSetting(this.Field1056);
        this.addSetting(this.Field1057);
        this.addSetting(this.Field1058);
        this.addSetting(this.Field1059);
        this.addSetting(this.Field1060);
        this.addSetting(this.Field1061);
        this.addSetting(this.Field1062);
        this.addSetting(this.Field1064);
        this.addSetting(this.Field1063);
        this.addSetting(this.Field1065);
        this.addSetting(this.Field1066);
        this.addSetting(this.Field1067);
        this.addSetting(this.Field1068);
        this.addSetting(this.Field1069);
        this.addSetting(this.Field1070);
        this.addSetting(this.Field1071);
        this.addSetting(this.Field1072);
        this.addSetting(this.Field1073);
        this.addSetting(this.Field1074);
        this.addSetting(this.Field1075);
    }

    public void Method1431(boolean bl) {
        if (bl) {
            this.Method1435();
            this.Field1012.setValue(false);
        }
    }

    public void Method1436() {
        long l = System.currentTimeMillis() - 1000L;
        if (this.Field1123 < l) {
            this.Field1117.clear();
            this.Field1119 = this.Field1121;
            this.Field1121 = 0;
            this.Field1120 = this.Field1122;
            this.Field1122 = 0;
            this.Field1123 = System.currentTimeMillis();
        }
        Object object = Field975;
        synchronized (object) {
            this.Field1118.removeIf(arg_0 -> Class152.Method1437(l, (Long) arg_0));
            while (!this.Field1116.isEmpty() && this.Field1118.size() < (Integer) this.Field1002.getValue()) {
                mc.player.connection.sendPacket((Packet) this.Field1116.removeLast());
                this.Field1118.add(System.currentTimeMillis());
            }
            while (this.Field1116.size() > 20) {
                this.Field1116.removeFirst();
            }
        }
    }


    private void Method1438() {
        if ((this.Field1091 || this.Field1089 || this.Field1044.getValue() == Class279.Target) && this.Field1088 != null && ((Boolean) this.Field1063.getValue()).booleanValue() && (((Boolean) this.Field1065.getValue()).booleanValue() || ((Boolean) this.Field1067.getValue()).booleanValue() || ((Boolean) this.Field1066.getValue()).booleanValue())) {
            if (this.Field1064.getValue() == Class346.FullBox) {
                Class50.Method137(this.Field1088, (Boolean) this.Field1072.getValue() ? this.Method1441((int) (((Float) this.Field1073.getValue()).floatValue() * 1000.0f), ((Float) this.Field1074.getValue()).floatValue(), ((Float) this.Field1075.getValue()).floatValue()) : this.Field1068.getValue(), this.Field1070.getValue(), (Boolean) this.Field1072.getValue() ? this.Method1441((int) (((Float) this.Field1073.getValue()).floatValue() * 1000.0f), ((Float) this.Field1074.getValue()).floatValue(), ((Float) this.Field1075.getValue()).floatValue()) : this.Field1071.getValue(), ((Float) this.Field1069.getValue()).floatValue(), this.Field1066.getValue(), this.Field1065.getValue(), ((Color) this.Field1068.getValue()).getAlpha(), false);
            }
            if (this.Field1064.getValue() == Class346.CustomBox) {
                Class50.Method793(this.Field1088, (Boolean) this.Field1072.getValue() ? this.Method1441((int) (((Float) this.Field1073.getValue()).floatValue() * 1000.0f), ((Float) this.Field1074.getValue()).floatValue(), ((Float) this.Field1075.getValue()).floatValue()) : this.Field1068.getValue(), this.Field1070.getValue(), (Boolean) this.Field1072.getValue() ? this.Method1441((int) (((Float) this.Field1073.getValue()).floatValue() * 1000.0f), ((Float) this.Field1074.getValue()).floatValue(), ((Float) this.Field1075.getValue()).floatValue()) : this.Field1071.getValue(), ((Float) this.Field1069.getValue()).floatValue(), this.Field1066.getValue(), this.Field1065.getValue(), ((Color) this.Field1068.getValue()).getAlpha(), false);
            }
            if (((Boolean) this.Field1067.getValue()).booleanValue()) {
                if (this.Field1064.getValue() == Class346.FullBox) {
                    Class50.Method847(this.Field1088, (Math.floor(this.Field1084) == this.Field1084 ? Double.valueOf(this.Field1084) : String.format("%.1f", this.Field1084)) + "");
                }
                if (this.Field1064.getValue() == Class346.CustomBox) {
                    Class50.Method841(this.Field1088, (Math.floor(this.Field1084) == this.Field1084 ? Double.valueOf(this.Field1084) : String.format("%.1f", this.Field1084)) + "", Color.WHITE.getRGB());
                }
            }
        }
    }

    public boolean getEnable() {
        super.getEnable();
        return false;
    }

    public void getDisable() {
        super.getDisable();
        this.Field1090 = false;
        Field1100.clear();
        if (((Boolean) this.Field1060.getValue()).booleanValue()) {
            Field1081 = null;
        }
        if (((Boolean) this.Field998.getValue()).booleanValue()) {
            this.Method1435();
        }
    }

    public String Method2421() {
        if (this.isEnabled() && Field1081 == null) {
            return ChatFormatting.GREEN + "ON";
        }
        if (!this.isEnabled() && Field1081 != null) {
            return ChatFormatting.AQUA + Field1081.getName();
        }
        return ChatFormatting.RED + "OFF";
    }

    private void Method1435() {
        if (((Boolean) this.Field995.getValue()).booleanValue()) {
            this.Field995.setValue(false);
        }
        this.Field1125 = false;
    }


    private void Method1451() {
        if (this.Field1131 >= (Integer) this.Field1015.getValue()) {
            this.Field1130 = true;
            this.Field1131 = 0;
        }
        ++this.Field1131;
        this.Method1453();
        this.Field1127 = Class30.Method51();
        if (Class30.Method1454(mc.player) < ((Float) this.Field1049.getValue()).floatValue() || (Boolean) this.Field1050.getValue() && this.Method1455() || (Boolean) this.Field1051.getValue() && mc.playerController.isHittingBlock && mc.player.getHeldItemMainhand().getItem() instanceof ItemTool || Manager.moduleManager.isModuleEnabled(HoleFiller.class) || Field1104 || Field1105) {
            this.Field1108 = true;
            return;
        }
        if (this.Method1458()) {
            switch ((Class260) this.Field978.getValue()) {
                case PlaceBreak: {
                    this.Method1459();
                    this.Method1460();
                    break;
                }
                case BreakPlace: {
                    this.Method1460();
                    this.Method1459();
                }
            }
        }
        ++this.Field1102;
        ++this.Field1103;
    }

    private boolean Method1458() {
        if (mc.player == null) return false;
        if (mc.world == null) {
            return false;
        }
        if (this.Field1098.get10_0(10000L)) {
            this.Field1088 = null;
            this.Field1098.reset();
        }
        this.Field1089 = mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL;
        this.Field1091 = mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL;
        this.Field1083 = 0.0;
        Object object = Field975;
        this.Field1087 = null;
        if (this.Field1094 != mc.player.inventory.currentItem) {
            this.Field1094 = mc.player.inventory.currentItem;
            this.Field1078.reset();
        }
        if (this.Field1091 || this.Field1089) {
            Field1086 = false;
        }                                                                                                                                                                                        
        if (!((this.Field1091 || this.Field1089 || this.Field1044.getValue() != Class279.Crystalslot || Field1086) && Class352.Method1462(mc.player))) {
            this.Field1088 = null;
            Field1081 = null;
            this.Field1090 = false;
            return false;
        }
        if (this.Field1046.getValue() == Class340.Always && this.Field1044.getValue() == Class279.Always) {
            this.Method1463();
        }
        this.Method1464();
        this.Field1108 = false;
        return true;
    }


    private void Method1464() {
        this.Field1082 = null;
        this.Field1092 = 0;
        this.Field1093 = 0;
        Entity entity = null;
        float f = 0.5f;
        for (Entity entity2 : mc.world.loadedEntityList) {
            if (entity2 instanceof Entity) {
                if (!(entity2 instanceof EntityEnderCrystal) || !this.Method1468(entity2)) continue;
                boolean bl = false;
                boolean bl2 = false;
                float f2 = Class352.Method1469(entity2, mc.player);
                if ((double) f2 + 0.5 < (double) Class30.Method1454(mc.player) && (((Boolean) this.Field1035.getValue()).booleanValue() || f2 < ((Float) this.Field1038.getValue()).floatValue())) {
                    for (EntityPlayer entityPlayer : mc.world.playerEntities) {
                        if (entityPlayer instanceof EntityPlayer) {
                            if (!(entityPlayer.getDistanceSq(entity2) < Class29.getDouble6(((Float) this.Field1028.getValue()).floatValue())) || !Class30.Method1471(entityPlayer, ((Float) this.Field1028.getValue()).floatValue() + ((Float) this.Field986.getValue()).floatValue()))
                                continue;
                            float f3 = Class352.Method1469(entity2, entityPlayer);
                            if (this.Field987.getValue() == Class339.Efficient && (f3 > ((Float) this.Field1034.getValue()).floatValue() || f3 > Class30.Method1454(entityPlayer) || Class30.Method1454(entityPlayer) <= ((Float) this.Field1029.getValue()).floatValue() && f3 > ((Float) this.Field1030.getValue()).floatValue() || Class352.Method1472(entityPlayer, this.Field1031.getValue()))) {
                                if (f3 > f) {
                                    f = f3;
                                    entity = entity2;
                                }
                                if (f3 >= ((Float) this.Field1034.getValue()).floatValue() || !((Boolean) this.Field983.getValue()).booleanValue()) {
                                    bl = true;
                                }
                                bl2 = true;
                            }
                            if (this.Field987.getValue() != Class339.Always) continue;
                            if (f3 >= 0.0f) {
                                f = f3;
                                entity = entity2;
                            }
                            if (f3 >= ((Float) this.Field1034.getValue()).floatValue() || !((Boolean) this.Field983.getValue()).booleanValue()) {
                                bl = true;
                            }
                            bl2 = true;
                        }
                    }
                }
                if (!bl2) continue;
                ++this.Field1093;
                if (!bl) continue;
                ++this.Field1092;
            }
        }
        if (((Boolean) this.Field1054.getValue()).booleanValue() && this.Field1097 != null) {
            if (mc.player.getDistanceSq(this.Field1097.up()) > Class29.getDouble6(((Float) this.Field986.getValue()).floatValue())) {
                this.Field1097 = null;
            } else {
                for (Entity entity2 : mc.world.getEntitiesWithinAABB(Entity.class, new AxisAlignedBB(this.Field1097.up()))) {
                    if (entity2 instanceof Entity) {
                        if (!(entity2 instanceof EntityEnderCrystal)) continue;
                        this.Field1076.add(entity2);
                        this.Field1082 = entity2;
                        this.Field1097 = null;
                        this.Field1085 = 0.5;
                        return;
                    }
                }
            }
        }
        this.Field1082 = entity;
    }


    private void Method1459() {
        int n = this.Field982.getValue();
        if ((this.Field1080.get10_0(((Float) this.Field1022.getValue()).longValue()) && this.Field1021.getValue() == Class347.Timer || this.Field1021.getValue() == Class347.Ticks && this.Field1102 >= (Integer) this.Field1024.getValue()) && ((Boolean) this.Field980.getValue()).booleanValue() && (this.Field1091 || this.Field1089 || this.Field1044.getValue() == Class279.Target || this.Field1044.getValue() == Class279.Crystalslot && Field1086)) {
            if (!(!this.Field1091 && !this.Field1089 && (this.Field1044.getValue() == Class279.Always || Field1086) || this.Field1092 < n || ((Boolean) this.Field1055.getValue()).booleanValue() && this.Field1099 != null && this.Field1099.equals(this.Field1087))) {
                return;
            }
            this.Method1479(this.Method1478(this.Field1027.getValue() == Class235.Unsafe));
            if (Field1081 != null && this.Field1087 != null && !Manager.Field223.Method233(Field1081.getName())) {
                if (!this.Field1091 && !this.Field1089 && this.Field1046.getValue() != Class340.None && this.Field1083 > (double) ((Float) this.Field1034.getValue()).floatValue() && !this.Method1463()) {
                    return;
                }
                if (this.Field1083 < (double) ((Float) this.Field1034.getValue()).floatValue() && Class30.Method1454(Field1081) > ((Float) this.Field1029.getValue()).floatValue()) {
                    n = 1;
                }
                if ((this.Field1091 || this.Field1089 || this.Field1046.getValue() != Class340.None) && (this.Field1092 < n || ((Boolean) this.Field1055.getValue()).booleanValue() && this.Field1099 != null && this.Field1099.equals(this.Field1087)) && (this.Field1083 > (double) ((Float) this.Field1034.getValue()).floatValue() || this.Field1093 < n) && this.Field1083 >= 1.0 && (Class352.Method1472(Field1081, this.Field1031.getValue()) || Class30.Method1454(Field1081) < ((Float) this.Field1029.getValue()).floatValue() || this.Field1083 > (double) ((Float) this.Field1034.getValue()).floatValue())) {
                    this.Field1088 = this.Field1087;
                    this.Field1084 = this.Field1083;
                    if (this.Method1463()) {
                        this.Method1481(this.Field1087);
                        Class31.Method1282(this.Field1087, this.Field1091 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
                        ++this.Field1121;
                        this.Field1117.add(this.Field1087.offset(EnumFacing.UP));
                        this.Field1117.add(this.Field1087.offset(EnumFacing.DOWN));
                        this.Field1117.add(this.Field1087);
                        Field1100.add(this.Field1087);
                        this.Field1099 = this.Field1087;
                        this.Field1080.reset();
                        this.Field1102 = 0;
                        this.Field1101 = false;
                        if (((Boolean) this.Field1011.getValue()).booleanValue() && Class30.Method51()) {
                            this.Method1435();
                        }
                        if ((((Boolean) this.Field995.getValue()).booleanValue() || this.Field1125) && (!((Boolean) this.Field1009.getValue()).booleanValue() || ((Boolean) this.Field1009.getValue()).booleanValue() && !Class30.Method51())) {
                            int n2 = 0;
                            if (this.Field994.getValue() == Class348.Auto) {
                                n2 = this.Field1110;
                            } else if (this.Field994.getValue() == Class348.Manual) {
                                n2 = this.Field1124 + (Integer) this.Field999.getValue();
                            }
                            for (int i = 0; i < (Integer) this.Field1001.getValue(); ++i) {
                                CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
                                cPacketUseEntity.entityId = n2 + (Integer) this.Field1000.getValue();
                                cPacketUseEntity.action = CPacketUseEntity.Action.ATTACK;
                                if (this.Field994.getValue() == Class348.Auto) {
                                    float f = Class29.getFloatCollection(this.Field1114);
                                    if (((Boolean) this.Field1006.getValue()).booleanValue()) {
                                        this.Field1116.addLast(cPacketUseEntity);
                                    } else {
                                        this.Method1487(cPacketUseEntity);
                                    }
                                } else if (this.Field994.getValue() == Class348.Manual) {
                                    this.Method1487(cPacketUseEntity);
                                } else if (this.Field994.getValue() == Class348.Next) {
                                    cPacketUseEntity.entityId = Entity.nextEntityID;
                                    this.Method1487(cPacketUseEntity);
                                }
                                n2 += ((Integer) this.Field1003.getValue()).intValue();
                            }
                        }
                    }
                }
            } else {
                this.Field1088 = null;
            }
        }
    }

    private void Method1487(CPacketUseEntity cPacketUseEntity) {
        Object object = Field975;
        synchronized (object) {
            if (this.Field1118.size() < (Integer) this.Field1002.getValue()) {
                mc.player.connection.sendPacket(cPacketUseEntity);
                this.Field1118.add(System.currentTimeMillis());
            }
        }
    }


    public int Method1453() {
        Object object = Field975;
        synchronized (object) {
            float f = Class29.getFloatIterator(this.Field1113, Class29.getFloatCollection(this.Field1113));
            float f2 = Class29.getFloatCollectionAmount(this.Field1113);
            float f3 = Class29.getFloatCollection(this.Field1114);
            float f4 = (Boolean) this.Field1005.getValue() ? f2 : f;
            int n = Math.round((float) Class152.Field976.Field1161 + f4) + (Integer) this.Field999.getValue();
            if (((Boolean) this.Field1008.getValue()).booleanValue()) {
                if (this.Field1112 < 0) {
                    ++n;
                }
                if (this.Field1112 > 0) {
                    --n;
                }
            }
            this.Field1110 = Math.max(n, this.Field1111);
            this.Field1111 = n;
            this.Field1110 = n;
            return this.Field1110;
        }
    }


    private boolean Method1463() {
        if (this.Field1091 || this.Field1089) {
            return true;
        }
        switch ((Class340) this.Field1046.getValue()) {
            case None: {
                return false;
            }
            case Toggle: {
                if (!Field1086) {
                    return false;
                }
            }
            case Always: {
                if (!this.Method1490()) break;
                return true;
            }
        }
        return false;
    }


    private boolean Method1490() {
        if (mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            this.Field1089 = false;
        } else {
            Class155.Method1491(ItemEndCrystal.class, false);
            this.Field1089 = true;
        }
        Field1086 = false;
        return true;
    }


    private void Method1481(BlockPos blockPos) {
        float[] fArray = new float[]{0.0f, 0.0f};
        switch ((Class151) this.Field1041.getValue()) {
            case Off: {
                this.Field1090 = false;
                break;
            }
            case Place:
            case All: {
                Class353 class353;
                if (this.Field1042.getValue() == Class345.Default) {
                    if (((Boolean) this.Field942.getValue()).booleanValue()) {
                        class353 = Class84.Method1492(blockPos);
                        if (class353 != null) {
                            try {
                                this.Field941 = class353.Method1493();
                                fArray[0] = class353.Method1494().Method70();
                                fArray[1] = class353.Method1494().Method69();
                            } catch (Exception exception) {
                                exception.printStackTrace();
                            }
                        }
                    } else {
                        fArray = Class29.getFloatVector(mc.player.getPositionEyes(mc.getRenderPartialTicks()), new Vec3d((float) blockPos.getX() + 0.5f, (float) blockPos.getY() - 0.5f, (float) blockPos.getZ() + 0.5f));
                    }
                    if (this.Field977.getValue() == Class343.MotionUpdate) {
                        Manager.Field456.Method523(fArray[0], fArray[1]);
                        break;
                    }
                    this.Field1095 = fArray[0];
                    this.Field1096 = fArray[1];
                    this.Field1090 = true;
                }
                if (this.Field1042.getValue() != Class345.Custom) break;
                if (((Boolean) this.Field942.getValue()).booleanValue()) {
                    class353 = Class84.Method1492(blockPos);
                    if (class353 != null) {
                        try {
                            this.Field941 = class353.Method1493();
                            this.Field1106[0] = class353.Method1494().Method70();
                            this.Field1106[1] = class353.Method1494().Method69();
                            System.out.println(mc.player.rotationYaw);
                            System.out.println(mc.player.rotationPitch);
                            System.out.println(this.Field1106[0]);
                            System.out.println(this.Field1106[1]);
                            System.out.println("------------------------");
                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                    }
                } else {
                    this.Field1106 = Class354.Method1505((double) blockPos.getX() + 0.5, (double) blockPos.getY() - 0.5, (double) blockPos.getZ() + 0.5, mc.player);
                }
                this.Field1107.reset();
            }
        }
    }

    private void Method1479(EntityPlayer entityPlayer) {
        Object object;
        BlockPos block3 = null;
        if (entityPlayer == null && this.Field1027.getValue() != Class235.Damage && !((Boolean) this.Field1056.getValue()))
            return;

        float f = 0.5f;
        EntityPlayer entityPlayer2 = null;
        float f2 = 0.0f;
        Object object2 = null;
        IBlockState iBlockState = null;
        if (((Boolean) this.Field1054.getValue()).booleanValue() && entityPlayer != null) {
            BlockPos blockpos3 = new BlockPos(entityPlayer.getPositionVector());
            Block block2 = Minecraft.getMinecraft().world.getBlockState(blockpos3).getBlock();
            if (block2 == Blocks.WEB) {
                this.Field1097 = blockpos3; 
                this.Field1098 = (Timer) Minecraft.getMinecraft().world.getBlockState(blockpos3);
                Minecraft.getMinecraft().world.setBlockToAir(blockpos3);
            }
        }
        BlockPos block2;
        BlockPos block333 = block3;
        for (Object block3_ : Class31.Method1273(((Float) this.Field981.getValue()).floatValue(), this.Field1055.getValue())) {
            if (block3_ instanceof Block) {
                BlockPos block33 = (BlockPos) block3_;
                float f3;
                if (!Class31.Method1223(block33, (this.Field990.getValue() == Class341.Place || this.Field990.getValue() == Class341.Full) && mc.player.getDistanceSq(block33) > Class29.getDouble6(((Float) this.Field991.getValue()).floatValue()), 1.0f) || !((double) (f3 = Class352.Method1510(block33, mc.player)) + 0.5 < (double) Class30.Method1454(mc.player)) || !((Boolean) this.Field1036.getValue()).booleanValue() && !(f3 < ((Float) this.Field1037.getValue()).floatValue()))
                    continue;
                if (entityPlayer != null) {
                    float f4 = Class352.Method1510(block33, entityPlayer);
                    if (!(f4 > f) && (!((Boolean) this.Field1057.getValue()).booleanValue() || !(f4 >= f) || !(f3 < f2)) || !(f4 > f3 || f4 > ((Float) this.Field1034.getValue()).floatValue() || f4 > Class30.Method1454(entityPlayer) || Class30.Method1454(entityPlayer) < ((Float) this.Field1029.getValue()).floatValue()) && !Class352.Method1472(Field1081, this.Field1031.getValue()))
                        continue;
                    f = f4;
                    entityPlayer2 = entityPlayer;
                    block2 = block33;
                    f2 = f3;
                    continue;
                }
                for (EntityPlayer entityPlayer3 : mc.world.playerEntities) {
                    if (entityPlayer3 instanceof EntityPlayer) {
                        float f5;
                        if (!Class30.Method1471(entityPlayer3, ((Float) this.Field981.getValue()).floatValue() + ((Float) this.Field1028.getValue()).floatValue()) || !((f5 = Class352.Method1510(block3, entityPlayer3)) > f) && (!((Boolean) this.Field1057.getValue()).booleanValue() || !(f5 >= f) || !(f3 < f2)) || !(f5 > f3 || f5 > ((Float) this.Field1034.getValue()).floatValue() || f5 > Class30.Method1454(entityPlayer3) || Class30.Method1454(entityPlayer3) < ((Float) this.Field1029.getValue()).floatValue()) && !Class352.Method1472(entityPlayer3, this.Field1031.getValue()))
                            continue;
                        f = f5;
                        entityPlayer2 = entityPlayer3;
                        block2 = block33;
                        f2 = f3;
                    }
                }
            }
        }
        if (object2 != null) {
            mc.world.setBlockState((BlockPos) object2, iBlockState);
            this.Field1097 = block333;
        }
        Field1081 = entityPlayer2;
        if (entityPlayer2 != null) {
            AutoEz.Method1512(entityPlayer2.getName());
        }
        this.Field1083 = f;
        object = Field975;
        synchronized (object) {
            this.Field1087 = block333;
        }
    }


    private EntityPlayer Method1478(boolean bl) {
        if (this.Field1027.getValue() == Class235.Damage) {
            return null;
        }
        EntityPlayer entityPlayer = null;
        for (EntityPlayer entityPlayer2 : mc.world.playerEntities) {
            if (entityPlayer2 instanceof EntityPlayer) {
                if (Manager.Field223.Method233(entityPlayer2.getName()) || Class30.Method749(entityPlayer2, ((Float) this.Field981.getValue()).floatValue() + ((Float) this.Field1028.getValue()).floatValue()) || bl && Class30.Method1513(entityPlayer2))
                    continue;
                if ((Integer) this.Field1031.getValue() > 0 && Class352.Method1472(entityPlayer2, this.Field1031.getValue()) && ((Boolean) this.Field1032.getValue()).booleanValue()) {
                    entityPlayer = entityPlayer2;
                    break;
                }
                if (entityPlayer == null) {
                    entityPlayer = entityPlayer2;
                    continue;
                }
                if (!(mc.player.getDistanceSq(entityPlayer2) < mc.player.getDistanceSq(entityPlayer)))
                    continue;
                entityPlayer = entityPlayer2;
            }
        }
        if (bl && entityPlayer == null) {
            return this.Method1478(false);
        }
        return entityPlayer;
    }


    private void Method1460() {
        if (((Boolean) this.Field985.getValue()).booleanValue() && this.Field1021.getValue() == Class347.Timer || this.Field1021.getValue() == Class347.Ticks && this.Field1103 >= (Integer) this.Field1025.getValue() && (this.Field1044.getValue() == Class279.Always || this.Field1089 || this.Field1091)) {
            Entity entity;
            if (((Boolean) this.Field1039.getValue()).booleanValue() && Field1081 != null && (float) Class152.Field1081.hurtResistantTime > (float) Class152.Field1081.maxHurtResistantTime / 2.0f) {
                return;
            }
            if (this.Field1082 != null) {
                this.Method1515(this.Field1082);
            } else if (!this.Field1076.isEmpty() && (entity = (Entity) this.Field1076.poll()) != null) {
                this.Method1515(entity);
            }
            this.Field1079.reset();
            this.Field1103 = 0;
        }
    }


    private void Method1515(Entity entity) {
        switch ((Class339) this.Field987.getValue()) {
            case Always:
            case Efficient: {
                this.Method1518(entity);
                break;
            }
            case OnlyOwn: {
                for (Object blockPos : new ArrayList(Field1100)) {
                    if (blockPos instanceof BlockPos) {
                        if (blockPos instanceof BlockPos) {
                            BlockPos blockPos1 = (BlockPos) blockPos;
                            if (blockPos1 == null || !(blockPos1.getDistance((int) entity.posX, (int) entity.posY, (int) entity.posZ) <= 3.0))
                                continue;
                            this.Method1518(entity);
                            Field1100.remove(blockPos);
                        }
                    }
                }
                break;
            }
        }
    }


    private void Method1518(Entity entity) {
        if (this.Field1118.size() >= (Integer) this.Field1002.getValue()) {
            return;
        }
        this.Method1523(entity);
        Class30.Method1524(entity, this.Field1058.getValue(), true);
        if (this.Field1053.getValue() != Class344.Off) {
            if (this.Field1053.getValue() == Class344.Default) {
                mc.world.removeEntity(entity);
            }
            if (this.Field1053.getValue() == Class344.Thread) {
                mc.addScheduledTask(() -> Class152.Method1526(entity));
            }
            if (this.Field1053.getValue() == Class344.New) {
                entity.setDead();
            }
        }
    }


    private void Method1523(Entity entity) {
        switch ((Class151) this.Field1041.getValue()) {
            case Off: {
                this.Field1090 = false;
                break;
            }
            case All:
            case Break: {
                if (this.Field1042.getValue() == Class345.Default) {
                    float[] fArray = Class29.getFloatVector(mc.player.getPositionEyes(mc.getRenderPartialTicks()), entity.getPositionVector());
                    if (this.Field977.getValue() == Class343.MotionUpdate) {
                        Manager.Field456.Method523(fArray[0], fArray[1]);
                        break;
                    }
                    this.Field1095 = fArray[0];
                    this.Field1096 = fArray[1];
                    this.Field1090 = true;
                }
                if (this.Field1042.getValue() != Class345.Custom) break;
                this.Field1106 = Class354.Method1505(entity.posX + 0.5, entity.posY - 0.5, entity.posZ + 0.5, mc.player);
                this.Field1107.reset();
            }
        }
    }


    private boolean Method1468(Entity entity) {
        return entity != null && mc.player.getDistanceSq(entity) <= Class29.getDouble6(((Float) this.Field986.getValue()).floatValue()) && (this.Field990.getValue() == Class341.None || this.Field990.getValue() == Class341.Place || mc.player.canEntityBeSeen(entity) || !mc.player.canEntityBeSeen(entity) && mc.player.getDistanceSq(entity) <= Class29.getDouble6(((Float) this.Field992.getValue()).floatValue()));
    }

    private boolean Method1455() {
        return mc.player.getHeldItemMainhand().getItem() instanceof ItemAppleGold && mc.player.isHandActive();
    }

    private Color Method1441(int n, float f, float f2) {
        float f3 = System.currentTimeMillis() % (long) n;
        return Color.getHSBColor(f3 /= (float) n, f, f2);
    }

    public ArrayDeque Method1531() {
        return this.Field1113;
    }

    public ArrayDeque Method1532() {
        return this.Field1114;
    }

    public ArrayDeque Method1533() {
        return this.Field1115;
    }

    public int Method1534() {
        return this.Field1112;
    }

    private static void Method1429(Class27 class27) {
        Class152.Field976.Field1161 = Math.max(class27.getEntity().getEntityId(), Class152.Field976.Field1161);
    }

    private static void Method1428(LivingDeathEvent livingDeathEvent) {
        if (livingDeathEvent.getEntity() instanceof EntityPlayer && livingDeathEvent.getEntity().getDistance(mc.player) <= 6.0f) {
            Field1128.reset();
        }
    }

    private static void Method1526(Entity entity) {
        mc.world.removeEntity(entity);
    }

    private void Method1427(EventPlayerUpdateWalking eventPlayerUpdateWalking) {
        if (eventPlayerUpdateWalking.getClass53() != Class53.PRE) {
            return;
        }
        if (eventPlayerUpdateWalking.isCancelled()) {
            this.Field1106 = null;
            return;
        }
        if (this.Field1108) {
            this.Field1106 = null;
            return;
        }
        if (this.Field1107.get10_0(1000L)) {
            this.Field1106 = null;
        }
        if (this.Field1106 != null) {
            eventPlayerUpdateWalking.setYaw(this.Field1106[0]);
            eventPlayerUpdateWalking.setPitch(this.Field1106[1]);
            eventPlayerUpdateWalking.cancel();
            Class202.Method934((float) this.Field1106[1], (float) this.Field1106[0]);
        }
    }

    private void Method1426(Class350 class350) {
        if (!(mc.currentScreen instanceof Class219)) {
            if (((Class207) this.Field1047.getValue()).Method592() == class350.Method1535()) {
                this.Method1463();
                boolean bl = Field1086 = !Field1086;
            }
            if (((Class207) this.Field997.getValue()).Method592() == class350.Method1535()) {
                this.Field1125 = !this.Field1125;
                String string = this.Field1125 ? ChatFormatting.GREEN + "enabled" : ChatFormatting.RED + "disabled";
                Class547.printChatMessage(ChatFormatting.AQUA + "Predict:" + string);
            }
            if (((Class207) this.Field1016.getValue()).Method592() == class350.Method1535()) {
                this.Field1012.setValue(!((Boolean) this.Field1012.getValue()));
            }
        }
    }

    private void Method1425(EventNetworkPrePacketEvent event) {
        if (!(event.getPacket() instanceof SPacketSoundEffect)) return;
        SPacketSoundEffect packet = (SPacketSoundEffect) event.getPacket();

        if (packet.getCategory() != SoundCategory.BLOCKS) return;
        if (packet.getSound() != SoundEvents.ENTITY_GENERIC_EXPLODE) return;

        for (Entity entity : Minecraft.getMinecraft().world.loadedEntityList) {
            if (entity instanceof Entity) {
                if (entity instanceof EntityEnderCrystal &&
                        entity.getDistance(packet.getX(), packet.getY(), packet.getZ()) <= 6.0) {
                    entity.setDead();
                }
            }
        }
    }

    private void Method1424(EventNetworkPostPacketEvent eventNetworkPostPacketEvent) {
        if (eventNetworkPostPacketEvent.getEra() == Class53.PRE && this.Field1041.getValue() != Class151.Off && this.Field1042.getValue() == Class345.Default && this.Field1090 && this.Field977.getValue() != Class343.MotionUpdate && eventNetworkPostPacketEvent.getPacket() instanceof CPacketPlayer) {
            CPacketPlayer cPacketPlayer = (CPacketPlayer) eventNetworkPostPacketEvent.getPacket();
            cPacketPlayer.yaw = this.Field1095;
            cPacketPlayer.pitch = this.Field1096;
            this.Field1090 = false;
        }
    }

    private void Method1423(Class66 class66) {
        this.Method1438();
    }

    private void Method1422(EventPlayerUpdate eventPlayerUpdate) {
        this.Method1436();
        if (this.Field977.getValue() == Class343.PlayerUpdate) {
            this.Method1451();
        }
    }

    private void Method1421(EventPlayerUpdateWalking eventPlayerUpdateWalking) {
        if (eventPlayerUpdateWalking.getClass53() == Class53.PRE && this.Field977.getValue() == Class343.MotionUpdate) {
            this.Method1451();
        }
    }

    private static boolean Method1437(long l, Long l2) {
        return l2 < l;
    }

    private void Method1420(Class26 class26) {
        this.Method1436();
        if (this.Field977.getValue() == Class343.ClientTick) {
            this.Method1451();
        }
    }

    private void Method1419(EventNetworkPrePacketEvent eventNetworkPrePacketEvent) {
        if (mc.player == null) return;
        if (mc.world == null) {
            return;
        }
        Packet packet = eventNetworkPrePacketEvent.mb_packet;
        String string = packet.getClass().getSimpleName();
        if (string.startsWith("C")) {
            return;
        }
        this.Method1436();
        try {
            Object object;
            int n;
            if (packet instanceof SPacketSpawnObject) {
                SPacketSpawnObject sPacketSpawnObject = (SPacketSpawnObject) packet;
                this.Field1124 = n = sPacketSpawnObject.getEntityID();
                if (sPacketSpawnObject.getType() == 51) {
                    Object object2 = Field975;
                    synchronized (object2) {
                        this.Field1113.addLast(n - Class152.Field976.Field1161);
                        while (this.Field1113.size() > (Integer) this.Field1004.getValue()) {
                            this.Field1113.removeFirst();
                        }
                        if (this.Field1110 < n && this.Field1112 > -2) {
                            --this.Field1112;
                        }
                        if (this.Field1110 > n && this.Field1112 < 2) {
                            ++this.Field1112;
                        }
                        Class152.Field976.Field1161 = n;
                        object = new BlockPos(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ());
                        if (this.Field1117.contains(object)) {
                            ++this.Field1122;
                        }
                        this.Field1114.add(this.Field1110 - Class152.Field976.Field1161);
                        while (this.Field1114.size() > (Integer) this.Field1004.getValue()) {
                            this.Field1114.removeFirst();
                        }
                        this.Field1115.addLast(this.Field1110 == Class152.Field976.Field1161);
                        while (this.Field1115.size() > 10) {
                            this.Field1115.removeFirst();
                        }
                    }
                }
            }
            if (((Boolean) this.Field1012.getValue()).booleanValue() && (this.Field1013.getValue() == Class349.Timer && this.Field1126.get10_0(((Integer) this.Field1014.getValue()).intValue()) || this.Field1013.getValue() == Class349.Tick && this.Field1130)) {
                int n2 = this.Method1453();
                n = 0;
                this.Field1130 = false;
                if (((Boolean) this.Field1009.getValue()).booleanValue() && Class30.Method51()) {
                    this.Field1129.reset();
                }
                if ((mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL || mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) && this.Field1088 != null && this.Field1087 != null && this.Field1129.get10_0(((Float) this.Field1010.getValue()).longValue()) && Field1128.get10_0(((Float) this.Field1010.getValue()).longValue()) && Field1081 != null && !Class152.Field1081.isDead) {
                    for (int i = 0; i < (Integer) this.Field1017.getValue(); ++i) {
                        if (mc.player.getDistance(this.Field1087.getX(), this.Field1087.getY(), this.Field1087.getZ()) > (double) ((Float) this.Field981.getValue()).floatValue())
                            continue;
                        object = Field975;
                        synchronized (object) {
                            if (this.Field1087 != null) {
                                Class31.Method1282(this.Field1087, this.Field1091 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
                                this.Field1117.add(this.Field1087.offset(EnumFacing.UP));
                                this.Field1117.add(this.Field1087.offset(EnumFacing.DOWN));
                                this.Field1117.add(this.Field1087);
                                ++this.Field1121;
                                CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
                                cPacketUseEntity.entityId = n2;
                                cPacketUseEntity.action = CPacketUseEntity.Action.ATTACK;
                                this.Method1487(cPacketUseEntity);
                                mc.player.swingArm(this.Field1062.getValue() == Class342.MainHand ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
                                if ((Integer) this.Field1017.getValue() > 1) {
                                    if (((Boolean) this.Field1018.getValue()).booleanValue()) {
                                        n = n2;
                                        if (n == (n2 = this.Method1453())) {
                                            n2 = n + 1;
                                        }
                                    } else {
                                        ++n2;
                                    }
                                }
                                if (((Boolean) this.Field1019.getValue()).booleanValue()) {
                                    Class31.Method1282(this.Field1087, this.Field1091 ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
                                }
                            }
                            continue;
                        }
                    }
                    this.Field1126.reset();
                }
            }
            if (!((Boolean) this.Field985.getValue())) return;
            if (!((Boolean) this.Field988.getValue())) return;
            if (!(packet instanceof SPacketSpawnObject)) return;
            SPacketSpawnObject sPacketSpawnObject = (SPacketSpawnObject) packet;
            if (sPacketSpawnObject.getType() != 51) return;
            BlockPos blockPos = new BlockPos(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ());
            if (this.Field1087 == null || !this.Field1087.equals(blockPos.down())) {
                if (!Field1100.contains(blockPos.down())) return;
            }
            CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
            cPacketUseEntity.entityId = sPacketSpawnObject.getEntityID();
            cPacketUseEntity.action = CPacketUseEntity.Action.ATTACK;
            this.Method1487(cPacketUseEntity);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private boolean Method1418() {
        return (Boolean) this.Field1063.getValue() && (Boolean) this.Field1072.getValue();
    }

    private boolean Method1417() {
        return (Boolean) this.Field1063.getValue() && (Boolean) this.Field1072.getValue();
    }

    private boolean Method1416() {
        return (Boolean) this.Field1063.getValue() && (Boolean) this.Field1072.getValue();
    }

    private boolean Method1415() {
        return (Boolean) this.Field1063.getValue() && (Boolean) this.Field1066.getValue() && (Boolean) this.Field1070.getValue();
    }

    private boolean Method1414() {
        return (Boolean) this.Field1063.getValue() && (Boolean) this.Field1066.getValue();
    }

    private boolean Method1413() {
        return (Boolean) this.Field1063.getValue() && (Boolean) this.Field1066.getValue();
    }

    private boolean Method1412() {
        return this.Field1063.getValue();
    }

    private boolean Method1411() {
        return this.Field1063.getValue();
    }

    private boolean Method1410() {
        return this.Field1063.getValue();
    }

    private boolean Method1409() {
        return this.Field1063.getValue();
    }

    private boolean Method1408() {
        return this.Field1063.getValue();
    }

    private static boolean Method1407() {
        return true;
    }

    private static boolean Method1406() {
        return true;
    }

    private static boolean Method1405() {
        return true;
    }

    private static boolean Method1404() {
        return true;
    }

    private static boolean Method1403() {
        return true;
    }

    private static boolean Method1402() {
        return true;
    }

    private static boolean Method1401() {
        return true;
    }

    private static boolean Method1400() {
        return true;
    }

    private static boolean Method1399() {
        return true;
    }

    private static boolean Method1398() {
        return true;
    }

    private static boolean Method1397() {
        return true;
    }

    private static boolean Method1396() {
        return true;
    }

    private static boolean Method1395() {
        return true;
    }

    private static boolean Method1394() {
        return true;
    }

    private boolean Method1393() {
        return this.Field1046.getValue() == Class340.Toggle;
    }

    private static boolean Method1392() {
        return true;
    }

    private static boolean Method1391() {
        return true;
    }

    private static boolean Method1390() {
        return true;
    }

    private boolean Method1389() {
        return this.Field1041.getValue() != Class151.Off;
    }

    private boolean Method1388() {
        return this.Field1041.getValue() != Class151.Off;
    }

    private static boolean Method1387() {
        return true;
    }

    private static boolean Method1386() {
        return true;
    }

    private boolean Method1385() {
        return !((Boolean) this.Field1035.getValue());
    }

    private boolean Method1384() {
        return !((Boolean) this.Field1036.getValue());
    }

    private static boolean Method1383() {
        return true;
    }

    private static boolean Method1382() {
        return true;
    }

    private static boolean Method1381() {
        return true;
    }

    private boolean Method1380() {
        return (Integer) this.Field1031.getValue() > 0;
    }

    private static boolean Method1379() {
        return true;
    }

    private static boolean Method1378() {
        return true;
    }

    private static boolean Method1377() {
        return true;
    }

    private static boolean Method1376() {
        return true;
    }

    private static boolean Method1375() {
        return true;
    }

    private boolean Method1374() {
        return this.Field1021.getValue() == Class347.Ticks;
    }

    private boolean Method1373() {
        return this.Field1021.getValue() == Class347.Ticks;
    }

    private boolean Method1372() {
        return this.Field1021.getValue() == Class347.Timer;
    }

    private boolean Method1371() {
        return this.Field1021.getValue() == Class347.Timer;
    }

    private static boolean Method1370() {
        return true;
    }

    private static boolean Method1369() {
        return true;
    }

    private boolean Method1368() {
        return (Integer) this.Field1017.getValue() > 1;
    }

    private static boolean Method1367() {
        return true;
    }

    private static boolean Method1366() {
        return true;
    }

    private boolean Method1365() {
        return (Boolean) this.Field1012.getValue() && this.Field1013.getValue() == Class349.Tick;
    }

    private boolean Method1364() {
        return (Boolean) this.Field1012.getValue() && this.Field1013.getValue() == Class349.Timer;
    }

    private static boolean Method1363() {
        return true;
    }

    private static boolean Method1362() {
        return true;
    }

    private static boolean Method1361() {
        return true;
    }

    private static boolean Method1360() {
        return true;
    }

    private static boolean Method1359() {
        return true;
    }

    private static boolean Method1358() {
        return true;
    }

    private static boolean Method1357() {
        return true;
    }

    private static boolean Method1356() {
        return true;
    }

    private static boolean Method1355() {
        return true;
    }

    private static boolean Method1354() {
        return true;
    }

    private static boolean Method1353() {
        return true;
    }

    private static boolean Method1352() {
        return true;
    }

    private static boolean Method1351() {
        return true;
    }

    private static boolean Method1350() {
        return true;
    }

    private static boolean Method1349() {
        return true;
    }

    private static boolean Method1348() {
        return true;
    }

    private static boolean Method1347() {
        return true;
    }

    private static boolean Method1346() {
        return true;
    }

    private static boolean Method1345() {
        return true;
    }

    private boolean Method1344() {
        return this.Field990.getValue() == Class341.Full || this.Field990.getValue() == Class341.Break;
    }

    private boolean Method1343() {
        return this.Field990.getValue() == Class341.Full || this.Field990.getValue() == Class341.Place;
    }

    private static boolean Method1342() {
        return true;
    }

    private static boolean Method1341() {
        return true;
    }

    private boolean Method1340() {
        return (Boolean) this.Field980.getValue() && (Integer) this.Field982.getValue() > 1;
    }

    private static boolean Method1339() {
        return true;
    }

    static Timer Method1537() {
        return Field1128;
    }

    public boolean Method1432() {
        return true;
    }
}