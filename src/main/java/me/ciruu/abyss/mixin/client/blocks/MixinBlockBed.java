package me.ciruu.abyss.mixin.client.blocks;

import me.ciruu.abyss.AbyssMod;
import me.ciruu.abyss.events.render.EventRenderBlockLayer;
import net.minecraft.block.BlockBed;
import net.minecraft.util.BlockRenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBed.class)
public class MixinBlockBed {

    @Inject(method = "getRenderLayer", at = @At("HEAD"), cancellable = true)
    public void getRenderLayer(CallbackInfoReturnable<BlockRenderLayer> cir) {
        EventRenderBlockLayer event = new EventRenderBlockLayer(null);
        AbyssMod.EVENT_BUS.post(event);
        if (event.isCancelled()) {
            cir.setReturnValue(event.getBlockRenderLayer());
            cir.cancel();
        }
    }
}
