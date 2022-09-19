package net.yuziouo.TownSystem;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import net.yuziouo.SubCommand;
import net.yuziouo.TownSystem.Commands.CreateCommand;

import java.util.HashMap;

public class TownCommand extends Command {
    private final HashMap<String, SubCommand> subCommands;
    public TownCommand(String name) {
        super(name);
        this.setPermission("yrpgcore.town.admin");
        subCommands = new HashMap<>();
        registerCommands();
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {
        if (!testPermission(commandSender)) return false;
        SubCommand.runSubCommand(strings,subCommands,commandSender,s);
        return true;
    }
    public void registerCommands(){
        subCommands.put("create",new CreateCommand());
    }
}
