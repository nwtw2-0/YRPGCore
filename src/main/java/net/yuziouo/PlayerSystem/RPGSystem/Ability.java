package net.yuziouo.PlayerSystem.RPGSystem;

public class Ability {
    private int STR,DEX,INT,DEF,LUK;
    private double MISS,HIT,CRIT,CRITDMG,ATKMANA,HEALTH,MANA,BOSS,SKILLDELAY,HATE,ATK;

    public Ability(int STR, int DEX, int INT, int DEF, int LUK) {
        this.STR = STR;
        this.DEX = DEX;
        this.INT = INT;
        this.DEF = DEF;
        this.LUK = LUK;
        MISS = 1;
        HIT = 1;
        CRIT = 1;
        CRITDMG = 1;
        ATKMANA = 1;
        HEALTH = 1;
        MANA = 1;
        BOSS = 1;
        SKILLDELAY = 1;
        HATE = 1;
        ATK = 1;
    }

    public int getSTR() {
        return STR;
    }

    public void setSTR(int STR) {
        this.STR = STR;
    }

    public int getDEX() {
        return DEX;
    }

    public void setDEX(int DEX) {
        this.DEX = DEX;
    }

    public int getINT() {
        return INT;
    }

    public void setINT(int INT) {
        this.INT = INT;
    }

    public int getDEF() {
        return DEF;
    }

    public void setDEF(int DEF) {
        this.DEF = DEF;
    }

    public int getLUK() {
        return LUK;
    }

    public void setLUK(int LUK) {
        this.LUK = LUK;
    }

    public double getMISS() {
        return MISS;
    }

    public void setMISS(double MISS) {
        this.MISS = MISS;
    }
    //物理穿透率
    public double getHIT() {
        return HIT;
    }

    public void setHIT(double HIT) {
        this.HIT = HIT;
    }
    //爆擊機率
    public double getCRIT() {
        return CRIT;
    }

    public void setCRIT(double CRIT) {
        this.CRIT = CRIT;
    }

    /**
     *
     * @return 爆擊傷害加成
     */
    public double getCRITDMG() {
        return CRITDMG;
    }

    public void setCRITDMG(double CRITDMG) {
        this.CRITDMG = CRITDMG;
    }

    /**
     *
     * @return 攻擊回復魔力細數
     */
    public double getATKMANA() {
        return ATKMANA;
    }

    public void setATKMANA(double ATKMANA) {
        this.ATKMANA = ATKMANA;
    }

    /**
     *
     * @return 血量增幅係數
     */
    public double getHEALTH() {
        return HEALTH;
    }

    public void setHEALTH(double HEALTH) {
        this.HEALTH = HEALTH;
    }

    public double getMANA() {
        return MANA;
    }

    public void setMANA(double MANA) {
        this.MANA = MANA;
    }

    /**
     *
     * @return 對王傷害
     */
    public double getBOSS() {
        return BOSS;
    }

    public void setBOSS(double BOSS) {
        this.BOSS = BOSS;
    }

    /**
     *
     * @return 詠唱縮短
     */
    public double getSKILLDELAY() {
        return SKILLDELAY;
    }

    public void setSKILLDELAY(double SKILLDELAY) {
        this.SKILLDELAY = SKILLDELAY;
    }

    public double getHATE() {
        return HATE;
    }

    public void setHATE(double HATE) {
        this.HATE = HATE;
    }

    public double getATK() {
        return ATK;
    }

    public void setATK(double ATK) {
        this.ATK = ATK;
    }
}
