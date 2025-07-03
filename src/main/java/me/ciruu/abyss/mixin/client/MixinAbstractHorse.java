package me.ciruu.abyss.mixin.client;

import me.ciruu.abyss.AbyssMod;
import me.ciruu.abyss.events.entity.EventHorseSaddled;
import me.ciruu.abyss.events.entity.EventSteerEntity;
import net.minecraft.entity.passive.AbstractHorse;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractHorse.class)
public class MixinAbstractHorse {

    @Inject(method = "canBeSteered", at = @At("HEAD"), cancellable = true)
    public void canBeSteered(CallbackInfoReturnable<Boolean> cir) {
        EventSteerEntity event = new EventSteerEntity();
        if (event.isCancelled()) {
            cir.setReturnValue(true);
            cir.cancel();
        }
        event.getClass53();
        AbyssMod.EVENT_BUS.post(event);
    }

    @Inject(method = "isHorseSaddled", at = @At("HEAD"), cancellable = true)
    public void isHorseSaddled(CallbackInfoReturnable<Boolean> cir) {
        EventHorseSaddled event = new EventHorseSaddled();
        if (event.isCancelled()) {
            cir.setReturnValue(true);
            cir.cancel();
        }
        AbyssMod.EVENT_BUS.post(event);
    }
}
