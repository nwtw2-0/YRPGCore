package net.yuziouo.TownSystem.Commands;

import cn.nukkit.Player;
import cn.nukkit.command.CommandSender;
import net.yuziouo.SubCommand;
import net.yuziouo.TownSystem.Town;

public class TpListCommand extends SubCommand {
    @Override
    public void execute(CommandSender commandSender, String s, String[] strings) {
        if (commandSender.isPlayer()){
            Player player = commandSender.asPlayer();
            Town.CanTeleportTown(player);
        }else {
            commandSender.sendMessage("請在遊戲內執行指令");
        }
    }
}
