package com.p1nero.hm.epicfight.weapon;

import yesman.epicfight.world.capabilities.item.WeaponCategory;

public enum PiPaWeaponCategories implements WeaponCategory {
    WSFH;
    private PiPaWeaponCategories(){
        this.id = WeaponCategory.ENUM_MANAGER.assign(this);
    }
    final int id;
    @Override
    public int universalOrdinal() {
        return this.id;
    }
}
