package com.p1nero.hm.entity;

import com.p1nero.hm.EpicFightHM;
import com.p1nero.hm.entity.client.BigFishFishRenderer;
import com.p1nero.hm.entity.patch.BigFishFishPatch;
import com.p1nero.hm.entity.client.BigFishFishPatchedRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;
import yesman.epicfight.api.forgeevent.EntityPatchRegistryEvent;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EpicFightHM.MOD_ID);
    public static final RegistryObject<EntityType<RainCutterSwordEntity>> SWORD = register("sword",
            EntityType.Builder.of(RainCutterSwordEntity::new, MobCategory.CREATURE));
    public static final RegistryObject<EntityType<FakeBiped>> FAKE = register("fake",
            EntityType.Builder.of(FakeBiped::new, MobCategory.CREATURE));

    public static final RegistryObject<EntityType<BigFishFish>> BIG_FISH_FISH = ENTITIES.register("big_fish_fish",
            () -> EntityType.Builder.<BigFishFish>of(BigFishFish::new, MobCategory.MONSTER)
                    .fireImmune().sized(0.9F, 3.5F).clientTrackingRange(10).build("big_fish_fish"));
    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return ENTITIES.register(registryname, () -> entityTypeBuilder.build(new ResourceLocation(EpicFightHM.MOD_ID, registryname).toString()));
    }

    @OnlyIn(Dist.CLIENT)
    public static void EntityRenderRegistry(final PatchedRenderersEvent.Add event) {
        event.addPatchedEntityRenderer(BIG_FISH_FISH.get(), (entityType -> new BigFishFishPatchedRenderer(event.getContext(), entityType)));
    }
    @OnlyIn(Dist.CLIENT)
    public static void rendererRegister(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(BIG_FISH_FISH.get(), BigFishFishRenderer::new);
    }

    public static void registerPatch(EntityPatchRegistryEvent event){
        event.getTypeEntry().put(BIG_FISH_FISH.get(), (entityIn) ->  BigFishFishPatch::new);
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(BIG_FISH_FISH.get(), BigFishFish.createAttributes().build());
    }

}
