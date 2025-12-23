package pers.yangs.advent.y2024;

/**
 * @className: Coordinate
 * @Description：
 * @Author: 卡卡西
 * @Date: 2024/12/17
 **/
public class Coordinate {
    public int x;

    public int y;

    public int dir;

    public String str;

    public Coordinate() {
    }

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinate(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public Coordinate(int x, int y, int dir, String str) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.str = str;
    }
}
