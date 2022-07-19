package com.technovision.tutorial.registry;

import com.technovision.tutorial.TutorialMod;
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
    public static final Item RUBY = new Item(new Item.Settings().group(ItemGroup.MATERIALS).rarity(Rarity.UNCOMMON).maxCount(16));

    public static void registerItems() {
        Registry.register(Registry.ITEM, new Identifier(TutorialMod.MOD_ID, "ruby"), RUBY);
    }
}
