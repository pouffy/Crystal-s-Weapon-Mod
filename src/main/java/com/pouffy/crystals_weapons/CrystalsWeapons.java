package com.pouffy.crystals_weapons;

import com.pouffy.crystals_weapons.common.item.CrystalsWeaponsItems;
import com.pouffy.crystals_weapons.registry.EnchantmentsRegistry;
import com.pouffy.crystals_weapons.registry.ItemRegistry;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CrystalsWeapons implements ModInitializer {
	public static final String MOD_ID = "crystals_weapons";
	public static final Logger LOGGER = LoggerFactory.getLogger("crystals_weapons");
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "main"),
			() -> new ItemStack(ItemRegistry.CRYSTALLINE_SCYTHE.get()));

	@Override
	public void onInitialize() {
		CrystalsWeaponsItems.registerModItems();
		EnchantmentsRegistry.registerAll();
	}

	/**
	 * Only displays the LOGGER text in a development environment
	 * @param text Input for the Logger
	 */
	public static void devLogger(String text) {
		if (!FabricLoader.getInstance().isDevelopmentEnvironment()) return;
		CrystalsWeapons.LOGGER.info("DEV - [" + text + "]");
	}
}
