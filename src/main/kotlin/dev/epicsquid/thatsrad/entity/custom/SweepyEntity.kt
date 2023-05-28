package dev.epicsquid.thatsrad.entity.custom

import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.Mob
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.monster.Monster
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.builder.ILoopType
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory
import software.bernie.geckolib3.util.GeckoLibUtil

class SweepyEntity protected constructor(pEntityType: EntityType<out Mob?>?, pLevel: Level?) : Mob(pEntityType, pLevel),
    IAnimatable {
   private var factory = GeckoLibUtil.createFactory(this)

    //can collect items
    override fun canPickUpLoot(): Boolean {
        return true
    }

    //can hold items
    override fun canHoldItem(stack: ItemStack): Boolean {
        return true
    }

    //can transfer items into a chest
    override fun canBeLeashed(player: Player): Boolean {
        return true
    }

    //animation
    private fun <E : IAnimatable?> predicate(event: AnimationEvent<E>): PlayState {
        if (event.isMoving) {
            event.controller.setAnimation(AnimationBuilder().addAnimation("animation.sweepy.walk", ILoopType.EDefaultLoopTypes.LOOP))
            return PlayState.CONTINUE
        }
        event.controller.setAnimation(AnimationBuilder().addAnimation("animation.sweepy.idle", ILoopType.EDefaultLoopTypes.LOOP))
        return PlayState.CONTINUE
    }
    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(
            AnimationController(
                this,
                "controller",
                0f
            ) { event: AnimationEvent<SweepyEntity> -> predicate(event) })
    }

    override fun getFactory(): AnimationFactory {
        return factory
    }

    companion object {
        //attributes
        fun setAttributes(): AttributeSupplier {
            return Monster.createMonsterAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.ARMOR_TOUGHNESS, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 0.0)
                .add(Attributes.ATTACK_SPEED, 1.0)
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .build()
        }
    }
}
