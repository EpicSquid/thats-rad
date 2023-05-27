package dev.epicsquid.thatsrad.entity;


import dev.epicsquid.thatsrad.ThatsRad;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ThatsRad.ID);
    //More Mob/Entity Registering
    public static final RegistryObject<EntityType<PlugSlugEntity>> PLUGSLUG =
            ENTITY_TYPES.register("plugslug",
                    () -> EntityType.Builder.of(PlugSlugEntity::new, MobCategory.CREATURE)
                            .sized(0.7F, 1.8F).clientTrackingRange(80)
                            .build(new ResourceLocation(ThatsRad.ID,"plugslug").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
