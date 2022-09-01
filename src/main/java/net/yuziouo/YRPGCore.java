package net.yuziouo;

import cn.nukkit.plugin.PluginBase;
import net.yuziouo.RPGSystem.RPGPlayer;

import java.util.HashMap;

public class YRPGCore extends PluginBase {
    private Database database;//儲存資料庫
    private static YRPGCore instance;
    private static HashMap<String, RPGPlayer> RPGPlayers;
    @Override
    public void onEnable() {
        instance = this;
        super.onEnable();
        database = new Database(this);
        database.initDatabase();
        database.initTable();
        RPGPlayers = new HashMap<>();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    public Database getDatabase() {
        return database;
    }

    public static YRPGCore getInstance() {
        return instance;
    }

    public static HashMap<String, RPGPlayer> getRPGPlayers() {
        return RPGPlayers;
    }
}
