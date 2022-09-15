package net.yuziouo.TownSystem;

import cn.nukkit.level.Position;
import cn.nukkit.math.Vector3;

public class Town {
    private String level;
    private Vector3 positionA;
    private Vector3 positionB;
    private String name;

    public Vector3 getPositionA() {
        return positionA;
    }

    public void setPositionA(Position positionA) {
        this.positionA = positionA;
    }

    public Vector3 getPositionB() {
        return positionB;
    }

    public void setPositionB(Position positionB) {
        this.positionB = positionB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
