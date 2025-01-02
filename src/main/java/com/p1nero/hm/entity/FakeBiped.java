package com.p1nero.hm.entity;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class FakeBiped extends PathfinderMob {
    private static final EntityDataAccessor<Integer> ORDINAL_ID = SynchedEntityData.defineId(FakeBiped.class, EntityDataSerializers.INT);
    public FakeBiped(EntityType<? extends PathfinderMob> p_21683_, Level p_21684_) {
        super(p_21683_, p_21684_);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        getEntityData().define(ORDINAL_ID, 0);
    }

    public void setOrdinalId(int id){
        getEntityData().set(ORDINAL_ID, id);
    }

    public int getOrdinalId(){
        return getEntityData().get(ORDINAL_ID);
    }

    @Override
    public boolean hurt(@NotNull DamageSource p_21016_, float p_21017_) {
        return false;
    }
}
