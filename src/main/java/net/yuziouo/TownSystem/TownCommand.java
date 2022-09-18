package net.yuziouo.TownSystem;

import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;

public class TownCommand extends Command {
    public TownCommand(String name) {
        super(name);
        this.setPermission("yrpgcore.town.admin");
    }

    @Override
    public boolean execute(CommandSender commandSender, String s, String[] strings) {

        return true;
    }
}
