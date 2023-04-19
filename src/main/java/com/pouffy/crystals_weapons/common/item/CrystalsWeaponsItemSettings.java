package com.pouffy.crystals_weapons.common.item;

import com.pouffy.crystals_weapons.CrystalsWeapons;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;

public class CrystalsWeaponsItemSettings extends FabricItemSettings {

    public static FabricItemSettings base() {
        return new CrystalsWeaponsItemSettings();
    }

    public static FabricItemSettings noStack() {
        return new CrystalsWeaponsItemSettings().maxCount(1);
    }


    public CrystalsWeaponsItemSettings() {
        super();
        group(CrystalsWeapons.ITEM_GROUP);
    }
}
