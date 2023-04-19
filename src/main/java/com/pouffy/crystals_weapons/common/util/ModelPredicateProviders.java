package com.pouffy.crystals_weapons.common.util;

import com.pouffy.crystals_weapons.common.item.CrystalsWeaponsItems;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.item.Item;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;


public class ModelPredicateProviders {

    public static void registerAll() {
        registerModelPredicateProvider(CrystalsWeaponsItems.CASTERS_CLEAVER);
    }

    private static void registerModelPredicateProvider(Item item) {
        ModelPredicateProviderRegistry.register(item, new Identifier("cast"), (itemStack, clientWorld, livingEntity, seed) -> {
            if (livingEntity == null) return 0.0f;
            if (itemStack.getItem() != item) return 0.0f;

            NbtCompound nbt = itemStack.getOrCreateNbt();

            if (nbt.contains("cast")) {
                return nbt.getFloat("cast");
            }

            return 1.0f;
        });
    }
}
