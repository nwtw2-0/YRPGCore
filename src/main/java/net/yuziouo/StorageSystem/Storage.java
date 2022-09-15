package net.yuziouo.StorageSystem;

import net.yuziouo.YRPGCore;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public abstract class Storage implements IStorage{
    protected Path path;
    private static final ArrayList<Storage> storages = new ArrayList<>();
    public Storage(Path path){
        this.path = path;
        if(!Files.exists(this.path)){
            try {
                Files.createDirectory(this.path);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        registerStorge(this);
        YRPGCore.getInstance().getLogWriter().writeData(getName()+"儲存器已經註冊");
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public static ArrayList<Storage> getStorages() {
        return storages;
    }
    public void registerStorge(Storage storage){
        storages.add(storage);
    }
    abstract String getName();
}
