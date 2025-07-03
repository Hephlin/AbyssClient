package me.ciruu.abyss.modules.combat;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import me.ciruu.abyss.*;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.enums.Class194;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.modules.render.Freecam;
import me.ciruu.abyss.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.block.BlockObsidian;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemPiston;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class AntiHoleCamp
extends Module {
    private final Setting mainwindow = new Setting("Main", "", this, new Class25("Main Settings"));
    private final Setting checkhole = new Setting("CheckHole", "", this, true);
    private final Setting redstone = new Setting("RedStone", "", this, Class194.Torch);
    private final Setting placeobsidian = new Setting("PlaceObby", "", this, true, this.mainwindow, this::redstone);
    private final Setting range = new Setting("Range", "", this, Float.valueOf(6.0f), Float.valueOf(0.0f), Float.valueOf(6.0f));
    private final Setting raytrace = new Setting("RayTrace", "", this, false);
    private final Setting rotate = new Setting("Rotate", "", this, false);
    private EntityPlayer currentTarget;
    private int item = -1;
    private int piston = -1;
    private int blocks = -1;
    private int obsidian = -1;
    private BlockPos direction;
    private final float[] rotation = new float[2];
    private boolean status = false;
    private BlockPos blockcolor = null;
    @EventHandler
    private final Listener listen1 = new Listener<Class26>(this::onBlockWithFreecam);
    @EventHandler
    private final Listener listen2 = new Listener<Class66>(this::getColor);

    public AntiHoleCamp() {
        super("AntiHoleCamp", "Pulls players out of their holes.", Category.COMBAT, "");
        this.addSetting(this.checkhole);
        this.addSetting(this.redstone);
        this.addSetting(this.placeobsidian);
        this.addSetting(this.range);
        this.addSetting(this.raytrace);
        this.addSetting(this.rotate);
        if (this.isEnabled()) {
            getDirection();
            getTorch();
        }
    }

    public boolean getEnable() {
        super.getEnable();
        this.currentTarget = Class30.Method518();
        if (this.currentTarget == null) {
            Class547.printChatMessage("Can't find any target!");
            this.onError();
            return false;
        }
        this.direction = Class30.Method209(this.currentTarget);
        if (!this.onItemPiston()) {
            this.onError();
        }
        return false;
    }

    public void getDisable() {
        super.getDisable();
        if (this.item != -1) {
            Class155.Method522(this.item, false);
        }
        if (this.status) {
            this.status = false;
            Manager.Field456.Method523(this.rotation[0], this.rotation[1]);
        }
    }

    private void getBlock() {
        this.blockRaytrace();
    }

    private void blockRaytrace() {
        this.direction = this.getTarget();
        if (this.direction == null) {
            Class547.printChatMessage("Can't place piston!");
            this.onError();
            return;
        }
        switch ((Class194) this.redstone.getValue()) {
            case Block: {
                this.getDirection();
                break;
            }
            case Torch: {
                this.getTorch();
            }
        }
    }

    private void getDirection() {
        List list = AntiHoleCamp.blockPos(this.direction);
        if (list.isEmpty()) {
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            EnumFacing enumFacing = (EnumFacing) list.get(i);
            BlockPos blockPos = this.direction.offset(enumFacing);
            if (!(Globals.mc.player.getDistanceSq(blockPos) < Class29.getDouble6(((Float)this.range.getValue()).floatValue())) || Class31.Method533(blockPos, this.raytrace.getValue()) != 3) {
                continue;
            }

            this.blockcolor = blockPos;
            this.getHoleCamp(this.direction, blockPos, false, enumFacing);
            break;
        }
    }


    private void getTorch() {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        BlockPos blockPos = this.direction.north();
        BlockPos blockPos2 = this.direction.west();
        BlockPos blockPos3 = this.direction.south();
        BlockPos blockPos4 = this.direction.east();
        arrayList.add(blockPos);
        arrayList.add(blockPos2);
        arrayList.add(blockPos3);
        arrayList.add(blockPos4);
        boolean bl = false;
        for (BlockPos blockPos5 : arrayList) {
            if (blockPos5 instanceof BlockPos) {
                if (!this.blockState(blockPos5) || Globals.mc.world.getBlockState(blockPos5.down()).getMaterial().isReplaceable())
                    continue;
                this.getHoleCamp(this.direction, blockPos5, true, EnumFacing.UP);
                bl = true;
                break;
            }
        }
        if (!bl && ((Boolean)this.placeobsidian.getValue()).booleanValue()) {
            for (BlockPos blockPos5 : arrayList) {
                if (blockPos5 instanceof BlockPos) {
                    if (!this.blockState(blockPos5.down()) || !this.blockState(blockPos5)) continue;
                    Class155.Method522(this.obsidian, false);
                    Class31.Method536(blockPos5.down(), EnumHand.MAIN_HAND, this.rotate.getValue(), true, Globals.mc.player.isSneaking());
                    this.getHoleCamp(this.direction, blockPos5, true, EnumFacing.UP);
                    break;
                }
            }
        }
    }

    private boolean blockState(BlockPos blockPos) {
        return !this.blockEntity(blockPos) && Globals.mc.world.getBlockState(blockPos).getMaterial().isReplaceable();
    }

    private boolean blockEntity(BlockPos blockPos) {
        for (Entity entity : Globals.mc.world.loadedEntityList) {
            if (entity instanceof Entity) {
                if (entity.equals(Globals.mc.player) || entity instanceof EntityItem || entity instanceof EntityXPOrb || !new AxisAlignedBB(blockPos).intersects(entity.getEntityBoundingBox()))
                    continue;
                return true;
            }
        }
        return false;
    }

    private void getHoleCamp(BlockPos blockPos, BlockPos blockPos2, boolean bl, EnumFacing enumFacing) {
        Class155.Method522(this.piston, false);
        this.rotation[0] = Globals.mc.player.rotationYaw;
        this.rotation[1] = Globals.mc.player.rotationPitch;
        this.status = true;
        double d = Class84.Method538((float)blockPos.getX() + 0.5f, (float)blockPos.getY() + 0.5f, (float)blockPos.getZ() + 0.5f, this.currentTarget)[0];
        Manager.Field456.Method523(Class84.Method540(Class84.Method539((float)d)), 0.0f);
        Globals.mc.player.connection.sendPacket(new CPacketPlayer.Rotation((float)Class84.Method540(Class84.Method539((float)d)), 0.0f, true));
        Class31.Method536(blockPos, EnumHand.MAIN_HAND, this.rotate.getValue(), true, Globals.mc.player.isSneaking());
        Class155.Method522(this.blocks, false);
        Class31.Method541(blockPos2, this.rotate.getValue(), this.rotate.getValue(), false);
        Class547.printChatMessage(ChatFormatting.GREEN + "Done!");
        this.onError();
    }

    private BlockPos getTarget() {
        ArrayList<BlockPos> arrayList = new ArrayList<BlockPos>();
        BlockPos blockPos = this.direction.north().up();
        BlockPos blockPos2 = this.direction.west().up();
        BlockPos blockPos3 = this.direction.south().up();
        BlockPos blockPos4 = this.direction.east().up();
        arrayList.add(blockPos);
        arrayList.add(blockPos2);
        arrayList.add(blockPos3);
        arrayList.add(blockPos4);
        BlockPos blockPos5 = null;
        ArrayList<BlockPos> arrayList2 = new ArrayList<BlockPos>();
        for (BlockPos blockPos6 : arrayList) {
            if (blockPos6 instanceof BlockPos) {
                if (Class31.Method533(blockPos6, this.raytrace.getValue()) != 3 || Globals.mc.player.getDistanceSq(blockPos6) > Class29.getDouble6(((Float) this.range.getValue()).floatValue()))
                    continue;
                arrayList2.add(blockPos6);
            }
        }
        double d = 5.0;
        if (!arrayList2.isEmpty()) {
            for (BlockPos blockPos7 : arrayList2) {
                if (blockPos7 instanceof BlockPos) {
                    Vec3d vec3d = new Vec3d(blockPos7);
                    if (!(this.currentTarget.getPositionVector().distanceTo(vec3d.add(0.5, 0.0, 0.5)) < d)) continue;
                    blockPos5 = blockPos7;
                }
            }
        }
        return blockPos5;
    }

    public static List blockPos(BlockPos blockPos) {
        ArrayList<EnumFacing> arrayList = new ArrayList<EnumFacing>();
        for (EnumFacing enumFacing : EnumFacing.values()) {
            if (enumFacing instanceof EnumFacing) {
                BlockPos blockPos2 = blockPos.offset(enumFacing);
                IBlockState iBlockState = Globals.mc.world.getBlockState(blockPos2);
                if (!iBlockState.getMaterial().isReplaceable()) continue;
                arrayList.add(enumFacing);
            }
        }
        return arrayList;
    }

    private boolean onFreecam() {
        if (Globals.mc.player == null || Globals.mc.world == null || Manager.moduleManager.isModuleEnabled(AutoTrap.class) || Manager.moduleManager.isModuleEnabled(Freecam.class)) {
            return false;
        }
        if (this.currentTarget != null && !Class30.Method543(this.currentTarget) && ((Boolean)this.checkhole.getValue()).booleanValue()) {
            Class547.printChatMessage("Target it's not in a hole!");
            return false;
        }
        return true;
    }

    private boolean onItemPiston() {
        this.piston = Class155.Method544(ItemPiston.class);
        if (this.piston == -1) {
            Class547.printChatMessage("Could not find pistons in hotbar!");
            return false;
        }
        switch ((Class194) this.redstone.getValue()) {
            case Torch: {
                this.blocks = Class155.Method545(Blocks.REDSTONE_TORCH);
                break;
            }
            case Block: {
                this.blocks = Class155.Method545(Blocks.REDSTONE_BLOCK);
            }
        }
        if (this.blocks == -1) {
            Class547.printChatMessage("Could not find redstone in hotbar!");
            return false;
        }
        if (((Boolean)this.placeobsidian.getValue()).booleanValue() && this.redstone.getValue() == Class194.Torch) {
            this.obsidian = Class155.Method544(BlockObsidian.class);
            if (this.obsidian == -1) {
                Class547.printChatMessage("Could not find obsidian in hotbar!");
                return false;
            }
        }
        this.item = Globals.mc.player.inventory.currentItem;
        return true;
    }

    private void getColor(Class66 class66) {
        if (this.blockcolor != null) {
            Class50.Method137(this.blockcolor, Color.RED, false, Color.RED, 1.0f, true, true, 125, true);
        }
    }

    private void onBlockWithFreecam(Class26 class26) {
        if (!this.onFreecam()) {
            this.onError();
            return;
        }
        this.getBlock();
    }

    private boolean redstone() {
        return this.redstone.getValue() == Class194.Torch;
    }

    public void onError() {
        Class547.printChatMessage(ChatFormatting.RED + "Operation canceled or failed.");
        this.status = false;
        this.status = false;
        if(this.blockcolor == null);
        if (this.direction == null);
        this.status = false;
        this.currentTarget = null;
        if (!this.onItemPiston()) {
            return;
        }
        this.getDisable();
        Class547.printChatMessage(ChatFormatting.YELLOW + "You can try again later.");
    }
}
