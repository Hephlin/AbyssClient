package me.ciruu.abyss.modules.combat;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.function.Predicate;

import me.ciruu.abyss.*;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.enums.Class53;
import me.ciruu.abyss.events.player.EventPlayerUpdate;
import me.ciruu.abyss.mixin.client.blocks.MixinBlockRailBase;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;
import me.ciruu.abyss.util.Timer;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockRailBase;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemMinecart;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItemOnBlock;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextComponentString;

public class AutoMinecart
extends Module {
    private final Setting placerange = new Setting("Place Range", "", (Module) this, (Object) Float.valueOf(5.0f), Float.valueOf(0.0f), Float.valueOf(6.0f));
    private final Setting targetrange = new Setting("Target Range", "", (Module) this, (Object) Float.valueOf(12.0f), Float.valueOf(0.1f), Float.valueOf(20.0f));
    private final Setting packetplace = new Setting("PacketPlace", "", this, true);
    private final Setting maxminecarts = new Setting("MaxMinecarts", "Max minecarts to place", (Module) this, (Object) 64, 1, 256);
    private final Setting minecartspertick = new Setting("MinecartsPerTick", "", (Module) this, (Object) 2, 1, 20);
    private final Setting placedelay = new Setting("PlaceDelay", "", (Module) this, (Object) 0, 0, 100);
    private final Setting webplace = new Setting("WebPlace", "", this, false);
    private final Setting bbreak = new Setting("Break", "", this, true);
    private final Setting flintandsteel = new Setting("Use Flint&Steel", "", this, true);
    private final Setting flinttimer = new Setting("UseFlintTimer", "", (Module) this, (Object) 100, 1, 1000);
    private final Setting fastplace = new Setting("FastPlace", "", this, true);
    private final Setting debug = new Setting("Debug", "", this, false);
    private EntityPlayer entity = null;
    private BlockPos blockPos = null;
    private IBlockState blockState = null;
    private int delay = 0;
    private boolean bool = false;
    private Timer time = new Timer();
    @EventHandler
    private Listener listen1 = new Listener<EventPlayerUpdate>(this::onFastPlace, new Predicate[0]);
    @EventHandler
    private Listener listen2 = new Listener<EventPlayerUpdate>(this::getEntityBreak, new Predicate[0]);

    public AutoMinecart() {
        super("AutoMinecart", "Place and light TNT minecarts to nearby players.", Category.COMBAT, "");
        this.addSetting(this.placerange);
        this.addSetting(this.targetrange);
        this.addSetting(this.packetplace);
        this.addSetting(this.maxminecarts);
        this.addSetting(this.minecartspertick);
        this.addSetting(this.placedelay);
        this.addSetting(this.webplace);
        this.addSetting(this.bbreak);
        this.addSetting(this.fastplace);
        this.addSetting(this.flintandsteel);
        this.addSetting(this.flinttimer);
        this.addSetting(this.debug);
    }

    public boolean getEnable() {
        super.getEnable();
        this.bool = false;
        return false;
    }

    public void getDisable() {
        super.getDisable();
        this.bool = false;
        this.delay = 0;
        this.bool = false;
    }

    private void getPlayerDistance() {
        if (this.entity == null || this.entity.isDead || this.entity.getDistance((Entity) Globals.mc.player) > ((Float) this.targetrange.getValue()).floatValue()) {
            this.entity = this.getPlayerEntity();
            if (this.entity == null) {
                this.getInGameChat("Can't find a new target, toggling!");
                return;
            }
            if (((Boolean) this.debug.getValue()).booleanValue()) {
                this.getInGameChat("Found new target:" + this.entity.getName());
            }
        }
    }

    public void getDistance() {
        this.getPlayerDistance();
        if (this.entity == null) {
            return;
        }
        if (!this.onBlock()) {
            if (((Boolean) this.debug.getValue()).booleanValue()) {
                this.getInGameChat("Can't place minecarts on target position, toggling!");
            }
            return;
        }
        this.onItem();
        if (!this.getRail(this.blockState.getBlock()) && !this.bool) {
            return;
        }
        if (((Boolean) this.webplace.getValue()).booleanValue()) {
            this.getBlockHand();
        }
        this.onPacketSend();
        if (((Boolean) this.flintandsteel.getValue()).booleanValue() && this.bool) {
            this.onTime();
        }
    }


    private boolean onBlock() {
        this.blockPos = Class30.Method209(this.entity);
        this.blockState = Globals.mc.world.getBlockState(this.blockPos);
        return this.getRail(this.blockState.getBlock()) || this.blockState.getBlock() == Blocks.AIR;
    }

    private void onItem() {
        if (this.getRail(this.blockState.getBlock()) || this.bool || this.bool) {
            return;
        }
        if (this.blockState.getBlock() == Blocks.AIR) {
            if (Class155.Method544(BlockRailBase.class) == -1) {
                if (((Boolean) this.debug.getValue()).booleanValue()) {
                    this.getInGameChat("Can't find rails in hotbar!");
                }
                new MixinBlockRailBase();
            } else {
                Globals.mc.player.inventory.currentItem = Class155.Method544(BlockRailBase.class);
                Globals.mc.playerController.processRightClickBlock(Globals.mc.player, Globals.mc.world, this.blockPos.down(), EnumFacing.UP, new Vec3d((Vec3i) this.blockPos), EnumHand.MAIN_HAND);
                Globals.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
        }
    }

    private boolean getRail(Block block) {
        return block == Blocks.ACTIVATOR_RAIL || block == Blocks.RAIL || block == Blocks.DETECTOR_RAIL || block == Blocks.GOLDEN_RAIL;
    }

    private void getBlockHand() {
        if (Globals.mc.world.getBlockState(this.blockPos.up()).getBlock() == Blocks.WEB) {
            return;
        }
        if (Globals.mc.world.getBlockState(this.blockPos.up()).getBlock() != Blocks.AIR) {
            if (((Boolean) this.debug.getValue()).booleanValue() && !this.bool) {
                this.getInGameChat("Can't place a web!");
                this.bool = true;
            }
            return;
        }
        if (Class155.Method545(Blocks.WEB) == -1) {
            if (((Boolean) this.debug.getValue()).booleanValue() && !this.bool) {
                this.getInGameChat("Can't find webs in hotbar!");
                this.bool = true;
            }
        } else {
            Globals.mc.player.inventory.currentItem = Class155.Method545(Blocks.WEB);
            Globals.mc.playerController.processRightClickBlock(Globals.mc.player, Globals.mc.world, this.blockPos, EnumFacing.UP, new Vec3d((Vec3i) this.blockPos), EnumHand.MAIN_HAND);
            Globals.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }

    private void onPacketSend() {
        if (this.bool) {
            return;
        }
        if (this.delay >= (Integer) this.maxminecarts.getValue()) {
            this.bool = true;
            return;
        }
        if (Class155.Method544(ItemMinecart.class) == -1 && !this.bool) {
            if (((Boolean) this.debug.getValue()).booleanValue()) {
                this.getInGameChat("Can't find minecarts in hotbar!");
            }
            return;
        }
        if (Class155.Method745(Item.getItemById((int) 407)) == -1) {
            this.delay = (Integer) this.maxminecarts.getValue();
            return;
        }
        Globals.mc.player.inventory.currentItem = Class155.Method745(Item.getItemById((int) 407));
        if (Globals.mc.player.inventory.currentItem == Class155.Method745(Item.getItemById((int) 407))) {
            for (int i = 0; i < (Integer) this.minecartspertick.getValue(); ++i) {
                if (((Boolean) this.packetplace.getValue()).booleanValue()) {
                    Globals.mc.player.connection.sendPacket((Packet) new CPacketPlayerTryUseItemOnBlock(this.blockPos, EnumFacing.UP, EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
                } else {
                    Globals.mc.playerController.processRightClickBlock(Globals.mc.player, Globals.mc.world, this.blockPos, EnumFacing.UP, new Vec3d((Vec3i) this.blockPos), EnumHand.MAIN_HAND);
                }
                Globals.mc.player.swingArm(EnumHand.MAIN_HAND);
                this.bool = true;
                ++this.delay;
            }
        }
        this.time.reset();
    }

    private void onTime() {
        if (Class155.Method544(ItemFlintAndSteel.class) == -1) {
            if (((Boolean) this.debug.getValue()).booleanValue()) {
                this.getInGameChat("Can't find Flint & Steel in hotbar!");
            }
        } else {
            Globals.mc.player.inventory.currentItem = Class155.Method745(Item.getItemById((int) 259));
            Globals.mc.playerController.updateController();
            Globals.mc.playerController.processRightClickBlock(Globals.mc.player, Globals.mc.world, this.blockPos.down(), EnumFacing.UP, new Vec3d((Vec3i) this.blockPos.down()), EnumHand.MAIN_HAND);
            Globals.mc.player.swingArm(EnumHand.MAIN_HAND);
            this.time.reset();
        }
        this.onTime();
    }

    private void onBlockPickaxe() {
        boolean bl;
        System.out.println("abyss: getting pickage!");
        boolean bl2 = bl = Globals.mc.player.getHeldItemMainhand().getItem() == Items.DIAMOND_PICKAXE;
        if (!bl) {
            for (int i = 0; i < 9; ++i) {
                ItemStack itemStack = Globals.mc.player.inventory.getStackInSlot(i);
                if (itemStack.isEmpty() || itemStack.getItem() != Items.DIAMOND_PICKAXE) continue;
                bl = true;
                Globals.mc.player.inventory.currentItem = i;
                Globals.mc.playerController.updateController();
                break;
            }
        }
        if (!bl) {
            Class547.printChatMessage(ChatFormatting.RED + "No pickaxe!");
        }
    }

    private boolean onDamageBlockPacket() {
        if (this.blockPos == null) {
            return false;
        }
        IBlockState iBlockState = Globals.mc.world.getBlockState(this.blockPos);
        if (AutoMinecart.onBlockBedrock(iBlockState) || Globals.mc.player.getDistanceSq(this.blockPos) > Math.pow(((Float) this.placerange.getValue()).floatValue(), ((Float) this.placerange.getValue()).floatValue())) {
            this.blockPos = null;
            return false;
        }
        Globals.mc.player.swingArm(EnumHand.MAIN_HAND);
        EnumFacing enumFacing = EnumFacing.UP;
        if (!this.bool) {
            this.bool = true;
            Globals.mc.player.connection.sendPacket((Packet) new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, this.blockPos, enumFacing));
        } else {
            Globals.mc.playerController.onPlayerDamageBlock(this.blockPos, enumFacing);
        }
        return true;
    }

    private static boolean onBlockBedrock(IBlockState iBlockState) {
        return iBlockState.getBlock() == Blocks.BEDROCK || iBlockState.getBlock() == Blocks.AIR || iBlockState.getBlock() instanceof BlockLiquid;
    }

    private EntityPlayer getPlayerEntity() {
        EntityPlayer entityPlayer4 = null;
        for (EntityPlayer entityPlayer2 : Globals.mc.world.playerEntities) {
            if (entityPlayer2 instanceof EntityPlayer) {
                if (Manager.Field223.Method233(entityPlayer2.getName()) || Class30.Method749((Entity) entityPlayer2, ((Float) this.placerange.getValue()).floatValue() + ((Float) this.targetrange.getValue()).floatValue()))
                    continue;
                if (entityPlayer2 == null) {
                    entityPlayer2 = entityPlayer4;
                    continue;
                }
                if (!(Globals.mc.player.getDistanceSq((Entity) entityPlayer2) < Globals.mc.player.getDistanceSq((Entity) entityPlayer2)))
                    continue;
                entityPlayer2 = entityPlayer4;
            }
        }
        return entityPlayer4;
    }

    private void getInGameChat(String string) {
        if (Globals.mc.ingameGUI != null || Globals.mc.player != null) {
            Globals.mc.ingameGUI.getChatGUI().printChatMessage((ITextComponent) new TextComponentString(ChatFormatting.GOLD + "" + ":" + ChatFormatting.WHITE + string));
        }
    }

    private void getEntityBreak(EventPlayerUpdate eventPlayerUpdate) {
        if (!((Boolean) this.bbreak.getValue()).booleanValue()) {
            return;
        }
        if (eventPlayerUpdate.getEra() != Class53.PRE) {
            return;
        }
        if (this.bool && !this.bool) {
            this.onBlockPickaxe();
            if (!this.onDamageBlockPacket()) {
                if (((Boolean) this.debug.getValue()).booleanValue()) {
                    this.getInGameChat("Finished");
                }
                this.bool = false;
                this.bool = true;
                this.time.reset();
                if (!((Boolean) this.flintandsteel.getValue()).booleanValue()) {
                    this.getPlayerEntity();
                }
            }
        }
    }

    private void onFastPlace(EventPlayerUpdate eventPlayerUpdate) {
        if (((Boolean) this.fastplace.getValue()).booleanValue()) {
            Globals.mc.rightClickDelayTimer = 0;
        }
        this.getDistance();
    }
}