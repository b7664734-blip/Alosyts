package com.aysunmod.proxy;

import com.aysunmod.AysunMod;
import com.aysunmod.entity.EntityAysun;
import com.aysunmod.client.RenderAysun;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

public class ClientProxy extends CommonProxy {
    @Override
    public void registerRenderers() {
        RenderingRegistry.registerEntityRenderingHandler(EntityAysun.class,
            manager -> new RenderAysun(manager));
    }
}
