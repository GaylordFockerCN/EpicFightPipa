package com.p1nero.hm.item.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.client.renderer.patched.item.RenderItemBase;
import yesman.epicfight.model.armature.HumanoidArmature;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

/**
 * 在副手渲染
 */
@OnlyIn(Dist.CLIENT)
public class RenderZWZJ extends RenderItemBase {

    @Override
    public void renderItemInHand(ItemStack stack, LivingEntityPatch<?> entitypatch, InteractionHand hand, HumanoidArmature armature, OpenMatrix4f[] poses, MultiBufferSource buffer, PoseStack poseStack, int packedLight, float partialTicks) {
        OpenMatrix4f modelMatrix = this.getCorrectionMatrix(stack, entitypatch, hand);
        Joint holdingHand = armature.toolL;
        modelMatrix.mulFront(poses[holdingHand.getId()]);
        poseStack.pushPose();
        this.mulPoseStack(poseStack, modelMatrix);
        ItemDisplayContext transformType = ItemDisplayContext.THIRD_PERSON_LEFT_HAND;
        Minecraft mc = Minecraft.getInstance();
        mc.gameRenderer.itemInHandRenderer.renderItem(entitypatch.getOriginal(), stack, transformType, true, poseStack, buffer, packedLight);
        poseStack.popPose();
    }

}
