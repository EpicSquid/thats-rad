package dev.epicsquid.thatsrad.entity

import dev.epicsquid.thatsrad.ThatsRad
import dev.epicsquid.thatsrad.entity.custom.PlugSlugEntity
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EntityType.EntityFactory
import net.minecraft.world.entity.MobCategory
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import net.minecraftforge.registries.RegistryObject
import java.util.function.Supplier

object ModEntityTypes {
    val ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ThatsRad.ID)


    //More Mob/Entity Registering
    @JvmField
    val PLUGSLUG: RegistryObject<EntityType<PlugSlugEntity>> =
        ModEntityTypes.ENTITY_TYPES.register<EntityType<Entity>>("plugslug",
            Supplier<EntityType<Entity>> {
                EntityType.Builder.of<Entity>(
                    EntityFactory<Entity> {PlugSlugEntity()},
                    MobCategory.CREATURE.sized(0.7f, 1.8f).clientTrackingRange(80)
                        .build(ResourceLocation(ThatsRad.ID, "plugslug").toString())
            })

    @JvmStatic
    fun register(eventBus: IEventBus?) {
        ModEntityTypes.ENTITY_TYPES.register(eventBus)
    }
}
