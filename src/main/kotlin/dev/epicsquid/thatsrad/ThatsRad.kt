package dev.epicsquid.thatsrad

import com.tterrag.registrate.Registrate
import dev.epicsquid.thatsrad.IsItRad.MODID
import dev.epicsquid.thatsrad.registery.BlockInit
import dev.epicsquid.thatsrad.data.ThatsRadTags
import dev.epicsquid.thatsrad.registery.ItemInit
import dev.epicsquid.thatsrad.item.ModItems
import net.minecraft.world.item.CreativeModeTab
import net.minecraft.world.item.ItemStack
import net.minecraftforge.common.data.ForgeBlockTagsProvider
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext
import software.bernie.geckolib3.GeckoLib
import javax.annotation.Nonnull

@Mod(ThatsRad.ID)
class ThatsRad {
	companion object {
		const val ID = "thatsrad"

		val registrate by lazy { Registrate.create(MODID).creativeModeTab { tab } }

		val tab: CreativeModeTab = object : CreativeModeTab(MODID) {
			@Nonnull
			override fun makeIcon(): ItemStack {
				return ItemStack(ModItems.PLUGSLUG.get())
			}
		}
	}

	init {

		FMLJavaModLoadingContext.get().modEventBus.addListener { event: GatherDataEvent -> gatherData(event) }

		ItemInit.classload()
		BlockInit.classload()
//        ModEntityTypes.register(modEventBus)
		GeckoLib.initialize()

	}

	private fun gatherData(event: GatherDataEvent) {
		val generator = event.generator
		val blockTagsProvider = ForgeBlockTagsProvider(generator, event.existingFileHelper)
		generator.addProvider(event.includeServer(), ThatsRadTags(generator, blockTagsProvider, event.existingFileHelper))

	}
}