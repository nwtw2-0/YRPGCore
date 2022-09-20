package net.yuziouo.PropsSystem;

import cn.nukkit.Player;
import net.yuziouo.Utils.BaseItem;

public abstract class PropsItem extends BaseItem {
    public PropsItem(String name, int id, int meta) {
        super(name, id, meta);
    }

    @Override
    protected boolean isWeapon() {
        return false;
    }

    @Override
    protected boolean isUse() {
        return true;
    }

    @Override
    protected boolean isArmor() {
        return false;
    }
    abstract void onActive(Player player);
}
