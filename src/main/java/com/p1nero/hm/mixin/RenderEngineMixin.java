package com.p1nero.hm.mixin;

import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.client.events.engine.RenderEngine;

@Mixin(value = RenderEngine.class, remap = false)
public abstract class RenderEngineMixin {

    @Inject(method = "hasRendererFor", at = @At("HEAD"), cancellable = true)
    private void injectHasRendererFor(Entity entity, CallbackInfoReturnable<Boolean> cir){
//        if(!entity.isAlive()){
//            cir.setReturnValue(false);
//        }
    }

}
