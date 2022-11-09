package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main_14888_연산자끼워넣기 {

    //  연산자 우선 순위를 무시하고 앞에서부터 진행해야 한다. 또, 나눗셈은 정수 나눗셈으로 몫만 취함.
    // 음수를 양수로 나눌 때는 C++14의 기준을 따른다. 즉, 양수로 바꾼 뒤 몫을 취하고, 그 몫을 음수로 바꾼 것과 같다. 양수로 바꿔서 나누고 몫을 음수로 치환
    // 음수를 양수로 나눌때

    static private int max = Integer.MIN_VALUE;
    static private int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(br.readLine());
        int[] nums = new int[size];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < size; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> list = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int AA = 0; AA < 4; AA++) {
            int cnt = Integer.parseInt(st.nextToken());
            for (int BB = 0; BB < cnt; BB++) {
                if (AA == 0) list.add(0);
                else if (AA == 1) list.add(1);
                else if (AA == 2) list.add(2);
                else list.add(3);
            }
        }

        // 실제 사용할 연산자가 담긴 배열
        Integer[] op = list.toArray(new Integer[size - 1]);

        // 연산시 사용될 연산자 담긴 배열
        int[] apply = new int[size - 1];

        // op배열의 방문여부 담긴 배열
        boolean[] visit = new boolean[size - 1];

        dfs(nums, size - 1, op, apply, visit, 0);

        System.out.println(max);
        System.out.println(min);
    }

    private static void dfs(int[] nums, int opSize, Integer[] op, int[] apply, boolean[] visit, int depth) {

        if (depth == opSize) {
            int rsult = nums[0];
            for (int i = 0; i < opSize; i++) {
                if (apply[i] == 0) {
                    rsult += nums[i + 1];
                } else if (apply[i] == 1) {
                    rsult -= nums[i + 1];
                } else if (apply[i] == 2) {
                    rsult *= nums[i + 1];
                } else {
                    rsult /= nums[i + 1];
                }
            }
            max = (max < rsult) ? rsult : max;
            min = (min > rsult) ? rsult : min;
            return;
        }
        for (int i = 0; i < opSize; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            apply[depth] = op[i];
            dfs(nums, opSize, op, apply, visit, depth + 1);
            visit[i] = false;
        }
    }
}
