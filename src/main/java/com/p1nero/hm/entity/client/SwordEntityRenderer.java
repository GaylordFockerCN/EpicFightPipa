package com.p1nero.hm.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.p1nero.hm.entity.RainCutterSwordEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemDisplayContext;
import org.jetbrains.annotations.NotNull;

public class SwordEntityRenderer extends EntityRenderer<RainCutterSwordEntity> {

    public SwordEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    /**
     * 调用物品渲染方法，渲染实体绑定的物品
     */
    @Override
    public void render(@NotNull RainCutterSwordEntity entity, float p_114486_, float p_114487_, @NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int light) {
        poseStack.pushPose();
        entity.setPose(poseStack);
        BakedModel model = Minecraft.getInstance().getItemRenderer().getItemModelShaper().getItemModel(entity.getItemStack());
        Minecraft.getInstance().getItemRenderer().render(entity.getItemStack(), ItemDisplayContext.FIXED, false, poseStack, multiBufferSource, light, 1, model);
        poseStack.popPose();
        super.render(entity, p_114486_, p_114487_, poseStack, multiBufferSource, light);
    }

    /**
     * 好像没什么用但是Renderer不能没有
     */
    @Override
    public @NotNull ResourceLocation getTextureLocation(@NotNull RainCutterSwordEntity swordEntity) {
        return TextureMapping.getItemTexture(swordEntity.getItemStack().getItem());
    }
}
