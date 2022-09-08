package net.yuziouo.MobSystem.Memory;

import cn.nukkit.Player;
import cn.nukkit.entity.ai.memory.PlayerMemory;

public class HatePlayerMemory extends PlayerMemory<Player> {
    public HatePlayerMemory(Player player) {
        super(player);
    }
    public HatePlayerMemory() {
        super(null);
    }
}
