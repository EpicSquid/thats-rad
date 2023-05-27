package dev.epicsquid.thatsrad.block;

import dev.epicsquid.thatsrad.ThatsRad;
import dev.epicsquid.thatsrad.item.ModCreativeModeTab;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)


public class ModBlocks {
    //deos things and adds it to the creative mode tab
    @SubscribeEvent
    public static void onRegisterItems(final RegisterEvent event) {
        if (event.getRegistryKey().equals(ForgeRegistries.Keys.ITEMS)){
            BLOCKS.getEntries().forEach( (blockRegistryObject) -> {
                Block block = blockRegistryObject.get();
                Item.Properties properties = new Item.Properties().tab(ModCreativeModeTab.THATSRADTAB);
                Supplier<Item> blockItemFactory = () -> new BlockItem(block, properties);
                event.register(ForgeRegistries.Keys.ITEMS, blockRegistryObject.getId(), blockItemFactory);
            });
        }
    }
    //more things maybe
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ThatsRad.ID);
//add blocks bellow to add blocks

    //Example block add
//    public static final RegistryObject<Block> CLEANSINGSTONE = BLOCKS.register("cleansingstone",
//            () -> new CleansingStone(Block.Properties.of(Material.STONE).strength(2f, 1200f)
//                    ));

}
