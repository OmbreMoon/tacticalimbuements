package net.m3tte.tactical_imbuements.definitions;

import net.m3tte.tactical_imbuements.init.TacticalImbuementsModParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;

public class ImbuementDefinitions {

    public static final String FLAMEID = "flame";
    public static final String VENOMID = "venom";
    public static final String FREEZEID = "freeze";
    public static final String SPARKID = "spark";
    public static final ImbuementDef FLAME = new ImbuementDef("flame", (SimpleParticleType) (TacticalImbuementsModParticleTypes.FLAME_PARTICLE.get()), 1, 0.7f, 0.3f,
            "tactical_imbuements:textures/screens/flame_aspect_overlay.png");

    public static final ImbuementDef VENOM = new ImbuementDef("venom", (SimpleParticleType) (TacticalImbuementsModParticleTypes.VENOM_PARTICLE.get()), 0.1f, 1f, 0.1f,
            "tactical_imbuements:textures/screens/venom_aspect_overlay.png");

    public static final ImbuementDef FREEZE = new ImbuementDef("freeze", (SimpleParticleType) (TacticalImbuementsModParticleTypes.FREEZE_PARTICLE.get()), 0.8f, 0.8f, 0.9f,
            "tactical_imbuements:textures/screens/snow_aspect_overlay.png");

    public static final ImbuementDef SPARK = new ImbuementDef("spark", (SimpleParticleType) (TacticalImbuementsModParticleTypes.SPARK_PARTICLE.get()), 0.4f, 0.4f, 1f,
            "tactical_imbuements:textures/screens/lightning_aspect_overlay.png");

}
