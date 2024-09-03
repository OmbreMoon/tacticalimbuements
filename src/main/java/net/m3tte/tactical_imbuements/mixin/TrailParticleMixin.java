package net.m3tte.tactical_imbuements.mixin;


import net.m3tte.tactical_imbuements.definitions.ImbuementDefinitions;
import net.m3tte.tactical_imbuements.init.TacticalImbuementsModParticleTypes;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.JigsawBlockEntity;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import yesman.epicfight.api.animation.Joint;
import yesman.epicfight.api.animation.Pose;
import yesman.epicfight.api.animation.types.StaticAnimation;
import yesman.epicfight.api.client.animation.property.TrailInfo;
import yesman.epicfight.api.utils.math.OpenMatrix4f;
import yesman.epicfight.api.utils.math.Vec3f;
import yesman.epicfight.client.particle.TrailParticle;
import yesman.epicfight.world.capabilities.entitypatch.EntityPatch;
import yesman.epicfight.world.capabilities.entitypatch.LivingEntityPatch;

import java.util.Random;

@Mixin(value = TrailParticle.class, remap = false)
public class TrailParticleMixin {
    @Inject(at = @At(value = "TAIL"), method = "<init>(Lnet/minecraft/client/multiplayer/ClientLevel;Lyesman/epicfight/world/capabilities/entitypatch/LivingEntityPatch;Lyesman/epicfight/api/animation/Joint;Lyesman/epicfight/api/animation/types/StaticAnimation;Lyesman/epicfight/api/client/animation/property/TrailInfo;Lnet/minecraft/client/particle/SpriteSet;)V", cancellable = true)
    private void injectTrailParticle(ClientLevel level, LivingEntityPatch<?> entitypatch, Joint joint, StaticAnimation animation, TrailInfo trailInfo, SpriteSet spriteSet, CallbackInfo cbk) {
        TrailParticle trailParticle = ((TrailParticle)(Object)this);

        ItemStack item = entitypatch.getValidItemInHand(trailInfo.hand);

        if (item.getTag() == null)
            return;

        String type = item.getOrCreateTag().getString("imbueType");

        switch (type) {
            case (ImbuementDefinitions.FLAMEID) -> trailParticle.setColor(ImbuementDefinitions.FLAME.getrCol(), ImbuementDefinitions.FLAME.getgCol(), ImbuementDefinitions.FLAME.getbCol());
            case (ImbuementDefinitions.VENOMID) -> trailParticle.setColor(ImbuementDefinitions.VENOM.getrCol(), ImbuementDefinitions.VENOM.getgCol(), ImbuementDefinitions.VENOM.getbCol());
            case (ImbuementDefinitions.FREEZEID) -> trailParticle.setColor(ImbuementDefinitions.FREEZE.getrCol(), ImbuementDefinitions.FREEZE.getgCol(), ImbuementDefinitions.FREEZE.getbCol());
            case (ImbuementDefinitions.SPARKID) -> trailParticle.setColor(ImbuementDefinitions.SPARK.getrCol(), ImbuementDefinitions.SPARK.getgCol(), ImbuementDefinitions.SPARK.getbCol());
        }
    }

    /*@Inject(at = @At(value = "TAIL"), method = "tick()V")
    private void injectTick(CallbackInfo cbk) {


        Vec3[] offsets = {new Vec3(0, -0.05, -0.95), new Vec3(0, -0.05, -0.5), new Vec3(0, -0.05, -0.15)};

        TrailParticleInterface trailParticleInterface = ((TrailParticleInterface)(Object)this);
        LivingEntityPatch<?> entityPatch = trailParticleInterface.getEntitypatch();
        TrailInfo trailInfo = trailParticleInterface.getTrailInfo();
        Pose currentPose = trailParticleInterface.getEntitypatch().getArmature().getCurrentPose();
        Level l = trailParticleInterface.getEntitypatch().getOriginal().level;
        Random r = l.random;
        for (int i = 2; i < 10; i += 3) {
            Pose middlePose = entityPatch.getArmature().getPose((float) (i + r.nextInt(3) - 1) / 10);
            Vec3 posMid = entityPatch.getOriginal().getPosition((float) (i + r.nextInt(3) - 1) / 10);

            OpenMatrix4f middleModelTf = OpenMatrix4f.createTranslation((float)posMid.x, (float)posMid.y, (float)posMid.z)
                    .mulBack(OpenMatrix4f.createRotatorDeg(180.0F, Vec3f.Y_AXIS)
                            .mulBack(entityPatch.getModelMatrix((float) (i + r.nextInt(3) - 1) / 10F)));

            OpenMatrix4f middleJointTf = entityPatch.getArmature().getBindedTransformFor(middlePose, trailParticleInterface.getJoint()).mulFront(middleModelTf);



            for (Vec3 modifier : offsets) {
                modifier = modifier.add(r.nextFloat() * 0.2f - 0.1, r.nextFloat() * 0.2f - 0.1, r.nextFloat() * 0.2f - 0.1);
                Vec3 particlePos = OpenMatrix4f.transform(middleJointTf, modifier);
                l.addParticle((SimpleParticleType) (TacticalImbuementsModParticleTypes.FLAME_PARTICLE.get()), particlePos.x, particlePos.y, particlePos.z, 0, 0, 0);
            }
        }



    }*/
}
