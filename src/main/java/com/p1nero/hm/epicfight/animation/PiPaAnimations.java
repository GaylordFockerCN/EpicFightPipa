package com.p1nero.hm.epicfight.animation;

import com.p1nero.hm.EpicFightHM;
import com.p1nero.hm.entity.RainCutterSwordEntity;
import com.p1nero.hm.epicfight.weapon.PiPaColliders;
import net.mcreator.hm.init.HmModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.utils.TimePairList;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;

@Mod.EventBusSubscriber(modid = EpicFightHM.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PiPaAnimations {

    public static StaticAnimation AUTO1;
    public static StaticAnimation AUTO2;
    public static StaticAnimation AUTO3;
    public static StaticAnimation WSFH_AUTO1;
    public static StaticAnimation WSFH_AUTO2;
    public static StaticAnimation WSFH_AUTO3;


    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event) {
        event.getRegistryMap().put(EpicFightHM.MOD_ID, PiPaAnimations::build);
    }

    public static final AnimationEvent.AnimationEventConsumer SHOOT = ((livingEntityPatch, staticAnimation, objects) -> {
        if(livingEntityPatch.getOriginal().level() instanceof ServerLevel serverLevel){
//            Arrow arrow = new Arrow(serverLevel, livingEntityPatch.getOriginal());
//            arrow.setGlowingTag(true);
            Vec3 pos = livingEntityPatch.getOriginal().getEyePosition().add(0, -0.3, 0);
            Vec3 view = livingEntityPatch.getOriginal().getViewVector(1.0f);
//            Vec3 targetDir;
//            if(livingEntityPatch.getTarget() != null){
//                targetDir = livingEntityPatch.getTarget().position().subtract(pos).normalize();
//            } else {
//                targetDir = view.normalize();
//            }
//            arrow.setPos(livingEntityPatch.getOriginal().position().add(0, 1.5, 0).add(view.normalize().scale(0.5)));
//            arrow.shoot(targetDir.x, targetDir.y, targetDir.z, 5, 0.0F);
//            livingEntityPatch.playSound(SoundEvents.ARROW_SHOOT, 1, 1);
//            serverLevel.sendParticles(ParticleTypes.END_ROD, pos.x, pos.y, pos.z, 10, 0.5, 0.5, 0.5, 1);
//            serverLevel.addFreshEntity(arrow);


            RainCutterSwordEntity sword = new RainCutterSwordEntity(HmModItems.SJ.get().getDefaultInstance(), serverLevel);
            sword.setOwner(livingEntityPatch.getOriginal());
            sword.setNoGravity(true);
            sword.setBaseDamage(0.01);
            sword.setSilent(true);
            sword.pickup = AbstractArrow.Pickup.DISALLOWED;
            sword.setKnockback(1);//击退
            sword.setPierceLevel((byte) 5);//穿透
            sword.setPos(pos.add(view.normalize().scale(2)));
            sword.initDirection();
            serverLevel.playSound(null, sword.getOnPos(), EpicFightSounds.ENTITY_MOVE.get(), SoundSource.BLOCKS, 0.3f,1);
            serverLevel.addFreshEntity(sword);

        }
    });

    private static void build() {
        HumanoidArmature biped = Armatures.BIPED;

        AUTO1 = new BasicAttackAnimation(0.15F, 0.15F, 0.5667F, 0.5667F, null, biped.toolR,  "biped/auto_1", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F));
        AUTO2 = new BasicAttackAnimation(0.01F, 0.1F, 0.4333F, 0.4333F, null, biped.toolR,  "biped/auto_2", biped);
        AUTO3 = new BasicAttackAnimation(0.15F, 1.2F, 1.5F, 1.9833F, PiPaColliders.PI_PA_PLUS, biped.toolR,  "biped/auto_3", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5F))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true);
        WSFH_AUTO1 = new ActionAnimation(0.15F, 1.4F,  "biped/wsfh/wsfh_auto1", biped)
                .newTimePair(0.0F, 1.4F)
                .addStateRemoveOld(EntityState.TURNING_LOCKED, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(1.2F, SHOOT, AnimationEvent.Side.SERVER))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.2F));
        WSFH_AUTO2 = new ActionAnimation(0.15F, 1.6F,  "biped/wsfh/wsfh_auto2", biped)
                .newTimePair(0.0F, 1.6F)
                .addStateRemoveOld(EntityState.TURNING_LOCKED, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(1.4F, SHOOT, AnimationEvent.Side.SERVER))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.2F));
        WSFH_AUTO3 = new ActionAnimation(0.15F, 3.7F,  "biped/wsfh/wsfh_auto3", biped)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 3.7F))
                .newTimePair(0.0F, 3.7F)
                .addStateRemoveOld(EntityState.TURNING_LOCKED, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(1.0F, SHOOT, AnimationEvent.Side.SERVER));
    }

}
