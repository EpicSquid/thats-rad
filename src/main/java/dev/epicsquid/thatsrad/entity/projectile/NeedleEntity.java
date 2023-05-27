//Adding Custom projectiles

//package dev.epicsquid.thatsrad.entity.projectile;
//
//import dev.epicsquid.thatsrad.entity.ModEntityTypes;
//import net.minecraft.world.entity.EntityType;
//import net.minecraft.world.entity.LivingEntity;
//import net.minecraft.world.entity.projectile.AbstractArrow;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.Level;
//
////custom projectile for NeedleBlightEntity
//public class NeedleEntity extends AbstractArrow {
//    private static final EntityType<? extends AbstractArrow> entityType =   ModEntityTypes.NEEDLE.get() ;
//
//    public NeedleEntity(EntityType<? extends AbstractArrow> entityType, Level world) {
//        super(entityType, world);
//        this.setBaseDamage(1.0F);
//    }
//
//    public NeedleEntity(EntityType<NeedleEntity> entityType, double x, double y, double z, Level world) {
//        super(entityType, x, y, z, world);
//        this.setBaseDamage(1.0F);
//    }
//
//    public NeedleEntity(EntityType<NeedleEntity> entityType, LivingEntity shooter, Level world) {
//        super(entityType, shooter, world);
//        this.setBaseDamage(1.0F);
//    }
//
//    public NeedleEntity(Level world, LivingEntity shooter) {
//        super(ModEntityTypes.NEEDLE.get(), shooter, world);
//        this.setBaseDamage(1.0F);
//    }
//
//
//    @Override
//    protected ItemStack getPickupItem() {
//        return ItemStack.EMPTY;
//    }
//}
//
