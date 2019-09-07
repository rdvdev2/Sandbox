package com.hrznstudio.sandbox.mixin.impl.entity;

import com.hrznstudio.sandbox.api.entity.ILivingEntity;
import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.*;

@Mixin(LivingEntity.class)
@Implements(@Interface(iface = ILivingEntity.class, prefix = "sbx$", remap = Interface.Remap.NONE))
@Unique
public abstract class MixinLivingEntity {
    @Shadow
    public abstract float getHealth();

    @Shadow
    public abstract void setHealth(float float_1);

    public void sbx$setHealth(float health) {
        this.setHealth(health);
    }

    public float sbx$getHealth() {
        return this.getHealth();
    }
}