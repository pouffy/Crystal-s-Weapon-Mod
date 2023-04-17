package com.pouffy.crystals_weapons.mixin;

import com.pouffy.crystals_weapons.CrystalsWeapons;
import com.pouffy.crystals_weapons.common.tool.ScytheItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentEnhancementMixin {
    @Inject(method = "isAcceptableItem", at = @At("RETURN"), cancellable = true)
    private void getPossibleEntriesEnhanced(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
        boolean canBeEnchanted = false;
        CrystalsWeapons.LOGGER.info("EnchantmentEnhancementMixin");
        if (stack.getItem() instanceof ScytheItem) {
            for (Enchantment enchantment : ScytheItem.ALLOWED_ENCHANTMENTS) {
                if ((Object)this == enchantment) {
                    canBeEnchanted = true;
                    break;
                }
            }
            cir.setReturnValue(canBeEnchanted);
        }
    }
}
