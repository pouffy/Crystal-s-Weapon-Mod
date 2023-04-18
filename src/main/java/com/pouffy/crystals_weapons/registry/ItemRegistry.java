package com.pouffy.crystals_weapons.registry;

import com.pouffy.crystals_weapons.CrystalsWeapons;
import com.pouffy.crystals_weapons.common.tool.FishingRodSwordItem;
import com.pouffy.crystals_weapons.common.tool.ScytheItem;
import com.pouffy.crystals_weapons.common.tool.ModMaterials;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

@SuppressWarnings("unused")
public enum ItemRegistry {
    CRYSTALLINE_SCYTHE("crystalline_scythe", () -> new ScytheItem(ModMaterials.CRYSTALLINE)),
    CASTERS_CLEAVER("casters_cleaver", () -> new FishingRodSwordItem(0.1f, (new Item.Settings()).maxDamage(2500).group(ItemGroup.TOOLS))),
    SWEEPSTRIKE_RAPIER("sweepstrike_rapier", () -> new SwordItem(ToolMaterials.DIAMOND, 3, -2.4F, new Item.Settings().group(CrystalsWeapons.ITEM_GROUP))),
    ;


    private final String pathName;
    private final Supplier<Item> itemSupplier;
    private final Integer burnTime;
    private Item item;

    ItemRegistry(String pathName, Supplier<Item> itemSupplier) {
        this(pathName, itemSupplier, null);
    }

    ItemRegistry(String pathName, Supplier<Item> itemSupplier, Integer burnTime) {
        this.pathName = pathName;
        this.itemSupplier = itemSupplier;
        this.burnTime = burnTime;
    }

    public static void registerAll() {
        for (ItemRegistry value : values()) {
            Registry.register(Registry.ITEM, new Identifier(CrystalsWeapons.MOD_ID, value.pathName), value.get());
            if (value.burnTime != null && value.burnTime > 0) {
                FuelRegistry.INSTANCE.add(value.get(), value.burnTime);
            }
        }
    }

    public Item get() {
        if (item == null) {
            item = itemSupplier.get();
        }
        return item;
    }

    public String getId() {
        return Registry.ITEM.getId(get()).toString();
    }

}
