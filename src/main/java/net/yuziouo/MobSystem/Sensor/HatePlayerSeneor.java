package net.yuziouo.MobSystem.Sensor;

import cn.nukkit.Player;
import cn.nukkit.entity.EntityIntelligent;
import cn.nukkit.entity.ai.sensor.ISensor;
import net.yuziouo.MobSystem.Memory.HatePlayerMemory;

import java.util.HashMap;
import java.util.Map;

public class HatePlayerSeneor implements ISensor {
    private final HashMap<Player,Double> damageList;
    public HatePlayerSeneor(HashMap<Player,Double> damageList){
        this.damageList = damageList;
    }
    @Override
    public void sense(EntityIntelligent entity) {
        HatePlayerMemory currentMemory = entity.getMemoryStorage().get(HatePlayerMemory.class);
        Player player = null;
        double dmg = -9999;
        for(Map.Entry<Player,Double> entry: damageList.entrySet()){
            if (entry.getValue()>dmg) player = entry.getKey();
        }
        currentMemory.setData(player);
    }

    @Override
    public int getPeriod() {
        return 20;
    }
}
