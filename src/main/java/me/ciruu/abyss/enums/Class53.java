package me.ciruu.abyss.enums;

import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;

import java.io.IOException;

/*
 * Exception performing whole class analysis ignored.
 */
public enum Class53 implements Packet<INetHandler> {
    PRE,
    PERI,
    POST;

    @Override
    public void readPacketData(PacketBuffer packetBuffer) throws IOException {

    }

    @Override
    public void writePacketData(PacketBuffer packetBuffer) throws IOException {

    }

    @Override
    public void processPacket(INetHandler iNetHandler) {

    }
}
