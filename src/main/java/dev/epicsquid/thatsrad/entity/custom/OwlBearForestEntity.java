//Exampple of Entity added


//package dev.epicsquid.thatsrad.entity.custom;
//
//import net.minecraft.core.BlockPos;
//import net.minecraft.nbt.CompoundTag;
//import net.minecraft.network.syncher.EntityDataAccessor;
//import net.minecraft.network.syncher.EntityDataSerializers;
//import net.minecraft.network.syncher.SynchedEntityData;
//import net.minecraft.sounds.SoundEvent;
//import net.minecraft.sounds.SoundEvents;
//import net.minecraft.util.RandomSource;
//import net.minecraft.util.VisibleForDebug;
//import net.minecraft.world.DifficultyInstance;
//import net.minecraft.world.SimpleContainer;
//import net.minecraft.world.damagesource.DamageSource;
//import net.minecraft.world.entity.*;
//import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
//import net.minecraft.world.entity.ai.attributes.Attributes;
//import net.minecraft.world.entity.ai.goal.EatBlockGoal;
//import net.minecraft.world.entity.ai.goal.FloatGoal;
//import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
//import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
//import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
//import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
//import net.minecraft.world.entity.animal.Animal;
//import net.minecraft.world.entity.animal.Chicken;
//import net.minecraft.world.entity.monster.CrossbowAttackMob;
//import net.minecraft.world.entity.monster.Monster;
//import net.minecraft.world.entity.monster.Slime;
//import net.minecraft.world.entity.monster.hoglin.Hoglin;
//import net.minecraft.world.entity.npc.InventoryCarrier;
//import net.minecraft.world.entity.player.Player;
//import net.minecraft.world.entity.projectile.Projectile;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.item.Items;
//import net.minecraft.world.item.ProjectileWeaponItem;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.block.state.BlockState;
//import software.bernie.geckolib3.core.AnimationState;
//import software.bernie.geckolib3.core.IAnimatable;
//import software.bernie.geckolib3.core.PlayState;
//import software.bernie.geckolib3.core.builder.AnimationBuilder;
//import software.bernie.geckolib3.core.controller.AnimationController;
//import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
//import software.bernie.geckolib3.core.manager.AnimationData;
//import software.bernie.geckolib3.core.manager.AnimationFactory;
//import software.bernie.geckolib3.util.GeckoLibUtil;
//
//public class OwlBearForestEntity extends Monster implements IAnimatable {
//    AnimationFactory manager = GeckoLibUtil.createFactory(this);
//
//    public OwlBearForestEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
//        super(pEntityType, pLevel);
//        this.xpReward = 80;
//    }
//
//
//    //stat block
//    public static AttributeSupplier setAttributes() {
//        return Monster.createMonsterAttributes()
//                .add(Attributes.MAX_HEALTH, 60.0D)
//                .add(Attributes.ATTACK_DAMAGE, 15.0D)
//                .add(Attributes.ATTACK_SPEED, 0.5F)
//                .add(Attributes.MOVEMENT_SPEED, 1.0F)
//                .add(Attributes.FOLLOW_RANGE, 48.0D)
//                .add(Attributes.ARMOR, 5.0D)
//                .build();
//    }
//
//    //ai goals
//    @Override
//    protected void registerGoals() {
//        this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 0.30F, false));
//        this.goalSelector.addGoal(1, new FloatGoal(this));
//        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 64.0F));
//
//        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
//        this.targetSelector.addGoal(2, new HurtByTargetGoal(this));
//        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Chicken.class, true));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Slime.class, true));
//
//    }
//
//    //sound events
//    protected void playStepSound(BlockPos p_34316_, BlockState p_34317_) {
//        this.playSound(SoundEvents.WOLF_STEP, 1.0F, 0.5F);
//    }
//
//    protected SoundEvent getHurtSound(DamageSource p_32527_) {
//        return SoundEvents.WOLF_HURT;
//    }
//
//    protected SoundEvent getAmbientSound() {
//        return SoundEvents.PARROT_AMBIENT;
//    }
//
//    protected SoundEvent getDeathSound() {
//        return SoundEvents.HOGLIN_DEATH;
//    }
//
//    protected float getSoundVolume() {
//        return 1.8F;
//    }
//
//    public boolean isSensitiveToWater() {
//        return false;
//    }
//
//    //animations
//    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
//        if (event.isMoving()) {
//            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.owlbear.walk", true));
//            return PlayState.CONTINUE;
//        }
//        event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.owlbear.idle", true));
//        return PlayState.CONTINUE;
//    }
//
//    private PlayState attackPredicate(AnimationEvent event) {
//        if (this.swinging && event.getController().getAnimationState().equals(AnimationState.Stopped)) {
//            event.getController().markNeedsReload();
//            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.owlbear.attack", false));
//            this.swinging = false;
//        }
//        return PlayState.CONTINUE;
//    }
//
//    @Override
//    public void registerControllers(AnimationData data) {
//        data.addAnimationController(new AnimationController(this, "controller",
//                0, this::predicate));
//        data.addAnimationController(new AnimationController(this, "attackController",
//                0, this::attackPredicate));
//    }
//
//    @Override
//    public AnimationFactory getFactory() {
//        return manager;
//    }
//
//
////when idle, regenerate health
//
//    @Override
//    public void aiStep() {
//        super.aiStep();
//        if (this.isAlive()) {
//            this.heal(1.0F);
//        }
//    }
//}
