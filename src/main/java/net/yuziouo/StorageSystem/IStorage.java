package net.yuziouo.StorageSystem;

import cn.nukkit.Player;

public interface IStorage {
    void save(Player player);
    void read(Player player);
}
