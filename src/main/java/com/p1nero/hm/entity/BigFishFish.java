package com.p1nero.hm.entity;

import net.minecraft.commands.arguments.EntityAnchorArgument;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import yesman.epicfight.world.entity.EpicFightEntities;
import yesman.epicfight.world.entity.ai.attribute.EpicFightAttributes;

public class BigFishFish extends FlyingMob {
    public BigFishFish(EntityType<? extends FlyingMob> entityType, Level level) {
        super(entityType, level);
        this.setNoGravity(true);
        this.noPhysics = true;
    }

    public BigFishFish(ServerLevel level, Vec3 position, LivingEntity target) {
        this(EpicFightEntities.WITHER_GHOST_CLONE.get(), level);
        this.setPos(position);
        this.lookAt(EntityAnchorArgument.Anchor.FEET, target.position());
        this.setTarget(target);
    }

    public boolean hurt(DamageSource damagesource, float damage) {
        return damagesource.is(DamageTypeTags.BYPASSES_INVULNERABILITY) && super.hurt(damagesource, damage);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(EpicFightAttributes.WEIGHT.get()).add(EpicFightAttributes.ARMOR_NEGATION.get()).add(EpicFightAttributes.IMPACT.get()).add(EpicFightAttributes.MAX_STRIKES.get()).add(Attributes.ATTACK_DAMAGE);
    }

    public void customServerAiStep() {
//        if (this.tickCount >= 40) {
//            this.remove(RemovalReason.DISCARDED);
//        }

    }

    public @NotNull MobType getMobType() {
        return MobType.UNDEAD;
    }
}
