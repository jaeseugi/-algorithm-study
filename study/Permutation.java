package study;

public class Permutation {
    public static void main(String[] args) {
        // 1 2 3 4
        // 2개
        // 4P2
        int[] num = {1, 2, 3, 4};
        boolean[] visit = new boolean[4];
        int[] answer = new int[2];
        perm(0, answer, num, visit);

    }

    private static void perm(int depth, int[] answer, int[] num, boolean[] visit) {
        if (depth == 2) {
            for (int i : answer) {
                System.out.print(i + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            answer[depth] = num[i];
            perm(depth + 1, answer, num, visit);
            visit[i] = false;
        }
    }
}
