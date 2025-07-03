package me.ciruu.abyss.modules.movement;

import net.minecraft.network.play.server.SPacketEntityStatus;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SPacketEntityStatus.class)
public interface AccessorSPacketEntityStatus {

    @Accessor("logicOpcode")
    byte getOpcode();

    @Accessor("entityId")
    int getEntityId();
}
