package net.m3tte.tactical_imbuements.procedures;

import net.m3tte.tactical_imbuements.EpicFight.ImbuementAnims;
import net.m3tte.tactical_imbuements.init.TacticalImbuementsModItems;
import net.m3tte.tactical_imbuements.init.TacticalImbuementsModEntities;
import net.m3tte.tactical_imbuements.entity.EmptyflaskthrowableEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import yesman.epicfight.world.capabilities.EpicFightCapabilities;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

public class UseemptyflaskProcedure {
	public static void execute(Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity living) {

			if (living.getMainHandItem().getItem().equals(TacticalImbuementsModItems.FLASK.get())) {
				if (entity instanceof Player _player)
					_player.getCooldowns().addCooldown(itemstack.getItem(), 20);
				LivingEntityPatch<?> entitypatch = (LivingEntityPatch<?>) living.getCapability(EpicFightCapabilities.CAPABILITY_ENTITY, null).orElse(null);
				entitypatch.playAnimationSynchronized(ImbuementAnims.THROW_FLASK, 0);

				(itemstack).shrink(1);
				Entity _shootFrom = entity;
				Level projectileLevel = _shootFrom.level;
				if (!projectileLevel.isClientSide()) {
					Projectile _entityToSpawn = new Object() {
						public Projectile getArrow(Level level, Entity shooter, float damage, int knockback) {
							AbstractArrow entityToSpawn = new EmptyflaskthrowableEntity(TacticalImbuementsModEntities.EMPTYFLASKTHROWABLE.get(), level);
							entityToSpawn.setOwner(shooter);
							entityToSpawn.setBaseDamage(damage);
							entityToSpawn.setKnockback(knockback);
							entityToSpawn.setSilent(true);
							return entityToSpawn;
						}
					}.getArrow(projectileLevel, entity, 8, 1);
					_entityToSpawn.setPos(_shootFrom.getX(), _shootFrom.getEyeY() - 0.1, _shootFrom.getZ());
					_entityToSpawn.shoot(_shootFrom.getLookAngle().x, _shootFrom.getLookAngle().y, _shootFrom.getLookAngle().z, 1, (float) 0.1);
					projectileLevel.addFreshEntity(_entityToSpawn);
				}
			}
		}

	}
}
