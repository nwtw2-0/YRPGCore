package net.yuziouo.GradeSystem.Commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import net.yuziouo.GradeSystem.GradeUtil;
import net.yuziouo.SubCommand;

public class SetExpCommand extends SubCommand {
    public SetExpCommand(){
        this.name = "setExp";
        this.description = "設定玩家等級";
    }
    @Override
    public void execute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length == 3){
            String name = strings[1];
            if(Server.getInstance().getPlayer(name)!= null) {
                Player player = Server.getInstance().getPlayer(name);
                try {
                    int exp = Integer.parseInt(strings[2]);
                    GradeUtil.setExp(player,exp);
                    player.sendMessage("你的經驗值被設定成+"+exp);
                } catch (Exception e) {
                    commandSender.sendMessage(TextFormat.RED + "請勿把經驗量設置為非數字");
                }
            }
        }
    }
}
