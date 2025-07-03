package me.ciruu.abyss.modules.combat;

import java.util.function.Predicate;

import me.ciruu.abyss.Globals;
import me.ciruu.abyss.Manager;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.events.network.EventNetworkPrePacketEvent;
import me.ciruu.abyss.modules.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.SPacketAnimation;
import net.minecraft.network.play.server.SPacketCollectItem;
import net.minecraft.network.play.server.SPacketCombatEvent;
import net.minecraft.network.play.server.SPacketDestroyEntities;
import net.minecraft.network.play.server.SPacketEntity;
import net.minecraft.network.play.server.SPacketEntityMetadata;
import net.minecraft.network.play.server.SPacketEntityProperties;
import net.minecraft.network.play.server.SPacketEntityStatus;
import net.minecraft.network.play.server.SPacketEntityTeleport;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.network.play.server.SPacketSpawnExperienceOrb;
import net.minecraft.network.play.server.SPacketSpawnGlobalEntity;
import net.minecraft.network.play.server.SPacketSpawnMob;
import net.minecraft.network.play.server.SPacketSpawnObject;
import net.minecraft.network.play.server.SPacketSpawnPainting;
import net.minecraft.network.play.server.SPacketSpawnPlayer;
import net.minecraft.world.World;

/*
 * Exception performing whole class analysis ignored.
 */
public class CrystalPredict extends Module {
    private final AutoCrystal autocrystal = (AutoCrystal) Manager.moduleManager.getModuleByClass(AutoCrystal.class);
    public int int1;

    @EventHandler
    public Listener listen1 = new Listener<EventNetworkPrePacketEvent>(this::Method3532, 400, new Predicate[0]);

    public CrystalPredict() {
        super("CrystalPredict", "Ez", Category.COMBAT, false);

    }

    public boolean getEnable() {
        super.getEnable();
        this.Method235();
        return false;
    }

    public AutoCrystal Method235() {
        try {
            if (Globals.mc.player == null || Globals.mc.world == null)
                return null;
            if (Manager.moduleManager.getModuleByClass(AutoCrystal.class) == null)
                return null;
            if (Manager.moduleManager.getModuleByClass(Predict.class) == null)
                return null;
            if (!Manager.moduleManager.isModuleEnabled(AutoCrystal.class))
                return null;
            if (!Manager.moduleManager.isModuleEnabled(Predict.class))
                return null;
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return Method235();
    }

    public AutoCrystal Method3532(EventNetworkPrePacketEvent eventNetworkPrePacketEvent) {
        Packet packet = eventNetworkPrePacketEvent.getPacket();
        String string = packet.getClass().getSimpleName();
        if (string.startsWith("C"))
            return null;
        try {
            if (packet == null)return null;
            SPacketSpawnObject sPacketSpawnObject;
            int n;
            if (packet instanceof SPacketCollectItem) {
                n = ((SPacketCollectItem) packet).getEntityID();
                this.int1 = Math.max(n, this.int1);
            }
            if (packet instanceof SPacketCombatEvent) {
                n = ((SPacketCombatEvent) packet).entityId;
                this.int1 = Math.max(n, this.int1);
            }
            if (packet instanceof SPacketEntity) {
                Object n3 = ((SPacketEntity) packet).getOnGround();
                n = (int) n3;
                this.int1 = Math.max(n, this.int1);
            }
            if (packet instanceof SPacketEntityMetadata) {
                n = ((SPacketEntityMetadata) packet).getEntityId();
                this.int1 = Math.max(n, this.int1);
            }
            if (packet instanceof SPacketEntityProperties) {
                n = ((SPacketEntityProperties) packet).getEntityId();
                this.int1 = Math.max(n, this.int1);
            }
            if (packet instanceof SPacketEntityStatus) {
                Object n4 = ((SPacketEntityStatus) packet).getOpCode();
                n = (int) n4;
                Entity object = ((SPacketEntityStatus) packet).getEntity((World) Globals.mc.world);
                this.int1 = Math.max(n, this.int1);
                if (((AutoCrystal) Manager.moduleManager.getModuleByClass(AutoCrystal.class)).player != null && object == ((AutoCrystal) Manager.moduleManager.getModuleByClass(AutoCrystal.class)).player && object.isDead)((Module)Predict.module).moduleEnabled();
                if (Predict.class != null) {
                    ((Module) Predict.module).enable();
                }
            }
            if (packet instanceof SPacketEntityVelocity) {
                n = ((SPacketEntityVelocity) packet).getEntityID();
                this.int1 = Math.max(n, this.int1);
            }
            if (packet instanceof SPacketSpawnExperienceOrb) {
                SPacketSpawnExperienceOrb sPacketSpawnExperienceOrb = (SPacketSpawnExperienceOrb) packet;
                int n2 = sPacketSpawnExperienceOrb.getEntityID();
                this.int1 = Math.max(n2, this.int1);
                if (Globals.mc.player.getDistance(sPacketSpawnExperienceOrb.getX(), sPacketSpawnExperienceOrb.getY(), sPacketSpawnExperienceOrb.getZ()) >= 6.0) {
                    Predict.getSaveSettings();
                }
            }
            if (packet instanceof SPacketSpawnGlobalEntity) {
                int n3 = ((SPacketSpawnGlobalEntity) packet).getEntityId();
                this.int1 = Math.max(n3, this.int1);
            }
            if (packet instanceof SPacketSpawnPainting) {
                int n4 = ((SPacketSpawnPainting) packet).getEntityID();
                this.int1 = Math.max(n4, this.int1);
            }
            if (packet instanceof SPacketSpawnPlayer) {
                int n5 = ((SPacketSpawnPlayer) packet).getEntityID();
                this.int1 = Math.max(n5, this.int1);
            }
            if (packet instanceof SPacketSpawnMob) {
                int n6 = ((SPacketSpawnMob) packet).getEntityID();
                this.int1 = Math.max(n6, this.int1);
            }
            if (packet instanceof SPacketEntityTeleport) {
                int n7 = ((SPacketEntityTeleport) packet).getEntityId();
                this.int1 = Math.max(n7, this.int1);
            }
            if (packet instanceof SPacketSpawnObject && (sPacketSpawnObject = (SPacketSpawnObject) packet).getType() != 51) {
                this.int1 = sPacketSpawnObject.getEntityID();
                if (Globals.mc.player.getDistance(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ()) >= 6.0 && sPacketSpawnObject.getType() == 60 || sPacketSpawnObject.getType() == 91 || sPacketSpawnObject.getType() == 75 || sPacketSpawnObject.getType() == 2) {
                    Predict.getSaveSettings();
                }
            }
            if (packet instanceof SPacketDestroyEntities) {
                int[] nArray;
                for (int n2 : nArray = ((SPacketDestroyEntities) packet).getEntityIDs()) {
                    this.int1 = Math.max(n2, this.int1);
                    if (((AutoCrystal) Manager.moduleManager.getModuleByClass(AutoCrystal.class)).player == null || ((AutoCrystal) Manager.moduleManager.getModuleByClass(AutoCrystal.class)).player.getEntityId() != n2)
                        continue;
                    Predict.getSaveSettings();
                }
            }
            if (packet instanceof SPacketAnimation) {
                int n8 = ((SPacketAnimation) packet).getEntityID();
                this.int1 = Math.max(n8, this.int1);
            }
        } catch (NullPointerException e) {
            e.getSuppressed();
        }
        return null;
    }
}

