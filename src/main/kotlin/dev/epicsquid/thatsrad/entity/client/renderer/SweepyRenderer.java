package dev.epicsquid.thatsrad.entity.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.epicsquid.thatsrad.ThatsRad;
import dev.epicsquid.thatsrad.entity.client.model.SweepyModel;
import dev.epicsquid.thatsrad.entity.custom.SweepyEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class SweepyRenderer extends GeoEntityRenderer<SweepyEntity> {

        public SweepyRenderer(EntityRendererProvider.Context renderManager) {
            super(renderManager, new SweepyModel());
            this.shadowRadius = 0.5f;
        }
        @Override
        public ResourceLocation getTextureLocation(SweepyEntity instance) {
            return new ResourceLocation(ThatsRad.ID, "textures/entity/sweepy.png");
        }
        @Override
        public RenderType getRenderType(SweepyEntity animatable, float partialTicks, PoseStack stack,
                                        @Nullable MultiBufferSource renderTypeBuffer,
                                        @Nullable VertexConsumer vertexBuilder, int packedLightIn,
                                        ResourceLocation textureLocation) {
            stack.scale(1.0f, 1.0f, 1.0f);
            return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
        }
    }
