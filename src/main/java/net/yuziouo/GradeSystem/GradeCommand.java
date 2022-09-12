package net.yuziouo.GradeSystem;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import net.yuziouo.GradeSystem.Commands.AddExpCommand;
import net.yuziouo.GradeSystem.Commands.AddGradeCommand;
import net.yuziouo.GradeSystem.Commands.SetExpCommand;
import net.yuziouo.GradeSystem.Commands.SetLevelCommand;
import net.yuziouo.SubCommand;
import net.yuziouo.YRPGCore;

import java.util.HashMap;

public class GradeCommand extends Command {
    private final HashMap<String,SubCommand> subCommands;
    public GradeCommand(String name, String description, String usageMessage) {
        super(name, description, usageMessage);
        subCommands = new HashMap<>();
        registerSubCommand();
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (strings.length >0){
            if(subCommands.containsKey(strings[0])){
                subCommands.get(strings[0]).execute(commandSender,s,strings);
                YRPGCore.getInstance().getLogWriter().writeData(commandSender.getName()+"執行了指令 /"+s);
            }else {
                commandSender.sendMessage("沒有找到此指令");
            }
        }else {
            commandSender.sendMessage("請輸入/grade help來找尋更多");
        }
        return true;
    }
    public void registerSubCommand(){
        subCommands.put("addExp",new AddExpCommand());
        subCommands.put("addLv",new AddGradeCommand());
        subCommands.put("setExp",new SetExpCommand());
        subCommands.put("setLv",new SetLevelCommand());
    }
}