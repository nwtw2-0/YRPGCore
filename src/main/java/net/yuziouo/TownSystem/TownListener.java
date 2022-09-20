package net.yuziouo.TownSystem;

import cn.nukkit.Player;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.EventPriority;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.event.player.PlayerMoveEvent;
import cn.nukkit.form.response.FormResponseSimple;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.nbt.tag.DoubleTag;
import cn.nukkit.nbt.tag.FloatTag;
import cn.nukkit.nbt.tag.ListTag;
import cn.nukkit.utils.TextFormat;
import net.yuziouo.TownSystem.Commands.CreateCommand;
import net.yuziouo.Utils.TextEntity;

import java.util.Random;


public class TownListener implements Listener {
    private void canMove(PlayerMoveEvent event){
        if (event.getPlayer().isOp()) return;
        if (event.getFrom().equals(event.getTo())) return;
        double x,y,z;
        x = event.getTo().getX();
        y = event.getTo().getY();
        z = event.getTo().getZ();
        for(Town town : Town.towns){
            if((x>= Math.min(town.getPositionA().x,town.getPositionB().x) && x <= Math.max(town.getPositionB().x,town.getPositionA().x))
                    &&(y>= Math.min(town.getPositionA().y,town.getPositionB().y) && z <= Math.max(town.getPositionB().y,town.getPositionA().y))
                    && (z>= Math.min(town.getPositionA().z,town.getPositionB().z) && z <= Math.max(town.getPositionB().z,town.getPositionA().z))) {
                double dx = event.getTo().x - event.getFrom().x;
                double dz = event.getTo().z - event.getFrom().z;
                event.getPlayer().addMotion(dx,0,dz);
                CompoundTag nbt = new CompoundTag()
                        .putList(new ListTag<DoubleTag>("Pos")
                                .add(new DoubleTag("", event.getTo().x+0.5))
                                .add(new DoubleTag("", event.getTo().z+1.2))
                                .add(new DoubleTag("", event.getTo().z+0.5)))
                        .putList(new ListTag<DoubleTag>("Motion")
                                .add(new DoubleTag("", 0))
                                .add(new DoubleTag("", 0))
                                .add(new DoubleTag("", 0)))
                        .putList(new ListTag<FloatTag>("Rotation")
                                .add(new FloatTag("", 0))
                                .add(new FloatTag("", 0)));
                TextEntity textEntity = new TextEntity(event.getTo().getChunk(), nbt,"===========================\n"+TextFormat.RED+"您尚未完成進入此城鎮的主線任務"+"\n===========================",20*5);
                textEntity.spawnTo(event.getPlayer());
                event.getPlayer().sendAnnouncement(TextFormat.RED+"你還未完成前置任務無法進入此區域");
                return;
            }
        }
    }
    @EventHandler(priority = EventPriority.LOWEST)
    public void onMove(PlayerMoveEvent event){
        if (event.isCancelled()) return;
        canMove(event);
    }
    @EventHandler
    public void onCreate(BlockBreakEvent event){
        Player player = event.getPlayer();
        if(CreateCommand.making.containsKey(player)){
            Town town = CreateCommand.making.get(player);
            if(town.getLevel().equals(event.getBlock().getLevelName())){
                if (town.getPositionA() == null){
                    town.setPositionA(event.getBlock().getLocation());
                    player.sendMessage("你已經把"+town.getName()+"的起始點設置在"+event.getBlock().getLocationHash());
                    player.sendMessage("請破壞一格方塊來當作終點");
                }else if(town.getPositionB() == null){
                    town.setPositionB(event.getBlock().getLocation());
                    player.sendMessage("你已經把"+town.getName()+"的終點設置在"+event.getBlock().getLocationHash());
                    player.sendMessage("請破壞一格方塊來當作傳送點");
                }else if (town.getTpLocation() == null){
                    town.setTpLocation(event.getBlock().getLocation());
                    player.sendMessage("你已經把"+town.getName()+"的傳送點設置在"+event.getBlock().getLocationHash());
                    player.sendMessage("城市:"+town.getName()+"已經設定完成");
                    Town.towns.add(town);
                    CreateCommand.making.remove(player);
                }
                event.setCancelled(true);
            }
        }
    }
    @EventHandler
    public void TpForm(PlayerFormRespondedEvent event){
        Player player = event.getPlayer();
        if (event.wasClosed()) return;
        if (event.getFormID() == Town.tpFormID){
            FormResponseSimple simple = (FormResponseSimple) event.getResponse();
            Town town = Town.getTown(simple.getClickedButton().getText());
            if (town != null){
                player.teleport(town.getTpLocation());
                player.sendMessage("你已經傳送到了城鎮:"+town.getName());
            }
        }
    }
}
