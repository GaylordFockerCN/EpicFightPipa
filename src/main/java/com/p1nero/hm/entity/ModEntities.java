package com.p1nero.hm.entity;

import com.p1nero.hm.EpicFightHM;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, EpicFightHM.MOD_ID);
    public static final RegistryObject<EntityType<RainCutterSwordEntity>> SWORD = register("sword",
            EntityType.Builder.of(RainCutterSwordEntity::new, MobCategory.CREATURE));
    public static final RegistryObject<EntityType<FakeBiped>> FAKE = register("fake",
            EntityType.Builder.of(FakeBiped::new, MobCategory.CREATURE));

    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return ENTITIES.register(registryname, () -> entityTypeBuilder.build(new ResourceLocation(EpicFightHM.MOD_ID, registryname).toString()));
    }

}
