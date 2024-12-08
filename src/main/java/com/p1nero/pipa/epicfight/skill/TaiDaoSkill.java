package com.p1nero.pipa.epicfight.skill;

import com.p1nero.pipa.epicfight.animation.PiPaAnimations;
import net.minecraft.network.FriendlyByteBuf;
import yesman.epicfight.skill.SkillSlots;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

public class TaiDaoSkill extends WeaponInnateSkill {
    public TaiDaoSkill(Builder<? extends WeaponInnateSkill> builder) {
        super(builder);
    }

    public void executeOnServer(ServerPlayerPatch executer, FriendlyByteBuf args) {
        int stack = executer.getSkill(SkillSlots.WEAPON_INNATE).getStack();
        switch (stack){
            case 3:
                executer.playAnimationSynchronized(PiPaAnimations.Waterbirds_dance_wildly_a1, 0.0F);
                break;
            case 2:
                executer.playAnimationSynchronized(PiPaAnimations.Waterbirds_dance_wildly_a2, 0.0F);
                break;
            case 1:
                executer.playAnimationSynchronized(PiPaAnimations.Waterbirds_dance_wildly_a3, 0.0F);
                break;
        }
        super.executeOnServer(executer, args);
    }
}
