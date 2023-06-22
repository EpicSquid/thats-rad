package dev.epicsquid.thatsrad.data

import dev.epicsquid.thatsrad.ThatsRad
import net.minecraft.data.DataGenerator
import net.minecraft.data.tags.BlockTagsProvider
import net.minecraft.data.tags.ItemTagsProvider
import net.minecraftforge.common.data.ExistingFileHelper

class ThatsRadTags (dataGenerator: DataGenerator,
                    blockTagsProvider: BlockTagsProvider,
                    existingFileHelper: ExistingFileHelper
	) : ItemTagsProvider(dataGenerator, blockTagsProvider, ThatsRad.ID, existingFileHelper) {

	}