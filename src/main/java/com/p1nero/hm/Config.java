package com.p1nero.hm;
import net.minecraftforge.common.ForgeConfigSpec;

public class Config
{
    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec.DoubleValue SONIC_BOOM_DAMAGE = BUILDER
            .comment("Sonic Boom Damage")
            .defineInRange("sonic_boom_damage", 5.0, 0, Double.MAX_VALUE);

    static final ForgeConfigSpec SPEC = BUILDER.build();

}
