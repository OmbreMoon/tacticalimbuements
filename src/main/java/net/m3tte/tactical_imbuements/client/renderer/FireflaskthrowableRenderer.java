package net.m3tte.tactical_imbuements.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.m3tte.tactical_imbuements.entity.FireflaskthrowableEntity;
import net.m3tte.tactical_imbuements.client.model.ModelGlassFlaskModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class FireflaskthrowableRenderer extends EntityRenderer<FireflaskthrowableEntity> {
	private static final ResourceLocation texture = new ResourceLocation("tactical_imbuements:textures/entities/fire_flask_model.png");
	private final ModelGlassFlaskModel model;

	public FireflaskthrowableRenderer(EntityRendererProvider.Context context) {
		super(context);
		model = new ModelGlassFlaskModel(context.bakeLayer(ModelGlassFlaskModel.LAYER_LOCATION));
	}

	@Override
	public void render(FireflaskthrowableEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
		VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
		poseStack.pushPose();
		poseStack.mulPose(Vector3f.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
		poseStack.mulPose(Vector3f.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
		model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 0.0625f);
		poseStack.popPose();
		super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
	}

	@Override
	public ResourceLocation getTextureLocation(FireflaskthrowableEntity entity) {
		return texture;
	}
}