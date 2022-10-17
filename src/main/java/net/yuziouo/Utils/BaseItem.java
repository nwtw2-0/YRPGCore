package net.yuziouo.Utils;

import cn.nukkit.item.Item;

public abstract class BaseItem {
    public static final String tagname = "BaseItem";
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
    public static boolean isBaseItem(Item item){
           return item.getNamedTag().contains(tagname);
    }
    protected Item toItem(){
        Item item = Item.get(id,meta);
        item.setCustomName(name);
        item.getNamedTag().putString(tagname,name);
        return item;
    }
}
