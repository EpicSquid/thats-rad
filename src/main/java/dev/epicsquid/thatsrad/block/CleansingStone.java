//Advanced Block Exampe

//package dev.epicsquid.thatsrad.block;
//
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.InteractionResult;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.level.block.Block;
//import net.minecraft.world.level.block.state.BlockState;
//import net.minecraft.world.phys.AABB;
//
//import java.util.Random;
//
//public class CleansingStone extends Block {
//    public CleansingStone(Properties properties) {
//        super(properties);
//    }
//
//    //when the player interacts the block, they are cleared of all negative mob effects
//    @Override
//    public InteractionResult use(BlockState state, net.minecraft.world.level.Level worldIn, BlockPos pos, Player player,
//                                 net.minecraft.world.InteractionHand handIn,
//                                 net.minecraft.world.phys.BlockHitResult hit) {
//        player.clearFire();
//        player.removeAllEffects();
//        return InteractionResult.SUCCESS;
//    }
//
//}