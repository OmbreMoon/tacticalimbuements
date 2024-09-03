package net.m3tte.tactical_imbuements.renderer;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.m3tte.tactical_imbuements.definitions.ImbuementDefinitions;
import net.minecraft.Util;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.glfw.GLFWWindowCloseCallback;

import java.util.Optional;
import java.util.function.BiFunction;

public class GlintRenderers extends RenderType {

    public static final ResourceLocation FIRE_GLINT_LOCATION = new ResourceLocation("tactical_imbuements:textures/glints/fire_glint.png");
    public static final ResourceLocation VENOM_GLINT_LOCATION = new ResourceLocation("tactical_imbuements:textures/glints/venom_glint.png");
    public static final ResourceLocation FREEZE_GLINT_LOCATION = new ResourceLocation("tactical_imbuements:textures/glints/snow_glint.png");
    public static final ResourceLocation SPARK_GLINT_LOCATION = new ResourceLocation("tactical_imbuements:textures/glints/lightning_glint.png");
    protected static final RenderStateShard.ShaderStateShard RENDERTYPE_FIRE_GLINT_DIRECT_SHADER = new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeGlintDirectShader);
    private static final RenderType FIRE_GLINT = create("fire_glint", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_GLINT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(FIRE_GLINT_LOCATION, true, false)).setWriteMaskState(COLOR_WRITE).setCullState(NO_CULL).setDepthTestState(EQUAL_DEPTH_TEST).setTransparencyState(GLINT_TRANSPARENCY).setTexturingState(GLINT_TEXTURING).createCompositeState(false));
    private static final RenderType FIRE_GLINT_DIRECT = create("fire_glint_direct", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_GLINT_DIRECT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(FIRE_GLINT_LOCATION, true, false)).setWriteMaskState(COLOR_WRITE).setCullState(NO_CULL).setDepthTestState(EQUAL_DEPTH_TEST).setTransparencyState(GLINT_TRANSPARENCY).setTexturingState(GLINT_TEXTURING).createCompositeState(false));
    //private static final RenderType FIRE_ENTITY_GLINT = create("fire_entity_glint", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_ENTITY_GLINT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(FIRE_GLINT_LOCATION, true, false)).setWriteMaskState(COLOR_WRITE).setCullState(NO_CULL).setDepthTestState(EQUAL_DEPTH_TEST).setTransparencyState(GLINT_TRANSPARENCY).setOutputState(ITEM_ENTITY_TARGET).setTexturingState(ENTITY_GLINT_TEXTURING).createCompositeState(false));
    private static final RenderType FIRE_ENTITY_GLINT_DIRECT = create("fire_entity_glint_direct", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_GLINT_DIRECT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(FIRE_GLINT_LOCATION, true, false)).setWriteMaskState(COLOR_WRITE).setCullState(NO_CULL).setDepthTestState(EQUAL_DEPTH_TEST).setTransparencyState(GLINT_TRANSPARENCY).setTexturingState(ENTITY_GLINT_TEXTURING).createCompositeState(false));

    private static final RenderType VENOM_GLINT = create("venom_glint", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_GLINT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(VENOM_GLINT_LOCATION, true, false)).setWriteMaskState(COLOR_WRITE).setCullState(NO_CULL).setDepthTestState(EQUAL_DEPTH_TEST).setTransparencyState(GLINT_TRANSPARENCY).setTexturingState(GLINT_TEXTURING).createCompositeState(false));
    private static final RenderType VENOM_GLINT_DIRECT = create("venom_glint_direct", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_GLINT_DIRECT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(VENOM_GLINT_LOCATION, true, false)).setWriteMaskState(COLOR_WRITE).setCullState(NO_CULL).setDepthTestState(EQUAL_DEPTH_TEST).setTransparencyState(GLINT_TRANSPARENCY).setTexturingState(GLINT_TEXTURING).createCompositeState(false));
    //private static final RenderType FIRE_ENTITY_GLINT = create("fire_entity_glint", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_ENTITY_GLINT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(FIRE_GLINT_LOCATION, true, false)).setWriteMaskState(COLOR_WRITE).setCullState(NO_CULL).setDepthTestState(EQUAL_DEPTH_TEST).setTransparencyState(GLINT_TRANSPARENCY).setOutputState(ITEM_ENTITY_TARGET).setTexturingState(ENTITY_GLINT_TEXTURING).createCompositeState(false));
    private static final RenderType VENOM_ENTITY_GLINT_DIRECT = create("venom_entity_glint_direct", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_GLINT_DIRECT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(VENOM_GLINT_LOCATION, true, false)).setWriteMaskState(COLOR_WRITE).setCullState(NO_CULL).setDepthTestState(EQUAL_DEPTH_TEST).setTransparencyState(GLINT_TRANSPARENCY).setTexturingState(ENTITY_GLINT_TEXTURING).createCompositeState(false));
    private static final RenderType FREEZE_GLINT_DIRECT = create("freeze_glint_direct", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_GLINT_DIRECT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(FREEZE_GLINT_LOCATION, true, false)).setWriteMaskState(COLOR_WRITE).setCullState(NO_CULL).setDepthTestState(EQUAL_DEPTH_TEST).setTransparencyState(GLINT_TRANSPARENCY).setTexturingState(GLINT_TEXTURING).createCompositeState(false));
    private static final RenderType FREEZE_ENTITY_GLINT_DIRECT = create("freeze_entity_glint_direct", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_GLINT_DIRECT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(FREEZE_GLINT_LOCATION, true, false)).setWriteMaskState(COLOR_WRITE).setCullState(NO_CULL).setDepthTestState(EQUAL_DEPTH_TEST).setTransparencyState(GLINT_TRANSPARENCY).setTexturingState(ENTITY_GLINT_TEXTURING).createCompositeState(false));

    private static final RenderType SPARK_GLINT_DIRECT = create("spark_glint_direct", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_GLINT_DIRECT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(SPARK_GLINT_LOCATION, true, false)).setWriteMaskState(COLOR_WRITE).setCullState(NO_CULL).setDepthTestState(EQUAL_DEPTH_TEST).setTransparencyState(GLINT_TRANSPARENCY).setTexturingState(GLINT_TEXTURING).createCompositeState(false));
    private static final RenderType SPARK_ENTITY_GLINT_DIRECT = create("spark_entity_glint_direct", DefaultVertexFormat.POSITION_TEX, VertexFormat.Mode.QUADS, 256, false, false, RenderType.CompositeState.builder().setShaderState(RENDERTYPE_GLINT_DIRECT_SHADER).setTextureState(new RenderStateShard.TextureStateShard(SPARK_GLINT_LOCATION, true, false)).setWriteMaskState(COLOR_WRITE).setCullState(NO_CULL).setDepthTestState(EQUAL_DEPTH_TEST).setTransparencyState(GLINT_TRANSPARENCY).setTexturingState(ENTITY_GLINT_TEXTURING).createCompositeState(false));


    public GlintRenderers(String p_173178_, VertexFormat p_173179_, VertexFormat.Mode p_173180_, int p_173181_, boolean p_173182_, boolean p_173183_, Runnable p_173184_, Runnable p_173185_) {
        super(p_173178_, p_173179_, p_173180_, p_173181_, p_173182_, p_173183_, p_173184_, p_173185_);
    }


    public static RenderType getDirectGlintRender(String preset) {


        switch (preset) {
            default:
                return RenderType.glintDirect();
            case ImbuementDefinitions.FLAMEID:
                return getFireGlintDirect();
            case ImbuementDefinitions.VENOMID:
                return getVenomGlintDirect();
            case ImbuementDefinitions.FREEZEID:
                return getFreezeGlintDirect();
            case ImbuementDefinitions.SPARKID:
                return getSparkGlintDirect();
        }
    }
    public static RenderType getEntityGlintDirect(String preset) {

        switch (preset) {
            default:
                return RenderType.entityGlintDirect();
            case ImbuementDefinitions.FLAMEID:
                return getFireEntityGlintDirect();
            case ImbuementDefinitions.VENOMID:
                return getVenomEntityGlintDirect();
            case ImbuementDefinitions.FREEZEID:
                return getFreezeEntityGlintDirect();
            case ImbuementDefinitions.SPARKID:
                return getSparkEntityGlintDirect();
        }
    }

    public static RenderType getFireEntityGlintDirect() {
        return FIRE_ENTITY_GLINT_DIRECT;
    }

    public static RenderType getFireGlintDirect() {
        return FIRE_GLINT_DIRECT;
    }

    public static RenderType getVenomEntityGlintDirect() {
        return VENOM_ENTITY_GLINT_DIRECT;
    }

    public static RenderType getVenomGlintDirect() {
        return VENOM_GLINT_DIRECT;
    }

    public static RenderType getFreezeEntityGlintDirect() {
        return FREEZE_ENTITY_GLINT_DIRECT;
    }

    public static RenderType getFreezeGlintDirect() {
        return FREEZE_GLINT_DIRECT;
    }

    public static RenderType getSparkEntityGlintDirect() {
        return SPARK_ENTITY_GLINT_DIRECT;
    }

    public static RenderType getSparkGlintDirect() {
        return SPARK_GLINT_DIRECT;
    }

    public static RenderType getGlintRender(String preset) {
        switch (preset) {
            default:
                return RenderType.glint();
            case ImbuementDefinitions.FLAMEID:
                return FIRE_GLINT;
        }
    }
}


