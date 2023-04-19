package com.pouffy.crystals_weapons;

import com.pouffy.crystals_weapons.common.item.CrystalsWeaponsItems;
import com.pouffy.crystals_weapons.common.util.ModelPredicateProviders;
import com.pouffy.crystals_weapons.registry.ItemRegistry;
import dev.architectury.event.events.client.ClientTooltipEvent;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Identifier;

import static com.pouffy.crystals_weapons.registry.ItemRegistry.CASTERS_CLEAVER;

@Environment(EnvType.CLIENT)
public class CrystalsWeaponsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ModelPredicateProviders.registerAll();
    }
}
