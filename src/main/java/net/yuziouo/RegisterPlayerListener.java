package net.yuziouo;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerJoinEvent;
import cn.nukkit.event.player.PlayerQuitEvent;
import net.yuziouo.RPGSystem.RPGPlayer;
import net.yuziouo.StorageSystem.Storage;

public class RegisterPlayerListener implements Listener {
    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerJoinEvent(PlayerJoinEvent event){
        registerPlayer(event.getPlayer());
    }

    private void registerPlayer(Player player){
        YRPGCore.getRPGPlayers().put(player,new RPGPlayer());
        Storage.getStorages().forEach(storage -> {
            storage.read(player);
        });
    }
    private void savePlayer(Player player){
        Storage.getStorages().forEach(storage -> {
            storage.save(player);
        });
        YRPGCore.getRPGPlayers().remove(player);
    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void PlayerQuiteEvent(PlayerQuitEvent event){
        var player = event.getPlayer();
        savePlayer(player);
    }
}
