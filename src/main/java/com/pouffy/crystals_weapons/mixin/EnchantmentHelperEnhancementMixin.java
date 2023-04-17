package com.pouffy.crystals_weapons.mixin;

import com.pouffy.crystals_weapons.CrystalsWeapons;
import com.pouffy.crystals_weapons.common.tool.ScytheItem;
import com.pouffy.crystals_weapons.registry.EnchantmentsRegistry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(EnchantmentHelper.class)
public class EnchantmentHelperEnhancementMixin {
    private EnchantmentHelperEnhancementMixin() {
        // Not instantiable class
    }
    @Inject(method = "getPossibleEntries", at = @At("RETURN"))
    private static void getPossibleEntriesEnhanced(int power, ItemStack stack, boolean treasureAllowed,
                                                   CallbackInfoReturnable<List<EnchantmentLevelEntry>> returnCallback) {
        List<EnchantmentLevelEntry> possibleEnchantmentList = returnCallback.getReturnValue();
        CrystalsWeapons.LOGGER.info("EnchantmentHelperEnhancementMixin");
        if (stack.getItem() instanceof ScytheItem) {
            ScytheItem.ALLOWED_ENCHANTMENTS.stream()
                    .filter(enchantment -> !containsEnchantment(possibleEnchantmentList, enchantment))
                    .forEach(enchantment -> addEntry(possibleEnchantmentList, power, enchantment));
            possibleEnchantmentList.removeIf(enchantmentLevelEntry ->
                    !ScytheItem.ALLOWED_ENCHANTMENTS.contains(enchantmentLevelEntry.enchantment));
        }
         else if (containsEnchantment(possibleEnchantmentList, EnchantmentsRegistry.REAPING.get())) {
            possibleEnchantmentList.removeIf(enchantmentLevelEntry -> enchantmentLevelEntry.enchantment == EnchantmentsRegistry.REAPING.get());
        }
    }

    @Unique
    private static void addEntry(List<EnchantmentLevelEntry> entries, int power, Enchantment enchantment) {
        for (int level = enchantment.getMaxLevel(); level >= enchantment.getMinLevel(); level--) {
            if (enchantment.getMinPower(level) <= power && power <= enchantment.getMaxPower(level)) {
                entries.add(new EnchantmentLevelEntry(enchantment, level));
                break;
            }
        }
    }

    @Unique
    private static boolean containsEnchantment(List<EnchantmentLevelEntry> entries, Enchantment enchantment) {
        return entries.stream().anyMatch(enchantmentLevelEntry -> enchantmentLevelEntry.enchantment == enchantment);
    }


}
