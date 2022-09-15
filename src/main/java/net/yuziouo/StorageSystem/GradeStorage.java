package net.yuziouo.StorageSystem;

import cn.nukkit.Player;
import com.google.gson.Gson;
import net.yuziouo.GradeSystem.Grade;
import net.yuziouo.YRPGCore;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class GradeStorage extends Storage{
    private final Gson gson = new Gson();
    public static Path DataFolder = YRPGCore.getInstance().getDataFolder().toPath().resolve("等級");

    public GradeStorage() {
        super(DataFolder);
    }

    @Override
    public void save(Player player) {
        try {
            Files.write(this.path.resolve(player.getName()),gson.toJson(YRPGCore.getRPGPlayers().get(player).getGrade()).getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void read(Player player) {
        try {
            YRPGCore.getRPGPlayers().get(player).setGrade(gson.fromJson(new FileReader(DataFolder.resolve(player.getName()).toFile()),Grade.class));
        } catch (FileNotFoundException e) {
            try {
                Files.createFile(GradeStorage.DataFolder.resolve(player.getName()));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Grade grade = new Grade();
            grade.setLv(0);
            grade.setExp(0);
            YRPGCore.getRPGPlayers().get(player).setGrade(grade);
            YRPGCore.getInstance().getLogWriter().writeData("找不到 "+player.getName() +"的等級資料所以創建了一份");
        }
    }

    @Override
    String getName() {
        return "等級";
    }
}
