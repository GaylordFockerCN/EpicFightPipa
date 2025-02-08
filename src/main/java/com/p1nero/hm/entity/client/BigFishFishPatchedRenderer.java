package com.p1nero.hm.entity.client;

import com.p1nero.hm.entity.BigFishFish;
import com.p1nero.hm.entity.client.BigFishFishModel;
import com.p1nero.hm.entity.client.BigFishFishRenderer;
import com.p1nero.hm.entity.patch.BigFishFishMesh;
import com.p1nero.hm.entity.patch.BigFishFishPatch;
import com.p1nero.hm.gameassets.Meshes;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.client.model.MeshProvider;
import yesman.epicfight.client.renderer.patched.entity.PatchedLivingEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class BigFishFishPatchedRenderer extends PatchedLivingEntityRenderer<BigFishFish, BigFishFishPatch, BigFishFishModel, BigFishFishRenderer, BigFishFishMesh> {

    public BigFishFishPatchedRenderer(EntityRendererProvider.Context context, EntityType<?> entityType) {
        super(context, entityType);
    }

    @Override
    public MeshProvider<BigFishFishMesh> getDefaultMesh() {
        return Meshes.bigFishFishMesh;
    }
}
