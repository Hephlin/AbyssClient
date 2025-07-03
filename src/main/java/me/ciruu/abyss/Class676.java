package me.ciruu.abyss;

import java.util.function.Predicate;

import me.ciruu.abyss.enums.Category;
import me.ciruu.abyss.modules.Module;
import me.ciruu.abyss.settings.Setting;
import me.zero.alpine.listener.EventHandler;
import me.zero.alpine.listener.Listener;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityEnderCrystal;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;

public class Class676
extends Module {
    private final Setting Field3493 = new Setting("ChangeId", "", this, false);
    private final Setting Field3494 = new Setting("Id", "", this, "1000");
    private int Field3495 = 1000000;
    @EventHandler
    private Listener Field3496 = new Listener<EntityJoinWorldEvent>(this::Method4241, new Predicate[0]);

    public Class676() {
        super("ChangeCrystalID", "", Category.MISC, "");
        this.addSetting(this.Field3493);
        this.addSetting(this.Field3494);
    }

    public void Method4247() {
        super.getEnable();
        if (((Boolean)this.Field3493.getValue()).booleanValue()) {
            Entity entity;
            RayTraceResult rayTraceResult = Globals.mc.objectMouseOver;
            RayTraceResult.Type type = rayTraceResult.typeOfHit;
            if (type == RayTraceResult.Type.ENTITY && (entity = rayTraceResult.entityHit) instanceof EntityEnderCrystal) {
                int n;
                try {
                    n = Integer.parseInt((String)this.Field3494.getValue());
                }
                catch (Exception exception) {
                    Class547.printChatMessage("Error, invalid ID!");
                    this.disable();
                    return;
                }
                entity.setEntityId(n);
                Class547.printChatMessage("Done!");
                Class547.printChatMessage("New ID:" + n);
                this.disable();
                return;
            }
            this.disable();
        }
    }

    private void Method4241(EntityJoinWorldEvent entityJoinWorldEvent) {
        if (entityJoinWorldEvent.getEntity() instanceof EntityEnderCrystal) {
            entityJoinWorldEvent.getEntity().setEntityId(this.Field3495);
            ++this.Field3495;
        }
    }
}
