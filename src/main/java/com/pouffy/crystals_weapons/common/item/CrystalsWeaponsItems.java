package com.pouffy.crystals_weapons.common.item;

import com.pouffy.crystals_weapons.CrystalsWeapons;
import com.pouffy.crystals_weapons.common.tool.FishingRodSwordItem;
import com.pouffy.crystals_weapons.common.tool.ModMaterials;
import com.pouffy.crystals_weapons.common.tool.ScytheItem;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CrystalsWeaponsItems {
    public static final Item CRYSTALLINE_SCYTHE = registerItem("crystalline_scythe",
            new ScytheItem(ModMaterials.CRYSTALLINE));
    public static final Item CASTERS_CLEAVER = registerItem("casters_cleaver",
            new FishingRodSwordItem(0.1f, (new Item.Settings()).maxDamage(2500).group(ItemGroup.TOOLS)));
    public static final Item SWEEPSTRIKE_RAPIER = registerItem("sweepstrike_rapier",
            new SwordItem(ToolMaterials.DIAMOND, 3, -2.4F, new Item.Settings()));



    private static Item registerItem(String name, Item item) {
        return Registry.register(Registry.ITEM, new Identifier(CrystalsWeapons.MOD_ID, name), item);
    }

    private static Item registerItemWithFuel(String name, int fuelTime, Item item) {
        Registry.register(Registry.ITEM, new Identifier(CrystalsWeapons.MOD_ID, name), item);
        FuelRegistry.INSTANCE.add(item, fuelTime);
        return item;
    }

    public static void registerModItems() {
        CrystalsWeapons.LOGGER.info("Registering " + CrystalsWeapons.MOD_ID + " Mod items");
    }
}
