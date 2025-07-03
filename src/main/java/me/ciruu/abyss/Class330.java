package me.ciruu.abyss;

import com.mojang.realmsclient.gui.ChatFormatting;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.enums.Class329;
import me.ciruu.abyss.enums.Class476;
import me.ciruu.abyss.events.network.EventNetworkExceptionCaught;
import me.ciruu.abyss.events.network.EventNetworkPostPacketEvent;
import me.ciruu.abyss.events.network.EventNetworkPrePacketEvent;
import me.ciruu.abyss.events.player.EventPlayerMove;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.init.Blocks;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketConfirmTeleport;
import net.minecraft.network.play.client.CPacketPlayer;
import net.minecraft.network.play.server.SPacketCloseWindow;
import net.minecraft.network.play.server.SPacketPlayerPosLook;
import net.minecraft.util.math.BlockPos;

public class Class330
extends Module {
    private final Setting Field1652 = new Setting("Mode", "", this, (Object)Class329.SETBACK);
    private final Setting Field1653 = new Setting("Bounds", "", this, (Object)Class476.DOWN);
    private final Setting Field1654 = new Setting("Speed", "", (Module)this, (Object)Float.valueOf(0.2f), Float.valueOf(0.01f), Float.valueOf(0.5f));
    private final Setting Field1655 = new Setting("Up Speed", "", (Module)this, (Object)Float.valueOf(0.05f), Float.valueOf(0.01f), Float.valueOf(0.2f));
    private final Setting Field1656 = new Setting("No Kick", "", this, true);
    private final Setting Field1657 = new Setting("Smooth", "", this, true);
    private final Setting Field1658 = new Setting("Extra Packets", "", this, true);
    private final List Field1659 = new ArrayList();
    private double Field1660 = 0.0;
    private double Field1661 = 0.0;
    private double Field1662 = 0.0;
    private int Field1663 = 0;
    private float Field1664 = 0.0f;
    private float Field1665 = 0.0f;
    @EventHandler
    public Listener Field1666 = new Listener<EventPlayerMove>(this::Method2028, new Predicate[0]);
    @EventHandler
    public Listener Field1667 = new Listener<EventNetworkPostPacketEvent>(this::Method2029, new Predicate[0]);
    @EventHandler
    public Listener Field1668 = new Listener<EventNetworkPrePacketEvent>(this::addSetting0, new Predicate[0]);
    @EventHandler
    public Listener Field1669 = new Listener<EventNetworkExceptionCaught>(this::addSetting1, new Predicate[0]);

    public Class330() {
        super("PacketFlyNew", "", Category.EXPLOIT, "");
        this.addSetting(this.Field1652);
        this.addSetting(this.Field1653);
        this.addSetting(this.Field1654);
        this.addSetting(this.Field1655);
        this.addSetting(this.Field1656);
        this.addSetting(this.Field1657);
        this.addSetting(this.Field1658);
    }

    public String addSetting3() {
        return ChatFormatting.WHITE + ((Class329)((Object)this.Field1652.getValue())).name();
    }

    public void addSetting4() {
        super.getEnable();
        this.Field1664 = Globals.mc.player.rotationPitch;
        this.Field1665 = Globals.mc.player.rotationYaw;
        this.Field1660 = Globals.mc.player.posX;
        this.Field1661 = Globals.mc.player.posY;
        this.Field1662 = Globals.mc.player.posZ;
    }

    public void addSetting5() {
        super.getDisable();
        this.Field1663 = 0;
        this.Field1659.clear();
    }

    private double addSetting6() {
        return this.Field1652.getValue() == Class329.SETBACK ? 0.003 : 0.03;
    }

    private void addSetting7(double d, double d2, double d3, float f, float f2) {
        Globals.mc.player.connection.sendPacket((Packet)this.addSetting8((CPacketPlayer)new CPacketPlayer.PositionRotation(d, d2, d3, f, f2, Globals.mc.player.onGround)));
        Globals.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(++this.Field1663));
        Globals.mc.player.connection.sendPacket((Packet)this.addSetting8((CPacketPlayer)new CPacketPlayer.PositionRotation(Globals.mc.player.posX, this.addSetting9(), Globals.mc.player.posZ, f, f2, Globals.mc.player.onGround)));
        Globals.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(++this.Field1663));
        Globals.mc.player.connection.sendPacket((Packet)this.addSetting8((CPacketPlayer)new CPacketPlayer.PositionRotation(d, d2, d3, f, f2, Globals.mc.player.onGround)));
        if (((Boolean)this.Field1658.getValue()).booleanValue()) {
            Globals.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.Field1663 - 1));
        }
        Globals.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.Field1663));
        if (((Boolean)this.Field1658.getValue()).booleanValue()) {
            Globals.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.Field1663 + 1));
        }
    }

    private void Method2040(double d, double d2, double d3, float f, float f2) {
        Globals.mc.player.connection.sendPacket((Packet)this.addSetting8((CPacketPlayer)new CPacketPlayer.PositionRotation(Globals.mc.player.posX, this.addSetting9(), Globals.mc.player.posZ, f, f2, Globals.mc.player.onGround)));
        Globals.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(++this.Field1663));
        Globals.mc.player.connection.sendPacket((Packet)this.addSetting8((CPacketPlayer)new CPacketPlayer.PositionRotation(d, d2, d3, f, f2, Globals.mc.player.onGround)));
        if (((Boolean)this.Field1658.getValue()).booleanValue()) {
            Globals.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.Field1663 - 1));
        }
        Globals.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.Field1663));
        if (((Boolean)this.Field1658.getValue()).booleanValue()) {
            Globals.mc.player.connection.sendPacket((Packet)new CPacketConfirmTeleport(this.Field1663 + 1));
        }
    }

    private double addSetting9() {
        return this.Field1653.getValue() == Class476.DOWN ? Globals.mc.player.posY - 1850.0 : (this.Field1653.getValue() == Class476.UP ? Globals.mc.player.posY + 1850.0 : this.Method2041());
    }

    private double Method2041() {
        BlockPos blockPos = Globals.mc.player.getPosition();
        while (Globals.mc.world.getBlockState(blockPos = blockPos.down()).getBlock() == Blocks.AIR) {
        }
        return blockPos.getY();
    }

    private CPacketPlayer addSetting8(CPacketPlayer cPacketPlayer) {
        this.Field1659.add(cPacketPlayer);
        return cPacketPlayer;
    }

    private void addSetting1(EventNetworkExceptionCaught eventNetworkExceptionCaught) {
        if (((Boolean)this.Field1656.getValue()).booleanValue()) {
            eventNetworkExceptionCaught.cancel();
        }
    }

    private void addSetting0(EventNetworkPrePacketEvent eventNetworkPrePacketEvent) {
        if (eventNetworkPrePacketEvent.getPacket() instanceof SPacketPlayerPosLook) {
            SPacketPlayerPosLook sPacketPlayerPosLook = (SPacketPlayerPosLook)eventNetworkPrePacketEvent.getPacket();
            this.Field1660 = sPacketPlayerPosLook.getX();
            this.Field1661 = sPacketPlayerPosLook.getY();
            this.Field1662 = sPacketPlayerPosLook.getZ();
            this.Field1663 = sPacketPlayerPosLook.getTeleportId();
            if (((Boolean)this.Field1657.getValue()).booleanValue()) {
                ((SPacketPlayerPosLook)eventNetworkPrePacketEvent.getPacket()).pitch = Globals.mc.player.rotationPitch;
                ((SPacketPlayerPosLook)eventNetworkPrePacketEvent.getPacket()).yaw = Globals.mc.player.rotationYaw;
            }
        }
        if (((Boolean)this.Field1656.getValue()).booleanValue() && eventNetworkPrePacketEvent.getPacket() instanceof SPacketCloseWindow) {
            eventNetworkPrePacketEvent.cancel();
        }
    }

    private void Method2029(EventNetworkPostPacketEvent eventNetworkPostPacketEvent) {
        if (eventNetworkPostPacketEvent.mb_packet instanceof CPacketPlayer) {
            if (this.Field1659.contains((CPacketPlayer)eventNetworkPostPacketEvent.mb_packet)) {
                this.Field1659.remove((CPacketPlayer)eventNetworkPostPacketEvent.mb_packet);
            } else {
                eventNetworkPostPacketEvent.cancel();
            }
            if (!eventNetworkPostPacketEvent.isCancelled() && ((Boolean)this.Field1657.getValue()).booleanValue()) {
                ((CPacketPlayer)eventNetworkPostPacketEvent.mb_packet).pitch = this.Field1664;
                ((CPacketPlayer)eventNetworkPostPacketEvent.mb_packet).yaw = this.Field1665;
            }
        }
    }

    private void Method2028(EventPlayerMove eventPlayerMove) {
        float f;
        float f2;
        boolean bl = Globals.mc.gameSettings.keyBindForward.isKeyDown() || Globals.mc.gameSettings.keyBindBack.isKeyDown() || Globals.mc.gameSettings.keyBindLeft.isKeyDown() || Globals.mc.gameSettings.keyBindRight.isKeyDown();
        double d = Globals.mc.player.posX;
        double d2 = Globals.mc.player.posY + (Globals.mc.gameSettings.keyBindJump.isKeyDown() ? (double)((Float)this.Field1655.getValue()).floatValue() : (!bl ? 0.0 : -this.addSetting6()));
        double d3 = Globals.mc.player.posZ;
        if (bl) {
            f2 = Globals.mc.player.rotationYaw;
            f = 1.0f;
            if (Globals.mc.player.moveForward < 0.0f) {
                f2 += 180.0f;
                f = -0.5f;
            } else if (Globals.mc.player.moveForward > 0.0f) {
                f = 0.5f;
            }
            if (Globals.mc.player.moveStrafing > 0.0f) {
                f2 -= 90.0f * f;
            }
            if (Globals.mc.player.moveStrafing < 0.0f) {
                f2 += 90.0f * f;
            }
            f2 = (float)Math.toRadians(f2);
            d += -Math.sin(f2) * (double)((Float)this.Field1654.getValue()).floatValue();
            d3 += Math.cos(f2) * (double)((Float)this.Field1654.getValue()).floatValue();
        }
        f2 = (Boolean)this.Field1657.getValue() != false ? this.Field1665 : Globals.mc.player.rotationYaw;
        float f3 = f = (Boolean)this.Field1657.getValue() != false ? this.Field1664 : Globals.mc.player.rotationPitch;
        if (this.Field1652.getValue() == Class329.SETBACK) {
            this.Method2040(d, d2, d3, f2, f);
        } else {
            this.addSetting7(d, d2, d3, f2, f);
        }
        Globals.mc.player.setPosition(this.Field1660, this.Field1661, this.Field1662);
        Globals.mc.player.setVelocity(0.0, 0.0, 0.0);
        eventPlayerMove.cancel();
        eventPlayerMove.getDoubVal4(0.0);
        eventPlayerMove.getDoubVal5(0.0);
        eventPlayerMove.getDoubVal6(0.0);
    }
}
