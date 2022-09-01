package net.yuziouo;

import cn.nukkit.plugin.PluginBase;

public class YRPGCore extends PluginBase {
    private Database database;//儲存資料庫
    @Override
    public void onEnable() {
        super.onEnable();
        database = new Database(this);
        database.initDatabase();
        database.initTable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public Database getDatabase() {
        return database;
    }
}
