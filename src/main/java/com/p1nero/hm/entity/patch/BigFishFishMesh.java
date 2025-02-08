package com.p1nero.hm.entity.patch;

import org.jetbrains.annotations.Nullable;
import yesman.epicfight.api.client.model.AnimatedMesh;
import yesman.epicfight.api.client.model.AnimatedVertexBuilder;
import yesman.epicfight.api.client.model.MeshPartDefinition;
import yesman.epicfight.api.client.model.MeshProvider;

import java.util.List;
import java.util.Map;

public class BigFishFishMesh extends AnimatedMesh implements MeshProvider<BigFishFishMesh> {
    public BigFishFishMesh(@Nullable Map<String, float[]> arrayMap, @Nullable Map<MeshPartDefinition, List<AnimatedVertexBuilder>> partBuilders, @Nullable AnimatedMesh parent, RenderProperties properties) {
        super(arrayMap, partBuilders, parent, properties);
    }

    @Override
    public BigFishFishMesh get() {
        return this;
    }
}
