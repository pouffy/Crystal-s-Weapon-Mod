package com.pouffy.crystals_weapons;

import com.pouffy.crystals_weapons.registry.EnchantmentsRegistry;
import com.pouffy.crystals_weapons.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrystalsWeapons implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "crystals_weapons";
	public static final Logger LOGGER = LoggerFactory.getLogger("crystals_weapons");
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "main"),
			() -> new ItemStack(ItemRegistry.CRYSTALLINE_SCYTHE.get()));

	public static TranslatableText i18n(String key, Object... args) {
		return new TranslatableText(MOD_ID + "." + key, args);
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ItemRegistry.registerAll();
		EnchantmentsRegistry.registerAll();

		LOGGER.info("Hello Fabric world!");
	}
}
