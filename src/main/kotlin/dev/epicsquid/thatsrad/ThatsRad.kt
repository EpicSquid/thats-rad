package dev.epicsquid.thatsrad

import net.minecraft.client.Minecraft
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import org.apache.logging.log4j.Level
import org.apache.logging.log4j.LogManager
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist

@Mod(ThatsRad.ID)
object ThatsRad {
	const val ID = "thatsrad"

	val LOGGER = LogManager.getLogger(ID)

	init {
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

	/**
	 * This is used for initializing client specific
	 * things such as renderers and keymaps
	 * Fired on the mod specific event bus.
	 */
	private fun onClientSetup(event: FMLClientSetupEvent) {
		LOGGER.log(Level.INFO, "Initializing client...")
	}

	/**
	 * Fired on the global Forge bus.
	 */
	private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
		LOGGER.log(Level.INFO, "Server starting...")
	}
}