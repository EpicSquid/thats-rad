package dev.epicsquid.thatsrad

import dev.epicsquid.thatsrad.ThatsRad.ClientModEvents.onClientSetup
import dev.epicsquid.thatsrad.item.ModItems
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import software.bernie.geckolib3.GeckoLib
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist
import java.util.function.Consumer

@Mod(ThatsRad.ID)
object ThatsRad {
    const val ID = "thatsrad"

    val LOGGER = LogManager.getLogger(ID)

    init {

        val modEventBus: IEventBus = FMLJavaModLoadingContext.get().getModEventBus()
        ModItems.register(modEventBus)
        ModEntityTypes.register(modEventBus)
        GeckoLib.initialize()
        modEventBus.addListener<FMLCommonSetupEvent>(Consumer<FMLCommonSetupEvent> { event: FMLCommonSetupEvent ->
            commonSetup(
                event
            )
        })
        MinecraftForge.EVENT_BUS.register(this)

        val obj = runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                Minecraft.getInstance()
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
                "test"
            })

        println(obj)
    }

    private fun commonSetup(event: FMLCommonSetupEvent) {
        event.enqueueWork(Runnable { ThatsRad.run() })
    }

    @Mod.EventBusSubscriber(modid = ThatsRad.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    object ClientModEvents {
        @SubscribeEvent
        fun onClientSetup(event: FMLClientSetupEvent?) {
            EntityRenderers(
//                ModEntityTypes.SWEEPY.get(),
//                EntityRendererProvider<T> { SweepyRenderer() })

        }

        /**
         * This is used for initializing client specific
         * things such as renderers and keymaps
         * Fired on the mod specific event bus.
         */
        private fun onClientSetup(event: FMLClientSetupEvent) {
            LOGGER.log(Level.INFO, "Initializing client...")
        }
    }

	/**
	 * Fired on the global Forge bus.
	 */
	private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
		LOGGER.log(Level.INFO, "Server starting...")
	}
}