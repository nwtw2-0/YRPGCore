package net.yuziouo;

import cn.nukkit.command.CommandSender;

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
}
