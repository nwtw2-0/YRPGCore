package net.yuziouo;

import cn.nukkit.Player;
import cn.nukkit.plugin.PluginBase;
import net.yuziouo.GradeSystem.GradeListener;
import net.yuziouo.RPGSystem.RPGPlayer;
import net.yuziouo.StorageSystem.GradeStorage;

import java.util.HashMap;

public class YRPGCore extends PluginBase {
    private static YRPGCore instance;
    private static HashMap<Player, RPGPlayer> RPGPlayers;
    private LogWriter logWriter;//日誌編寫器
    {
        instance = this;
    }
    @Override
    public void onEnable() {
        logWriter = new LogWriter();
        super.onEnable();
        RPGPlayers = new HashMap<>();
        getServer().getPluginManager().registerEvents(new GradeListener(),this);
        getServer().getPluginManager().registerEvents(new RegisterPlayerListener(),this);
        logWriter.writeData("插件啟動");
        registerStorage();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        logWriter.writeData("插件關閉");
    }
    public static YRPGCore getInstance() {
        return instance;
    }

    public static HashMap<Player, RPGPlayer> getRPGPlayers() {
        return RPGPlayers;
    }

    public LogWriter getLogWriter() {
        return logWriter;
    }
    private void registerStorage(){
        new GradeStorage();
    }
}
