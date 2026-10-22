package com.aysunmod.client;

import com.aysunmod.AysunMod;
import com.aysunmod.entity.EntityAysun;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.util.ResourceLocation;

public class RenderAysun extends RenderBiped<EntityAysun> {
    private static final ResourceLocation SKIN =
        new ResourceLocation(AysunMod.MODID, "textures/entity/aysun.png");

    public RenderAysun(RenderManager manager) {
        super(manager, new ModelBiped(), 0.35F);
    }

    @Override
    protected ResourceLocation getEntityTexture(EntityAysun entity) {
        return SKIN;
    }
}
