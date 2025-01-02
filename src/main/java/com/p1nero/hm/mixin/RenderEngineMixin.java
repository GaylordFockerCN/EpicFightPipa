package com.p1nero.hm.mixin;

import com.google.common.collect.BiMap;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.item.*;
import net.minecraftforge.fml.ModLoader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;
import yesman.epicfight.api.client.model.Meshes;
import yesman.epicfight.client.events.engine.RenderEngine;
import yesman.epicfight.client.renderer.AimHelperRenderer;
import yesman.epicfight.client.renderer.FirstPersonRenderer;
import yesman.epicfight.client.renderer.patched.entity.*;
import yesman.epicfight.client.renderer.patched.item.*;
import yesman.epicfight.world.capabilities.item.*;
import yesman.epicfight.world.entity.EpicFightEntities;
import yesman.epicfight.world.item.EpicFightItems;

import java.util.Map;
import java.util.function.Function;

@Mixin(value = RenderEngine.class, remap = false)
public abstract class RenderEngineMixin {

    @Inject(method = "hasRendererFor", at = @At("HEAD"), cancellable = true)
    private void injectHasRendererFor(Entity entity, CallbackInfoReturnable<Boolean> cir){
        if(entity instanceof IronGolem){
//            cir.setReturnValue(false);
        }
    }

//    @Shadow @Final private BiMap<EntityType<?>, Function<EntityType<?>, PatchedEntityRenderer>> entityRendererProvider;
//
//    @Shadow private FirstPersonRenderer firstPersonRenderer;
//
//    @Shadow private PHumanoidRenderer<?, ?, ?, ?, ?> basicHumanoidRenderer;
//
//    @Shadow private AimHelperRenderer aimHelper;
//
//    @Shadow @Final private Map<Item, RenderItemBase> itemRendererMapByInstance;
//
//    @Shadow @Final private Map<Class<?>, RenderItemBase> itemRendererMapByClass;
//
//    @Shadow public abstract void resetRenderers();
//
//    /**
//     * 移除铁傀儡渲染
//     */
//    @Inject(method = "bootstrap", at = @At("HEAD"))
//    private void inject(EntityRendererProvider.Context context, CallbackInfo ci){
//        this.entityRendererProvider.clear();
//        this.entityRendererProvider.put(EntityType.CREEPER, (entityType) -> new PCreeperRenderer(context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.ENDERMAN, (entityType) -> new PEndermanRenderer(context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.ZOMBIE, (entityType) -> new PHumanoidRenderer<>(() -> Meshes.BIPED_OLD_TEX, context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.ZOMBIE_VILLAGER, (entityType) -> new PZombieVillagerRenderer(context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.ZOMBIFIED_PIGLIN, (entityType) -> new PHumanoidRenderer<>(() -> Meshes.PIGLIN, context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.HUSK, (entityType) -> new PHumanoidRenderer<>(() -> Meshes.BIPED_OLD_TEX, context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.SKELETON, (entityType) -> new PHumanoidRenderer<>(() -> Meshes.SKELETON, context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.WITHER_SKELETON, (entityType) -> new PHumanoidRenderer<>(() -> Meshes.SKELETON, context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.STRAY, (entityType) -> new PStrayRenderer(context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.PLAYER, (entityType) -> new PPlayerRenderer(context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.SPIDER, (entityType) -> new PSpiderRenderer(context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.CAVE_SPIDER, (entityType) -> new PSpiderRenderer(context, entityType).initLayerLast(context, entityType));
////        this.entityRendererProvider.put(EntityType.IRON_GOLEM, (entityType) -> new PIronGolemRenderer(context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.VINDICATOR, (entityType) -> new PVindicatorRenderer(context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.EVOKER, (entityType) -> new PIllagerRenderer<> (context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.WITCH, (entityType) -> new PWitchRenderer(context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.DROWNED, (entityType) -> new PDrownedRenderer(context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.PILLAGER, (entityType) -> new PIllagerRenderer<> (context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.RAVAGER, (entityType) -> new PRavagerRenderer(context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.VEX, (entityType) -> new PVexRenderer(context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.PIGLIN, (entityType) -> new PHumanoidRenderer<>(() -> Meshes.PIGLIN, context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.PIGLIN_BRUTE, (entityType) -> new PHumanoidRenderer<>(() -> Meshes.PIGLIN, context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.HOGLIN, (entityType) -> new PHoglinRenderer<> (context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.ZOGLIN, (entityType) -> new PHoglinRenderer<> (context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EntityType.ENDER_DRAGON, (entityType) -> new PEnderDragonRenderer());
//        this.entityRendererProvider.put(EntityType.WITHER, (entityType) -> new PWitherRenderer(context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EpicFightEntities.WITHER_SKELETON_MINION.get(), (entityType) -> new PWitherSkeletonMinionRenderer(context, entityType).initLayerLast(context, entityType));
//        this.entityRendererProvider.put(EpicFightEntities.WITHER_GHOST_CLONE.get(), (entityType) -> new WitherGhostCloneRenderer());
//
//        this.firstPersonRenderer = new FirstPersonRenderer(context, EntityType.PLAYER);
//        this.basicHumanoidRenderer = new PHumanoidRenderer<>(() -> Meshes.BIPED, context, EntityType.PLAYER);
//        this.aimHelper = new AimHelperRenderer();
//
//        RenderItemBase baseRenderer = new RenderItemBase();
//        RenderBow bowRenderer = new RenderBow();
//        RenderCrossbow crossbowRenderer = new RenderCrossbow();
//        RenderTrident tridentRenderer = new RenderTrident();
//        RenderMap mapRenderer = new RenderMap();
//        RenderShield shieldRenderer = new RenderShield();
//
//        //Clear item renderers
//        this.itemRendererMapByInstance.clear();
//        this.itemRendererMapByClass.clear();
//
//        this.itemRendererMapByInstance.put(Items.AIR, baseRenderer);
//        this.itemRendererMapByInstance.put(Items.BOW, bowRenderer);
//        this.itemRendererMapByInstance.put(Items.SHIELD, shieldRenderer);
//        this.itemRendererMapByInstance.put(Items.CROSSBOW, crossbowRenderer);
//        this.itemRendererMapByInstance.put(Items.TRIDENT, tridentRenderer);
//        this.itemRendererMapByInstance.put(Items.FILLED_MAP, mapRenderer);
//        this.itemRendererMapByInstance.put(EpicFightItems.UCHIGATANA.get(), new RenderKatana());
//
//        //Render by item class
//        this.itemRendererMapByClass.put(BowItem.class, bowRenderer);
//        this.itemRendererMapByClass.put(CrossbowItem.class, crossbowRenderer);
//        this.itemRendererMapByClass.put(ShieldItem.class, baseRenderer);
//        this.itemRendererMapByClass.put(TridentItem.class, tridentRenderer);
//        this.itemRendererMapByClass.put(ShieldItem.class, shieldRenderer);
//        //Render by capability class
//        this.itemRendererMapByClass.put(BowCapability.class, bowRenderer);
//        this.itemRendererMapByClass.put(CrossbowCapability.class, crossbowRenderer);
//        this.itemRendererMapByClass.put(TridentCapability.class, tridentRenderer);
//        this.itemRendererMapByClass.put(MapCapability.class, mapRenderer);
//        this.itemRendererMapByClass.put(ShieldCapability.class, shieldRenderer);
//
//        ModLoader.get().postEvent(new PatchedRenderersEvent.Add(this.entityRendererProvider, this.itemRendererMapByInstance, context));
//
//        this.resetRenderers();
//    }
//
//    @Inject(method = "registerCustomEntityRenderer", at = @At("HEAD"), cancellable = true)
//    private void cancel(EntityType<?> entityType, String rendererName, CompoundTag compound, CallbackInfo ci){
//        if(entityType.equals(EntityType.IRON_GOLEM)){
//            ci.cancel();
//        }
//    }

}
