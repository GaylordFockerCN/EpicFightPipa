package com.p1nero.pipa.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.animation.types.EntityState;

@Mixin(value = EntityState.class, remap = false)
public class EntityStateMixin {
    @Inject(method = "turningLocked", at = @At("HEAD"), cancellable = true)
    private void injected(CallbackInfoReturnable<Boolean> cir){
        cir.setReturnValue(false);
    }
}
