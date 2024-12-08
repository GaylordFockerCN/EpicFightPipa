package com.p1nero.pipa.epicfight.skill;

import com.p1nero.pipa.EpicFightPiPa;
import com.p1nero.pipa.epicfight.animation.PiPaAnimations;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.animation.types.AttackAnimation;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;

@Mod.EventBusSubscriber(modid = EpicFightPiPa.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class PiPaSkills {
    public static Skill SONIC_BOOM;
    public static Skill TAIDAO_INNATE;

    @SubscribeEvent
    public static void BuildSkills(SkillBuildEvent event) {
        SkillBuildEvent.ModRegistryWorker registryWorker = event.createRegistryWorker(EpicFightPiPa.MOD_ID);
//        SONIC_BOOM = registryWorker.build("sonic_boom", SimpleWeaponInnateSkill::new, SimpleWeaponInnateSkill.createSimpleWeaponInnateBuilder().setAnimations(() -> (AttackAnimation) PiPaAnimations.SONIC_BOOM));
        TAIDAO_INNATE = registryWorker.build("taidao_innate", TaiDaoSkill::new, WeaponInnateSkill.createWeaponInnateBuilder());
    }
}
