package net.yuziouo.PlayerSystem.GradeSystem;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.command.data.CommandParamType;
import cn.nukkit.command.data.CommandParameter;
import net.yuziouo.PlayerSystem.GradeSystem.Commands.AddExpCommand;
import net.yuziouo.PlayerSystem.GradeSystem.Commands.AddGradeCommand;
import net.yuziouo.PlayerSystem.GradeSystem.Commands.SetExpCommand;
import net.yuziouo.PlayerSystem.GradeSystem.Commands.SetLevelCommand;
import net.yuziouo.SubCommand;

import java.util.HashMap;

public class GradeCommand extends Command {
    private final HashMap<String,SubCommand> subCommands;
    public GradeCommand(String name, String description, String usageMessage) {
        super(name, description,"",new String[]{"grade"});
        this.setPermission("yrpgcore.grade.admin");
        this.commandParameters.clear();
        this.commandParameters.put("addExp",new CommandParameter[]{
                CommandParameter.newEnum("addExp",new String[]{"addExp"}),
                CommandParameter.newType("target", CommandParamType.TARGET),
                CommandParameter.newType("amount", CommandParamType.INT)
        });
        this.commandParameters.put("addLv",new CommandParameter[]{
                CommandParameter.newEnum("addLv",new String[]{"addLv"}),
                CommandParameter.newType("target", CommandParamType.TARGET),
                CommandParameter.newType("amount", CommandParamType.INT)
        });
        this.commandParameters.put("setExp",new CommandParameter[]{
                CommandParameter.newEnum("setExp",new String[]{"setExp"}),
                CommandParameter.newType("target", CommandParamType.TARGET),
                CommandParameter.newType("amount", CommandParamType.INT)
        });
        this.commandParameters.put("setLv",new CommandParameter[]{
                CommandParameter.newEnum("setLv",new String[]{"setLv"}),
                CommandParameter.newType("target", CommandParamType.TARGET),
                CommandParameter.newType("amount", CommandParamType.INT)
        });
        subCommands = new HashMap<>();
        registerSubCommand();
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!testPermission(commandSender)) return false;
        SubCommand.runSubCommand(strings,subCommands,commandSender,s);
        return true;
    }
    public void registerSubCommand(){
        subCommands.put("addExp",new AddExpCommand());
        subCommands.put("addLv",new AddGradeCommand());
        subCommands.put("setExp",new SetExpCommand());
        subCommands.put("setLv",new SetLevelCommand());
    }
}
