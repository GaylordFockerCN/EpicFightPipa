package com.p1nero.hm.epicfight.animation;

import net.minecraft.client.Minecraft;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import yesman.epicfight.api.animation.*;
import yesman.epicfight.api.animation.types.ActionAnimation;
import yesman.epicfight.api.animation.types.DynamicAnimation;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.client.animation.ClientAnimator;
import yesman.epicfight.api.client.animation.Layer;
import yesman.epicfight.api.model.Armature;
import yesman.epicfight.api.utils.math.MathUtils;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.QuaternionUtils;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public class WSFHActionAnimation extends ActionAnimation {
    public WSFHActionAnimation(float convertTime, String path, Armature armature) {
        super(convertTime, path, armature);
    }

    public WSFHActionAnimation(float convertTime, String path, Armature armature, boolean noRegister) {
        super(convertTime, path, armature, noRegister);
    }

    public WSFHActionAnimation(float convertTime, float postDelay, String path, Armature armature) {
        super(convertTime, postDelay, path, armature);
    }

    public WSFHActionAnimation(float convertTime, float postDelay, String path, Armature armature, boolean noRegister) {
        super(convertTime, postDelay, path, armature, noRegister);
    }

    @Override
    public void tick(LivingEntityPatch<?> entitypatch) {
        super.tick(entitypatch);
        ClientAnimator animator = entitypatch.getClientAnimator();
        Layer layer = animator.getCompositeLayer(this.getPriority());
        AnimationPlayer player = layer.animationPlayer;
        if (player.getElapsedTime() >= this.getTotalTime() - 0.06F) {
            layer.pause();
        }

    }

//    @Override
//    public Pose getPoseByTime(LivingEntityPatch<?> entitypatch, float time, float partialTicks) {
//        if (!entitypatch.isFirstPerson()) {
//            LivingMotion livingMotion = entitypatch.getCurrentLivingMotion();
//            if (livingMotion != LivingMotions.SWIM && livingMotion != LivingMotions.FLY && livingMotion != LivingMotions.CREATIVE_FLY) {
//                float pitch = ((LivingEntity)entitypatch.getOriginal()).getViewXRot(Minecraft.getInstance().getFrameTime());
//                StaticAnimation interpolateAnimation = pitch > 0.0F ? this.lookDown : this.lookUp;
//                Pose pose1 = super.getPoseByTime(entitypatch, time, partialTicks);
//                Pose pose2 = interpolateAnimation.getPoseByTime(entitypatch, time, partialTicks);
//                this.modifyPose(this, pose2, entitypatch, time, partialTicks);
//                Pose interpolatedPose = Pose.interpolatePose(pose1, pose2, Math.abs(pitch) / 90.0F);
//                return interpolatedPose;
//            }
//        } else {
//            return super.getPoseByTime(entitypatch, time, partialTicks);
//        }
//    }

    @Override
    public void modifyPose(DynamicAnimation animation, Pose pose, LivingEntityPatch<?> entitypatch, float time, float partialTicks) {
        super.modifyPose(animation, pose, entitypatch, time, partialTicks);
        if (!entitypatch.isFirstPerson() && !animation.isLinkAnimation()) {
            JointTransform chest = pose.getOrDefaultTransform("Chest");
            JointTransform head = pose.getOrDefaultTransform("Head");
            float f = 90.0F;
            float ratio = (f - Math.abs(((LivingEntity)entitypatch.getOriginal()).getXRot())) / f;
            float yRotHead = ((LivingEntity)entitypatch.getOriginal()).yHeadRotO;
            float yRot = ((LivingEntity)entitypatch.getOriginal()).getVehicle() != null ? yRotHead : entitypatch.getYRot();
            MathUtils.mulQuaternion(QuaternionUtils.YP.rotationDegrees(Mth.wrapDegrees(yRot - yRotHead) * ratio), head.rotation(), head.rotation());
            chest.frontResult(JointTransform.getRotation(QuaternionUtils.YP.rotationDegrees(Mth.wrapDegrees(yRotHead - yRot) * ratio)), OpenMatrix4f::mulAsOriginInverse);
        }

    }

    public boolean isClientAnimation() {
        return true;
    }

}
