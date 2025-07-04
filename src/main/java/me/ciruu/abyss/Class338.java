package me.ciruu.abyss;

import java.util.function.Predicate;

import me.ciruu.abyss.events.network.EventNetworkPrePacketEvent;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
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
public class Class338 {
    public int Field1161;
    @EventHandler
    public Listener Field2369 = new Listener<EventNetworkPrePacketEvent>(this::Method2923, 400, new Predicate[0]);

    private void Method2923(EventNetworkPrePacketEvent eventNetworkPrePacketEvent) {
        SPacketSpawnObject sPacketSpawnObject;
        int n;
        if (Globals.mc.player == null || Globals.mc.world == null) {
            return;
        }
        Packet packet = eventNetworkPrePacketEvent.getPacket();
        String string = packet.getClass().getSimpleName();
        if (string.startsWith("C")) {
            return;
        }
        if (packet instanceof SPacketCollectItem) {
            n = ((SPacketCollectItem)packet).getEntityID();
            this.Field1161 = Math.max(n, this.Field1161);
        }
        if (packet instanceof SPacketCombatEvent) {
            n = ((SPacketCombatEvent)packet).entityId;
            this.Field1161 = Math.max(n, this.Field1161);
        }
        if (packet instanceof SPacketEntity) {
            n = ((SPacketEntity)packet).hashCode();
            this.Field1161 = Math.max(n, this.Field1161);
        }
        if (packet instanceof SPacketEntityMetadata) {
            n = ((SPacketEntityMetadata)packet).getEntityId();
            this.Field1161 = Math.max(n, this.Field1161);
        }
        if (packet instanceof SPacketEntityProperties) {
            n = ((SPacketEntityProperties)packet).getEntityId();
            this.Field1161 = Math.max(n, this.Field1161);
        }
        if (packet instanceof SPacketEntityStatus) {
            n = ((SPacketEntityStatus)packet).hashCode();
            Entity object = ((SPacketEntityStatus)packet).getEntity((World)Globals.mc.world);
            this.Field1161 = Math.max(n, this.Field1161);
            if (Class152.Field1081 != null && object == Class152.Field1081 && object.isDead) {
                Class152.Method1537().reset();
            }
        }
        if (packet instanceof SPacketEntityVelocity) {
            n = ((SPacketEntityVelocity)packet).getEntityID();
            this.Field1161 = Math.max(n, this.Field1161);
        }
        if (packet instanceof SPacketSpawnExperienceOrb) {
            SPacketSpawnExperienceOrb sPacketSpawnExperienceOrb = (SPacketSpawnExperienceOrb)packet;
            int n2 = sPacketSpawnExperienceOrb.getEntityID();
            this.Field1161 = Math.max(n2, this.Field1161);
            if (Globals.mc.player.getDistance(sPacketSpawnExperienceOrb.getX(), sPacketSpawnExperienceOrb.getY(), sPacketSpawnExperienceOrb.getZ()) >= 6.0) {
                Class152.Method1537().reset();
            }
        }
        if (packet instanceof SPacketSpawnGlobalEntity) {
            int n3 = ((SPacketSpawnGlobalEntity)packet).getEntityId();
            this.Field1161 = Math.max(n3, this.Field1161);
        }
        if (packet instanceof SPacketSpawnPainting) {
            int n4 = ((SPacketSpawnPainting)packet).getEntityID();
            this.Field1161 = Math.max(n4, this.Field1161);
        }
        if (packet instanceof SPacketSpawnPlayer) {
            int n5 = ((SPacketSpawnPlayer)packet).getEntityID();
            this.Field1161 = Math.max(n5, this.Field1161);
        }
        if (packet instanceof SPacketSpawnMob) {
            int n6 = ((SPacketSpawnMob)packet).getEntityID();
            this.Field1161 = Math.max(n6, this.Field1161);
        }
        if (packet instanceof SPacketEntityTeleport) {
            int n7 = ((SPacketEntityTeleport)packet).getEntityId();
            this.Field1161 = Math.max(n7, this.Field1161);
        }
        if (packet instanceof SPacketSpawnObject && (sPacketSpawnObject = (SPacketSpawnObject)packet).getType() != 51) {
            this.Field1161 = sPacketSpawnObject.getEntityID();
            if (Globals.mc.player.getDistance(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ()) >= 6.0 && sPacketSpawnObject.getType() == 60 || sPacketSpawnObject.getType() == 91 || sPacketSpawnObject.getType() == 75 || sPacketSpawnObject.getType() == 2) {
                Class152.Method1537().reset();
            }
        }
        if (packet instanceof SPacketDestroyEntities) {
            int[] nArray;
            for (int n2 : nArray = ((SPacketDestroyEntities)packet).getEntityIDs()) {
                this.Field1161 = Math.max(n2, this.Field1161);
                if (Class152.Field1081 == null || n2 != Class152.Field1081.getEntityId()) continue;
                Class152.Method1537().reset();
            }
        }
    }
}
