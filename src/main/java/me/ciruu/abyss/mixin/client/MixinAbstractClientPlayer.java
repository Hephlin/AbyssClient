package me.ciruu.abyss.mixin.client;

import me.ciruu.abyss.Manager;
import me.ciruu.abyss.modules.client.Capes;
import me.ciruu.abyss.util.Cape;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.util.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = AbstractClientPlayer.class, priority = 0x7FFFFFFE)
public abstract class MixinAbstractClientPlayer extends MixinEntityPlayer {

    @Shadow
    public abstract boolean isSpectator();
    public AbstractClientPlayer player;

    @Inject(method = "getLocationCape", at = @At("RETURN"), cancellable = true)
    public void getCape(CallbackInfoReturnable<ResourceLocation> cir) {
        if (!Manager.moduleManager.isModuleEnabled(Capes.class)) {
            return;
        }

        player = (AbstractClientPlayer) (Object) this;
        Capes capesModule = (Capes) Manager.moduleManager.getModuleByClass(Capes.class);
        capesModule.animtime();
        String uuid = player.getUniqueID().toString();
        Cape cape = Manager.capeManager.getCape(uuid);
        if (cape != null && cape.Method1543()) {
            cir.setReturnValue(cape.Method1544());
        }
    }
}
