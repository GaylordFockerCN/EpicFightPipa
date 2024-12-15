package com.p1nero.hm.data.sound;

import com.p1nero.hm.EpicFightHM;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SoundGenerator extends SoundProvider {

    public SoundGenerator(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }

    @Override
    public void registerSounds() {
        this.generateNewSoundWithSubtitle(EpicFightHM.SONIC_BOOM, "", 5);
    }
}
