package com.technovision.tutorial;

import com.technovision.tutorial.registry.ModBlocks;
import com.technovision.tutorial.registry.ModItems;
import net.fabricmc.api.ModInitializer;

/**
 * Main class where our mod is initialized.
 *
 * @author TechnoVision
 */
public class TutorialMod implements ModInitializer {

    public static final String MOD_ID = "tutorial";

    @Override
    public void onInitialize() {
        ModItems.registerItems();
        ModBlocks.registerBlocks();
    }
}
