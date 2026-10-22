package com.aysunmod;

import com.aysunmod.entity.EntityAysun;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemAysunSpawnEgg extends Item {
    public ItemAysunSpawnEgg() {
        setMaxStackSize(16);
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos,
                                     EnumHand hand, net.minecraft.util.EnumFacing facing,
                                     float hitX, float hitY, float hitZ) {
        if (world.isRemote) return EnumActionResult.SUCCESS;
        BlockPos spawnPos = pos.offset(facing);
        EntityAysun aysun = new EntityAysun(world);
        aysun.setPosition(spawnPos.getX() + 0.5D, spawnPos.getY(), spawnPos.getZ() + 0.5D);
        if (world.spawnEntity(aysun) && !player.capabilities.isCreativeMode) {
            player.getHeldItem(hand).shrink(1);
        }
        return EnumActionResult.SUCCESS;
    }
}
