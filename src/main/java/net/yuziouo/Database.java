package net.yuziouo;

import cn.nukkit.Player;

import java.sql.*;

public class Database {
    private final YRPGCore plugin;//插件讀取器
    private Connection connection;//SQL連接

    public Database(YRPGCore plugin) {
        this.plugin = plugin;
    }

    public YRPGCore getPlugin() {
        return plugin;
    }

    /**
     * 連接伺服器至DataBase
     */
    public void initDatabase() {
        try {
            String dataFolder = plugin.getDataFolder().getAbsolutePath()+".db";//DataBase路徑
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            plugin.getLogger().info("SQLite連接成功");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    /**
     * 連接到Table上
     */
    public void initTable(){
        String createTableSQL = "CREATE TABLE IF NOT EXISTS grade "
                                +"(player TEXT PRIMARY KEY NOT NULL,"
                                +" lv INT NOT NULL,"
                                +" exp INT NOT NULL)";
        try {
            Statement stsm = connection.createStatement();
            stsm.executeUpdate(createTableSQL);
            stsm.close();
            plugin.getLogger().info("Table連接成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param player 傳入玩家
     * @return 是否在Databaase中找尋到玩家
     */
    public boolean existPlayer(Player player){
        String selectSQL = "Select * From grade WHERE player = '"+player.getLoginChainData().getXUID()+"'";
        try {
            PreparedStatement stsm = connection.prepareStatement(selectSQL);
            ResultSet set = stsm.executeQuery();
            return set.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
