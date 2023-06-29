package dev.epicsquid.thatsrad.entity.client.model

import dev.epicsquid.thatsrad.ThatsRad
import dev.epicsquid.thatsrad.entity.custom.SweepyEntity
import net.minecraft.resources.ResourceLocation
import software.bernie.geckolib3.model.AnimatedGeoModel

abstract class SweepyModel : AnimatedGeoModel<SweepyEntity?>() {
	fun getModelLocation(`object`: SweepyEntity?): ResourceLocation {
		return ResourceLocation(ThatsRad.ID, "geo/sweepy.geo.json")
	}

	fun getTextureLocation(`object`: SweepyEntity?): ResourceLocation {
		return ResourceLocation(ThatsRad.ID, "textures/entity/sweepy.png")
	}

	fun getAnimationFileLocation(animatable: SweepyEntity?): ResourceLocation {
		return ResourceLocation(ThatsRad.ID, "animations/sweepy.animation.json")
	}
	}
