package pers.yangs.advent.y2024.day17;

import pers.yangs.advent.y2024.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className: Day17
 * @Description：
 * @Author: 卡卡西
 * @Date: 2024/12/21
 **/
public class Day17 {

    static Integer A;
    static Integer B;
    static Integer C;

    static Integer PC = 0;

    static StringBuilder OUT = new StringBuilder();

    static Map<Integer, Opcode> opcodes = new HashMap<>(){
        {
            put(0, operand -> {
                operand = getComboOperand(operand);
                double res  = A / Math.pow(2, operand);
                A = (int) res;
                PC +=2;
            });

            put(1, operand -> {
                B = B ^ operand;
                PC +=2;
            });

            put(2, operand -> {
                operand = getComboOperand(operand);
                B = operand % 8;
                PC +=2;
            });

            put(3, operand -> {
                if(A == 0) {
                    PC +=2;
                } else {
                    PC = operand;
                }
            });

            put(4, operand -> {
                B = B ^ C;
                PC +=2;
            });

            put(5, operand -> {
                operand = getComboOperand(operand);
                int val = operand % 8;
                OUT.append(val).append(",");
                PC +=2;
            });

            put(6, operand -> {
                operand = getComboOperand(operand);
                double res  = A / Math.pow(2, operand);
                B = (int) res;
                PC +=2;
            });

            put(7, operand -> {
                operand = getComboOperand(operand);
                double res  = A / Math.pow(2, operand);
                C = (int) res;
                PC +=2;
            });
        }
    };
    public static void main(String[] args) {
//        String input = Util.readFile("src/main/java/pers/yangs/advent/y2024/day17/1.txt");
//        String input = Util.readFile("src/main/java/pers/yangs/advent/y2024/day17/2.txt");
        String input = Util.readFile("src/main/java/pers/yangs/advent/y2024/day17/3.txt");
        String[] lines = input.split("\n\n");
        String[] datas = lines[0].split("\n");
        A = getNum(datas[0]);
        B = getNum(datas[1]);
        C = getNum(datas[2]);

        String instructionStr = lines[1].split(": ")[1];
        Integer[] instructions = Arrays.stream(instructionStr.split(",")).map(Integer::parseInt).toList()
                .toArray(value -> new Integer[]{value});
        resolve(instructions);
        System.out.println(OUT.substring(0, OUT.length()-1));
    }
//    0,3,5,4,3,0
//    5,7,3,0

    private static int getNum(String str) {
        Pattern compile = Pattern.compile("\\d+");
        Matcher matcher = compile.matcher(str);
        if(matcher.find()) {
            return Integer.parseInt(matcher.group());
        }
        throw new RuntimeException();
    }

    private static void resolve(Integer[] instructions) {
        while (PC < instructions.length - 1) {
            getOpcode(instructions[PC])
                    .doAlgorithm(instructions[PC+1]);
        }
    }

    private static int getComboOperand(int operand) {
        if (0 <= operand && operand <= 3) {
            return operand;
        } else if (4 == operand) {
            return A;
        } else if (5 == operand) {
            return B;
        } else if (6 == operand) {
            return C;
        } else {

        }
        throw new RuntimeException("error");
    }

    private static Opcode getOpcode(int num) {
        return opcodes.get(num);
    }

    interface Opcode {
        void doAlgorithm(int operand);
    }

//    private static void doMy{
//        int b = 0;
//        int a = 30899381;
//        int c = 0;
//
//        b = 4 % 8;
//        b = b ^ 1;
//        c = (int) (a / Math.pow(2, b));
//        b = b ^ c;
//        a = a % 8;
//        b = b^c;
//
//    }
}


