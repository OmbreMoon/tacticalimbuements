
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.m3tte.tactical_imbuements.init;

import net.m3tte.tactical_imbuements.client.renderer.*;
import net.m3tte.tactical_imbuements.entity.VenomFlaskThrowableEntity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TacticalImbuementsModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(TacticalImbuementsModEntities.EMPTYFLASKTHROWABLE.get(), EmptyflaskthrowableRenderer::new);
		event.registerEntityRenderer(TacticalImbuementsModEntities.FIREFLASKTHROWABLE.get(), FireflaskthrowableRenderer::new);
		event.registerEntityRenderer(TacticalImbuementsModEntities.VENOMFLASKTHROWABLE.get(), VenomFlaskThrowableRenderer::new);
		event.registerEntityRenderer(TacticalImbuementsModEntities.FREEZEFLASKTHROWABLE.get(), FreezeFlaskThrowableRenderer::new);
		event.registerEntityRenderer(TacticalImbuementsModEntities.SPARKFLASKTHROWABLE.get(), SparkFlaskThrowableRenderer::new);
	}
}
