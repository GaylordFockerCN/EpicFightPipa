package com.p1nero.pipa.mixin;

import com.p1nero.pipa.EpicFightPiPa;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import yesman.epicfight.client.particle.TrailParticle;

@Mixin(TrailParticle.class)
public class TrailParticleMixin {
    @Unique
    private long epicFight_PiPa$lastTimeUpdate = System.currentTimeMillis();
    @Unique
    private int epicFight_PiPa$time = 1;
    @ModifyArg(method = "<init>(Lnet/minecraft/client/multiplayer/ClientLevel;Lyesman/epicfight/world/capabilities/entitypatch/LivingEntityPatch;Lyesman/epicfight/api/animation/Joint;Lyesman/epicfight/api/animation/types/StaticAnimation;Lyesman/epicfight/api/client/animation/property/TrailInfo;Lnet/minecraft/client/particle/SpriteSet;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/TextureManager;getTexture(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/renderer/texture/AbstractTexture;"), index = 0)
    private ResourceLocation inject(ResourceLocation location){
        if(location.getPath().contains("textures/trails/fireframe")){
            long currentTime = System.currentTimeMillis();
            long timeDifference = currentTime - epicFight_PiPa$lastTimeUpdate;
            if (timeDifference >= 50) {
                epicFight_PiPa$lastTimeUpdate = currentTime;
                epicFight_PiPa$time++;
                if (epicFight_PiPa$time > 60) {
                    epicFight_PiPa$time = 1;
                }
            }
            return new ResourceLocation(EpicFightPiPa.MOD_ID, "textures/trails/fireframe" + epicFight_PiPa$time + ".png");
        }
        return location;
    }
}
