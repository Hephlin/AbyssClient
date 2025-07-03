package me.ciruu.abyss;

import java.util.function.Predicate;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.enums.Class53;
import me.ciruu.abyss.events.network.EventNetworkPostPacketEvent;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.network.play.client.CPacketAnimation;
import net.minecraft.network.play.client.CPacketUseEntity;

public class Class672
extends Module {
    private final Setting Field3430 = new Setting("CancelUseEntity", "", this, false);
    private final Setting Field3431 = new Setting("CancelF", "", this, false);
    private final Setting Field3432 = new Setting("CancelAnimation", "", this, false);
    @EventHandler
    private Listener Field3433 = new Listener<EventNetworkPostPacketEvent>(this::Method4147, new Predicate[0]);

    public Class672() {
        super("CancelUseEntity", "", Category.MISC, "");
        this.addSetting(this.Field3430);
        this.addSetting(this.Field3431);
        this.addSetting(this.Field3432);
    }

    private void Method4147(EventNetworkPostPacketEvent eventNetworkPostPacketEvent) {
        if (eventNetworkPostPacketEvent.era != Class53.PRE) {
            return;
        }
        if (eventNetworkPostPacketEvent.mb_packet instanceof CPacketUseEntity && ((Boolean)this.Field3430.getValue()).booleanValue()) {
            eventNetworkPostPacketEvent.cancel();
        }
        if (((Boolean)this.Field3431.getValue()).booleanValue() && eventNetworkPostPacketEvent.mb_packet.getClass().getSimpleName().startsWith("f")) {
            eventNetworkPostPacketEvent.cancel();
        }
        if (((Boolean)this.Field3432.getValue()).booleanValue() && eventNetworkPostPacketEvent.mb_packet instanceof CPacketAnimation) {
            eventNetworkPostPacketEvent.cancel();
        }
    }
}
