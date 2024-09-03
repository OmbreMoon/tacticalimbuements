package net.m3tte.tactical_imbuements;

import net.minecraft.world.item.alchemy.PotionBrewing;
import org.spongepowered.asm.mixin.Mixins;
import org.spongepowered.asm.mixin.connect.IMixinConnector;

public class MixinConnector implements IMixinConnector {
    @Override
    public void connect() {
        System.out.println("mixin connected");
        Mixins.addConfiguration(("mixin.json"));
    }
}
