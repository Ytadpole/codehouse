package pers.yangs.advent.y2025.day5;

import pers.yangs.advent.y2025.MyUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @className: Day5
 * @Description：
 * @Author: 卡卡西
 * @Date: 2025/12/24
 **/
public class Day5 {

    public static void main(String[] args) {
        List<String> inputs = MyUtil.readLines("/Users/yangsong/code/java/codehourse/src/main/java/" +
                "pers/yangs/advent/y2025/day5/input.pro");
        InputObj inputObj = parseInputs(inputs);
//        long res = part1(inputObj);
        long res = part2(inputObj);
        System.out.println(res);
    }

    private static long part2(InputObj inputObj) {
        List<Range> oldRanges = inputObj.ranges;
        List<Range> newRanges = inputObj.ranges;
        do {
            oldRanges = newRanges;
            newRanges = resize(oldRanges);
        } while (newRanges.size() != oldRanges.size());
//        System.out.println(set);

        long res  =0;
        for (Range range : newRanges) {
            res += range.end - range.start + 1;
        }
        return res;
    }

    private static List<Range> resize(List<Range> oldRanges) {
        List<Range> newRanges = new ArrayList<>();
        newRanges.add(new Range(oldRanges.get(0).start, oldRanges.get(0).end));

        for (int i = 1; i < oldRanges.size(); i++) {
            Range oldRange = oldRanges.get(i);
            boolean cover = false;
            for (int j = 0; j < newRanges.size(); j++) {
                Range newRange = newRanges.get(j);
                if(oldRange.start >= newRange.start && oldRange.start <= newRange.end){
                    // start在里面
                    newRange.end = Math.max(oldRange.end, newRange.end);
                    cover = true;
                    break;
                }else if(oldRange.end >= newRange.start && oldRange.end <= newRange.end) {
                    // end在里面
                    newRange.start = Math.min(oldRange.start, newRange.start);
                    cover = true;
                    break;
                } else if(oldRange.start < newRange.start && oldRange.end > newRange.end) {
                    // curRange包含resRange
                    newRange.start = oldRange.start;
                    newRange.end = oldRange.end;
                    cover = true;
                    break;
                } else if(oldRange.start > newRange.start && oldRange.end < newRange.end){
                    // resRange包含curRange
                    cover = true;
                    break;
                } else {
                    // 继续下一个
                }
            }
            if(!cover) {
                newRanges.add(new Range(oldRange.start, oldRange.end));
            }
        }
        return newRanges;
    }

    private static long part1(InputObj inputObj) {
        long res = 0;
        for (Long id : inputObj.ids) {
            for (Range range : inputObj.ranges) {
                if(id >= range.start && id <= range.end){
                    res++;
                    System.out.println(id);
                    break;
                }
            }
        }
        return res;
    }

    private static InputObj parseInputs(List<String> inputs) {
        InputObj inputObj = new InputObj();
        boolean isRanges = true;
        for (String input : inputs) {
            if("".equals(input)){
                isRanges = false;
                continue;
            }
            if(isRanges){
                String[] rangeArr = input.split("-");
                Range range = new Range(Long.parseLong(rangeArr[0]), Long.parseLong(rangeArr[1]));
                inputObj.ranges.add(range);
            }else{
                inputObj.ids.add(Long.parseLong(input));
            }
        }
        return inputObj;
    }

    static class InputObj {
       public List<Range> ranges = new ArrayList<>();

       public List<Long> ids = new ArrayList<>();


    }

    static class Range {
        long start;
        long end;

        public Range(long start, long end) {
            this.start = start;
            this.end = end;
        }


    }
}
