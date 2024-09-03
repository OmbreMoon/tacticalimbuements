
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.m3tte.tactical_imbuements.init;

import net.m3tte.tactical_imbuements.item.*;
import net.m3tte.tactical_imbuements.TacticalImbuementsMod;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TacticalImbuementsModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, TacticalImbuementsMod.MODID);
	public static final RegistryObject<Item> FLASK_OF_FLAME = REGISTRY.register("flask_of_flame", () -> new FlaskofflameItem());
	public static final RegistryObject<Item> FLASK_OF_VENOM = REGISTRY.register("flask_of_venom", () -> new FlaskOfVenomItem());

	public static final RegistryObject<Item> FLASK_OF_SPARKS = REGISTRY.register("flask_of_sparks", () -> new FlaskOfSparks());
	public static final RegistryObject<Item> FLASK_OF_ICE = REGISTRY.register("flask_of_ice", () -> new FlaskOfIce());
	public static final RegistryObject<Item> FLASK = REGISTRY.register("flask", () -> new FlaskItem());
}
