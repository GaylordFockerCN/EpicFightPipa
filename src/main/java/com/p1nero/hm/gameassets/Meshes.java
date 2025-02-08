package com.p1nero.hm.gameassets;

import com.p1nero.hm.EpicFightHM;
import com.p1nero.hm.entity.patch.BigFishFishMesh;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import yesman.epicfight.api.forgeevent.ModelBuildEvent;

public class Meshes {

    public static BigFishFishMesh bigFishFishMesh;
    @OnlyIn(Dist.CLIENT)
    public static void build(ModelBuildEvent.MeshBuild event) {
        bigFishFishMesh = event.getAnimated(EpicFightHM.MOD_ID, "entity/yuyu", BigFishFishMesh::new);
    }
}
