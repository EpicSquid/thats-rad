package dev.epicsquid.thatsrad.entity.event;

import dev.epicsquid.thatsrad.ThatsRad;
import dev.epicsquid.thatsrad.entity.ModEntityTypes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = ThatsRad.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
            event.put(ModEntityTypes.PLUGSLUG.get(), PlugSlugEntity.setAttributes());


        }
    }
}
