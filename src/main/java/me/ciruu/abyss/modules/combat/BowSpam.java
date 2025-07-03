package me.ciruu.abyss.modules.combat;

import java.util.function.Predicate;
import me.ciruu.abyss.Class26;
import me.ciruu.abyss.Globals;
import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.modules.Module;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.item.ItemBow;
import net.minecraft.network.Packet;
import net.minecraft.network.play.client.CPacketPlayerDigging;
import net.minecraft.network.play.client.CPacketPlayerTryUseItem;
import net.minecraft.util.math.BlockPos;

public class BowSpam
extends Module {
    @EventHandler
    private Listener Field1227 = new Listener<Class26>(this::getEnable, new Predicate[0]);

    public BowSpam() {
        super("BowSpam", "Spam arrows with a bow.", Category.COMBAT, "");
    }

    private void getEnable(Class26 class26) {
        super.getEnable();
        if (Globals.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBow && Globals.mc.player.isHandActive() && Globals.mc.player.getItemInUseMaxCount() >= 3) {
            Globals.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, Globals.mc.player.getHorizontalFacing()));
            Globals.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(Globals.mc.player.getActiveHand()));
            Globals.mc.player.stopActiveHand();
        }
    }
}
