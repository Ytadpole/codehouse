package pers.yangs.advent.y2024.day16;

import pers.yangs.advent.y2024.Coordinate;
import pers.yangs.advent.y2024.Util;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @className: Day16
 * @Description：
 * @Author: 卡卡西
 * @Date: 2024/12/17
 **/
public class Day16 {

    static Map<Integer, Coordinate> dirs = new HashMap<>() {
        {
            put(0, new Coordinate(0, 1));
            put(1, new Coordinate(1, 0));
            put(2, new Coordinate(0, -1));
            put(3, new Coordinate(-1, 0));
        }
    };

    static Integer[][][] dp ;

    static Integer init = Integer.MAX_VALUE - 2000;

    static Coordinate start;

    public static void main(String[] args) {
        String[][] map = Util.parseInputToStringArray2("src/main/java/pers/yangs/advent/y2024/day16/1.txt");
//        String[][] map = Util.parseInputToStringArray2("src/main/java/pers/yangs/advent/y2024/day16/2.txt");

        dp = new Integer[map.length][map[0].length][4];

        Coordinate robot = new Coordinate();
        Coordinate end = new Coordinate();
        getStartAndEnd(map, robot, end);

        List<Coordinate> path = new LinkedList<>();

        int score = resolve(map, robot, end, "");
        System.out.println(score);
    }

    private static int resolve(String[][] map, Coordinate robot, Coordinate end, String stat) {
        String cuStr = "("+robot.x+","+robot.y+")";

        if(robot.x == end.x && robot.y == end.y) {
            System.out.println(stat + " end");
            return 0;
        }
//        if( robot.x == start.x && robot.y == start.y) {
//            return init;
//        }
        if(null != dp[robot.x][robot.y]) {
            Integer[] vals = dp[robot.x][robot.y];
            int val = init;
            if(null == vals[0] && null == vals[1] && null == vals[2] && null == vals[3]) {

            }
            else {
                for (int i = 0; i < vals.length; i++) {
//                int addVal = getAddVal(i, robot);
                    Integer addVal = vals[i];
                    if (init.equals(addVal)) {
                        continue;
                    }
                    val = Math.min(val, addVal);
                }
                return val;
            }
        }
        System.out.println(stat + cuStr);
//        stat += cuStr;
//        path.add(robot);
//        printlnPath(map, path);
        int val = init;
        for (int i = 0; i < 4; i++) {
            Coordinate dir = dirs.get(i);
            try {
                if ("#".equals(map[robot.x + dir.x][robot.y + dir.y])) {
                    dp[robot.x][robot.y][i] = init;
                    throw new RuntimeException();
                }

                int tmp = resolve(map, new Coordinate(robot.x + dir.x, robot.y + dir.y, i), end, stat + cuStr);
                if(tmp == init) {
                    throw new RuntimeException();
                }
                if (i == robot.dir) {
                    tmp = 1 + tmp;
                } else if(Math.abs(i = robot.dir) == 1){
                    tmp = 1001 + tmp;
                } else {
                    tmp = 2001 + tmp;
                }
                val = Math.min(val, tmp);
            }catch (RuntimeException e) {
                System.out.println(stat + cuStr + "," + i + " is error");
            }

        }
        dp[robot.x][robot.y][robot.dir] = val;
        System.out.println( stat + " back ");

        return val;

    }

    private static int getAddVal(int i, Coordinate robot) {
        if(i == robot.dir) {
            return 1;
        } else if (Math.abs(i - robot.dir) == 2) {
            return 1001;
        } else {
            return init;
        }
    }

    private static void getStartAndEnd(String[][] map, Coordinate robot, Coordinate end) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j].equals("S")) {
                    robot.x = i;
                    robot.y = j;
                    robot.dir = 0;
                    start = new Coordinate();
                    start.x = i;
                    start.y = j;
                }
                if (map[i][j].equals("E")) {
                    end.x = i;
                    end.y = j;
                }
            }
        }
    }
}
