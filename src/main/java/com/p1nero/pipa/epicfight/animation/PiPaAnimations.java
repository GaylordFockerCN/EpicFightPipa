package com.p1nero.pipa.epicfight.animation;

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
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.property.AnimationEvent;
import yesman.epicfight.api.animation.property.AnimationProperty;
import yesman.epicfight.api.animation.types.BasicAttackAnimation;
import yesman.epicfight.api.animation.types.EntityState;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.forgeevent.AnimationRegistryEvent;
import yesman.epicfight.api.utils.LevelUtil;
import yesman.epicfight.api.utils.math.ValueModifier;
import yesman.epicfight.gameasset.Armatures;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

@Mod.EventBusSubscriber(modid = EpicFightPiPa.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PiPaAnimations {

    public static StaticAnimation AUTO1;
    public static StaticAnimation AUTO2;
    public static StaticAnimation AUTO3;
    public static StaticAnimation SONIC_BOOM;

    @SubscribeEvent
    public static void registerAnimations(AnimationRegistryEvent event) {
        event.getRegistryMap().put(EpicFightPiPa.MOD_ID, PiPaAnimations::build);
    }

    private static void build() {
        HumanoidArmature biped = Armatures.BIPED;

        AUTO1 = new BasicAttackAnimation(0.15F, 0.15F, 0.5667F, 0.5667F, null, biped.toolR,  "biped/auto_1", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(0.8F))
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 0.98F));
        AUTO2 = new BasicAttackAnimation(0.01F, 0.1F, 0.4333F, 0.4333F, null, biped.toolR,  "biped/auto_2", biped)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 0.97F));
        AUTO3 = new BasicAttackAnimation(0.15F, 1.2F, 1.5F, 1.9833F, PiPaColliders.PI_PA_PLUS, biped.toolR,  "biped/auto_3", biped)
                .addProperty(AnimationProperty.AttackPhaseProperty.DAMAGE_MODIFIER, ValueModifier.multiplier(1.5F))
                .addProperty(AnimationProperty.AttackPhaseProperty.HIT_SOUND, EpicFightSounds.BLUNT_HIT_HARD.get())
                .addProperty(AnimationProperty.ActionAnimationProperty.MOVE_VERTICAL, true);
        SONIC_BOOM = new BasicAttackAnimation(0.15F, 0, 0, 2.533F, null, biped.toolR,  "biped/sonic_boom", biped)
                .addProperty(AnimationProperty.StaticAnimationProperty.PLAY_SPEED_MODIFIER, ((dynamicAnimation, livingEntityPatch, v, v1, v2) -> 1.0F))
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
    }

}
