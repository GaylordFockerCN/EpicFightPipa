package com.p1nero.hm.entity.client;

import com.p1nero.hm.EpicFightHM;
import com.p1nero.hm.entity.BigFishFish;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
@OnlyIn(Dist.CLIENT)
public class BigFishFishRenderer extends MobRenderer<BigFishFish, BigFishFishModel> {
    public BigFishFishRenderer(EntityRendererProvider.Context p_174304_) {
        super(p_174304_, new BigFishFishModel(), 1);
    }

    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull BigFishFish bigFishFish) {
        return new ResourceLocation(EpicFightHM.MOD_ID, "textures/entity/big_fish_fish.png");
    }
}
