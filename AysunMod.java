package com.aysunmod;

import com.aysunmod.entity.EntityAysun;
import com.aysunmod.proxy.CommonProxy;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@Mod(modid = AysunMod.MODID, name = AysunMod.NAME, version = AysunMod.VERSION)
@EventBusSubscriber(modid = AysunMod.MODID)
public class AysunMod {
    public static final String MODID = "aysunmod";
    public static final String NAME = "Aysun Mod";
    public static final String VERSION = "0.1.1";

    @SidedProxy(clientSide = "com.aysunmod.proxy.ClientProxy",
                serverSide = "com.aysunmod.proxy.CommonProxy")
    public static CommonProxy proxy;

    public static Item AYSUN_SPAWN_EGG;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        EntityRegistry.registerModEntity(
            new ResourceLocation(MODID, "aysun"),
            EntityAysun.class, "Aysun", 1, this, 64, 3, true, 0xE6B89C, 0x5A3448
        );
        proxy.registerRenderers();
    }

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {
        AYSUN_SPAWN_EGG = new ItemAysunSpawnEgg();
        AYSUN_SPAWN_EGG.setRegistryName(MODID, "aysun_spawn_egg");
        AYSUN_SPAWN_EGG.setUnlocalizedName(MODID + ".aysun_spawn_egg");
        AYSUN_SPAWN_EGG.setCreativeTab(net.minecraft.creativetab.CreativeTabs.MISC);
        event.getRegistry().register(AYSUN_SPAWN_EGG);
    }
}
