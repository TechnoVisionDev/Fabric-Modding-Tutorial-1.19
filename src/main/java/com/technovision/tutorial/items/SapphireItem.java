package com.technovision.tutorial.items;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

/**
 * Advanced custom item class
 *
 * @author TechnoVision
 */
public class SapphireItem extends Item {

    public SapphireItem() {
        super(new FabricItemSettings().group(ItemGroup.MATERIALS));
    }

    /**
     * Runs anytime the item is right-clicked.
     */
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }

    /**
     * Turns a grass block into a lapis block when clicked.
     */
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        // Ensure client-side only
        World world = context.getWorld();
        PlayerEntity user = context.getPlayer();
        if (!world.isClient() && user != null) {
            // Check if block is grass
            Block block = world.getBlockState(context.getBlockPos()).getBlock();
            if (block == Blocks.GRASS_BLOCK) {
                // Set grass block to lapis block
                world.setBlockState(context.getBlockPos(), Blocks.LAPIS_BLOCK.getDefaultState());
                user.playSound(SoundEvents.BLOCK_WOOL_PLACE, 1.0f, 1.0f);
                user.getStackInHand(context.getHand()).decrement(1);
            }
        }
        return super.useOnBlock(context);
    }

    /**
     * Dyes a sheep blue when clicked
     */
    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        // Ensure client-side and main hand only
        if (!user.getWorld().isClient() && hand == Hand.MAIN_HAND) {
            // Check if entity is a sheep
            if (entity.getType() == EntityType.SHEEP) {
                // Set sheep color to blue and play sound
                SheepEntity sheep = (SheepEntity) entity;
                sheep.setColor(DyeColor.BLUE);
                user.playSound(SoundEvents.BLOCK_WOOL_PLACE, 1.0f, 1.0f);
                user.getStackInHand(hand).decrement(1);
            } else {
                user.sendMessage(Text.literal("Try using that item on a sheep ;)"));
            }
        }
        return super.useOnEntity(stack, user, entity, hand);
    }
}
