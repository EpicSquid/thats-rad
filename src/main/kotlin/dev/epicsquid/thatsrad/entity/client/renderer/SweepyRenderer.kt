//package dev.epicsquid.thatsrad.entity.client.renderer
//
//import com.mojang.blaze3d.vertex.PoseStack
//import com.mojang.blaze3d.vertex.VertexConsumer
//import dev.epicsquid.thatsrad.ThatsRad
//import dev.epicsquid.thatsrad.entity.client.model.SweepyModel
//import dev.epicsquid.thatsrad.entity.custom.SweepyEntity
//import net.minecraft.client.renderer.MultiBufferSource
//import net.minecraft.client.renderer.RenderType
//import net.minecraft.resources.ResourceLocation
//import software.bernie.geckolib3.model.AnimatedGeoModel
//import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer
//
//class SweepyRenderer(modelProvider: AnimatedGeoModel<SweepyEntity?>?) :
//	GeoEntityRenderer<SweepyEntity?>(SweepyModel, modelProvider) {
//	init {
//		shadowRadius = 0.5f
//	}
//
//	 fun getTextureLocation(instance: SweepyEntity): ResourceLocation {
//		return ResourceLocation(ThatsRad.ID, "textures/entity/sweepy.png")
//	}
//
//	 fun getRenderType(
//		animatable: SweepyEntity, partialTicks: Float, stack: PoseStack,
//		renderTypeBuffer: MultiBufferSource?,
//		vertexBuilder: VertexConsumer?, packedLightIn: Int,
//		textureLocation: ResourceLocation
//	): RenderType {
//		stack.scale(1.0f, 1.0f, 1.0f)
//		return super.getRenderType(
//			animatable,
//			partialTicks,
//			stack,
//			renderTypeBuffer,
//			vertexBuilder,
//			packedLightIn,
//			textureLocation
//		)
//	}
//}
