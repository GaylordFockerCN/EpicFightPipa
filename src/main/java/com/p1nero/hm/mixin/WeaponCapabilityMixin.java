package com.p1nero.hm.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

@Mixin(value = WeaponCapability.class, remap = false)
public class WeaponCapabilityMixin {
    @Inject(method = "shouldCancelCombo", at = @At("HEAD"), cancellable = true)
    private void inject(LivingEntityPatch<?> entitypatch, CallbackInfoReturnable<Boolean> cir){
        if(!entitypatch.getOriginal().getMainHandItem().getItem().getDescriptionId().contains("cdmoveset")){
            cir.setReturnValue(false);
        }
    }
}
