package dev.epicsquid.thatsrad.entity;


import dev.epicsquid.thatsrad.ThatsRad;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ThatsRad.ID);
    //More Mob/Entity Registering
//    public static final RegistryObject<EntityType<AnimatedDiamondArmorEntity>> ANIMATEDDIAMONDARMOR =
//            ENTITY_TYPES.register("animateddiamondarmor",
//                    () -> EntityType.Builder.of(AnimatedDiamondArmorEntity::new, MobCategory.MONSTER)
//                            .sized(0.7F, 1.8F).clientTrackingRange(80)
//                            .build(new ResourceLocation(ForgottenMobs.MODID,"animateddiamondarmor").toString()));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
