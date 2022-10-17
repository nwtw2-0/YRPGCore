package net.yuziouo.PlayerSystem.GradeSystem;

import cn.nukkit.Player;
import cn.nukkit.Server;
import net.yuziouo.YRPGCore;

public class GradeUtil {

    public static void addExp(String player,int exp){
        addExp(Server.getInstance().getPlayer(player),exp);
    }
    public static void addExp(Player player,int exp){
         addExp(player,exp,true);
    }
    public static void addExp(String player, int exp, boolean pass){
        addExp(Server.getInstance().getPlayer(player),exp,pass);
    }
    /**
     *
     * @param player 需要添加經驗值的玩家
     * @param exp 經驗值量
     * @param pass 是否觸發PlayerAddExpEvent事件
     */
    public static void addExp(Player player,int exp,boolean pass){
        Grade grade = YRPGCore.getRPGPlayers().get(player).getGrade();
        if (pass){
            PlayerAddExpEvent event = new PlayerAddExpEvent(player,exp);
            Server.getInstance().getPluginManager().callEvent(event);
            if (event.isCancelled()) return;
            grade.setExp(grade.getExp()+event.getExp());
        }else {
            grade.setExp(exp+ grade.getExp());
        }
    }
    public static void addLevel(String player,int exp){
        addLevel(Server.getInstance().getPlayer(player),exp);
    }
    public static void addLevel(Player player,int exp){
        addLevel(player,exp,true);
    }
    /**
     *
     * @param player 需要增加等級的玩家
     * @param lv 增加等級數量
     * @param pass 是否觸發PlayerAddLevelEvent事件
     */
    public static void addLevel(Player player,int lv,boolean pass){
        Grade grade = YRPGCore.getRPGPlayers().get(player).getGrade();
        if (pass){
            PlayerAddLevelEvent event = new PlayerAddLevelEvent(player,lv);
            Server.getInstance().getPluginManager().callEvent(event);
            if (event.isCancelled()) return;
            grade.setLv(grade.getLv()+event.getLevel());
        }else {
            grade.setLv(grade.getLv()+lv);
        }
    }

    /**
     *
     * @param player 需要設定經驗值得玩家
     * @param exp 需要設定的經驗
     */
    public static void setExp(Player player,int exp){
        Grade grade = YRPGCore.getRPGPlayers().get(player).getGrade();
        grade.setExp(exp);
    }

    /**
     *
     * @param player 需要設定等級的玩家
     * @param lv 設定等級數量
     */
    public static void setLevel(Player player,int lv){
        Grade grade = YRPGCore.getRPGPlayers().get(player).getGrade();
        grade.setLv(lv);
    }
}
