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

    public int getExp() {
        return exp;
    }

    public int getLv() {
        return lv;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public void setLv(int lv) {
        this.lv = lv;
    }
}
