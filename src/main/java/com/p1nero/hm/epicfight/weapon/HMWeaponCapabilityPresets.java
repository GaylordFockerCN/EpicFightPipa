package com.p1nero.hm.epicfight.weapon;

import com.p1nero.hm.EpicFightHM;
import com.p1nero.hm.epicfight.animation.HMAnimations;
import com.p1nero.hm.epicfight.skill.HMSkills;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import reascer.wom.gameasset.WOMAnimations;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.collider.MultiOBBCollider;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.EpicFightSkills;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.function.Function;

public class HMWeaponCapabilityPresets {
    public static final Function<Item, CapabilityItem.Builder> WSFH = (item) ->
            (CapabilityItem.Builder) WeaponCapability.builder().category(HMWeaponCategories.WSFH)
                    .styleProvider((playerPatch) -> CapabilityItem.Styles.ONE_HAND).collider(HMColliders.WSFH)
                    .hitSound(EpicFightSounds.BLUNT_HIT.get())
                    .hitParticle(EpicFightParticles.HIT_BLUNT.get())
                    .canBePlacedOffhand(false)
                    .newStyleCombo(
                            CapabilityItem.Styles.ONE_HAND,
                            HMAnimations.ELYSIA_ORIGIN_AUTO1,
                            HMAnimations.ELYSIA_ORIGIN_AUTO2,
                            HMAnimations.ELYSIA_ORIGIN_AUTO3,
                            HMAnimations.ELYSIA_ORIGIN_AUTO4,
                            HMAnimations.ELYSIA_ORIGIN_AUTO1,
                            HMAnimations.ELYSIA_ORIGIN_AUTO1)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, (itemstack) -> HMSkills.ELYSIA_ORIGIN)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, HMAnimations.ELYSIA_ORIGIN_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, HMAnimations.ELYSIA_ORIGIN_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, HMAnimations.ELYSIA_ORIGIN_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.SWIM, HMAnimations.ELYSIA_ORIGIN_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.FALL, HMAnimations.ELYSIA_ORIGIN_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.FLOAT, HMAnimations.ELYSIA_ORIGIN_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.CONFRONT, HMAnimations.ELYSIA_ORIGIN_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.CHASE, HMAnimations.ELYSIA_ORIGIN_WALK)
                    .comboCancel((style) -> false);

    public static void register(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(new ResourceLocation(EpicFightHM.MOD_ID, "wsfh"), WSFH);
    }

}
