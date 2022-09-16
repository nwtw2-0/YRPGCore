package net.yuziouo.TownSystem;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.utils.TextFormat;


public class TownListener implements Listener {
    private void canMove(PlayerMoveEvent event){
        if (event.getFrom().equals(event.getTo())) return;
        double x,y,z;
        x = event.getTo().getX();
        y = event.getTo().getY();
        z = event.getTo().getZ();
        for(Town town : Town.towns){
            if((x>= town.getPositionA().x && x <= town.getPositionB().x)&&(y>= town.getPositionA().y && y <= town.getPositionB().y)&& (z>= town.getPositionA().z && z <= town.getPositionB().z)) {
                double dx = event.getTo().x - event.getFrom().x;
                double dz = event.getTo().z - event.getFrom().z;
                event.getPlayer().addMotion(dx,0,dz);
                event.getPlayer().sendAnnouncement(TextFormat.RED+"你還未完成前置任務無法進入此區域");
            }
        }
    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void onMove(PlayerMoveEvent event){
        if (event.isCancelled()) return;
        canMove(event);
    }
}
