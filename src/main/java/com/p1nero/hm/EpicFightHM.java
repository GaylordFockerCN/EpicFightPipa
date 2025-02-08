package com.p1nero.hm;

import com.mojang.logging.LogUtils;
import com.p1nero.hm.entity.FakeBiped;
import com.p1nero.hm.entity.ModEntities;
import com.p1nero.hm.entity.client.FakeBipedRenderer;
import com.p1nero.hm.entity.client.SwordEntityRenderer;
import com.p1nero.hm.epicfight.animation.HMAnimations;
import com.p1nero.hm.epicfight.skill.HMDataKeys;
import com.p1nero.hm.epicfight.skill.HMSkills;
import com.p1nero.hm.epicfight.weapon.HMWeaponCapabilityPresets;
import com.p1nero.hm.gameassets.Armatures;
import com.p1nero.hm.gameassets.Meshes;
import com.p1nero.hm.item.client.RenderZWZJ;
import net.mcreator.hm.init.HmModItems;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;
import yesman.epicfight.api.client.forgeevent.PatchedRenderersEvent;
import yesman.epicfight.world.item.EpicFightCreativeTabs;
import yesman.epicfight.world.item.WeaponItem;

@Mod(EpicFightHM.MOD_ID)
public class EpicFightHM {
    public static final String MOD_ID = "ef_hm";
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MOD_ID);
    public static final RegistryObject<Item> PIPA = ITEMS.register("pipa", ()->new WeaponItem(Tiers.WOOD, 3, -3F, new Item.Properties().rarity(Rarity.RARE).stacksTo(1).defaultDurability(256)) {
        @Override
        public boolean isCorrectToolForDrops(BlockState blockIn) {
            return super.isCorrectToolForDrops(blockIn);
        }
    });

    public static final RegistryObject<Item> HQ = ITEMS.register("hq", ()->new WeaponItem(Tiers.WOOD, 30, -3F, new Item.Properties().rarity(Rarity.RARE).stacksTo(1).defaultDurability(256)) {
        @Override
        public boolean isCorrectToolForDrops(BlockState blockIn) {
            return super.isCorrectToolForDrops(blockIn);
        }
    });

    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, EpicFightHM.MOD_ID);
    public static final RegistryObject<SoundEvent> SONIC_BOOM = SOUNDS.register("sonic_boom", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(EpicFightHM.MOD_ID, "sonic_boom")));
    public EpicFightHM() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ITEMS.register(modEventBus);
        SOUNDS.register(modEventBus);
        ModEntities.ENTITIES.register(modEventBus);
        HMDataKeys.DATA_KEYS.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(HMAnimations::registerAnimations);
        modEventBus.addListener(HMSkills::BuildSkills);
        modEventBus.addListener(HMWeaponCapabilityPresets::register);
        modEventBus.addListener(Armatures::build);
        modEventBus.addListener(Meshes::build);
        modEventBus.addListener(ModEntities::registerAttributes);
        modEventBus.addListener(ModEntities::EntityRenderRegistry);
        modEventBus.addListener(ModEntities::registerPatch);
        modEventBus.addListener(ModEntities::rendererRegister);
        modEventBus.addListener(this::addCreative);
        modEventBus.addListener(this::setAttributes);
//        MinecraftForge.EVENT_BUS.addListener(this::onLivingDeath);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void addCreative(BuildCreativeModeTabContentsEvent event){
        if (event.getTab() == EpicFightCreativeTabs.ITEMS.get()){
//            event.accept(PIPA.get());
//            event.accept(HQ.get());
        }
    }

    private void setAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.FAKE.get(), Zombie.createAttributes().build());
    }

    private void onLivingDeath(LivingDeathEvent event) {
        LivingEntity ordinal = event.getEntity();
        FakeBiped fakeBiped = ModEntities.FAKE.get().create(ordinal.level());
        if(fakeBiped != null){
            fakeBiped.setOrdinalId(ordinal.getId());
            fakeBiped.setPos(ordinal.position());
            ordinal.level().addFreshEntity(fakeBiped);
            fakeBiped.setHealth(0);
        }
    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents{
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event){
            EntityRenderers.register(ModEntities.SWORD.get(), SwordEntityRenderer::new);
            EntityRenderers.register(ModEntities.FAKE.get(), FakeBipedRenderer::new);
        }

        @SubscribeEvent
        public static void onRenderItem(final PatchedRenderersEvent.Add event) {
            event.addItemRenderer(HmModItems.ALFZ.get(), new RenderZWZJ());
        }

    }



}
