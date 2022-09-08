package net.yuziouo.GradeSystem.Commands;

import cn.nukkit.Player;
import cn.nukkit.Server;
import cn.nukkit.command.CommandSender;
import cn.nukkit.utils.TextFormat;
import net.yuziouo.GradeSystem.GradeUtil;
import net.yuziouo.SubCommand;

public class AddGradeCommand extends SubCommand {
    public AddGradeCommand(){
        this.name = "addLv";
        this.description = "增加玩家等級";
    }
    @Override
    public void execute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length == 3){
            String name = strings[1];
            if(Server.getInstance().getPlayer(name)!= null) {
                Player player = Server.getInstance().getPlayer(name);
                try {
                    int grade = Integer.parseInt(strings[2]);
                    GradeUtil.addLevel(player,grade,false);
                    player.sendMessage("你的等級已經增加了+"+grade);
                } catch (Exception e) {
                    commandSender.sendMessage(TextFormat.RED + "請勿把等級量設置為非數字");
                }
            }
        }
    }
}
