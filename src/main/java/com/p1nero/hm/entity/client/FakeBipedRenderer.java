package com.p1nero.hm.entity.client;

import com.p1nero.hm.entity.FakeBiped;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.NotNull;

public class FakeBipedRenderer extends HumanoidMobRenderer<FakeBiped, HumanoidModel<FakeBiped>> {
    public FakeBipedRenderer(EntityRendererProvider.Context context) {
        super(context, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER)), 0.5F);
        this.addLayer(new HumanoidArmorLayer<>(this, new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel<>(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
    }

    public @NotNull ResourceLocation getTextureLocation(@NotNull FakeBiped entity) {
        if(Minecraft.getInstance().level != null){
            Entity ordinal = Minecraft.getInstance().level.getEntity(entity.getOrdinalId());
            if(ordinal != null){
                EntityRenderer<?> renderer = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(ordinal);
                if(renderer instanceof HumanoidMobRenderer<?,?>){
                    return Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(ordinal).getTextureLocation(ordinal);
                }
            }
        }
        return new ResourceLocation("hm:textures/entities/fire.png");
    }
}