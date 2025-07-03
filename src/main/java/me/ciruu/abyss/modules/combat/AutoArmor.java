package me.ciruu.abyss.modules.combat;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Predicate;
import me.ciruu.abyss.Class154;
import me.ciruu.abyss.Class155;
import me.ciruu.abyss.Class207;
import me.ciruu.abyss.Class219;
import me.ciruu.abyss.util.Timer;
import me.ciruu.abyss.Class25;
import me.ciruu.abyss.Class29;
import me.ciruu.abyss.Class30;
import me.ciruu.abyss.Class350;
import me.ciruu.abyss.Class352;
import me.ciruu.abyss.Globals;
import me.ciruu.abyss.Manager;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.enums.Class53;
import me.ciruu.abyss.events.player.EventPlayerUpdateWalking;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.modules.misc.XCarry;
import me.ciruu.abyss.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemExpBottle;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketHeldItemChange;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.EnumHand;
import org.lwjgl.input.Keyboard;

public class AutoArmor
extends Module {
    private final Setting mainwindow = new Setting("Main", "", this, new Class25("Main Settings"));
    private final Setting delay = new Setting("Delay(ms)", "", (Module)this, (Object)50, 0, 500);
    private final Setting automend = new Setting("AutoMend", "", this, true);
    private final Setting enemydistance = new Setting("EnemyDistance", "", this, 1, 1, 20, this.mainwindow, this.automend::getValue);
    private final Setting mendkey = new Setting("MendKey", "", (Module)this, (Object)new Class207(0), this.mainwindow, this.automend::getValue);
    public final Setting armor = new Setting("Armor%", "", (Module)this, (Object)100, 1, 100);
    private final Setting curseOfBinding = new Setting("CurseOfBinding", "", this, false);
    private final Setting actions = new Setting("Actions", "", (Module)this, (Object)3, 1, 12);
    private final Setting elytraswap = new Setting("ElytraSwap", "", this, new Class207(0));
    private final Setting shiftclick = new Setting("ShiftClick", "", this, false);
    private final Timer timer = new Timer();
    private final Queue item = new ConcurrentLinkedQueue();
    private final List inventory = new ArrayList();
    private boolean status = false;
    private boolean xp = false;
    private int packet = -1;
    @EventHandler
    private Listener listen1 = new Listener<Class350>(this::onScreen, new Predicate[0]);
    @EventHandler
    private Listener listen2 = new Listener<EventPlayerUpdateWalking>(this::onAutoArmor, new Predicate[0]);

    public AutoArmor() {
        super("AutoArmor", "Automatically puts on armor.", Category.COMBAT, "");
        this.addSetting(this.delay);
        this.addSetting(this.automend);
        this.addSetting(this.enemydistance);
        this.addSetting(this.mendkey);
        this.addSetting(this.armor);
        this.addSetting(this.curseOfBinding);
        this.addSetting(this.elytraswap);
    }

    public boolean getEnable() {
        super.getEnable();
        this.timer.reset();
        this.timer.reset();
        this.inventory.clear();
        this.item.clear();
        return false;
    }

    public void getDisable() {
        super.getDisable();
        this.item.clear();
        this.inventory.clear();
        this.status = false;
    }

    private void onInventory(int n) {
        if (this.item.isEmpty()) {
            int n2 = -1;
            Iterator iterator = Class155.Method1807(Manager.moduleManager.isModuleEnabled(XCarry.class)).iterator();
            while (iterator.hasNext()) {
                int n3 = (Integer)iterator.next();
                if (this.inventory.contains(n2)) continue;
                n2 = n3;
                this.inventory.add(n3);
            }
            if (n2 != -1) {
                if (n2 < 5 && n2 > 0 || !((Boolean)this.shiftclick.getValue()).booleanValue()) {
                    this.item.add(new Class154(n));
                    this.item.add(new Class154(n2));
                } else {
                    this.item.add(new Class154(n, true));
                }
                this.item.add(new Class154());
            }
        }
    }

    private void getItem(int n, int n2) {
        if (this.item.isEmpty()) {
            this.inventory.remove((Object)n2);
            if (n2 < 5 && n2 > 0 || !((Boolean)this.shiftclick.getValue()).booleanValue()) {
                this.item.add(new Class154(n2));
                this.item.add(new Class154(n));
            } else {
                this.item.add(new Class154(n2, true));
            }
            this.item.add(new Class154());
        }
    }

    private boolean onEnemy() {
        try {
            EntityPlayer entityPlayer = Class30.Method1810(((Integer)this.enemydistance.getValue()).intValue());
            if (entityPlayer == null) {
                return true;
            }
            status = Globals.mc.player.getDistanceSq((Entity)entityPlayer) >= Class29.getDouble6(((Integer)this.enemydistance.getValue()).intValue());
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return status;
    }

    public static int onXP() {
        for (int i = 0; i < 9; ++i) {
            ItemStack itemStack = Globals.mc.player.inventory.getStackInSlot(i);
            if (itemStack == ItemStack.EMPTY || itemStack.getItem() != Items.EXPERIENCE_BOTTLE) continue;
            return i;
        }
        return -1;
    }

    private void onAutoArmor(EventPlayerUpdateWalking eventPlayerUpdateWalking) {
        if (Globals.mc.player == null || Globals.mc.world == null || eventPlayerUpdateWalking.getClass53() != Class53.PRE) {
            return;
        }
        if (Globals.mc.currentScreen instanceof GuiContainer && !(Globals.mc.currentScreen instanceof GuiInventory)) {
            return;
        }
        boolean bl = Keyboard.isKeyDown((int)((Class207)this.mendkey.getValue()).Method592());
        this.packet = Globals.mc.player.inventory.currentItem;
        if (this.xp && !bl && this.packet != -1) {
            Globals.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.packet));
            this.xp = false;
        }
        if (this.item.isEmpty()) {
            int n;
            int n2;
            int n3;
            ItemStack itemStack3;
            if (((Boolean) this.automend.getValue()).booleanValue() && (Class155.Method1812(ItemExpBottle.class) && Globals.mc.gameSettings.keyBindUseItem.isKeyDown() || bl) && this.onEnemy()) {
                int n4;
                int n5;
                int n6;
                int n7;
                itemStack3 = Globals.mc.player.inventoryContainer.getSlot(5).getStack();
                n3 = 0;
                if (!itemStack3.isEmpty && (n7 = Class352.Method1813(itemStack3)) >= (Integer) this.armor.getValue()) {
                    n3 = 1;
                }
                ItemStack itemStack4 = Globals.mc.player.inventoryContainer.getSlot(6).getStack();
                n2 = 0;
                if (!itemStack4.isEmpty && (n6 = Class352.Method1813(itemStack4)) >= (Integer) this.armor.getValue()) {
                    n2 = 1;
                }
                ItemStack itemStack5 = Globals.mc.player.inventoryContainer.getSlot(7).getStack();
                boolean bl2 = false;
                if (!itemStack5.isEmpty && (n5 = Class352.Method1813(itemStack5)) >= (Integer) this.armor.getValue()) {
                    bl2 = true;
                }
                ItemStack itemStack6 = Globals.mc.player.inventoryContainer.getSlot(8).getStack();
                boolean bl3 = false;
                if (!itemStack6.isEmpty && (n4 = Class352.Method1813(itemStack6)) >= (Integer) this.armor.getValue()) {
                    bl3 = true;
                }
                int n8 = n4 = !(n3 == 0 && !itemStack3.isEmpty || n2 == 0 && !itemStack4.isEmpty || !bl2 && !itemStack5.isEmpty || !bl3 && !itemStack6.isEmpty) ? 1 : 0;
                if (bl && !this.xp && n4 == 0) {
                    boolean bl4 = !Class155.Method1812(ItemExpBottle.class);
                    int n9 = AutoArmor.onXP();
                    if (bl4 && n9 != -1) {
                        this.xp = true;
                        Class155.Method522(n9, true);
                    }
                }
                if ((this.xp || Class155.Method1812(ItemExpBottle.class)) && n4 == 0 && bl) {
                    Globals.mc.player.connection.sendPacket((Packet) new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
                    eventPlayerUpdateWalking.setPitch(90.0f);
                    eventPlayerUpdateWalking.cancel();
                }
                if (n4 == 0) {
                    if (n3 != 0) {
                        this.onInventory(5);
                    }
                    if (n2 != 0) {
                        this.onInventory(6);
                    }
                    if (bl2) {
                        this.onInventory(7);
                    }
                    if (bl3) {
                        this.onInventory(8);
                    }
                    return;
                }
            }
            if ((itemStack3 = Globals.mc.player.inventoryContainer.getSlot(5).getStack()).getItem() == Items.AIR && (n3 = Class155.Method1814(EntityEquipmentSlot.HEAD, (Boolean) this.curseOfBinding.getValue(), Manager.moduleManager.isModuleEnabled(XCarry.class))) != -1) {
                this.getItem(5, n3);
            }
            Object object_;
            Item object = null;
            if ((object_ = Globals.mc.player.inventoryContainer.getSlot(6).getStack()) == Items.AIR) {
                if (this.item.isEmpty()) {
                    int n10;
                    if (this.status && this.timer.booleanTime(500L)) {
                        int n11 = Class155.Method1815(Items.ELYTRA, false, Manager.moduleManager.isModuleEnabled(XCarry.class));
                        if (n11 != -1) {
                            if (n11 < 5 && n11 > 1 || !((Boolean) this.shiftclick.getValue()).booleanValue()) {
                                this.item.add(new Class154(n11));
                                this.item.add(new Class154(6));
                            } else {
                                this.item.add(new Class154(n11, true));
                            }
                            this.item.add(new Class154());
                            this.timer.reset();
                        }
                    } else if (!this.status && (n10 = Class155.Method1814(EntityEquipmentSlot.CHEST, (Boolean) this.curseOfBinding.getValue(), Manager.moduleManager.isModuleEnabled(XCarry.class))) != -1) {
                        this.getItem(6, n10);
                    }
                }
            } else if (this.status && object != Items.ELYTRA && this.timer.booleanTime(500L)) {
                if (this.item.isEmpty()) {
                    int n12 = Class155.Method1815(Items.ELYTRA, false, Manager.moduleManager.isModuleEnabled(XCarry.class));
                    if (n12 != -1) {
                        this.item.add(new Class154(n12));
                        this.item.add(new Class154(6));
                        this.item.add(new Class154(n12));
                        this.item.add(new Class154());
                    }
                    this.timer.reset();
                }
                object = null;
            } else if (!this.status && object == Items.ELYTRA && this.timer.booleanTime(500L) && this.item.isEmpty()) {
                int n13 = Class155.Method1815((Item) Items.DIAMOND_CHESTPLATE, false, Manager.moduleManager.isModuleEnabled(XCarry.class));
                if (n13 == -1 && (n13 = Class155.Method1815((Item) Items.IRON_CHESTPLATE, false, Manager.moduleManager.isModuleEnabled(XCarry.class))) == -1 && (n13 = Class155.Method1815((Item) Items.GOLDEN_CHESTPLATE, false, Manager.moduleManager.isModuleEnabled(XCarry.class))) == -1 && (n13 = Class155.Method1815((Item) Items.CHAINMAIL_CHESTPLATE, false, Manager.moduleManager.isModuleEnabled(XCarry.class))) == -1) {
                    n13 = Class155.Method1815((Item) Items.LEATHER_CHESTPLATE, false, Manager.moduleManager.isModuleEnabled(XCarry.class));
                }
                if (n13 != -1) {
                    this.item.add(new Class154(n13));
                    this.item.add(new Class154(6));
                    this.item.add(new Class154(n13));
                    this.item.add(new Class154());
                }
                this.timer.reset();
            }
            if (((itemStack3 = Globals.mc.player.inventoryContainer.getSlot(7).getStack()).getItem() == Items.AIR) && (((n2 = Class155.Method1814(EntityEquipmentSlot.LEGS, (Boolean) this.curseOfBinding.getValue(), Manager.moduleManager.isModuleEnabled(XCarry.class)))) != -1)) {
                this.getItem(7, n2);
            }
            if ((itemStack3 = Globals.mc.player.inventoryContainer.getSlot(8).getStack()).getItem() == Items.AIR && (n = Class155.Method1814(EntityEquipmentSlot.FEET, (Boolean) this.curseOfBinding.getValue(), Manager.moduleManager.isModuleEnabled(XCarry.class))) != -1) {
                this.getItem(8, n);
            }
        }
        if (this.timer.booleanTime(((Integer)this.delay.getValue()).intValue())) {
            if (!this.item.isEmpty()) {
                for (int i = 0; i < (Integer)this.actions.getValue(); ++i) {
                    Class154 object = (Class154) this.item.poll();
                    if (object == null) continue;
                    ((Class154)object).Method438();
                }
            }
            this.timer.reset();
        }
    }

    private void onScreen(Class350 class350) {
        if (!(Globals.mc.currentScreen instanceof Class219) && ((Class207)this.elytraswap.getValue()).Method592() == class350.Method1535()) {
            this.status = !this.status;
        }
    }
}
