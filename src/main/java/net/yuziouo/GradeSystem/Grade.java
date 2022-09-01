package net.yuziouo.GradeSystem;

public record Grade(int lv,int exp) {
    /**
     *
     * @return 回傳玩家當前等級所需要的經驗值
     */
    public int getMaxExp(){
        return (int) Math.floor(Math.pow(lv,2))+50;
    }

}
