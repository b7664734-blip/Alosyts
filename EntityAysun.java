package com.aysunmod.entity;

import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.world.World;

/** Aysun NPC for Minecraft Java 1.12.2 / Forge. */
public class EntityAysun extends EntityCreature {
    private boolean sitting = false;
    private int chatIndex = 0;

    private static final String[] DIALOGUE = new String[] {
        "Merhaba! Ben Aysun. 😊",
        "Bugün hava çok güzel!",
        "Biraz dinlenmek ister misin?",
        "Evimizi birlikte dekore edebiliriz!",
        "Buradayım, ne yapmak istersin?"
    };

    public EntityAysun(World world) {
        super(world);
        setSize(0.6F, 1.8F);
    }

    @Override
    protected void initEntityAI() {
        tasks.addTask(0, new EntityAISwimming(this));
        tasks.addTask(5, new EntityAIWander(this, 0.6D));
        tasks.addTask(6, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        tasks.addTask(7, new EntityAILookIdle(this));
    }

    @Override
    protected void applyEntityAttributes() {
        super.applyEntityAttributes();
        getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
        getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(20.0D);
    }

    @Override
    public boolean processInteract(EntityPlayer player, EnumHand hand) {
        if (world.isRemote) return true;

        if (player.isSneaking()) {
            sitting = !sitting;
            setNoAI(sitting);
            if (sitting) {
                player.sendMessage(new TextComponentString("§dAysun: §fTamam, burada oturup dinleniyorum. (Tekrar eğilerek sağ tıkla: kalk)"));
            } else {
                player.sendMessage(new TextComponentString("§dAysun: §fTamam, kalktım!"));
            }
            return true;
        }

        player.sendMessage(new TextComponentString("§6§l— AYSUN —"));
        player.sendMessage(new TextComponentString("§eSağ tık: §fKonuş"));
        player.sendMessage(new TextComponentString("§eEğil + sağ tık: §fOtur / kalk"));
        player.sendMessage(new TextComponentString("§dAysun: §f" + DIALOGUE[chatIndex]));
        chatIndex = (chatIndex + 1) % DIALOGUE.length;
        return true;
    }

    @Override
    public boolean getCanSpawnHere() {
        return super.getCanSpawnHere();
    }
}
