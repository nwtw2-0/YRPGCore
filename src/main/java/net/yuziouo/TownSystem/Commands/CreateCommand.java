package net.yuziouo.TownSystem.Commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import net.yuziouo.SubCommand;
import net.yuziouo.TownSystem.Town;

import java.util.HashMap;

public class CreateCommand extends SubCommand {
    public static final HashMap<Player, Town> making = new HashMap<>();
    public CreateCommand(){
        this.name = "create";
        this.description = "創建程式指令";
    }
    @Override
    public void execute(CommandSender commandSender, String s, String[] strings){
        if (commandSender instanceof Player player) {
            if (!making.containsKey(player)) {
                if (strings.length == 2) {
                    if (Town.notHasName(strings[1])) {
                        Town town = new Town();
                        town.setName(strings[1]);
                        town.setLevel(player.getLevel().getName());
                        making.put(player,town);
                        player.sendMessage("創建模式啟動請先破壞一格方塊來當作城市起點");
                    }
                }else {
                    player.sendMessage("格式錯誤");
                }
            }else {
                player.sendMessage("你已經在創建模式中");
            }
        }else{
            commandSender.sendMessage("請在遊戲中輸入此指令");
        }
    }
}
