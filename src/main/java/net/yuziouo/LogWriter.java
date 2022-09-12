package net.yuziouo;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LogWriter {
    //日誌文件
    private final File file;
    public LogWriter(){
        file = new File(YRPGCore.getInstance().getDataFolder()+"/logs",getDate()+".txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //寫入日誌數據
    public void writeData(String str){

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            PrintWriter pw = new PrintWriter(writer);
            pw.println(getTime()+" "+str);
            pw.flush();
            pw.close();
            writer.close();
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
