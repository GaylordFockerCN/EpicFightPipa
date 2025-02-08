package com.p1nero.hm.epicfight.skill;

import com.p1nero.hm.EpicFightHM;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import yesman.epicfight.api.forgeevent.SkillBuildEvent;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;

public class HMSkills {
    public static Skill ELYSIA_ORIGIN;

    public static void BuildSkills(SkillBuildEvent event) {
        SkillBuildEvent.ModRegistryWorker registryWorker = event.createRegistryWorker(EpicFightHM.MOD_ID);
        ELYSIA_ORIGIN = registryWorker.build("elysia_origin", ElysiaOriginSkill::new, WeaponInnateSkill.createWeaponInnateBuilder());
    }
}
