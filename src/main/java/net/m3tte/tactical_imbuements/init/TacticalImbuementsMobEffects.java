
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.m3tte.tactical_imbuements.init;

import net.m3tte.tactical_imbuements.TacticalImbuementsMod;
import net.m3tte.tactical_imbuements.potion.ChilledMobEffect;
import net.m3tte.tactical_imbuements.potion.DecayingMobEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TacticalImbuementsMobEffects {
	public static final DeferredRegister<MobEffect> REGISTRY = DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TacticalImbuementsMod.MODID);
	public static final RegistryObject<MobEffect> CHILLED = REGISTRY.register("chilled", ChilledMobEffect::new);
	public static final RegistryObject<MobEffect> DECAYING = REGISTRY.register("decaying", DecayingMobEffect::new);
}
