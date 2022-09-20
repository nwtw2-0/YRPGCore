package net.yuziouo.Utils;

import cn.nukkit.entity.item.EntityArmorStand;
import cn.nukkit.level.format.FullChunk;
import cn.nukkit.nbt.tag.CompoundTag;
import cn.nukkit.potion.Effect;

public class TextEntity extends EntityArmorStand {
    private String text;//顯示文字
    private int liveTime;//存在時間
    public TextEntity(FullChunk chunk, CompoundTag nbt,String text,int liveTime) {
        super(chunk, nbt);
        this.text = text;
        this.liveTime = liveTime;
        this.addEffect(Effect.getEffect(Effect.INVISIBILITY).setDuration(99999999));
        this.setNameTag(text);
    }

    public String getText() {
        return text;
    }

    public int getLiveTime() {
        return liveTime;
    }

    public void setLiveTime(int liveTime) {
        this.liveTime = liveTime;
    }

    public void setText(String text) {
        this.text = text;
    }
    @Override
    public void setNameTagVisible(boolean value) {
        super.setNameTagVisible(true);
    }

    @Override
    public void setNameTagAlwaysVisible(boolean value) {
        super.setNameTagAlwaysVisible(true);
    }

    @Override
    public boolean entityBaseTick(int tickDiff) {
        if (this.ticksLived >= getLiveTime()){
            this.close();
        }
        return super.entityBaseTick(tickDiff);
    }
}
