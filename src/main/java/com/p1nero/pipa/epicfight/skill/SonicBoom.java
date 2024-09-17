package com.p1nero.pipa.epicfight.skill;

import com.p1nero.pipa.EpicFightPiPa;
import com.p1nero.pipa.util.EntityUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import yesman.epicfight.skill.weaponinnate.SimpleWeaponInnateSkill;
import yesman.epicfight.world.capabilities.entitypatch.player.PlayerPatch;
import yesman.epicfight.world.capabilities.entitypatch.player.ServerPlayerPatch;

public class SonicBoom extends SimpleWeaponInnateSkill {
    public SonicBoom(Builder builder) {
        super(builder);
    }

    @Override
    public void executeOnServer(ServerPlayerPatch executor, FriendlyByteBuf args) {
        ServerPlayer player = executor.getOriginal();
        ServerLevel serverLevel = player.serverLevel();
        ItemStack itemStack = player.getMainHandItem();
        if(itemStack.is(EpicFightPiPa.PIPA.get()) && itemStack.getDamageValue() < itemStack.getMaxDamage()-1){
            super.executeOnServer(executor, args);

            Vec3 attacker = player.getEyePosition();
            Vec3 target = player.getViewVector(1.0F).normalize();
            for(int i = 1; i < Mth.floor(target.length()) + 7; ++i) {
                Vec3 pos = attacker.add(target.scale(i));
                serverLevel.sendParticles(ParticleTypes.SONIC_BOOM, pos.x, pos.y, pos.z, 1, 0, 0, 0, 0);
            }
            itemStack.setDamageValue(itemStack.getDamageValue() + 1);
            for(LivingEntity entity : EntityUtil.getNearByEntities(serverLevel, player, 20)){
                if(EntityUtil.isInFront(entity, player, 20) && entity.distanceTo(player) < 10){
                    entity.hurt(player.damageSources().sonicBoom(player), 5);
                }
            }

        }
    }

    @Override
    public boolean canExecute(PlayerPatch<?> executer) {
        return true;
    }
}