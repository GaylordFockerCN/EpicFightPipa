package com.p1nero.hm.gameassets;

import com.p1nero.hm.EpicFightHM;
import com.p1nero.hm.entity.ModEntities;
import com.p1nero.hm.entity.patch.BigFishFishArmature;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.forgeevent.ModelBuildEvent;

import static yesman.epicfight.gameasset.Armatures.registerEntityTypeArmature;

public class Armatures {
    public static BigFishFishArmature bigFishFishArmature;
    @OnlyIn(Dist.CLIENT)
    public static void build(ModelBuildEvent.ArmatureBuild event) {
        bigFishFishArmature = event.get(EpicFightHM.MOD_ID, "entity/yuyu", BigFishFishArmature::new);
        registerEntityTypeArmature(ModEntities.BIG_FISH_FISH.get(), bigFishFishArmature);
    }
}
