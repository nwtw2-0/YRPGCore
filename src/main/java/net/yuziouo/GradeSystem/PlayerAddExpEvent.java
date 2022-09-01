package net.yuziouo.GradeSystem;

import cn.nukkit.Player;
import cn.nukkit.event.Cancellable;
import cn.nukkit.event.Event;
import cn.nukkit.event.HandlerList;

public class PlayerAddExpEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS_LIST = new HandlerList();
    private boolean isCancelled;
    private int exp;
    private final Player player;

    public PlayerAddExpEvent(Player player,int exp) {
        this.exp = exp;
        this.player = player;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        isCancelled = cancelled;
    }

    public static HandlerList getHandlersList() {
        return HANDLERS_LIST;
    }

    public Player getPlayer() {
        return player;
    }
}
