package com.pouffy.crystals_weapons.common.tag;

import com.pouffy.crystals_weapons.CrystalsWeapons;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.impl.tag.convention.TagRegistration;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;

public class ModItemTags {
    public static final TagKey<Item> SCYTHES = TagRegistration.ITEM_TAG_REGISTRATION.registerCommon("tools/scythes");

    public static final TagKey<Block> REAPING_WORTHY = create("reapable", Registry.BLOCK_KEY);
    private static <E> TagKey<E> create(String pathName, RegistryKey<Registry<E>> registry) {
        return TagKey.of(registry, new Identifier(CrystalsWeapons.MOD_ID, pathName));
    }

    private ModItemTags() throws InstantiationException {
        throw new InstantiationException("Constant class cannot be instantiate");
    }
}
