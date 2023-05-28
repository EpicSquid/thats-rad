package dev.epicsquid.thatsrad.entity.custom

import net.minecraft.core.BlockPos
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.animal.Animal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.builder.ILoopType.EDefaultLoopTypes
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory
import software.bernie.geckolib3.util.GeckoLibUtil

class BikeEntity(type: EntityType<out Animal?>?, worldIn: Level?) : Animal(type, worldIn), IAnimatable {
    private val factory = GeckoLibUtil.createFactory(this)
    private fun <E : IAnimatable?> predicate(event: AnimationEvent<E>): PlayState {
        event.controller.setAnimation(AnimationBuilder().addAnimation("animation.bike.idle", EDefaultLoopTypes.LOOP))
        return PlayState.CONTINUE
    }

    init {
        noCulling = true
    }

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        if (!this.isVehicle) {
            player.startRiding(this)
            return super.mobInteract(player, hand)
        }
        return super.mobInteract(player, hand)
    }

    override fun playStepSound(pos: BlockPos, blockIn: BlockState) {}
    override fun travel(pos: Vec3) {
        if (this.isAlive) {
            if (this.isVehicle) {
                val livingentity = this.controllingPassenger as LivingEntity?
                yRot = livingentity!!.yRot
                yRotO = yRot
                xRot = livingentity.xRot * 0.5f
                setRot(yRot, xRot)
                yBodyRot = yRot
                yHeadRot = yBodyRot
                val f = livingentity.xxa * 0.5f
                var f1 = livingentity.zza
                if (f1 <= 0.0f) {
                    f1 *= 0.25f
                }
                speed = 0.3f
                super.travel(Vec3(f.toDouble(), pos.y, f1.toDouble()))
            }
        }
    }

    override fun getControllingPassenger(): Entity? {
        return if (passengers.isEmpty()) null else passengers[0]
    }

    override fun isControlledByLocalInstance(): Boolean {
        return true
    }

    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(
            AnimationController(
                this,
                "controller",
                0f
            ) { event: AnimationEvent<BikeEntity> -> predicate(event) })
    }

    override fun getFactory(): AnimationFactory {
        return factory
    }

    override fun getBreedOffspring(p_241840_1_: ServerLevel, p_241840_2_: AgeableMob): AgeableMob? {
        return null
    }
}
