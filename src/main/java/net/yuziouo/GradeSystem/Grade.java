package net.yuziouo.GradeSystem;

public class Grade {
    private int lv;
    private int exp;
    /**
     *
     * @return 回傳玩家當前等級所需要的經驗值
     */
    public int getMaxExp(){
        return (int) Math.floor(Math.pow(lv,2))+50;
    }

    /**
     *
     * @return 是否可以升級
     */
    public boolean canLevelUp(){
        return exp>=getMaxExp();
    }

    /**
     *
     * @return 玩家目前所擁有的經驗值
     */
    public int getExp() {
        return exp;
    }

    /**
     *
     * @return 玩家目前的等級
     */
    public int getLv() {
        return lv;
    }

    /**
     *
     * @param exp 把玩家的經驗值設定成exp
     */
    public void setExp(int exp) {
        this.exp = exp;
    }

    /**
     *
     * @param lv 設定玩家等級
     */
    public void setLv(int lv) {
        this.lv = lv;
    }
}
