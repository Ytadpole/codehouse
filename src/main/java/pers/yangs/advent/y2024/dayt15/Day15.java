package pers.yangs.advent.y2024.dayt15;

import pers.yangs.advent.y2024.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: Day15
 * @Description：
 * @Author: 卡卡西
 * @Date: 2024/12/16
 **/
public class Day15 {
    static Map<String, Coordinate> operations = new HashMap<>() {
        {
            put("<", new Coordinate(0, -1));
            put("^", new Coordinate(-1, 0));
            put("v", new Coordinate(1, 0));
            put(">", new Coordinate(0, 1));
        }
    };

    static String BOX = "O";
    static String WALL = "#";
    static String EMPTY = ".";
    static String ROBOT = "@";

    public static void main(String[] args) {
//        String input = Util.readFile("/Users/yangsong/code/java/codehourse/src/main/java/pers/yangs/advent/y2024/dayt15/1.txt");
//        String input = Util.readFile("/Users/yangsong/code/java/codehourse/src/main/java/pers/yangs/advent/y2024/dayt15/2.txt");
        String input = Util.readFile("/Users/yangsong/code/java/codehourse/src/main/java/pers/yangs/advent/y2024/dayt15/3.txt");
        Robot robot = new Robot();
        String[][] map = parseInput(input, robot);

        resolve(map, robot);
    }

    private static void resolve(String[][] map, Robot robot) {
        for (int i = 0; i < robot.commands.length; i++) {
            Coordinate command = robot.getCommand(i);
            int nx = robot.x + command.x;
            int ny = robot.y + command.y;
            if(map[nx][ny].equals(WALL)) {
                //没变化
            } else if(map[nx][ny].equals(EMPTY)){

                move(robot, map, nx, ny);
            } else if(map[nx][ny].equals(BOX)) {
                push(nx, ny, map, command, robot);
            }
            printMap(map, robot, i);
        }

        gps(map);
    }

    private static void gps(String[][] map) {
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j].equals(BOX)) {
                    count += i * 100 + j;
                }
            }
        }
        System.out.println("gps="+count);
    }

    private static void move(Robot robot, String[][] map, int nx, int ny) {
        map[robot.x][robot.y] = EMPTY;
        robot.x = nx;
        robot.y = ny;
        map[robot.x][robot.y] = ROBOT;
    }

    private static void printMap(String[][] map, Robot robot, int i) {
        System.out.println(i + ":" + robot.commands[i]);
        for (String[] strings : map) {
            System.out.println(Arrays.toString(strings));
        }
    }

    private static void push(int nx, int ny, String[][] map, Coordinate command, Robot robot) {
        int tmpX = nx;
        int tmpY = ny;
        do {
            tmpX = tmpX + command.x;
            tmpY = tmpY + command.y;
        }while (map[tmpX][tmpY].equals(BOX));
        if(map[tmpX][tmpY].equals(WALL)) {
            //没变化
        } else if(map[tmpX][tmpY].equals(EMPTY)) {
            //迁移
            map[tmpX][tmpY] = BOX;
            move(robot, map, nx, ny);
        }
    }

    private static String[][] parseInput(String input, Robot robot) {
        String[] info = input.split("\n\n");
        String mapStr = info[0];
        String[] rows = mapStr.split("\n");
        String[][] map = new String[rows.length][];
        for (int i = 0; i < rows.length; i++) {
            String[] m = rows[i].split("");
            map[i] = m;
            for (int j = 0; j < m.length; j++) {
                if(m[j].equals("@")) {
                    robot.x = i;
                    robot.y = j;
                }
            }
        }
        // command
        robot.commands = info[1].replace("\n", "").split("");

        return map;
    }
}

class Robot{
    int x;
    int y;

    String[] commands;

    Coordinate getCommand(int i) {
        return Day15.operations.get(commands[i]);
    }
}

class Coordinate{
    int x;
    int y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
