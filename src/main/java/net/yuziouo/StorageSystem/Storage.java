package net.yuziouo.StorageSystem;

import cn.nukkit.utils.Config;

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
       return (Map<String, Object>) config.getSection(name).get(type.getName());
    }

    public Config getConfig() {
        return config;
    }
}
