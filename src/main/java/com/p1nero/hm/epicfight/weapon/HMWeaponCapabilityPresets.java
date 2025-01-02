package com.p1nero.hm.epicfight.weapon;

import com.p1nero.hm.EpicFightHM;
import com.p1nero.hm.epicfight.animation.HMAnimations;
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

@Mod.EventBusSubscriber(modid = EpicFightHM.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class HMWeaponCapabilityPresets {

    public static final Function<Item, CapabilityItem.Builder> YING_HUA = (item) ->
            (CapabilityItem.Builder) WeaponCapability.builder().category(CapabilityItem.WeaponCategories.SWORD)
                    .styleProvider((playerpatch) -> playerpatch.getHoldingItemCapability(InteractionHand.OFF_HAND)
                            .getWeaponCategory() == CapabilityItem.WeaponCategories.SWORD ? CapabilityItem.Styles.TWO_HAND : CapabilityItem.Styles.ONE_HAND)
                    .collider(new MultiOBBCollider(3, 0.4, 0.4, 0.0, 0.8, 0.0, -0.75))
                    .swingSound(EpicFightSounds.WHOOSH_BIG.get())
                    .hitSound(EpicFightSounds.BLADE_RUSH_FINISHER.get())
                    .hitParticle(EpicFightParticles.HIT_BLUNT.get())
                    .canBePlacedOffhand(false)
                    .newStyleCombo(
                            CapabilityItem.Styles.TWO_HAND,
                            WOMAnimations.ANTITHEUS_AUTO_1,
                            WOMAnimations.ANTITHEUS_AUTO_2,
                            WOMAnimations.ANTITHEUS_AUTO_3,
                            WOMAnimations.ANTITHEUS_AUTO_4,
                            WOMAnimations.TORMENT_CHARGED_ATTACK_1,
                            WOMAnimations.ANTITHEUS_AGRESSION,
                            WOMAnimations.ANTITHEUS_GUILLOTINE)
                    .innateSkill(CapabilityItem.Styles.TWO_HAND, (itemstack) -> EpicFightSkills.SWEEPING_EDGE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.BLOCK, WOMAnimations.AGONY_GUARD)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.IDLE, WOMAnimations.SOLAR_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.TWO_HAND, LivingMotions.WALK, WOMAnimations.HERRSCHER_WALK)
                    .comboCancel((style) -> false);

    public static final Function<Item, CapabilityItem.Builder> WSFH = (item) ->
            (CapabilityItem.Builder) WeaponCapability.builder().category(HMWeaponCategories.WSFH)
                    .styleProvider((playerPatch) -> CapabilityItem.Styles.ONE_HAND).collider(HMColliders.WSFH)
                    .hitSound(EpicFightSounds.BLUNT_HIT.get())
                    .hitParticle(EpicFightParticles.HIT_BLUNT.get())
                    .canBePlacedOffhand(false)
                    .newStyleCombo(
                            CapabilityItem.Styles.ONE_HAND,
                            HMAnimations.WSFH_AUTO1,
                            HMAnimations.WSFH_AUTO2,
                            HMAnimations.WSFH_AUTO3,
//                            Animations.SWORD_AUTO1,
                            Animations.GREATSWORD_DASH,
                            Animations.LONGSWORD_AIR_SLASH)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, (itemstack) -> EpicFightSkills.SWEEPING_EDGE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, HMAnimations.IDLE1)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, HMAnimations.WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, HMAnimations.RUN)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.CHASE, HMAnimations.RUN)
                    .comboCancel((style) -> false);



    @SubscribeEvent
    public static void register(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(new ResourceLocation(EpicFightHM.MOD_ID, "wsfh"), WSFH);
        event.getTypeEntry().put(new ResourceLocation(EpicFightHM.MOD_ID, "ying_hua"), YING_HUA);
    }

}
