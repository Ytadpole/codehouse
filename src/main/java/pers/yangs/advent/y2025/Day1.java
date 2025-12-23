package pers.yangs.advent.y2025;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @className: Day1
 * @Description：
 * @Author: 卡卡西
 * @Date: 2025/12/22
 **/
public class Day1 {


    public static void main(String[] args) {
        List<String> input1 = readData("/Users/yangsong/code/java/codehourse/src/main/java/pers/yangs/advent/y2025/input1.pro");
        List<Command> commands = commands(input1);
        int pos = 50;
        int res = 0;
        for (Command command : commands) {
            if("L".equals(command.direction)) {
                pos -= command.steps;
                pos = pos % 100;
            } else {
                pos += command.steps;
                pos = (pos + 100) % 100;
            }
            if(pos == 0) {
                res++;
            }
        }
        System.out.println(res);
    }

    static List<Command> commands(List<String> data) {
        List<Command> commands = new ArrayList<>();
        for (String item : data) {
            commands.add(new Command(item.substring(0, 1), Integer.parseInt(item.substring(1))));
        }
        return commands;
    }

    static class Command {
        String direction;
        int steps;

        Command(String direction, int steps) {
            this.direction = direction;
            this.steps = steps;
        }
    }

    static List<String> readData(String path) {
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
