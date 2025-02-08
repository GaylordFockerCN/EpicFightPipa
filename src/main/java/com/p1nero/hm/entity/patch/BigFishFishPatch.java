package com.p1nero.hm.entity.patch;

import com.p1nero.hm.entity.BigFishFish;
import com.p1nero.hm.epicfight.animation.HMAnimations;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import yesman.epicfight.api.animation.Animator;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.world.capabilities.entitypatch.Faction;
import yesman.epicfight.world.capabilities.entitypatch.MobPatch;
import yesman.epicfight.world.damagesource.StunType;

public class BigFishFishPatch extends MobPatch<BigFishFish> {
    public BigFishFishPatch() {
        super(Faction.NEUTRAL);
    }

    @Override
    public void onJoinWorld(BigFishFish entity, EntityJoinLevelEvent event) {
        super.onJoinWorld(entity, event);
//        this.playAnimationSynchronized(HMAnimations.FISH_FLY, 0.0F);
    }

    @Override
    public void initAnimator(Animator animator) {
        animator.addLivingAnimation(LivingMotions.IDLE, HMAnimations.FISH_IDLE);

//        animator.addLivingAnimation(LivingMotions.DEATH, HMAnimations.FISH_FLY);
    }

    @Override
    public void updateMotion(boolean considerInaction) {
        this.currentLivingMotion = LivingMotions.IDLE;
    }

    @Override
    public StaticAnimation getHitAnimation(StunType stunType) {
        return HMAnimations.FISH_FLY;
    }

}