package net.yuziouo;

import cn.nukkit.Player;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.plugin.service.RegisteredServiceProvider;
import com.nukkitx.fakeinventories.inventory.FakeInventories;
import net.yuziouo.PlayerSystem.GradeSystem.GradeCommand;
import net.yuziouo.PlayerSystem.GradeSystem.GradeListener;
import net.yuziouo.PlayerSystem.RPGPlayer;
import net.yuziouo.StorageSystem.Storage;
import net.yuziouo.TownSystem.Town;
import net.yuziouo.TownSystem.TownCommand;
import net.yuziouo.TownSystem.TownListener;

import java.util.HashMap;

public class YRPGCore extends PluginBase {
    private static YRPGCore instance;
    private static HashMap<Player, RPGPlayer> RPGPlayers;
    private static HashMap<String,Storage> storageHashMap;
    private LogWriter logWriter;//日誌編寫器

    {
        instance = this;
    }
    @Override
    public void onEnable() {
        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") == null){
            getLogger().error("請先安裝前置插件 PlaceholderAPI");
            getPluginLoader().disablePlugin(this);
        }
        RegisteredServiceProvider<FakeInventories> provider = getServer().getServiceManager().getProvider(FakeInventories.class);

        if (provider == null || provider.getProvider() == null) {
            getLogger().error("請先安裝前置插件 FakeInventories");
            this.getServer().getPluginManager().disablePlugin(this);
        }
        logWriter = new LogWriter();
        storageHashMap = new HashMap<>();
        super.onEnable();
        RPGPlayers = new HashMap<>();
        initStorageMap();
        getServer().getCommandMap().register("",new GradeCommand("grade","等級指令","grade"));
        getServer().getCommandMap().register("town",new TownCommand("town"));
        getServer().getPluginManager().registerEvents(new GradeListener(),this);
        getServer().getPluginManager().registerEvents(new RegisterPlayerListener(),this);
        getServer().getPluginManager().registerEvents(new TownListener(),this);
        logWriter.writeData("插件啟動");
        Town.load();
    }

    @Override
    public void onDisable() {
        super.onDisable();
        Town.towns.forEach(Town::save);
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

    public static HashMap<String, Storage> getStorageHashMap() {
        return storageHashMap;
    }
    public static Storage getTownStorage(){
        return storageHashMap.get("town");
    }
    public void initStorageMap(){
        storageHashMap.put("player",new Storage(getDataFolder().toPath().resolve("玩家資料.yml")));
        storageHashMap.put("town",new Storage(getDataFolder().toPath().resolve("城市資料.yml")));
    }
    public Storage getPlayerStorage(){
        return storageHashMap.get("player");
    }
}
