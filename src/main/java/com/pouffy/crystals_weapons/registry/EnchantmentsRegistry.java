package com.pouffy.crystals_weapons.registry;

import com.pouffy.crystals_weapons.CrystalsWeapons;
import com.pouffy.crystals_weapons.common.enchantment.ReapingEnchantment;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.function.Supplier;

public enum EnchantmentsRegistry {
    REAPING("reaping", ReapingEnchantment::new);

    private final String pathName;
    private final Supplier<? extends Enchantment> enchantmentSupplier;
    private Enchantment enchantment;

    EnchantmentsRegistry(String pathName, Supplier<? extends Enchantment> enchantmentSupplier) {
        this.pathName = pathName;
        this.enchantmentSupplier = enchantmentSupplier;
    }

    public static void registerAll() {
        for (EnchantmentsRegistry value : values()) {
            Registry.register(Registry.ENCHANTMENT, new Identifier(CrystalsWeapons.MOD_ID, value.pathName), value.get());
        }
    }

    public Enchantment get() {
        if (enchantment == null) {
            enchantment = enchantmentSupplier.get();
        }

        return enchantment;
    }
}
