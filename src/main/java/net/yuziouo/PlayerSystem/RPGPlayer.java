package net.yuziouo.PlayerSystem;

import net.yuziouo.PlayerSystem.GradeSystem.Grade;
import net.yuziouo.PlayerSystem.RPGSystem.Ability;

public class RPGPlayer {
    private Grade grade;
    private Ability ability;
    private int mana;

    public RPGPlayer(int mana) {
        this.mana = mana;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Ability getAbility() {
        return ability;
    }

    public void setAbility(Ability ability) {
        this.ability = ability;
    }

    public int getMana() {
        return mana;
    }

    public void setMana(int mana) {
        this.mana = mana;
    }
    public int getMaxMana(){
        return (int) (200+ability.getMANA());
    }
    public double ATKDamage(){
        return ability.getSTR()*ability.getATK();
    }
    public double BowDamage(){
        return ability.getDEX() * ability.getATK();
    }
}
