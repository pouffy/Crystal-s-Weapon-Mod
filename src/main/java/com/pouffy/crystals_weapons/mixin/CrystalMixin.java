package com.pouffy.crystals_weapons.mixin;

import com.pouffy.crystals_weapons.CrystalsWeapons;
import net.minecraft.client.gui.screen.TitleScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class CrystalMixin {
	@Inject(at = @At("HEAD"), method = "init()V")
	private void init(CallbackInfo info) {
		CrystalsWeapons.LOGGER.info("This line is printed by an example mod mixin!");
	}
}
