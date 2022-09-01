package net.yuziouo.GradeSystem;

import cn.nukkit.Player;
import cn.nukkit.Server;
import net.yuziouo.YRPGCore;

public class GradeUtil {

    /**
     *
     * @param player 需要添加經驗值的玩家
     * @param exp 經驗值量
     * @param pass 是否觸發PlayerAddExpEvent事件
     */
    public static void addExp(Player player,int exp,boolean pass){
        Grade grade = YRPGCore.getRPGPlayers().get(player.getName()).getGrade();
        if (pass){
            PlayerAddExpEvent event = new PlayerAddExpEvent(player,exp);
            Server.getInstance().getPluginManager().callEvent(event);
            if (event.isCancelled()) return;
            grade.setExp(grade.getExp()+event.getExp());
        }else {
            grade.setExp(exp+ grade.getExp());
        }
    }
}
