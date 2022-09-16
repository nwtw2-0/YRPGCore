package net.yuziouo.TownSystem;

import cn.nukkit.Player;
import cn.nukkit.math.Vector3;
import net.yuziouo.StorageSystem.IYAMLStorage;
import net.yuziouo.StorageSystem.StorageType;
import net.yuziouo.YRPGCore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Town implements IYAMLStorage {
    public static ArrayList<Town> towns = new ArrayList<>();
    private String level;
    private Vector3 positionA;
    private Vector3 positionB;
    private String name;
    private ArrayList<String> canPass = new ArrayList<>();
    private boolean pass;
    public Vector3 getPositionA() {
        return positionA;
    }

    public void setPositionA(Vector3 positionA) {
        this.positionA = positionA;
    }

    public Vector3 getPositionB() {
        return positionB;
    }

    public void setPositionB(Vector3 positionB) {
        this.positionB = positionB;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public ArrayList<String> getCanPass() {
        return canPass;
    }

    public void setCanPass(ArrayList<String> canPass) {
        this.canPass = canPass;
    }
    public boolean canPass(Player player){
        return canPass.contains(player.getName());
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }

    public boolean isPass() {
        return pass;
    }

    @Override
    public Map<String, Object> toMap() {
        HashMap<String,Object> map = new HashMap<>();
        map.put("name",name);
        map.put("start",positionA.getFloorX()+":"+positionA.getFloorY()+":"+positionA.getFloorZ());
        map.put("end",positionB.getFloorX()+":"+positionB.getFloorY()+":"+positionB.getFloorZ());
        map.put("level",level);
        map.put("canPass",canPass);
        map.put("pass",pass);
        return map;
    }
    public static Town toClass(Map<String,Object> map){
        Town town = new Town();
        town.setName((String) map.get("name"));
        String[] start = ((String) map.get("start")).split(":");
        town.setPositionA(new Vector3(Integer.parseInt(start[0]),Integer.parseInt(start[1]),Integer.parseInt(start[2])));
        String[] end = ((String) map.get("end")).split(":");
        town.setPositionB(new Vector3(Integer.parseInt(end[0]),Integer.parseInt(end[1]),Integer.parseInt(end[2])));
        town.setLevel((String) map.get("level"));
        town.setCanPass((ArrayList<String>) map.get("canPass"));
        town.setPass((boolean)map.get("pass"));
        return town;
    }
    public void save(){
        YRPGCore.getTownStorage().set(name, StorageType.town,this);
        YRPGCore.getInstance().getLogWriter().writeData("城市 "+name+"存檔成功");
    }
    public static void load(){
        for(String key:YRPGCore.getTownStorage().getConfig().getKeys(false)){
            towns.add(Town.toClass(YRPGCore.getTownStorage().get(key,StorageType.town)));
            YRPGCore.getInstance().getLogWriter().writeData("城市:"+key+"讀取成功!");
        }
        YRPGCore.getInstance().getLogWriter().writeData("城市讀取完成!");
        YRPGCore.getInstance().getLogger().info("城市讀取完成");
    }
}
