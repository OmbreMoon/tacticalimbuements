package net.m3tte.tactical_imbuements.mixin;


import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import net.m3tte.tactical_imbuements.TacticalImbuementsMod;
import net.m3tte.tactical_imbuements.definitions.ImbuementDefinitions;
import net.m3tte.tactical_imbuements.procedures.TickProcedure;
import net.m3tte.tactical_imbuements.renderer.GlintRenderers;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.ChatComponent;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.StainedGlassPaneBlock;
import net.minecraftforge.client.ForgeHooksClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import javax.annotation.Nullable;
import java.util.SortedMap;
import java.util.logging.LogManager;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin implements ResourceManagerReloadListener {
    @Redirect(at = @At(value = "INVOKE",
            target = "Lnet/minecraft/client/renderer/entity/ItemRenderer;getFoilBufferDirect(Lnet/minecraft/client/renderer/MultiBufferSource;Lnet/minecraft/client/renderer/RenderType;ZZ)Lcom/mojang/blaze3d/vertex/VertexConsumer;"),
            method = "render")
    public VertexConsumer overlayRendererDirect(MultiBufferSource multiBuffer, RenderType p_115224_, boolean p_115225_, boolean p_115226_, ItemStack item, ItemTransforms.TransformType p_115145_ , boolean bool) {

        LocalPlayer localplayer = Minecraft.getInstance().player;

        if (localplayer != null && item.getTag() != null) {

            int timeout = (int)item.getOrCreateTag().getDouble("imbueCounter") - localplayer.tickCount;

            if (item.getOrCreateTag().getString("imbueType").length() > 2 && timeout < TickProcedure.defaultMaxTime + 60 && timeout > 0){
                boolean flag1;
                if (p_115145_ != ItemTransforms.TransformType.GUI && !p_115145_.firstPerson() && item.getItem() instanceof BlockItem) {
                    Block block = ((BlockItem)item.getItem()).getBlock();
                    flag1 = !(block instanceof HalfTransparentBlock) && !(block instanceof StainedGlassPaneBlock);
                } else {
                    flag1 = true;
                }
                // Modified Enchantment Buffer
                return getEffectFoilBufferDirect(item, multiBuffer, p_115224_, true, true);
            }
        }
        // Default Enchantment Buffer
        return ItemRenderer.getFoilBufferDirect(multiBuffer, p_115224_, true, item.hasFoil());
    }

    private static VertexConsumer getEffectFoilBuffer(ItemStack item, MultiBufferSource p_115212_, RenderType p_115213_, boolean p_115214_, boolean hasFoil) {
        if (hasFoil) {
            return Minecraft.useShaderTransparency() && p_115213_ == Sheets.translucentItemSheet() ? VertexMultiConsumer.create(p_115212_.getBuffer(RenderType.glintTranslucent()), p_115212_.getBuffer(p_115213_)) : VertexMultiConsumer.create(p_115212_.getBuffer(p_115214_ ? RenderType.glint() : RenderType.entityGlint()), p_115212_.getBuffer(p_115213_));
        } else {
            return p_115212_.getBuffer(p_115213_);
        }
    }



    private static VertexConsumer getEffectFoilBufferDirect(ItemStack item, MultiBufferSource mbs, RenderType p_115224_, boolean p_115225_, boolean hasFoil) {

        String imbuetype = item.getOrCreateTag().getString("imbueType");

        VertexConsumer cons = hasFoil ? VertexMultiConsumer.create(mbs.getBuffer(p_115225_ ? GlintRenderers.getDirectGlintRender(imbuetype) : GlintRenderers.getEntityGlintDirect(imbuetype)), mbs.getBuffer(p_115224_)) : mbs.getBuffer(p_115224_);

        return cons;
    }

    @Inject(at = @At(value = "TAIL"), method = "renderGuiItemDecorations(Lnet/minecraft/client/gui/Font;Lnet/minecraft/world/item/ItemStack;IILjava/lang/String;)V", cancellable = true)
    private void renderDecorations(Font p_115175_, ItemStack item, int p_115177_, int p_115178_, @Nullable String p_115179_, CallbackInfo inf) {

        ItemRenderer renderer = ((ItemRenderer)(Object)this);

        LocalPlayer localplayer = Minecraft.getInstance().player;
        if (localplayer == null || item.getTag() == null)
            return;
        /*RenderSystem.disableDepthTest();
        RenderSystem.disableTexture();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();


        RenderSystem.setShaderColor(1, 1, 1, 1);


        //((rectFillInvoker) renderer).invokeFillRect(bufferbuilder1, p_115177_, p_115178_ + Mth.floor(16.0F * (1.0F - f)), 16, Mth.ceil(16.0F * f), 255, 25, 25, 255);
        RenderSystem.enableTexture();
        RenderSystem.enableDepthTest();

        PoseStack posestack = new PoseStack();
        String s = p_115179_ == null ? String.valueOf(item.getCount()) : p_115179_;
        posestack.translate(0.0D, 0.0D, (renderer.blitOffset + 220.0F));


        Minecraft.getInstance().getTextureManager().bindForSetup(new ResourceLocation("tactical_imbuements:textures/screens/flame_aspect_overlay.png"));
        blit(posestack, 0, 0, 0f, 0f, 18, 18, 18, 18);*/

        PoseStack posestack = new PoseStack();
        posestack.translate(0.0D, 0.0D, (renderer.blitOffset + 220.0F));


        int timeout = (int)item.getOrCreateTag().getDouble("imbueCounter") - localplayer.tickCount;
        int maxtime = (int)item.getOrCreateTag().getDouble("maxImbueTime");
        String type = item.getOrCreateTag().getString("imbueType");
        if (timeout > maxtime)
            timeout = maxtime;
        //System.out.println(timeout + " | " +Minecraft.getInstance().player.getLevel().isClientSide()+" | "+(Minecraft.getInstance().player.getServer()!=null));
        if (timeout > 0) {
                // Creates the cooldown-ish bar

            int r = 255;
            int g = 255;
            int b = 255;
            int a = 32;
            String texture = "";
            switch (type) {
                case ImbuementDefinitions.FLAMEID -> texture = ImbuementDefinitions.FLAME.getFireOverlayIdent();
                case ImbuementDefinitions.VENOMID -> texture = ImbuementDefinitions.VENOM.getFireOverlayIdent();
                case ImbuementDefinitions.SPARKID -> texture = ImbuementDefinitions.SPARK.getFireOverlayIdent();
                case ImbuementDefinitions.FREEZEID -> texture = ImbuementDefinitions.FREEZE.getFireOverlayIdent();
            }



            switch (type) {
                case ImbuementDefinitions.FLAMEID -> {
                    g=(int)(ImbuementDefinitions.FLAME.getgCol() * 255);
                    b=(int)(ImbuementDefinitions.FLAME.getbCol() * 255);
                }
                case ImbuementDefinitions.VENOMID -> {
                    r=(int)(ImbuementDefinitions.VENOM.getrCol() * 255);
                    g=(int)(ImbuementDefinitions.VENOM.getgCol() * 255);
                    b=(int)(ImbuementDefinitions.VENOM.getbCol() * 255);
                }
                case ImbuementDefinitions.FREEZEID -> {
                    r=(int)(ImbuementDefinitions.FREEZE.getrCol() * 255);
                    g=(int)(ImbuementDefinitions.FREEZE.getgCol() * 255);
                    b=(int)(ImbuementDefinitions.FREEZE.getbCol() * 255);
                }
                case ImbuementDefinitions.SPARKID -> {
                    r=(int)(ImbuementDefinitions.SPARK.getrCol() * 255);
                    g=(int)(ImbuementDefinitions.SPARK.getgCol() * 255);
                    b=(int)(ImbuementDefinitions.SPARK.getbCol() * 255);
                }
            }
            float mult = (float) timeout / maxtime;
            RenderSystem.disableDepthTest();
            RenderSystem.disableTexture();
            RenderSystem.enableBlend();
            RenderSystem.defaultBlendFunc();
            Tesselator tesselator1 = Tesselator.getInstance();
            BufferBuilder bufferbuilder1 = tesselator1.getBuilder();
            ((rectFillInvoker) renderer).invokeFillRect(bufferbuilder1, p_115177_, p_115178_ + Mth.floor(16.0F * (1.0F - mult)), 16, Mth.ceil(16.0F * mult), r, g, b, a);

            RenderSystem.enableTexture();
            RenderSystem.enableDepthTest();


            // Creates the border depending on type
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);
            RenderSystem.enableBlend();
            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            RenderSystem.setShaderColor(1, 1, 1, 1);
            RenderSystem.setShaderTexture(0, new ResourceLocation(texture));

            GuiComponent.blit(posestack, p_115177_, p_115178_ + (int)(16 * (1 - mult)), 0, (int)(16 * (1 - mult)), 16, (int) (Math.ceil(16*mult)), 16, 16);
            RenderSystem.depthMask(true);
            RenderSystem.defaultBlendFunc();
            RenderSystem.enableDepthTest();
            RenderSystem.disableBlend();
            RenderSystem.setShaderColor(1, 1, 1, 1);

        }




    }


    /*@Inject(at = @At(value = "HEAD"), method = "render(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/client/renderer/block/model/ItemTransforms/TransformType;ZLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;IILnet/minecraft/client/resources/model/BakedModel;)V", cancellable = true)
    public void overlayRenderer(ItemStack item, ItemTransforms.TransformType p_115145_, boolean p_115146_, PoseStack poseStack, MultiBufferSource p_115148_, int p_115149_, int p_115150_, BakedModel p_115151_, CallbackInfo inf) {
        ItemRenderer renderer = ((ItemRenderer)(Object)this);

        LocalPlayer localplayer = Minecraft.getInstance().player;




        int timeout = (int)item.getOrCreateTag().getDouble("imbueCounter") - localplayer.tickCount;
        int maxtime = (int)item.getOrCreateTag().getDouble("maxImbueTime");
        String type = item.getOrCreateTag().getString("imbueType");



        if (timeout > 0) {
            //System.out.println(timeout);
            int r = 255;
            int g = 255;
            int b = 255;
            int a = 125;

            switch (type) {
                case "flame" -> {
                    g=103;
                    b=0;
                }
            }

            float mult = (float) timeout / maxtime;


        }



    }*/


    /*private void fillRect(BufferBuilder p_115153_, int p_115154_, int p_115155_, int p_115156_, int p_115157_, int p_115158_, int p_115159_, int p_115160_, int p_115161_) {
        RenderSystem.setShader(GameRenderer::getPositionColorShader);
        p_115153_.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        p_115153_.vertex((double)(p_115154_ + 0), (double)(p_115155_ + 0), 0.0D).color(p_115158_, p_115159_, p_115160_, p_115161_).endVertex();
        p_115153_.vertex((double)(p_115154_ + 0), (double)(p_115155_ + p_115157_), 0.0D).color(p_115158_, p_115159_, p_115160_, p_115161_).endVertex();
        p_115153_.vertex((double)(p_115154_ + p_115156_), (double)(p_115155_ + p_115157_), 0.0D).color(p_115158_, p_115159_, p_115160_, p_115161_).endVertex();
        p_115153_.vertex((double)(p_115154_ + p_115156_), (double)(p_115155_ + 0), 0.0D).color(p_115158_, p_115159_, p_115160_, p_115161_).endVertex();
        p_115153_.end();
        BufferUploader.end(p_115153_);
    }*/





    public void blit(PoseStack poseStack, int x, int y, int width, int height, float uOffset, float vOffset, int uWidth, int vHeight, int textureWidth, int textureHeight) {
        innerBlit(poseStack, x, x + width, y, y + height, 0, uWidth, vHeight, uOffset, vOffset, textureWidth, textureHeight);
    }

    public void blit(PoseStack poseStack, int x, int y, float uOffset, float vOffset, int width, int height, int textureWidth, int textureHeight) {
        blit(poseStack, x, y, width, height, uOffset, vOffset, width, height, textureWidth, textureHeight);
    }
    private void innerBlit(PoseStack p_93188_, int p_93189_, int p_93190_, int p_93191_, int p_93192_, int p_93193_, int p_93194_, int p_93195_, float p_93196_, float p_93197_, int p_93198_, int p_93199_) {
        innerBlit(p_93188_.last().pose(), p_93189_, p_93190_, p_93191_, p_93192_, p_93193_, (p_93196_ + 0.0F) / (float)p_93198_, (p_93196_ + (float)p_93194_) / (float)p_93198_, (p_93197_ + 0.0F) / (float)p_93199_, (p_93197_ + (float)p_93195_) / (float)p_93199_);
    }

    private void innerBlit(Matrix4f p_93113_, int p_93114_, int p_93115_, int p_93116_, int p_93117_, int p_93118_, float p_93119_, float p_93120_, float p_93121_, float p_93122_) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        BufferBuilder bufferbuilder = Tesselator.getInstance().getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(p_93113_, (float)p_93114_, (float)p_93117_, (float)p_93118_).uv(p_93119_, p_93122_).endVertex();
        bufferbuilder.vertex(p_93113_, (float)p_93115_, (float)p_93117_, (float)p_93118_).uv(p_93120_, p_93122_).endVertex();
        bufferbuilder.vertex(p_93113_, (float)p_93115_, (float)p_93116_, (float)p_93118_).uv(p_93120_, p_93121_).endVertex();
        bufferbuilder.vertex(p_93113_, (float)p_93114_, (float)p_93116_, (float)p_93118_).uv(p_93119_, p_93121_).endVertex();
        bufferbuilder.end();
        BufferUploader.end(bufferbuilder);
    }


}
