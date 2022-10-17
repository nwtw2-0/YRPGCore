package net.yuziouo.StorageSystem;

import cn.nukkit.utils.Config;
import net.yuziouo.PlayerSystem.GradeSystem.Grade;
import net.yuziouo.YRPGCore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Storage {
    private Config config;

    public Storage(Path file) {
        if (!Files.exists(file)){
            try {
                Files.createFile(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        this.config = new Config(file.toFile(),Config.YAML);
    }

    public void setConfig(Config config) {
        this.config = config;
    }
    public void set(String name,StorageType type,IYAMLStorage storage){
        config.set(name+"."+type.getName(),storage.toMap());
        config.save();
    }
    public Map<String,Object> get(String name,StorageType type){
        if(!config.exists(name)){
            registerPlayer(name);
        }
       return (Map<String, Object>) config.getSection(name).get(type.getName());
    }

    public Config getConfig() {
        return config;
    }
    public void registerPlayer(String name){
        Grade grade = new Grade();
        grade.setLv(0);
        grade.setExp(0);
        set(name,StorageType.grade,grade);
        YRPGCore.getInstance().getLogWriter().writeData("未找到"+name+"的玩家資訊正在創建中");
    }
}
