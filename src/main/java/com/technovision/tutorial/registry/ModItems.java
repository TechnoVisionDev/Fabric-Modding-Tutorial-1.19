package com.technovision.tutorial.registry;

import com.technovision.tutorial.TutorialMod;
import com.technovision.tutorial.item.SapphireItem;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

/**
 * Creates and registers our custom items to the game.
 *
 * @author TechnoVision
 */
public class ModItems {

    //Items
    public static final Item RUBY = new Item(new FabricItemSettings().group(ItemGroup.MATERIALS).rarity(Rarity.UNCOMMON).maxCount(16));
    public static final SapphireItem SAPPHIRE = new SapphireItem();

    //Block Items
    public static final BlockItem RUBY_BLOCK = new BlockItem(ModBlocks.RUBY_BLOCK, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(TutorialMod.MOD_ID, "ruby"), RUBY);
        Registry.register(Registry.ITEM, new Identifier(TutorialMod.MOD_ID, "sapphire"), SAPPHIRE);
        Registry.register(Registry.ITEM, new Identifier(TutorialMod.MOD_ID, "ruby_block"), RUBY_BLOCK);
    }
}
