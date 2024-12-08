package com.p1nero.pipa.epicfight.animation;

import com.mojang.datafixers.util.Pair;
import com.p1nero.pipa.Config;
import com.p1nero.pipa.EpicFightPiPa;
import com.p1nero.pipa.epicfight.weapon.PiPaColliders;
import com.p1nero.pipa.util.EntityUtil;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.*;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.world.damagesource.StunType;

import java.util.List;

@Mod.EventBusSubscriber(modid = EpicFightPiPa.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PiPaAnimations {

    public static StaticAnimation AUTO1;
    public static StaticAnimation AUTO2;
    public static StaticAnimation AUTO3;
    public static StaticAnimation SONIC_BOOM;
    public static StaticAnimation TACHI_IDLE;
    public static StaticAnimation TACHI_WALK;
    public static StaticAnimation TACHI_RUN;
    public static StaticAnimation UCHIGATANA_AUTO1;
    public static StaticAnimation UCHIGATANA_AUTO2;
    public static StaticAnimation UCHIGATANA_AUTO3;
    public static StaticAnimation UCHIGATANA_AUTO4;
    public static StaticAnimation UCHIGATANA_AUTO5;
    public static StaticAnimation UCHIGATANA_DASH;
    public static StaticAnimation DUAL_TACHI_AUTO1;
    public static StaticAnimation DUAL_TACHI_AUTO2;
    public static StaticAnimation DUAL_TACHI_AUTO3;
    public static StaticAnimation DUAL_TACHI_AUTO4;
    public static StaticAnimation DUAL_TACHI_AUTO5;
    public static StaticAnimation DUAL_TACHI_SKILL1;
    public static StaticAnimation DUAL_TACHI_SKILL2;
    public static StaticAnimation YULLIAN_COMBOA1;
    public static StaticAnimation YULLIAN_COMBOA2;
    public static StaticAnimation YULLIAN_COMBOA3;
    public static StaticAnimation YULLIAN_COMBOB1;
    public static StaticAnimation YULLIAN_COMBOC1;
    public static StaticAnimation YULLIAN_COMBOC2;
    public static StaticAnimation YULLIAN_DODGEATTACK;
    public static StaticAnimation YULLIAN_IDLE;
    public static StaticAnimation YULLIAN_JUMP_HEAVYATTACK;
    public static StaticAnimation YULLIAN_JUMPPATTACK;
    public static StaticAnimation YULLIAN_RUN;
    public static StaticAnimation YULLIAN_SPECIALATTACK1;
    public static StaticAnimation YULLIAN_SPECIALATTACK2;
    public static StaticAnimation YULLIAN_SPECIALATTACK3;
    public static StaticAnimation YULLIAN_WALK;
    public static StaticAnimation YULLIAN_DASHAHATTCK;
    public static StaticAnimation SAKURA_DANCE;
    public static StaticAnimation Waterbirds_dance_wildly_a1;
    public static StaticAnimation Waterbirds_dance_wildly_a2;
    public static StaticAnimation Waterbirds_dance_wildly_a3;


    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event) {
        event.getRegistryMap().put(EpicFightPiPa.MOD_ID, PiPaAnimations::build);
    }

    private static void build() {
        HumanoidArmature biped = Armatures.BIPED;

        AUTO1 = new BasicAttackAnimation(0.15F, 0.15F, 0.5667F, 0.5667F, null, biped.toolR,  "biped/auto_1", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F));
        AUTO2 = new BasicAttackAnimation(0.01F, 0.1F, 0.4333F, 0.4333F, null, biped.toolR,  "biped/auto_2", biped);
        AUTO3 = new BasicAttackAnimation(0.15F, 1.2F, 1.5F, 1.9833F, PiPaColliders.PI_PA_PLUS, biped.toolR,  "biped/auto_3", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5F))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true);
        SONIC_BOOM = new BasicAttackAnimation(0.15F, 0, 0, 2.533F, null, biped.toolR,  "biped/sonic_boom", biped)
                .addState(EntityState.CAN_SKILL_EXECUTION, false)
                .addState(EntityState.CAN_BASIC_ATTACK, false)
                .addEvents(AnimationEvent.TimeStampedEvent.create(0.8833F, ((livingEntityPatch, staticAnimation, objects) -> {
                    LivingEntity livingEntity = livingEntityPatch.getOriginal();
                    ServerLevel serverLevel = ((ServerLevel) livingEntity.level());
                    ItemStack itemStack = livingEntity.getMainHandItem();
                    if(itemStack.is(EpicFightPiPa.PIPA.get()) && itemStack.getDamageValue() < itemStack.getMaxDamage()-1){
                        Vec3 attacker = livingEntity.getEyePosition();
                        Vec3 target = livingEntity.getViewVector(1.0F).normalize();
                        for(int i = 1; i < Mth.floor(target.length()) + 7; ++i) {
                            Vec3 pos = attacker.add(target.scale(i));
                            serverLevel.sendParticles(ParticleTypes.SONIC_BOOM, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0);
                        }
                        itemStack.setDamageValue(itemStack.getDamageValue() + 1);

                        serverLevel.playSound(null, livingEntity.getX(), livingEntity.getY(), livingEntity.getZ(), EpicFightPiPa.SONIC_BOOM.get(), SoundSource.BLOCKS, 1, 1);
                        for(LivingEntity entity : EntityUtil.getNearByEntities(serverLevel, livingEntity, 20)){
                            if(EntityUtil.isInFront(entity, livingEntity, 20) && entity.distanceTo(livingEntity) < 10){
                                entity.hurt(livingEntity.damageSources().sonicBoom(livingEntity), Config.SONIC_BOOM_DAMAGE.get().floatValue());
                            }
                        }

                    }
                }), AnimationEvent.Side.SERVER));

        TACHI_IDLE = new StaticAnimation(true, "biped/combat/dual_tachi/uchigatana_idle", biped);
        TACHI_WALK = new MovementAnimation(true, "biped/combat/dual_tachi/uchigatana_walk", biped);
        TACHI_RUN = new MovementAnimation(true, "biped/combat/dual_tachi/uchigatana_run", biped);

        UCHIGATANA_AUTO1 = new BasicAttackAnimation(0.05F, 0.467F, 0.6F, 0.833F, null, biped.toolR,
                "biped/combat/dual_tachi/uchigatana_auto1", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 0.9F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER,
                        ValueModifier.setter(1.5F));
        UCHIGATANA_AUTO2 = new BasicAttackAnimation(0.05F, 0.433F, 0.55F, 0.833F, null, biped.toolR,
                "biped/combat/dual_tachi/uchigatana_auto2", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 0.9F);
        UCHIGATANA_AUTO3 = new BasicAttackAnimation(0.05F, 0.4F, 0.7F, 0.833F, null, biped.toolR,
                "biped/combat/dual_tachi/uchigatana_auto3", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 0.9F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER,
                        ValueModifier.setter(1.25F));
        UCHIGATANA_AUTO4 = new BasicAttackAnimation(0.05F, 0.467F, 0.7F, 0.833F, null, biped.toolR,
                "biped/combat/dual_tachi/uchigatana_auto4", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 0.9F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER,
                        ValueModifier.setter(1.5F));
        UCHIGATANA_AUTO5 = new BasicAttackAnimation(0.05F, 0.567F, 0.667F, 1F, null, biped.toolR,
                "biped/combat/dual_tachi/uchigatana_auto5", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG);
        UCHIGATANA_DASH = new DashAttackAnimation(0.1F, 0.5F, 0.6F, 0.7F, 1.2F, null, biped.toolR,
                "biped/combat/dual_tachi/uchigatana_dash", biped)
                .addProperty(AnimationProperty.ActionAnimationProperty.STOP_MOVEMENT, true)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER,
                        Animations.ReusableSources.CONSTANT_ONE);
        DUAL_TACHI_AUTO1 = new BasicAttackAnimation(0.05F, "biped/combat/dual_tachi/dual_tachi_auto1", biped,
                new AttackAnimation.Phase(0.0F, 0.367F, 0.41F, 1.2F, 1.3F, InteractionHand.OFF_HAND,
                        biped.toolL, null),
                new AttackAnimation.Phase(0.2F, 0.633F, 0.68F, 1F, 1.3F, biped.toolR, null))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER,
                        ValueModifier.setter(1.5F))
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F);
        DUAL_TACHI_AUTO2 = new BasicAttackAnimation(0.05F, "biped/combat/dual_tachi/dual_tachi_auto2", biped,
                new AttackAnimation.Phase(0.0F, 0.5F, 0.63F, 0.667F, 0.667F, InteractionHand.MAIN_HAND,
                        biped.toolR, null),
                new AttackAnimation.Phase(0.2F, 0.7F, 0.8F, 1.167F, 0.8F, biped.toolL, null))
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER,
                        ValueModifier.setter(2.5F));
//        DUAL_TACHI_AUTO3 = new BasicAttackAnimation(0.05F, "biped/combat/dual_tachi/dual_tachi_auto3", biped,
//                new AttackAnimation.Phase(0.0F, 0.66F, 0.69F, 0.733F, 1F, Float.MAX_VALUE, false,
//                        InteractionHand.MAIN_HAND,
//                        List.of(Pair.of(biped.toolR, null), Pair.of(biped.toolL, null))))
//                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.0F)
//                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
//                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER,
//                        ValueModifier.setter(2.5F));
//        DUAL_TACHI_AUTO4 = new BasicAttackAnimation(0.05F, "biped/combat/dual_tachi/dual_tachi_auto4", biped,
//                new AttackAnimation.Phase(0.0F, 0.633F, 0.69F, 0.8F, 1.167F, 1.169F, false,
//                        InteractionHand.MAIN_HAND,
//                        List.of(Pair.of(biped.toolR, null), Pair.of(biped.toolL, null))))
//                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F)
//                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.LONG);

        DUAL_TACHI_AUTO5 = new BasicAttackAnimation(0.05F, "biped/combat/dual_tachi/dual_tachi_auto5", biped,
                new AttackAnimation.Phase(0.0F, 0.367F, 0.41F, 0.567F, 1.3F, InteractionHand.OFF_HAND,
                        biped.toolL, null),
                new AttackAnimation.Phase(0.2F, 0.633F, 0.68F, 0.767F, 1.3F, biped.toolR, null))
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.HOLD)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER,
                        ValueModifier.setter(1.5F))
                .addProperty(AnimationProperty.AttackAnimationProperty.BASIS_ATTACK_SPEED, 1.2F);

        DUAL_TACHI_SKILL1 = new BasicAttackAnimation(0.5F, 0.967F, 1.1F, 1.3333F, null, biped.toolR,
                "biped/combat/dual_tachi/uchigatana_heavy1", biped)
                .addProperty(AnimationProperty.AttackAnimationProperty.ATTACK_SPEED_FACTOR, 0.9F)
                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER,
                        ValueModifier.setter(25F))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER,
                        Animations.ReusableSources.CONSTANT_ONE)
                .addState(EntityState.MOVEMENT_LOCKED, true);
        DUAL_TACHI_SKILL2 = new BasicAttackAnimation(0.5F, 1.167F, 1.35F, 1.667F, null, biped.toolR,
                "biped/combat/dual_tachi/uchigatana_heavy2", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.STUN_TYPE, StunType.FALL)
                .addProperty(AnimationProperty.AttackPhaseProperty.IMPACT_MODIFIER,ValueModifier.setter(3F))
                .addProperty(AnimationProperty.AttackPhaseProperty.ARMOR_NEGATION_MODIFIER, ValueModifier.setter(25F))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER,Animations.ReusableSources.CONSTANT_ONE)
                .addState(EntityState.MOVEMENT_LOCKED, true);

        // yullian
        YULLIAN_COMBOA1 = new BasicAttackAnimation(0.1F, 0.8F, 0.93F, 1F, null, biped.toolR,
                "biped/yullian/yullian_comboa1", biped)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 0.9F));
        YULLIAN_COMBOA2 = new BasicAttackAnimation(0.1F, 0.7F, 0.8F, 0.85F, null, biped.toolR,
                "biped/yullian/yullian_comboa2", biped)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 0.9F));
        YULLIAN_COMBOA3 = new BasicAttackAnimation(0.1F, 0.467F, 0.6F, 2.43F, null, biped.toolR,
                "biped/yullian/yullian_comboa3", biped);
        YULLIAN_COMBOB1 = new BasicAttackAnimation(0.1F, "biped/yullian/yullian_combob1", biped,
                new AttackAnimation.Phase(0F, 0.63F, 0.76F, 3.23F, 0.76F, InteractionHand.MAIN_HAND,
                        biped.toolR, null),
                new AttackAnimation.Phase(0.76F, 1.36F, 1.53F, 3.23F, 3.23F, InteractionHand.MAIN_HAND,
                        biped.toolR, null));

        YULLIAN_COMBOC1 = new BasicAttackAnimation(0.1F, 0.4F, 0.5F, 0.73F, null, biped.toolR,
                "biped/yullian/yullian_comboc1", biped)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 0.9F));

        YULLIAN_COMBOC2 = new BasicAttackAnimation(0.05F, "biped/yullian/yullian_comboc2", biped,
                new AttackAnimation.Phase(0F, 0.5F, 0.9F, 0F, 0.9F, InteractionHand.MAIN_HAND,
                        biped.toolR, null),
                new AttackAnimation.Phase(0.9F, 0.9F, 1.1F, 1F, 233F, InteractionHand.MAIN_HAND,
                        biped.toolR, null))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 0.9F));

        YULLIAN_DODGEATTACK = new BasicAttackAnimation(0.1F, 0.26F, 0.83F, 2.667F, null, biped.toolR,
                "biped/yullian/yullian_dodgeattack", biped);

        YULLIAN_JUMP_HEAVYATTACK = new BasicAttackAnimation(0.1F, 0.967F, 1.06F, 4.267F, null, biped.toolR,
                "biped/yullian/yullian_jump_heavyattack", biped);

        YULLIAN_JUMPPATTACK = new BasicAttackAnimation(0.1F, 0.67F, 0.76F, 2.83F, null, biped.toolR,
                "biped/yullian/yullian_jumpattack", biped);
        YULLIAN_DASHAHATTCK = new BasicAttackAnimation(0.1F, 1.5F, 1.9F, 2.83F, null, biped.toolR,
                "biped/yullian/yullian_dashattack", biped);

        YULLIAN_SPECIALATTACK1 = new BasicAttackAnimation(0.1F, 1.43F, 1.8F, 4.2F, null, biped.toolR,
                "biped/yullian/yullian_specialattack1", biped);
        YULLIAN_SPECIALATTACK2 = new BasicAttackAnimation(0.1F, 1.23F, 1.56F, 4.167F, null, biped.toolR,
                "biped/yullian/yullian_specialattack2", biped);
        YULLIAN_SPECIALATTACK3 = new BasicAttackAnimation(0.1F, 1.567F, 2.0F, 3.8F, null, biped.toolR,
                "biped/yullian/yullian_specialattack3", biped);
        YULLIAN_WALK = new StaticAnimation(true, "biped/yullian/yullian_walk", biped);
        YULLIAN_RUN = new StaticAnimation(true, "biped/yullian/yullian_run", biped);
        YULLIAN_IDLE = new StaticAnimation(true, "biped/yullian/yullian_idle", biped);

        SAKURA_DANCE = new BasicAttackAnimation(0.2F, "biped/combat/sakura_dance", biped,
                new AttackAnimation.Phase(0F, 0.1F, 0.167F, 0.333F, 0.333F, InteractionHand.MAIN_HAND,
                        biped.toolR, null),
                new AttackAnimation.Phase(0.333F, 0.333F, 0.5F, 0.5F, 0.9F, InteractionHand.MAIN_HAND,
                        biped.toolR, null),
                new AttackAnimation.Phase(0.9F, 0.9F, 1.067F, 1.067F, 1.067F, InteractionHand.MAIN_HAND,
                        biped.toolR, null))
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 0.99F));

        Waterbirds_dance_wildly_a1 = new AttackAnimation(0.15F,  "biped/combat/waterbirds/waterbirds_dance_wildly_a1", biped,
                new AttackAnimation.Phase(0F, 1.533F, 1.6333F, 2.73F, 1.6333F, InteractionHand.MAIN_HAND,biped.toolR, null),
                new AttackAnimation.Phase(1.6333F, 1.9F, 1.966F, 2.73F, 1.966F, InteractionHand.MAIN_HAND,biped.toolR, null),
                new AttackAnimation.Phase(1.966F, 2.133F, 2.2F, 2.73F, 2.2F, InteractionHand.MAIN_HAND,biped.toolR, null),
                new AttackAnimation.Phase(2.2F, 2.4F, 2.466F, 2.73F, 4.5F, InteractionHand.MAIN_HAND,biped.toolR, null)
        )
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                // .newTimePair(0, 2.55F)
                //.addState(EntityState.CAN_SKILL_EXECUTION, false)
                .addState(EntityState.MOVEMENT_LOCKED, true)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.3F));

        Waterbirds_dance_wildly_a2 = new AttackAnimation(0.15F,   "biped/combat/waterbirds/waterbirds_dance_wildly_a2", biped,
                new AttackAnimation.Phase(0F, 0.8F, 0.9F, 1F, 2.833F, InteractionHand.MAIN_HAND,biped.toolR, null))
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                .addState(EntityState.MOVEMENT_LOCKED, true)
                // .newTimePair(0,0.97F)
                // .addState(EntityState.CAN_SKILL_EXECUTION, false)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.3F));
        Waterbirds_dance_wildly_a3 = new AttackAnimation(0.15F,    "biped/combat/waterbirds/waterbirds_dance_wildly_a3", biped,
                new AttackAnimation.Phase(0F, 0.933F, 0.9666F, 3.0F, 0.9666F, InteractionHand.MAIN_HAND,biped.toolR, null),
                new AttackAnimation.Phase(0.9666F, 1.166F, 1.266F, 3.0F, 1.266F, InteractionHand.MAIN_HAND,biped.toolR, null),
                new AttackAnimation.Phase(1.266F, 1.5333F, 1.6333F, 3.0F, 1.6333F, InteractionHand.MAIN_HAND,biped.toolR, null),
                new AttackAnimation.Phase(1.6333F, 1.9F, 1.966F, 3.0F, 1.966F, InteractionHand.MAIN_HAND,biped.toolR, null),
                new AttackAnimation.Phase(1.966F, 1.9F, 1.966F, 3.0F, 4.4333F, InteractionHand.MAIN_HAND,biped.toolR, null)
        )
                .addProperty(AnimationProperty.ActionAnimationProperty.CANCELABLE_MOVE, false)
                // .newTimePair(0f, 3.0F)
                // .addState(EntityState.CAN_SKILL_EXECUTION, false)
                .addState(EntityState.MOVEMENT_LOCKED, true)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.3F));


    }

}
