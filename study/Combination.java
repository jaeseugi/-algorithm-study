package study;

public class Combination {
    public static void main(String[] args) {
        // 1 2 3 4
        // 2개
        // 4C2
        int[] num = {1, 2, 3, 4};
        boolean[] visit = new boolean[4];
        int[] answer = new int[2];
        comb(0, 0, num, visit, answer);
    }

    private static void comb(int depth, int start, int[] num, boolean[] visit, int[] answer) {
        if (depth == 2) {
            for (int i : answer) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = start; i < 4; i++) {
            if(visit[i]) continue;
            visit[i] = true;
            answer[depth] = num[i];
            comb(depth +1, i +1, num, visit, answer);
            visit[i] = false;
        }
    }
}
