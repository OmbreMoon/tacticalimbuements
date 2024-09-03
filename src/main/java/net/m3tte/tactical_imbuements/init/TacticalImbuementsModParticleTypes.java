
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.m3tte.tactical_imbuements.init;

import net.m3tte.tactical_imbuements.TacticalImbuementsMod;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TacticalImbuementsModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, TacticalImbuementsMod.MODID);
	public static final RegistryObject<ParticleType<?>> GLASS_SHARD_PARTICLE = REGISTRY.register("glass_shard", () -> new SimpleParticleType(true));
	public static final RegistryObject<ParticleType<?>> FLAME_PARTICLE = REGISTRY.register("flame_particle", () -> new SimpleParticleType(true));
	public static final RegistryObject<ParticleType<?>> VENOM_PARTICLE = REGISTRY.register("venom_particle", () -> new SimpleParticleType(true));
	public static final RegistryObject<ParticleType<?>> FREEZE_PARTICLE = REGISTRY.register("freeze_particle", () -> new SimpleParticleType(true));
	public static final RegistryObject<ParticleType<?>> SPARK_PARTICLE = REGISTRY.register("spark_particle", () -> new SimpleParticleType(true));
}
