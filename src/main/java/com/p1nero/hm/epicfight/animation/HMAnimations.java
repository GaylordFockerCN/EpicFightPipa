package com.p1nero.hm.epicfight.animation;

import com.p1nero.hm.EpicFightHM;
import com.p1nero.hm.entity.RainCutterSwordEntity;
import com.p1nero.hm.epicfight.weapon.HMColliders;
import net.mcreator.hm.init.HmModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.projectile.AbstractArrow;
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
public class HMAnimations {
    public static StaticAnimation IDLE1;
    public static StaticAnimation IDLE2;
    public static StaticAnimation IDLE3;
    public static StaticAnimation WALK;
    public static StaticAnimation RUN;
    public static StaticAnimation C2_IDLE;
    public static StaticAnimation C2_AUTO1;
    public static StaticAnimation C2_AUTO2;
    public static StaticAnimation WSFH_AUTO1;
    public static StaticAnimation WSFH_AUTO2;
    public static StaticAnimation WSFH_AUTO3;
    public static StaticAnimation ELYSIA_ORIGIN_IDLE;
    public static StaticAnimation ELYSIA_ORIGIN_SKILL;
    public static StaticAnimation ELYSIA_ORIGIN_WALK;
    public static StaticAnimation ELYSIA_ORIGIN_WALK_BEGIN;
    public static StaticAnimation ELYSIA_ORIGIN_WALK_END;
    public static StaticAnimation ELYSIA_ORIGIN_AUTO1;
    public static StaticAnimation ELYSIA_ORIGIN_AUTO2;
    public static StaticAnimation ELYSIA_ORIGIN_AUTO3;
    public static StaticAnimation ELYSIA_ORIGIN_AUTO4;


    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event) {
        event.getRegistryMap().put(EpicFightHM.MOD_ID, HMAnimations::build);
    }

    public static final AnimationEvent.AnimationEventConsumer SHOOT = ((livingEntityPatch, staticAnimation, objects) -> {
        if (livingEntityPatch.getOriginal().level() instanceof ServerLevel serverLevel) {
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
            serverLevel.playSound(null, sword.getOnPos(), EpicFightSounds.ENTITY_MOVE.get(), SoundSource.BLOCKS, 0.3f, 1);
            serverLevel.addFreshEntity(sword);

        }
    });

    private static void build() {
        HumanoidArmature biped = Armatures.BIPED;

        IDLE1 = new StaticAnimation(false, "biped/zwzj/idle1", biped)
                .addEvents(AnimationEvent.TimeStampedEvent.create(3.9f, ((livingEntityPatch, staticAnimation, objects) -> {
                    if (livingEntityPatch.getOriginal().getRandom().nextBoolean()) {
                        livingEntityPatch.playAnimationSynchronized(IDLE2, 0.45F);
                    } else {
                        livingEntityPatch.playAnimationSynchronized(IDLE3, 0.45F);
                    }
                }), AnimationEvent.Side.BOTH));

        IDLE2 = new StaticAnimation(true, "biped/zwzj/idle2", biped);

        IDLE3 = new StaticAnimation(true, "biped/zwzj/idle3", biped);

        WALK = new MovementAnimation(true, "biped/zwzj/walk", biped);

        RUN = new MovementAnimation(true, "biped/zwzj/run", biped);

        C2_IDLE = new StaticAnimation(true, "biped/zwzj/elysia_c2_idle", biped);

        C2_AUTO1 = new ActionAnimation(0.15F, 0.75F, "biped/zwzj/elysia_c2_auto1", biped)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 2.5F))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.0F));

        C2_AUTO2 = new ActionAnimation(0.15F, 2.5F, "biped/zwzj/elysia_c2_auto2", biped)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 5.33F))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.0F));

        WSFH_AUTO1 = new ActionAnimation(0.15F, 1.4F, "biped/zwzj/wsfh_auto1", biped)
                .newTimePair(0.0F, 1.4F)
                .addStateRemoveOld(EntityState.TURNING_LOCKED, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(1.2F, SHOOT, AnimationEvent.Side.SERVER))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.2F));
        WSFH_AUTO2 = new ActionAnimation(0.15F, 1.6F, "biped/wsfh/wsfh_auto2", biped)
                .newTimePair(0.0F, 1.6F)
                .addStateRemoveOld(EntityState.TURNING_LOCKED, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(1.4F, SHOOT, AnimationEvent.Side.SERVER))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.2F));
        WSFH_AUTO3 = new ActionAnimation(0.15F, 3.7F, "biped/wsfh/wsfh_auto3", biped)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 3.7F))
                .newTimePair(0.0F, 3.7F)
                .addStateRemoveOld(EntityState.TURNING_LOCKED, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(1.0F, SHOOT, AnimationEvent.Side.SERVER));

        ELYSIA_ORIGIN_IDLE = new StaticAnimation(true, "biped/zwzj/elysia_origin_idle", biped);
        ELYSIA_ORIGIN_WALK = new StaticAnimation(true, "biped/zwzj/elysia_origin_walk", biped);
        ELYSIA_ORIGIN_WALK_BEGIN = new ActionAnimation(0.15F, "biped/zwzj/elysia_origin_walkbegin", biped)
                .newTimePair(0.0F, Float.MAX_VALUE)
                .addStateRemoveOld(EntityState.MOVEMENT_LOCKED, false)
                .addEvents(AnimationProperty.StaticAnimationProperty.ON_END_EVENTS,
                        (AnimationEvent.create(((livingEntityPatch, staticAnimation, objects) -> livingEntityPatch.reserveAnimation(ELYSIA_ORIGIN_WALK)), AnimationEvent.Side.SERVER)));
        ELYSIA_ORIGIN_WALK_END = new ActionAnimation(0.15F, "biped/zwzj/elysia_origin_walkend", biped);

        ELYSIA_ORIGIN_AUTO1 = new ActionAnimation(0.15F, 1.08F, "biped/zwzj/elysia_origin_auto1", biped)
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 4.033F));
//                .newTimePair(1.08F, 1.66F)
//                .addStateRemoveOld(EntityState.CAN_BASIC_ATTACK, true);
        ELYSIA_ORIGIN_AUTO2 = new ActionAnimation(0.15F, 2.5F, "biped/zwzj/elysia_origin_auto2", biped)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 5.333F))
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true);
//                .newTimePair(2.50F, 3.33F)
//                .addStateRemoveOld(EntityState.CAN_BASIC_ATTACK, true);
        ELYSIA_ORIGIN_AUTO3 = new ActionAnimation(0.15F, 2.5F, "biped/zwzj/elysia_origin_auto3", biped)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 4.667F))
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true);
//                .newTimePair(2.50F, 3.0F)
//                .addStateRemoveOld(EntityState.CAN_BASIC_ATTACK, true);
        ELYSIA_ORIGIN_AUTO4 = new ActionAnimation(0.15F, 1.05F, "biped/zwzj/elysia_origin_auto4", biped)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 1.05F))
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 0.66F));

        ELYSIA_ORIGIN_SKILL = new ActionAnimation(0.15F, 0.8F, "biped/zwzj/elysia_origin_skill", biped)
                .addProperty(AnimationProperty.ActionAnimationProperty.NO_GRAVITY_TIME, TimePairList.create(0.0F, 0.8F))
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true);

    }

}
