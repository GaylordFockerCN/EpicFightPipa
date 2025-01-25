package com.p1nero.hm.epicfight.skill;

import com.p1nero.hm.epicfight.animation.HMAnimations;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.Input;
import net.minecraft.client.player.LocalPlayer;
import yesman.epicfight.client.ClientEngine;
import yesman.epicfight.client.events.engine.ControllEngine;
import yesman.epicfight.skill.Skill;
import yesman.epicfight.skill.SkillContainer;
import yesman.epicfight.skill.SkillDataManager;
import yesman.epicfight.skill.weaponinnate.WeaponInnateSkill;
import yesman.epicfight.world.entity.eventlistener.PlayerEventListener;

import java.util.UUID;

public class ElysiaOriginSkill extends WeaponInnateSkill {
    private static final UUID EVENT_UUID = UUID.fromString("d1d191cc-f20f-11ed-a05b-0242ac114514");

    public ElysiaOriginSkill(Builder<? extends Skill> builder) {
        super(builder);
    }

    @Override
    public void onInitiate(SkillContainer container) {
        super.onInitiate(container);
        SkillDataManager manager = container.getDataManager();
        container.getExecuter().getEventListener().addEventListener(PlayerEventListener.EventType.MOVEMENT_INPUT_EVENT, EVENT_UUID, (event -> {
            Input input = event.getMovementInput();
            boolean isUp = input.up;
            if(input.forwardImpulse < 0){
                input.forwardImpulse = 0.0F;//防止后退
            }
            input.leftImpulse = 0.0F;
            input.down = false;
            input.up = false;
            input.left = false;
            input.right = false;
            input.jumping = false;
            input.shiftKeyDown = false;
            LocalPlayer clientPlayer = event.getPlayerPatch().getOriginal();
            clientPlayer.setSprinting(true);
//            clientPlayer.sprintTriggerTime = -1;
            Minecraft mc = Minecraft.getInstance();
            ControllEngine.setKeyBind(mc.options.keySprint, true);
            if(isUp && !(manager.hasData(HMDataKeys.IS_WALKING.get()) && manager.getDataValue(HMDataKeys.IS_WALKING.get()))){
                container.getExecuter().playAnimationSynchronized(HMAnimations.ELYSIA_ORIGIN_WALK_BEGIN, 0.15F);
                manager.setDataSync(HMDataKeys.IS_WALKING.get(), true, event.getPlayerPatch().getOriginal());
            }
            if(!isUp && manager.hasData(HMDataKeys.IS_WALKING.get()) && manager.getDataValue(HMDataKeys.IS_WALKING.get())){
                container.getExecuter().playAnimationSynchronized(HMAnimations.ELYSIA_ORIGIN_WALK_END, 0.15F);
                manager.setDataSync(HMDataKeys.IS_WALKING.get(), false, event.getPlayerPatch().getOriginal());
            }
        }));
    }

    @Override
    public void onRemoved(SkillContainer container) {
        super.onRemoved(container);
        container.getExecuter().getEventListener().removeListener(PlayerEventListener.EventType.MOVEMENT_INPUT_EVENT, EVENT_UUID);
    }

    @Override
    public void updateContainer(SkillContainer container) {
        super.updateContainer(container);
    }
}
