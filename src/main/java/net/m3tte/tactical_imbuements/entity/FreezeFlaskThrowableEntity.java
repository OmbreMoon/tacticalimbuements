
package net.m3tte.tactical_imbuements.entity;

import net.m3tte.tactical_imbuements.init.TacticalImbuementsModEntities;
import net.m3tte.tactical_imbuements.procedures.FlaskImpact;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.ItemSupplier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Random;

@OnlyIn(value = Dist.CLIENT, _interface = ItemSupplier.class)
public class FreezeFlaskThrowableEntity extends AbstractArrow implements ItemSupplier {
	public FreezeFlaskThrowableEntity(PlayMessages.SpawnEntity packet, Level world) {
		super(TacticalImbuementsModEntities.FREEZEFLASKTHROWABLE.get(), world);
	}

	public FreezeFlaskThrowableEntity(EntityType<? extends FreezeFlaskThrowableEntity> type, Level world) {
		super(type, world);
	}

	public FreezeFlaskThrowableEntity(EntityType<? extends FreezeFlaskThrowableEntity> type, double x, double y, double z, Level world) {
		super(type, x, y, z, world);
	}

	public FreezeFlaskThrowableEntity(EntityType<? extends FreezeFlaskThrowableEntity> type, LivingEntity entity, Level world) {
		super(type, entity, world);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public ItemStack getItem() {
		return ItemStack.EMPTY;
	}

	@Override
	protected ItemStack getPickupItem() {
		return ItemStack.EMPTY;
	}

	@Override
	protected void doPostHurtEffects(LivingEntity entity) {
		super.doPostHurtEffects(entity);
		entity.setArrowCount(entity.getArrowCount() - 1);
	}

	@Override
	public void onHitEntity(EntityHitResult entityHitResult) {
		super.onHitEntity(entityHitResult);
		FlaskImpact.freezeImpact(this.level(), this.getX(), this.getY(), this.getZ());
		if (!this.isRemoved()) {
			this.discard();
		}
	}

	@Override
	public void onHitBlock(BlockHitResult blockHitResult) {
		super.onHitBlock(blockHitResult);
		FlaskImpact.freezeImpact(this.level(), this.getX(), this.getY(), this.getZ());
	}

	@Override
	public void tick() {
		super.tick();
		if (this.inGround)
			this.discard();
	}

	public static FreezeFlaskThrowableEntity shoot(Level world, LivingEntity entity, Random random, float power, double damage, int knockback) {
		FreezeFlaskThrowableEntity entityarrow = new FreezeFlaskThrowableEntity(TacticalImbuementsModEntities.FREEZEFLASKTHROWABLE.get(), entity, world);
		entityarrow.shoot(entity.getViewVector(1).x, entity.getViewVector(1).y, entity.getViewVector(1).z, power * 2, 0);
		entityarrow.setSilent(true);
		entityarrow.setCritArrow(false);
		entityarrow.setBaseDamage(damage);
		entityarrow.setKnockback(knockback);
		world.addFreshEntity(entityarrow);
		world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (random.nextFloat() * 0.5f + 1) + (power / 2));
		return entityarrow;
	}

	public static FreezeFlaskThrowableEntity shoot(LivingEntity entity, LivingEntity target) {
		FreezeFlaskThrowableEntity entityarrow = new FreezeFlaskThrowableEntity(TacticalImbuementsModEntities.FREEZEFLASKTHROWABLE.get(), entity, entity.level());
		double dx = target.getX() - entity.getX();
		double dy = target.getY() + target.getEyeHeight() - 1.1;
		double dz = target.getZ() - entity.getZ();
		entityarrow.shoot(dx, dy - entityarrow.getY() + Math.hypot(dx, dz) * 0.2F, dz, 0.1f * 2, 12.0F);
		entityarrow.setSilent(true);
		entityarrow.setBaseDamage(5);
		entityarrow.setKnockback(1);
		entityarrow.setCritArrow(false);
		entity.level().addFreshEntity(entityarrow);
		entity.level().playSound(null, entity.getX(), entity.getY(), entity.getZ(), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.arrow.shoot")), SoundSource.PLAYERS, 1, 1f / (new Random().nextFloat() * 0.5f + 1));
		return entityarrow;
	}
}
