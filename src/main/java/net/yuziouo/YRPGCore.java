package net.yuziouo;

import cn.nukkit.plugin.PluginBase;
import net.yuziouo.GradeSystem.GradeListener;
import net.yuziouo.RPGSystem.RPGPlayer;

import java.util.HashMap;

public class YRPGCore extends PluginBase {
    private Database database;//儲存資料庫
    private static YRPGCore instance;
    private static HashMap<String, RPGPlayer> RPGPlayers;
    private LogWriter logWriter;//日誌編寫器
    @Override
    public void onEnable() {
        instance = this;
        logWriter = new LogWriter();
        super.onEnable();
        database = new Database(this);
        database.initDatabase();
        database.initTable();
        RPGPlayers = new HashMap<>();
        getServer().getPluginManager().registerEvents(new GradeListener(),this);
        logWriter.writeData("插件啟動");
    }

    @Override
    public void onDisable() {
        super.onDisable();
        logWriter.writeData("插件關閉");
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

    public LogWriter getLogWriter() {
        return logWriter;
    }
}
