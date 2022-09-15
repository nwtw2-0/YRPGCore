package net.yuziouo;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogWriter {
    //日誌文件
    private final Path file;
    public LogWriter(){
        if (!Files.exists(YRPGCore.getInstance().getDataFolder().toPath())){
            try {
                Files.createDirectory(YRPGCore.getInstance().getDataFolder().toPath());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        file = YRPGCore.getInstance().getDataFolder().toPath().resolve("log.txt");
        if (!Files.exists(file)) {
            try {
                Files.createFile(file);
                YRPGCore.getInstance().getLogger().info("尚未找到Log紀錄檔案 正在創建一份");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //寫入日誌數據
    public void writeData(String str){

        try {
           Files.write(file,(getTime()+"  "+str+"\n").getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @return 回傳當前日期
     */
    public String getDate(){
        return DateTimeFormatter.ofPattern("yyyy/MM/dd").format(LocalDateTime.now());
    }

    /**
     *
     * @return 回傳當前日期及時間
     */
    public String getTime(){
        return DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss").format(LocalDateTime.now());
    }
}
