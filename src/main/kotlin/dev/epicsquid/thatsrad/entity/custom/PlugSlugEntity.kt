package dev.epicsquid.thatsrad.entity.custom

import dev.epicsquid.thatsrad.entity.ModEntityTypes
import dev.epicsquid.thatsrad.entity.custom.PlugSlugEntity
import dev.epicsquid.thatsrad.item.ModItems
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.animal.Pig
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.state.BlockState
import net.minecraftforge.common.Tags
import software.bernie.example.entity.TestEntity
import software.bernie.geckolib3.core.IAnimatable
import software.bernie.geckolib3.core.PlayState
import software.bernie.geckolib3.core.builder.AnimationBuilder
import software.bernie.geckolib3.core.builder.ILoopType
import software.bernie.geckolib3.core.controller.AnimationController
import software.bernie.geckolib3.core.controller.AnimationController.IAnimationPredicate
import software.bernie.geckolib3.core.event.predicate.AnimationEvent
import software.bernie.geckolib3.core.manager.AnimationData
import software.bernie.geckolib3.core.manager.AnimationFactory
import software.bernie.geckolib3.util.GeckoLibUtil

//Exampple of Entity added
class PlugSlugEntity(pEntityType: EntityType<out Pig?>?, pLevel: Level?) : Pig(pEntityType, pLevel), IAnimatable {
    var factory = GeckoLibUtil.createFactory(this)
    var tamingTime = 0

    init {
        xpReward = 5
    }

    var heldStack: ItemStack?
        get() = this.mainHandItem
        set(stack) {
            setItemSlot(EquipmentSlot.MAINHAND, stack ?: ItemStack.EMPTY)
        }

    constructor(world: Level?, tamed: Boolean) : this(ModEntityTypes.PLUGSLUG.get(), world) {
        isTamed = tamed
    }

    //sound events
    override fun playStepSound(p_34316_: BlockPos, p_34317_: BlockState) {
        this.playSound(SoundEvents.SLIME_SQUISH, 1.0f, 0.5f)
    }

    override fun getHurtSound(p_32527_: DamageSource): SoundEvent? {
        return SoundEvents.SLIME_BLOCK_BREAK
    }

    override fun getDeathSound(): SoundEvent? {
        return SoundEvents.GHAST_DEATH
    }

    override fun getSoundVolume(): Float {
        return 1f
    }

    var isTamed: Boolean
        //Tame Logic
        get() = entityData.get(TAMED)
        set(tamed) {
            entityData.set(TAMED, tamed)
        }

    //capture
    fun attemptTame() {
        if (!isTamed && heldStack!!.`is`(Tags.Items.SLIMEBALLS)) {
            tamingTime++
            if (tamingTime > 60 && !level.isClientSide) {
                val stack = ItemStack(ModItems.XXX.get(), 1 + level.random.nextInt(2))
                level.addFreshEntity(ItemEntity(level, x, y + 0.5, z, stack))
                level.playSound(null, x, y, z, SoundEvents.ILLUSIONER_MIRROR_MOVE, SoundSource.NEUTRAL, 1f, 1f)
                this.remove(RemovalReason.DISCARDED)
            } else if (tamingTime > 55 && level.isClientSide) {
                for (i in 0..9) {
                    val d0 = x
                    val d1 = y + 0.1
                    val d2 = z
                    level.addParticle(
                        ParticleTypes.END_ROD,
                        d0,
                        d1,
                        d2,
                        (level.random.nextFloat() * 1 - 0.5) / 3,
                        (level.random.nextFloat() * 1 - 0.5) / 3,
                        (level.random.nextFloat() * 1 - 0.5) / 3
                    )
                }
            }
        }
    }

    //Pick thing up
    override fun pickUpItem(itemEntity: ItemEntity) {
        if (!heldStack!!.isEmpty) return
        if (!isTamed && itemEntity.item.`is`(Tags.Items.SLIMEBALLS)) {
            heldStack = itemEntity.item.split(1)
            return
        }
    }

    //animations
    private fun <E : IAnimatable?> predicate(event: AnimationEvent<E>): PlayState {
            if (event.isMoving) {
            event.controller.setAnimation(AnimationBuilder().addAnimation("animation.plugslug.walk", ILoopType.EDefaultLoopTypes.LOOP))
            return PlayState.CONTINUE
        }
        event.controller.setAnimation(AnimationBuilder().addAnimation("animation.plugslug.idle", ILoopType.EDefaultLoopTypes.LOOP))
        return PlayState.CONTINUE
    }
    override fun registerControllers(data: AnimationData) {
        data.addAnimationController(
            AnimationController(
                this,
                "controller",
                0f
            ) { event: AnimationEvent<PlugSlugEntity> -> predicate(event) })
    }

    override fun getFactory(): AnimationFactory {
        return factory
    }

    companion object {
        val TAMED = SynchedEntityData.defineId(
            PlugSlugEntity::class.java, EntityDataSerializers.BOOLEAN
        )
    }
}
