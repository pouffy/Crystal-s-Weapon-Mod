package com.pouffy.crystals_weapons.common.item;

import com.pouffy.crystals_weapons.CrystalsWeapons;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class ModItemSettings extends FabricItemSettings {

    public static FabricItemSettings base() {
        return new ModItemSettings();
    }

    public static FabricItemSettings noStack() {
        return new ModItemSettings().maxCount(1);
    }


    public ModItemSettings() {
        super();
        group(CrystalsWeapons.ITEM_GROUP);
    }
}
