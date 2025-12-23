package pers.yangs.advent.y2024.dayt15_2;

import pers.yangs.advent.y2024.Util;

import java.util.*;

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

    static String BOX2_LEFT = "[";
    static String BOX2_RIGHT = "]";


    public static void main(String[] args) {
//        String input = Util.readFile("/Users/yangsong/code/java/codehourse/src/main/java/pers/yangs/advent/y2024/dayt15/1.txt");
//        String input = Util.readFile("/Users/yangsong/code/java/codehourse/src/main/java/pers/yangs/advent/y2024/dayt15/4.txt");
        String input = Util.readFile("/Users/yangsong/code/java/codehourse/src/main/java/pers/yangs/advent/y2024/dayt15/3.txt");
        Robot robot = new Robot();
        String[][] map = parseInput(input, robot);
        printMap(map, robot, 0);
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
            } else if(map[nx][ny].equals(BOX2_LEFT) || map[nx][ny].equals(BOX2_RIGHT)) {
                push(nx, ny, map, command, robot);
            }
//            printMap(map, robot, i);
        }

        gps(map);
    }

    private static void gps(String[][] map) {
        int count = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j].equals(BOX2_LEFT)) {
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
        if(command.equals(operations.get("<"))
        || command.equals(operations.get(">"))) {
            int tmpX = nx;
            int tmpY = ny;
            do {
                tmpX = tmpX + command.x;
                tmpY = tmpY + command.y;
            }while (map[tmpX][tmpY].equals(BOX2_LEFT) || map[tmpX][tmpY].equals(BOX2_RIGHT));
            if(map[tmpX][tmpY].equals(WALL)) {
                //没变化
            } else if(map[tmpX][tmpY].equals(EMPTY)) {
                //迁移
                String head = map[tmpX - command.x][tmpY - command.y];
                // 头 最远离robot
                map[tmpX][tmpY] = head;

                // 最接近robot的设置为robot
                map[robot.x][robot.y] = EMPTY;
                robot.x = nx;
                robot.y = ny;
                map[robot.x][robot.y] = ROBOT;

                //中间的取反
                int min = Math.min(ny + command.y, tmpY - command.y);
                int max = Math.max(ny + command.y, tmpY - command.y);
                for(int t = min; t <= max; t++) {
                    if(map[tmpX][t].equals(BOX2_LEFT)) {
                        map[tmpX][t] = BOX2_RIGHT;
                    } else if(map[tmpX][t].equals(BOX2_RIGHT)) {
                        map[tmpX][t] = BOX2_LEFT;
                    }
                }
            }
        } else {
            //上下移动
            boolean right = map[nx][ny].equals(BOX2_RIGHT);
            //广度优先
            Queue<Coordinate> queue = new LinkedList<>();
            queue.add(new Coordinate(nx, ny));
            Set<Coordinate> set = new HashSet<>();
            if(right) {
                queue.add(new Coordinate(nx, ny - 1));
            } else {
                queue.add(new Coordinate(nx, ny+1));
            }

            while (!queue.isEmpty()) {
                Coordinate coordinate = queue.poll();
                if(map[coordinate.x][coordinate.y].equals(EMPTY)) {
                    continue;
                }
                if(map[coordinate.x][coordinate.y].equals(WALL)) {
                    // 无迁移
                    return;
                }
                coordinate.str = map[coordinate.x][coordinate.y];
                set.add(coordinate);

                if(map[coordinate.x][coordinate.y].equals(EMPTY)) {
                    continue;
                }
                int line = coordinate.x + command.x;
                if(map[line][coordinate.y].equals(WALL)) {
                    return;
                }
                if(map[line][coordinate.y].equals(EMPTY)){
                    continue;
                }

                boolean tmpRight = map[line][coordinate.y].equals(BOX2_RIGHT);
                //广度优先
                Coordinate coordinate1 = new Coordinate(line, coordinate.y);
                if(!queue.contains(coordinate1)) {
                    queue.add(coordinate1);
                }
                Coordinate coordinate2;
                if(tmpRight) {

                    coordinate2 = new Coordinate(line, coordinate.y - 1);
                } else {
                    coordinate2 = new Coordinate(line, coordinate.y + 1);
                }
                if(!queue.contains(coordinate2)) {
                    queue.add(coordinate2);
                }
            }

            // 刷新
            for (Coordinate coordinate : set) {
                // 先刷空
                map[coordinate.x][coordinate.y] = EMPTY;
            }
            for (Coordinate coordinate : set) {
                // 先刷空
                map[coordinate.x + command.x][coordinate.y] = coordinate.str;
            }

            // 刷robot
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
            String[] tmp = new String[2*m.length];
            for (int j = 0; j < m.length; j++) {
                if(m[j].equals(ROBOT)) {
                    robot.x = i;
                    robot.y = 2*j;
                    tmp[2*j] = ROBOT;
                    tmp[2*j+1] = EMPTY;
                }else if(m[j].equals(WALL)) {
                    tmp[2*j] = WALL;
                    tmp[2*j+1] = WALL;
                } else if(m[j].equals(EMPTY)){
                    tmp[2*j] = EMPTY;
                    tmp[2*j+1] = EMPTY;
                } else if(m[j].equals(BOX)){
                    tmp[2*j] = BOX2_LEFT;
                    tmp[2*j+1] = BOX2_RIGHT;
                }
            }
            map[i] = tmp;
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

    String str;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(int x, int y, String str) {
        this.x = x;
        this.y = y;
        this.str = str;
    }
}
