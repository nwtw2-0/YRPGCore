package net.yuziouo.PlayerSystem.GradeSystem.Commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import net.yuziouo.PlayerSystem.GradeSystem.GradeUtil;
import net.yuziouo.SubCommand;

public class SetLevelCommand extends SubCommand {
    public SetLevelCommand(){
        this.name = "setLv";
        this.description = "設定玩家等級";
    }
    @Override
    public void execute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length == 3){
            String name = strings[1];
            if(Server.getInstance().getPlayer(name)!= null) {
                Player player = Server.getInstance().getPlayer(name);
                try {
                    int lv = Integer.parseInt(strings[2]);
                    GradeUtil.setLevel(player,lv);
                    player.sendMessage("你的等級已經被設定為"+lv);
                } catch (Exception e) {
                    commandSender.sendMessage(TextFormat.RED + "請勿把經驗量設置為非數字");
                }
            }
        }
    }
}
