package net.yuziouo;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import net.yuziouo.PlayerSystem.GradeSystem.Grade;
import net.yuziouo.PlayerSystem.RPGPlayer;
import net.yuziouo.StorageSystem.StorageType;

public class RegisterPlayerListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerJoinEvent(PlayerJoinEvent event) {
        registerPlayer(event.getPlayer());
    }

    private void registerPlayer(Player player) {
        YRPGCore.getRPGPlayers().put(player, new RPGPlayer(0));
        YRPGCore.getRPGPlayers().get(player).setGrade(Grade.toClass(YRPGCore.getInstance().getPlayerStorage().get(player.getName(), StorageType.grade)));
        YRPGCore.getInstance().getLogWriter().writeData(player.getName() + "的資料讀取成功!");
    }

    private void savePlayer(Player player) {
        YRPGCore.getInstance().getPlayerStorage().set(player.getName(), StorageType.grade, YRPGCore.getRPGPlayers().get(player).getGrade());
        YRPGCore.getInstance().getLogWriter().writeData(player.getName() + "的資料存取成功!");
        YRPGCore.getRPGPlayers().remove(player);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerQuiteEvent(PlayerQuitEvent event) {
        var player = event.getPlayer();
        savePlayer(player);
    }
}
