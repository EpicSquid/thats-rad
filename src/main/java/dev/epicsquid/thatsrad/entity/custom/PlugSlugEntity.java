package dev.epicsquid.thatsrad.entity.custom;//Exampple of Entity added

import dev.epicsquid.thatsrad.entity.ModEntityTypes;
import dev.epicsquid.thatsrad.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Chicken;
import net.minecraft.world.entity.animal.Pig;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class PlugSlugEntity extends Pig implements IAnimatable {
    AnimationFactory manager = GeckoLibUtil.createFactory(this);

    public PlugSlugEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 5;
    }
    public static final EntityDataAccessor<Boolean> TAMED = SynchedEntityData.defineId(PlugSlugEntity.class, EntityDataSerializers.BOOLEAN);
    public int tamingTime;
    
    public void setHeldStack(ItemStack stack) {
        this.setItemSlot(EquipmentSlot.MAINHAND, stack == null ? ItemStack.EMPTY : stack);
    }

    public ItemStack getHeldStack() {
        return this.getMainHandItem();
    }
    public PlugSlugEntity(Level world, boolean tamed) {
        this(ModEntityTypes.PLUGSLUG.get(), world);
        this.setTamed(tamed);
    }


    //sound events
    protected void playStepSound(BlockPos p_34316_, BlockState p_34317_) {
        this.playSound(SoundEvents.SLIME_SQUISH, 1.0F, 0.5F);
    }

    protected SoundEvent getHurtSound(DamageSource p_32527_) {
        return SoundEvents.SLIME_BLOCK_BREAK;
    }
    
    protected SoundEvent getDeathSound() {
        return SoundEvents.GHAST_DEATH;
    }

    protected float getSoundVolume() {
        return 1.F;
    }

    //Tame Logic
    public boolean isTamed() {
        return this.entityData.get(TAMED);
    }

    public void setTamed(boolean tamed) {
        this.entityData.set(TAMED, tamed);
    }

    //capture
public void attemptTame() {
    if (!isTamed() && this.getHeldStack().is(Tags.Items.SLIMEBALLS)) {
        tamingTime++;

        if (tamingTime > 60 && !level.isClientSide) {
            ItemStack stack = new ItemStack(ModItems.XXX.get(), 1 + level.random.nextInt(2));
            level.addFreshEntity(new ItemEntity(level, getX(), getY() + 0.5, getZ(), stack));
            level.playSound(null, getX(), getY(), getZ(), SoundEvents.ILLUSIONER_MIRROR_MOVE, SoundSource.NEUTRAL, 1f, 1f);
            this.remove(RemovalReason.DISCARDED);
        } else if (tamingTime > 55 && level.isClientSide) {
            for (int i = 0; i < 10; i++) {
                double d0 = getX();
                double d1 = getY() + 0.1;
                double d2 = getZ();
                level.addParticle(ParticleTypes.END_ROD, d0, d1, d2, (level.random.nextFloat() * 1 - 0.5) / 3, (level.random.nextFloat() * 1 - 0.5) / 3, (level.random.nextFloat() * 1 - 0.5) / 3);
            }
        }
    }
}

//Pick thing up
@Override
protected void pickUpItem(ItemEntity itemEntity) {
    if(!this.getHeldStack().isEmpty())
        return;
    if(!this.isTamed() && itemEntity.getItem().is(Tags.Items.SLIMEBALLS)){
        setHeldStack(itemEntity.getItem().split(1));
        return;
    }
    this.dynamicBehavior.pickUpItem(itemEntity);
}

    //animations
    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.plugslug.walk", true));
            return PlayState.CONTINUE;
        }
        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.plugslug.idle", true));
        return PlayState.CONTINUE;
    }

    private PlayState attackPredicate(AnimationEvent event) {
        if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
            event.getController().markNeedsReload();
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.plugslug.attack", false));
            this.swinging = false;
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));
        data.addAnimationController(new AnimationController(this, "attackController",
                0, this::attackPredicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return manager;
    }
}
