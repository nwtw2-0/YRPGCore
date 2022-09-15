package net.yuziouo.MobSystem;

import cn.nukkit.Player;
import cn.nukkit.entity.ai.behavior.Behavior;
import cn.nukkit.entity.ai.behaviorgroup.BehaviorGroup;
import cn.nukkit.entity.ai.behaviorgroup.IBehaviorGroup;
import cn.nukkit.entity.ai.controller.LookController;
import cn.nukkit.entity.ai.controller.WalkController;
import cn.nukkit.entity.ai.evaluator.AllMatchEvaluator;
import cn.nukkit.entity.ai.evaluator.MemoryCheckNotEmptyEvaluator;
import cn.nukkit.entity.ai.executor.RandomRoamExecutor;
import cn.nukkit.entity.ai.memory.AttackTargetMemory;
import cn.nukkit.entity.ai.memory.NearestPlayerMemory;
import cn.nukkit.entity.ai.route.SimpleFlatAStarRouteFinder;
import cn.nukkit.entity.ai.route.posevaluator.WalkingPosEvaluator;
import cn.nukkit.entity.ai.sensor.NearestPlayerSensor;
import cn.nukkit.entity.mob.EntityWalkingMob;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import net.yuziouo.MobSystem.Executor.MeleeAttackCustomDamage;
import net.yuziouo.MobSystem.Memory.HatePlayerMemory;
import net.yuziouo.MobSystem.Sensor.HatePlayerSeneor;

import java.util.HashMap;
import java.util.Set;

public class BaseMonster extends EntityWalkingMob {
    private MonsterAbility ability;
    private IBehaviorGroup behaviorGroup;
    private final HashMap<Player,Double> damageList = new HashMap<>();

    public BaseMonster(FullChunk chunk, CompoundTag nbt,MonsterAbility ability) {
        super(chunk, nbt);
        this.ability = ability;
        this.setMaxHealth(ability.health());
        this.setHealth(getMaxHealth());
        this.setNameTagAlwaysVisible(true);
        this.setNameTag("Lv."+ability.grade()+" "+ability.name());
    }

    @Override
    public int getNetworkId() {
        return 32;
    }

    @Override
    public IBehaviorGroup getBehaviorGroup() {
        if (behaviorGroup == null){
            behaviorGroup = new BehaviorGroup(
                    this.tickSpread,
                    Set.of(),
                    Set.of(
                            new Behavior(new MeleeAttackCustomDamage(HatePlayerMemory.class, 0.15f,40,true, 10, ability.damage()), new AllMatchEvaluator(
                                    new MemoryCheckNotEmptyEvaluator(AttackTargetMemory.class),
                                    entity -> {
                                        if (!entity.getMemoryStorage().notEmpty(HatePlayerMemory.class)) return true;
                                        Player player = entity.getMemoryStorage().get(HatePlayerMemory.class).getData();
                                        return player.isSurvival();
                                    }
                            ),3,1),
                            new Behavior(new MeleeAttackCustomDamage(NearestPlayerMemory.class, 0.15f,40,false, 10,ability.damage()), new AllMatchEvaluator(
                                    new MemoryCheckNotEmptyEvaluator(NearestPlayerMemory.class),
                                    entity -> {
                                        if (entity.getMemoryStorage().isEmpty(NearestPlayerMemory.class)) return true;
                                        Player player = entity.getMemoryStorage().get(NearestPlayerMemory.class).getData();
                                        return player.isSurvival();
                                    }
                            ),2,1),
                            new Behavior(new RandomRoamExecutor(0.15f, 12, 100, false,-1,true,10), (entity -> true), 1, 1)
                    ),
                    Set.of(new NearestPlayerSensor(10, 0,20)
                    , new HatePlayerSeneor(damageList)),
                    Set.of(new WalkController(), new LookController(true, true)),
                    new SimpleFlatAStarRouteFinder(new WalkingPosEvaluator(), this)
            );
        }
        return behaviorGroup;
    }
}
