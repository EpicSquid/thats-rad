package dev.epicsquid.thatsrad.entity.client.model;

import dev.epicsquid.thatsrad.ThatsRad;
import dev.epicsquid.thatsrad.entity.custom.SweepyEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class SweepyModel extends AnimatedGeoModel<SweepyEntity> {
    @Override
    public ResourceLocation getModelLocation(SweepyEntity object) {
        return new ResourceLocation(ThatsRad.ID, "geo/sweepy.geo.json");
    }
    @Override
    public ResourceLocation getTextureLocation(SweepyEntity object) {
        return new ResourceLocation(ThatsRad.ID, "textures/entity/sweepy.png");
    }
    @Override
    public ResourceLocation getAnimationFileLocation(SweepyEntity animatable) {
        return new ResourceLocation(ThatsRad.ID, "animations/sweepy.animation.json");
    }
}
