package dev.epicsquid.thatsrad.entity.event;

import dev.epicsquid.thatsrad.ThatsRad;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModEvents {
    @Mod.EventBusSubscriber(modid = ThatsRad.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ModEventBusEvents {
        @SubscribeEvent
        public static void entityAttributeEvent(EntityAttributeCreationEvent event) {
//Entity adding here
// event.put(ModEntityTypes.DEVA.get(), DevaEntity.setAttributes());


        }
    }
}
