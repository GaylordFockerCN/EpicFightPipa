package com.p1nero.hm.util;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec2;
import net.minecraft.world.phys.Vec3;

import java.util.List;

/**
 * @author LZY
 * 方便对实体进行一些操作，目前主要是射线判断
 */
public class EntityUtil {
    /**
     * 获取视线和目标位置连线的夹角
     */
    public static double getDegree(Entity target, Entity self){
        return getDegree(target.position(), self);
    }

    /**
     * 获取视线和目标位置连线的夹角
     */
    public static double getDegree(Vec3 target, Entity self){
        Vec3 targetToBoss = target.subtract(self.position());
        Vec2 targetToBossV2 = new Vec2(((float) targetToBoss.x), ((float) targetToBoss.z));
        Vec3 view = self.getViewVector(1.0F);
        Vec2 viewV2 = new Vec2(((float) view.x), ((float) view.z));
        double angleRadians = Math.acos(targetToBossV2.dot(viewV2)/(targetToBossV2.length() * viewV2.length()));
        return Math.toDegrees(angleRadians);
    }

    /**
     * 返回一个范围
     * @param pos 中心位置
     * @param offset 半径
     * @return 以pos为中心offset的两倍为边长的一个正方体
     */
    public static AABB getPlayerAABB(BlockPos pos, int offset){
        return new AABB(pos.offset(offset,offset,offset),pos.offset(-offset,-offset,-offset));
    }

    public static boolean isInFront(Entity target, Entity self, double degree){
        return Math.abs(getDegree(target, self)) <= degree;
    }

    /**
     * 获取附近的玩家
     */
    public static List<Player> getNearByPlayers(Level level, LivingEntity self, int offset){
        return level.getNearbyPlayers(TargetingConditions.DEFAULT, self, getPlayerAABB(self.getOnPos(), offset));
    }

    public static List<LivingEntity> getNearByEntities(Level level, LivingEntity self, int offset){
        return level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, self, getPlayerAABB(self.getOnPos(), offset));
    }

    public static <T extends LivingEntity> List<T> getNearByEntities(Class<T> aClass, Level level, LivingEntity self, int offset){
        return level.getNearbyEntities(aClass, TargetingConditions.DEFAULT, self, getPlayerAABB(self.getOnPos(), offset));
    }

}
