package dev.epicsquid.thatsrad.entity;


import dev.epicsquid.thatsrad.ThatsRad;
import dev.epicsquid.thatsrad.entity.custom.SweepyEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ThatsRad.ID);

    public static final RegistryObject<EntityType<SweepyEntity>> SWEEPY =
            ENTITY_TYPES.register("sweepy",
                    () -> EntityType.Builder.of(SweepyEntity::new, MobCategory.MONSTER)
                            .sized(0.4f, 1.5f)
                            .build(new ResourceLocation(ThatsRad.ID, "sweepy").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }

    public static void register() {

    }
}