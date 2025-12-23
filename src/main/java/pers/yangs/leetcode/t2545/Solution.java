package pers.yangs.leetcode.t2545;

/**
 * @className: Solution
 * @Description：
 * @Author: 卡卡西
 * @Date: 2024/12/21
 **/
public class Solution {

    public static void main(String[] args) {
        int[][] arr = {
                {26063, 77277, 24329, 97449, 83641, 98499, 99037, 73504, 35216, 21060},
                {79689, 20580, 86773, 52964, 84323, 78255, 42425, 20990, 74117, 1685},
                {78047, 83048, 30042, 95812, 56302, 77661, 95589, 51556, 83830, 2765},
                {70023, 9694, 63807, 23997, 967, 71957, 55944, 46460, 4003, 61615},
                {90149, 53227, 97853, 73362, 27354, 60268, 57710, 99030, 5226, 46597},
                {80916, 99369, 90239, 55759, 95899, 64117, 50449, 43677, 63001, 14245},
                {84407, 92198, 61438, 66791, 16945, 16469, 35322, 46244, 78847, 6529},
                {97658, 46783, 86167, 18462, 29233, 89548, 1402, 4549, 23593, 60733},
                {89982, 41773, 52974, 46354, 3821, 16637, 1718, 96385, 5214, 63120},
                {14928, 38392, 35017, 62313, 91433, 34791, 29599, 69491, 35111, 8683}

        };
//        int i = 0;
//        int j = 2;
//        // 交换二维数组中的两行
//        int[] temp = arr[i];
//        arr[i] = arr[j];
//        arr[j] = temp;
//
//        // 输出交换后的二维数组查看结果
//        for (int[] row : arr) {
//            for (int element : row) {
//                System.out.print(element + " ");
//            }
//            System.out.println();
//        }
        System.out.println(new Solution().sortTheStudents(arr, 2));
    }

    public int[][] sortTheStudents(int[][] score, int k) {
        for (int i = 0; i < score.length; i++) {
            int[] s1 = score[i];
            for (int j = i + 1; j < score.length; j++) {
                int[] s2 = score[j];
                if (s1[k] < s2[k]) {
                    int[] tmp = s1;
                    score[i] = s2;
                    score[j] = tmp;
                    s1 = s2;
                }
            }
        }
        return score;
    }

}
