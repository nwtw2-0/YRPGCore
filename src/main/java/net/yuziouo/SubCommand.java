package net.yuziouo;

import cn.nukkit.command.CommandSender;

import java.util.Arrays;
import java.util.HashMap;

public abstract class SubCommand {
    //子指令名稱
    protected String name;
    //子指令介紹
    protected String description;
    public abstract void execute(CommandSender commandSender, String s, String[] strings);

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public  static void runSubCommand(String[] strings, HashMap<String,SubCommand> subCommands,CommandSender commandSender,String s){
        if (strings.length >0){
            if(subCommands.containsKey(strings[0])){
                subCommands.get(strings[0]).execute(commandSender,s,strings);
                StringBuilder builder = new StringBuilder();
                Arrays.stream(strings).forEach(string ->{
                    builder.append(string).append(" ");
                });
                YRPGCore.getInstance().getLogWriter().writeData(commandSender.getName()+"執行了指令 /"+s+" "+builder);
            }else {
                commandSender.sendMessage("沒有找到此指令");
            }
        }else {
            commandSender.sendMessage("未找到此指令");
        }
    }
}
