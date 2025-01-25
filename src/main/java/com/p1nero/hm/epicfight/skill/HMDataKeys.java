package com.p1nero.hm.epicfight.skill;

import com.p1nero.hm.EpicFightHM;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.main.EpicFightMod;
import yesman.epicfight.skill.SkillDataKey;

public class HMDataKeys {
    public static final DeferredRegister<SkillDataKey<?>> DATA_KEYS = DeferredRegister.create(new ResourceLocation(EpicFightMod.MODID, "skill_data_keys"), EpicFightHM.MOD_ID);
    public static final RegistryObject<SkillDataKey<Boolean>> IS_WALKING = DATA_KEYS.register("is_walking", () ->
            SkillDataKey.createBooleanKey(false, false, ElysiaOriginSkill.class));
}
