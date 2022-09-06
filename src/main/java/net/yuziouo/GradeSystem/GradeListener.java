package net.yuziouo.GradeSystem;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import net.yuziouo.YRPGCore;

public class GradeListener implements Listener {
    @EventHandler
    public void PlayerAddExpEvent(PlayerAddExpEvent event){
        var player = event.getPlayer();
        var grade = YRPGCore.getRPGPlayers().get(player.getName()).getGrade();
        if(grade.canLevelUp()){
            GradeUtil.addLevel(player,1,false);
        }
    }
}
