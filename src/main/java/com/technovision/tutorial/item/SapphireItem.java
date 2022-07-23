package com.technovision.tutorial.item;

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
        // Check if block is grass
        World world = context.getWorld();
        Block block = world.getBlockState(context.getBlockPos()).getBlock();
        if (block == Blocks.GRASS_BLOCK) {
            if (!world.isClient()) {
                // Update block and item stack on server
                world.setBlockState(context.getBlockPos(), Blocks.LAPIS_BLOCK.getDefaultState());
                context.getPlayer().getStackInHand(context.getHand()).decrement(1);
            } else {
                // Play sound on client
                context.getPlayer().playSound(SoundEvents.BLOCK_WOOL_PLACE, 1.0f, 1.0f);
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
                if (!user.isCreative()) {
                    user.getStackInHand(hand).decrement(1);
                }
            } else {
                // Send message in chat
                user.sendMessage(Text.literal("Try using that item on a sheep ;)"));
            }
        }
        return super.useOnEntity(stack, user, entity, hand);
    }
}
