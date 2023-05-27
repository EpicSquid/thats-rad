package dev.epicsquid.thatsrad.entity.client.renderer;//Example Renderering package for mob

//package dev.epicsquid.thatsrad.entity.client.renderer;
//
//
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.blaze3d.vertex.VertexConsumer;
//import net.grinner117.forgottenmobs.ForgottenMobs;
//import net.grinner117.forgottenmobs.entity.client.model.OwlBearForestModel;
//import net.grinner117.forgottenmobs.entity.custom.OwlBearForestEntity;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.RenderType;
//import net.minecraft.client.renderer.entity.EntityRendererProvider;
//import net.minecraft.resources.ResourceLocation;
//import org.jetbrains.annotations.Nullable;
//import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
//
//public class OwlBearForestRenderer extends GeoEntityRenderer<OwlBearForestEntity> {
//    public OwlBearForestRenderer(EntityRendererProvider.Context renderManager) {
//        super(renderManager, new OwlBearForestModel());
//        this.shadowRadius = 1.0f;
//    }
//    @Override
//    public ResourceLocation getTextureLocation(OwlBearForestEntity instance) {
//        return new ResourceLocation(ForgottenMobs.MODID, "textures/entity/owlbearforest.png");
//    }
//    @Override
//    public RenderType getRenderType(OwlBearForestEntity animatable, float partialTicks, PoseStack stack,
//                                    @Nullable MultiBufferSource renderTypeBuffer,
//                                    @Nullable VertexConsumer vertexBuilder, int packedLightIn,
//                                    ResourceLocation textureLocation) {
//        stack.scale(3.0f, 3.0f, 3.0f);
//        return super.getRenderType(animatable, partialTicks, stack, renderTypeBuffer, vertexBuilder, packedLightIn, textureLocation);
//    }
//}