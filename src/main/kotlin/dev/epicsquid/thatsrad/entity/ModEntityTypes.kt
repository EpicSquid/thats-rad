package dev.epicsquid.thatsrad.entity

import dev.epicsquid.thatsrad.ThatsRad
import dev.epicsquid.thatsrad.entity.custom.SweepyEntity
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.Level
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries

object ModEntityTypes {
	val ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ThatsRad.ID)
	val SWEEPY = ENTITY_TYPES.register(
		"sweepy"
	) {
		EntityType.Builder.of({ pEntityType: EntityType<SweepyEntity?>?, pLevel: Level? ->
			SweepyEntity(
				pEntityType,
				pLevel
			)
		}, MobCategory.MONSTER)
			.sized(0.4f, 1.5f)
			.build(ResourceLocation(ThatsRad.ID, "sweepy").toString())
	}

	fun register(eventBus: IEventBus?) {
		ENTITY_TYPES.register(eventBus)
	}

	fun register() {}
}
