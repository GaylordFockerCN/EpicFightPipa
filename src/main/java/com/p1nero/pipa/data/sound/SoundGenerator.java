package com.p1nero.pipa.data.sound;

import com.p1nero.pipa.EpicFightPiPa;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SoundGenerator extends SoundProvider {

    public SoundGenerator(PackOutput output, ExistingFileHelper helper) {
        super(output, helper);
    }

    @Override
    public void registerSounds() {
        this.generateNewSoundWithSubtitle(EpicFightPiPa.SONIC_BOOM, "", 5);
    }
}
