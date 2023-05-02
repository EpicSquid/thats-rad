package dev.epicsquid.thatsrad

import com.mojang.logging.LogUtils
import dev.epicsquid.thatsrad.entity.ModEntityTypes
import dev.epicsquid.thatsrad.entity.client.renderer.SweepyRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.EntityRenderers
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.SpawnPlacements
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraftforge.eventbus.api.IEventBus
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import org.slf4j.Logger
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
        event.enqueueWork(Runnable { ForgottenMobs.run() })
    }

    @Mod.EventBusSubscriber(modid = ForgottenMobs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
    object ClientModEvents {
        @SubscribeEvent
        fun onClientSetup(event: FMLClientSetupEvent?) {
            EntityRenderers.register(
                ModEntityTypes.SWEEPY.get(),
                EntityRendererProvider<T> { SweepyRenderer() })
        }

        object {
            // Define mod id in a common place for everything to reference
            const val MODID = "forgottenmobs"
            private val LOGGER: Logger = LogUtils.getLogger()

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