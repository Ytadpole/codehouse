package pers.yangs.advent.y2025.day2;

import pers.yangs.advent.y2025.MyUtil;

import java.util.List;

/**
 * @className: Day2
 * @Description：
 * @Author: 卡卡西
 * @Date: 2025/12/23
 **/
public class Day2 {


    public static void main(String[] args) {
        String input = MyUtil.readLines("/Users/yangsong/code/java/codehourse/src/main/java/pers/yangs/advent/y2025/day2/input.pro").get(0);
        long res = 0;
        for (String item : input.split(",")) {
            String[] split = item.split("-");
            long start = Long.parseLong(split[0]);
            long end = Long.parseLong(split[1]);
            System.out.printf("%s invalidId", item);
            for (long i = start; i <= end ; i++) {
                String tmp = i+"";
                if(tmp.length() % 2 == 0) {
                    String pre = tmp.substring(0, tmp.length() / 2);
                    String suf = tmp.substring(tmp.length() / 2);
                    if(pre.equals(suf)) {
                        res += i;
                        System.out.printf(" %s ", i);
                    }
                }
            }
            System.out.println();
        }
        System.out.println(res);
    }
}
