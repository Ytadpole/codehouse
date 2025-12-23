package pers.yangs.advent.y2025.day2;

import pers.yangs.advent.y2025.MyUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className: Day2
 * @Description：
 * @Author: 卡卡西
 * @Date: 2025/12/23
 **/
public class Day2_1 {


    public static void main(String[] args) {
        String input = MyUtil.readLines("/Users/yangsong/code/java/codehourse/src/main/java/pers/yangs/advent/y2025/day2/input.pro").get(0);
        long res = 0;
        for (String item : input.split(",")) {
            String[] split = item.split("-");
            long start = Long.parseLong(split[0]);
            long end = Long.parseLong(split[1]);
            System.out.printf("%s invalidId", item);
//            boolean invalidFlag = false;
            for (long i = start; i <= end ; i++) {
                String tmp = i+"";
                Pattern p = Pattern.compile("^(.+)\\1+$");
                Matcher m = p.matcher(tmp);
                if(m.find()) {
                    System.out.printf(" %s ", i);
                    res += i;
//                    break;
                }
            }
            System.out.println();
        }
        System.out.println(res);
    }
}
