package dev.epicsquid.thatsrad;

import com.mojang.logging.LogUtils;
import dev.epicsquid.thatsrad.block.ModBlocks;
import dev.epicsquid.thatsrad.entity.ModEntityTypes;
import dev.epicsquid.thatsrad.item.ModItems;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(IsItRad.MODID)
public class IsItRad {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "thatsrad";
    private static final Logger LOGGER = LogUtils.getLogger();

    // Directly reference a slf4j logger
    public IsItRad() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

       //Item Adding
        ModItems.register(modEventBus);
//Mob Adding
       ModEntityTypes.register(modEventBus);
        //block adding
        ModBlocks.BLOCKS.register(modEventBus);

//advanded animations and model adding
        //TODO:GeckoLib.initialize();

        modEventBus.addListener(this::commonSetup);

        MinecraftForge.EVENT_BUS.register(this);

    }
//Example Mob Spawn Check
//    private static void run() {
//        SpawnPlacements.register(ModEntityTypes.ANIMATEDLEATHERARMOR.get(),
//                SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
//                Monster::checkMonsterSpawnRules);
//
//
//    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(IsItRad::run);
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
          //Entity Renderer Assigner
            // EntityRenderers.register(ModEntityTypes.ANIMATEDLEATHERARMOR.get(), AnimatedLeatherArmorRenderer::new);
        }
    }
}
