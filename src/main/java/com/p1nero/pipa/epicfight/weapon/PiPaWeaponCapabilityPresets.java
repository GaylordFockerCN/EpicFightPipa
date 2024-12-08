package com.p1nero.pipa.epicfight.weapon;

import com.p1nero.pipa.EpicFightPiPa;
import com.p1nero.pipa.epicfight.animation.PiPaAnimations;
import com.p1nero.pipa.epicfight.skill.PiPaSkills;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.LivingMotions;
import yesman.epicfight.api.forgeevent.WeaponCapabilityPresetRegistryEvent;
import yesman.epicfight.gameasset.Animations;
import yesman.epicfight.gameasset.ColliderPreset;
import yesman.epicfight.gameasset.EpicFightSounds;
import yesman.epicfight.particle.EpicFightParticles;
import yesman.epicfight.world.capabilities.item.CapabilityItem;
import yesman.epicfight.world.capabilities.item.WeaponCapability;

import java.util.function.Function;

@Mod.EventBusSubscriber(modid = EpicFightPiPa.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PiPaWeaponCapabilityPresets {

    public static final Function<Item, CapabilityItem.Builder> PI_PA = (item) ->
            (CapabilityItem.Builder) WeaponCapability.builder().category(PiPaWeaponCategories.PI_PA)
            .styleProvider((playerPatch) -> CapabilityItem.Styles.ONE_HAND).collider(PiPaColliders.PI_PA)
            .hitSound(EpicFightSounds.BLUNT_HIT.get())
            .hitParticle(EpicFightParticles.HIT_BLUNT.get())
            .canBePlacedOffhand(false)
            .newStyleCombo(
                    CapabilityItem.Styles.ONE_HAND,
                    PiPaAnimations.AUTO1,
                    PiPaAnimations.AUTO2,
                    PiPaAnimations.AUTO3,
                    Animations.GREATSWORD_DASH,
                    Animations.LONGSWORD_AIR_SLASH)
            .innateSkill(CapabilityItem.Styles.ONE_HAND, (itemstack) -> PiPaSkills.SONIC_BOOM)
            .comboCancel((style) -> false);

    public static final Function<Item, CapabilityItem.Builder> TAI_DAO = (item) ->
            (CapabilityItem.Builder) WeaponCapability.builder().category(CapabilityItem.WeaponCategories.UCHIGATANA)
                    .styleProvider((playerPatch) -> CapabilityItem.Styles.ONE_HAND).collider(ColliderPreset.UCHIGATANA)
                    .hitSound(EpicFightSounds.BLADE_HIT.get())
                    .hitParticle(EpicFightParticles.HIT_BLADE.get())
                    .canBePlacedOffhand(false)
                    .newStyleCombo(
                            CapabilityItem.Styles.ONE_HAND,
                            PiPaAnimations.YULLIAN_COMBOA1,
                            PiPaAnimations.YULLIAN_COMBOA2,
                            PiPaAnimations.YULLIAN_COMBOC1,
                            PiPaAnimations.YULLIAN_COMBOC2,
                            PiPaAnimations.YULLIAN_DASHAHATTCK,
                            PiPaAnimations.SAKURA_DANCE)
                    .innateSkill(CapabilityItem.Styles.ONE_HAND, (itemstack) -> PiPaSkills.TAIDAO_INNATE)
                    .comboCancel((style) -> false)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.IDLE, PiPaAnimations.YULLIAN_IDLE)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.WALK, PiPaAnimations.YULLIAN_WALK)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.RUN, PiPaAnimations.YULLIAN_RUN)
                    .livingMotionModifier(CapabilityItem.Styles.ONE_HAND, LivingMotions.CHASE, PiPaAnimations.YULLIAN_RUN);

    @SubscribeEvent
    public static void register(WeaponCapabilityPresetRegistryEvent event) {
        event.getTypeEntry().put(new ResourceLocation(EpicFightPiPa.MOD_ID, "pi_pa"), PI_PA);
        event.getTypeEntry().put(new ResourceLocation(EpicFightPiPa.MOD_ID, "tai_dao"), TAI_DAO);
    }

}
