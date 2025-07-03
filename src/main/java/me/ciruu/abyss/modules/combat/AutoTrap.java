package me.ciruu.abyss.modules.combat;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.function.Predicate;

import me.ciruu.abyss.*;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.enums.Class238;
import me.ciruu.abyss.enums.Class580;
import me.ciruu.abyss.events.player.EventPlayerUpdate;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;
import me.ciruu.abyss.util.Timer;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.BlockObsidian;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemEndCrystal;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.client.CPacketUseEntity;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

import static me.ciruu.abyss.Globals.mc;

public class AutoTrap
extends Module {
    private final Setting mainwindow = new Setting("Main", "", this, new Class25("Main Settings"));
    private final Setting mode = new Setting("Mode", "", this, (Object)Class580.Normal);
    private final Setting rotate = new Setting("Rotate", "Rotate", this, false);
    private final Setting smartdisable = new Setting("SmartDisable", "", this, true);
    private final Setting range = new Setting("Range", "", (Module)this, (Object)Float.valueOf(6.0f), Float.valueOf(0.0f), Float.valueOf(6.0f));
    private final Setting delay = new Setting("Delay", "Delay in ticks", (Module)this, (Object)0, 0, 10);
    private final Setting blockspertick = new Setting("BlocksPerTick", "Blocks per tick", (Module)this, (Object)4, 1, 8);
    private final Setting silentswitch = new Setting("SilentSwitch", "", this, false);
    private final Setting disableswitchback = new Setting("DisableSwitchBack", "", this, false);
    private final Setting disable = new Setting("Disable", "", this, (Object)Class238.None);
    private final Setting timer = new Setting("Timer", "Delay (MS)", this, Float.valueOf(1000.0f), Float.valueOf(0.0f), Float.valueOf(10000.0f), this.mainwindow, this::Method3319);
    private final Setting crystaltrapwindow = new Setting("", "", this, new Class25("CrystalTrap Settings"));
    private final Setting crystalbind = new Setting("CrystalBind", "", (Module)this, (Object)new Class207(0), this.crystaltrapwindow, AutoTrap::Method3320);
    private final Setting breakdelay = new Setting("BreakDelay", "Delay in ticks", this, 1, 0, 10, this.crystaltrapwindow, AutoTrap::Method3321);
    private int switch_mode;
    private EntityPlayer entity;
    private BlockPos blockPos;
    private int integer = 0;
    private boolean status = false;
    private EntityEnderCrystal rotation;
    private BlockPos blockState;
    private double[] packetState = new double[2];
    public Vec3d vec3d = new Vec3d(0.0, 0.0, 0.0);
    private final Timer time = new Timer();
    private final Vec3d[] vec3d1 = new Vec3d[]{new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0)};
    private final Vec3d[] vec3d2 = new Vec3d[]{new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 1.0), new Vec3d(1.0, 3.0, 0.0), new Vec3d(-1.0, 3.0, 0.0), new Vec3d(0.0, 3.0, 0.0)};
    private final Vec3d[] vec3d3 = new Vec3d[]{new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0)};
    private final Vec3d[] vec3d4 = new Vec3d[]{new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0)};
    private final Vec3d[] vec3d5 = new Vec3d[]{new Vec3d(0.0, 0.0, -1.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-1.0, 0.0, 0.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 2.0, -1.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(-1.0, 2.0, 0.0), new Vec3d(0.0, 3.0, -1.0), new Vec3d(0.0, 3.0, 0.0), new Vec3d(0.0, 4.0, 0.0)};
    @EventHandler
    private Listener listen1 = new Listener<EventPlayerUpdate>(this::onTimerObsidian, new Predicate[0]);
    @EventHandler
    private Listener listen2 = new Listener<Class350>(this::onCrystalBind, new Predicate[0]);

    public AutoTrap() {
        super("AutoTrap", "Trap nearby players in obsidian.", Category.COMBAT, "");
        this.addSetting(this.mode);
        this.addSetting(this.rotate);
        this.addSetting(this.smartdisable);
        this.addSetting(this.range);
        this.addSetting(this.delay);
        this.addSetting(this.blockspertick);
        this.addSetting(this.silentswitch);
        this.addSetting(this.disableswitchback);
        this.addSetting(this.disable);
        this.addSetting(this.timer);
        this.addSetting(this.crystaltrapwindow);
        this.addSetting(this.crystalbind);
        this.addSetting(this.breakdelay);
    }

    public boolean getEnable() {
        super.getEnable();
        if (!this.onObsidian()) {
            this.disable();
            return false;
        }
        this.onObsidian();
        if (mc.player != null)this.onObsidian();
        this.switch_mode = mc.player.inventory.currentItem;
        this.entity = this.getPlayerEntity();
        if (this.entity == null) {
            Class547.printChatMessage("Can't find target!");
            this.disable();
            return false;
        }
        this.blockPos = Class30.Method209(this.entity);
        this.time.reset();
        return false;
    }

    public void getDisable() {
        super.getDisable();
        this.status = false;
        if (((Boolean)this.disableswitchback.getValue()).booleanValue() && !((Boolean)this.silentswitch.getValue()).booleanValue()) {
            Class155.Method522(this.switch_mode, false);
        }
    }

    public String onInGameChat() {
        return this.status ? ChatFormatting.YELLOW + "Crystal" : ChatFormatting.WHITE + ((Class580)((Object)this.mode.getValue())).name();
    }

    public void getInteger(boolean bl) {
        if (this.integer != -1) {
            Class155.Method522(this.integer, false);
        }
    }

    private void getBlock() {
        if (this.entity.isDead || this.entity == null || this.entity.getDistance((Entity) mc.player) >= 8.0f) {
            this.onObsidian();
            return;
        }
        if (((Boolean)this.smartdisable.getValue()).booleanValue() && (Math.abs(Class30.Method209(this.entity).getZ() - this.blockPos.getZ()) >= 1 || Math.abs(Class30.Method209(this.entity).getX() - this.blockPos.getX()) >= 1)) {
            this.onObsidian();
            return;
        }
        ArrayList arrayList = new ArrayList();
        switch ((Class580)((Object)this.mode.getValue())) {
            case Normal: {
                Collections.addAll(arrayList, this.vec3d1);
                break;
            }
            case DoubleTop: {
                Collections.addAll(arrayList, this.vec3d5);
                break;
            }
            case Feet: {
                Collections.addAll(arrayList, this.vec3d4);
                break;
            }
            case Face: {
                Collections.addAll(arrayList, this.vec3d2);
                break;
            }
            default: {
                Collections.addAll(arrayList, this.vec3d3);
            }
        }
        if (this.integer >= (Integer)this.delay.getValue()) {
            this.integer = 0;
            int n = 0;
            int n2 = Class155.Method544(BlockObsidian.class);
            if (((Boolean)this.silentswitch.getValue()).booleanValue() && n2 != -1) {
                this.switch_mode = mc.player.inventory.currentItem;
                mc.player.inventory.currentItem = n2;
                mc.playerController.updateController();
            }
            while (n < (Integer)this.blockspertick.getValue()) {
                if (this.integer >= arrayList.size()) {
                    this.integer = 0;
                    break;
                }
                BlockPos blockPos = new BlockPos((Vec3d)arrayList.get(this.integer));
                BlockPos blockPos2 = new BlockPos(this.entity.getPositionVector()).down().add(blockPos.getX(), blockPos.getY(), blockPos.getZ());
                boolean bl = true;
                if (!mc.world.getBlockState(blockPos2).getMaterial().isReplaceable()) {
                    bl = false;
                }
                for (Entity entity : mc.world.getEntitiesWithinAABBExcludingEntity(null, new AxisAlignedBB(blockPos2))) {
                    if (entity instanceof Entity) {
                        if (entity instanceof EntityItem || entity instanceof EntityXPOrb) continue;
                        bl = false;
                        break;
                    }
                }
                if (blockPos.equals((Object)new BlockPos(0, 3, 0)) && this.mode.getValue() != Class580.DoubleTop && this.status) {
                    this.blockState = blockPos2;
                    for (Entity entity : mc.world.loadedEntityList) {
                        if (entity instanceof Entity) {
                            if (entity instanceof EntityEnderCrystal && entity.getPosition().equals((Object) blockPos2.add(new Vec3i(0.5, 1.0, 0.5)))) {
                                bl = false;
                                this.status = true;
                                this.rotation = (EntityEnderCrystal) entity;
                                break;
                            }
                            this.status = false;
                            this.rotation = null;
                        }
                    }
                }
                if (bl) {
                    if (!((Boolean)this.silentswitch.getValue()).booleanValue()) {
                        Class155.Method522(Class155.Method544(BlockObsidian.class), false);
                    }
                    if (Class31.Method1261(blockPos2, (Boolean)this.rotate.getValue(), ((Float)this.range.getValue()).floatValue())) {
                        ++n;
                    }
                }
                ++this.integer;
                if (bl || this.integer < arrayList.size() || this.disable.getValue() != Class238.Blocks) continue;
                this.onObsidian();
            }
            if (((Boolean)this.silentswitch.getValue()).booleanValue() && this.switch_mode != -1 && n2 != -1) {
                mc.player.inventory.currentItem = this.switch_mode;
                mc.playerController.updateController();
            }
        }
        ++this.integer;
        if (this.status && this.mode.getValue() != Class580.DoubleTop) {
            if (this.blockState == null || !(mc.world.getBlockState(this.blockState).getBlock() instanceof BlockObsidian)) {
                Class602.Method3024(null);
                this.status = false;
            } else if (!this.status) {
                Class602.Method3024(this.blockState);
                Class602.Method3026(((Float)this.range.getValue()).floatValue(), false);
                this.status = true;
            }
            if (!this.status && this.blockState != null) {
                if (mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL) {
                    if (Class155.Method544(ItemEndCrystal.class) == -1) {
                        Class547.printChatMessage(ChatFormatting.RED + "Can't find crystals in your hotbar!");
                        this.status = false;
                        return;
                    }
                    Class155.Method1491(ItemEndCrystal.class, false);
                }
                if (Class308.Method1106(this.blockState, true, false)) {
                    this.onRotatePacketVector(this.blockState);
                    Class31.Method52(this.blockState, mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, (Boolean)this.rotate.getValue(), this.vec3d);
                }
            }
            if (this.rotation != null && this.blockState != null) {
                if (mc.world.getBlockState(this.blockState).getMaterial().isReplaceable() && this.integer > (Integer)this.breakdelay.getValue()) {
                    this.integer = 0;
                    this.onRotatePacket((Entity)this.rotation);
                    this.getItemHand(this.rotation);
                }
                if (!mc.world.getBlockState(this.blockState).getMaterial().isReplaceable()) {
                    if (this.getItem()) {
                        Class602.Method3026(((Float)this.range.getValue()).floatValue(), false);
                    } else {
                        Class547.printChatMessage(ChatFormatting.RED + "Can't find pickaxe in your hotbar!");
                        this.status = false;
                        return;
                    }
                }
            }
            ++this.integer;
        }
    }

    private boolean getItem() {
        boolean bl;
        boolean bl2 = bl = mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_PICKAXE;
        if (!bl) {
            for (int i = 0; i < 9; ++i) {
                ItemStack itemStack = mc.player.inventory.getStackInSlot(i);
                if (itemStack.isEmpty() || itemStack.getItem() != Items.DIAMOND_PICKAXE) continue;
                Class155.Method522(i, false);
                return true;
            }
        } else {
            return true;
        }
        return false;
    }

    private void onRotatePacketVector(BlockPos blockPos) {
        if (((Boolean)this.rotate.getValue()).booleanValue()) {
            Class353 class353 = Class84.Method1492(blockPos);
            if (class353 != null) {
                this.vec3d = class353.Method1493();
            }
            this.packetState = Class354.Method1505((double)blockPos.getX() + 0.5, (double)blockPos.getY() - 0.5, (double)blockPos.getZ() + 0.5, (EntityPlayer) mc.player);
            mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation((float)this.packetState[0], (float)this.packetState[1], mc.player.onGround));
        }
    }

    private void onRotatePacket(Entity entity) {
        if (((Boolean)this.rotate.getValue()).booleanValue()) {
            double[] dArray = Class354.Method1505(entity.posX + 0.5, entity.posY - 0.5, entity.posZ + 0.5, (EntityPlayer) mc.player);
            mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation((float)dArray[0], (float)dArray[1], mc.player.onGround));
        }
    }

    private void getItemHand(EntityEnderCrystal entityEnderCrystal) {
        mc.player.connection.sendPacket((Packet)new CPacketUseEntity((Entity)entityEnderCrystal));
        mc.player.swingArm(mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
    }

    private EntityPlayer getPlayerEntity() {
        if (mc.world.playerEntities.isEmpty()) {
            return null;
        }
        EntityPlayer entityPlayer = null;
        for (EntityPlayer entityPlayer2 : mc.world.playerEntities) {
            if (entityPlayer2 instanceof EntityPlayer) {
                if (entityPlayer2 == mc.player || Manager.Field223.Method711((Entity) entityPlayer2) || !Class354.Method1908((Entity) entityPlayer2) || entityPlayer2.getHealth() <= 0.0f || entityPlayer != null && Minecraft.getMinecraft().player.getDistance((Entity) entityPlayer2) > Minecraft.getMinecraft().player.getDistance((Entity) entityPlayer))
                    continue;
                entityPlayer = entityPlayer2;
            }
        }
        return entityPlayer;
    }

    private boolean onObsidian() {
        if (Class155.Method544(BlockObsidian.class) == -1) {
            Class547.printChatMessage("Can't find obsidian in hotbar!");
            return false;
        }
        return true;
    }

    private void onCrystalBind(Class350 class350) {
        if (!(mc.currentScreen instanceof Class219) && class350.Method1535() == ((Class207)this.crystalbind.getValue()).Method592() && this.mode.getValue() != Class580.DoubleTop) {
            this.status = !this.status;
        }
    }

    private void onTimerObsidian(EventPlayerUpdate eventPlayerUpdate) {
        if (this.disable.getValue() == Class238.Timer && this.time.booleanTime(((Float)this.timer.getValue()).longValue())) {
            this.onObsidian();
            return;
        }
        this.getBlock();
    }

    private static boolean Method3321() {
        return true;
    }

    private static boolean Method3320() {
        return true;
    }

    private boolean Method3319() {
        return this.disable.getValue() == Class238.Timer;
    }
}
