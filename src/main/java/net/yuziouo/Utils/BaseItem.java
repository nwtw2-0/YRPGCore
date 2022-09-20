package net.yuziouo.Utils;

import cn.nukkit.item.Item;

public abstract class BaseItem {
    protected abstract boolean isWeapon();
    protected abstract boolean isUse();
    protected abstract boolean isArmor();
    protected String name;
    protected int id,meta;

    public BaseItem(String name, int id, int meta) {
        this.name = name;
        this.id = id;
        this.meta = meta;
    }

    protected Item toItem(){
        return Item.get(id,meta).setCustomName(name);
    }
}
