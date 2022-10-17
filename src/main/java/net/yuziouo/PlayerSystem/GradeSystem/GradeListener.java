package net.yuziouo.PlayerSystem.GradeSystem;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import net.yuziouo.YRPGCore;

public class GradeListener implements Listener {
    @EventHandler
    public void PlayerAddExpEvent(PlayerAddExpEvent event){
        var player = event.getPlayer();
        var grade = YRPGCore.getRPGPlayers().get(player).getGrade();
        YRPGCore.getInstance().getLogWriter().writeData(player.getName()+"獲的了 "+event.getExp()+" 點經驗值");
        if(grade.canLevelUp()){
            GradeUtil.addLevel(player,1,false);
            YRPGCore.getInstance().getLogWriter().writeData(player.getName()+"由於經驗滿了所以提升了一等");
        }
    }
    @EventHandler
    public void PlayerAddLevelEvent(PlayerAddLevelEvent event){
        YRPGCore.getInstance().getLogWriter().writeData(event.getPlayer().getName()+"獲得了等級 +"+event.getLevel());
    }
}
