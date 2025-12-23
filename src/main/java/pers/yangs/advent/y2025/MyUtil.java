package pers.yangs.advent.y2025;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @className: Util
 * @Description：
 * @Author: 卡卡西
 * @Date: 2025/12/23
 **/
public class MyUtil {

    public static List<String> readLines(String path) {
        // 从文件读取每一行内容到list
        File file = new File(path);
        try {
            FileReader fileReader = new FileReader(file);
            char[] bytes = new char[1024];
            int len = -1;
            StringBuffer sb = new StringBuffer();
            while ((len = fileReader.read(bytes)) != -1) {
                sb.append(new String(bytes, 0, len));
            }
            return Arrays.asList(sb.toString().split("\n"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
