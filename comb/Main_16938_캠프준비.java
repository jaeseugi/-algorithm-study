package comb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_16938_캠프준비 {
    //
//    백준이는 문제를 N개 가지고 있고, 모든 문제의 난이도를 정수로 수치화했다. i번째 문제의 난이도는 Ai이다.
//    캠프에 사용할 문제는 두 문제 이상이어야 한다.
//    문제가 너무 어려우면 학생들이 멘붕에 빠지고, 문제가 너무 쉬우면 학생들이 실망에 빠지게 된다.
//    따라서, 문제 난이도의 합은 L보다 크거나 같고, R보다 작거나 같아야 한다.
//    또, 다양한 문제를 경험해보기 위해 가장 어려운 문제와 가장 쉬운 문제의 난이도 차이는 X보다 크거나 같아야 한다.
//    첫째 줄에 N, L, R, X가 주어진다.
//    둘째 줄에는 문제의 난이도 A1, A2, ..., AN이 주어진다.


    private static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 문제수
        int n = Integer.parseInt(st.nextToken());
        // 문제 난이도의 합은 L보다 크거나 같고
        int l = Integer.parseInt(st.nextToken());
        // 문제 난이도의 합은 R보다 작거나 같아야 한다.
        int r = Integer.parseInt(st.nextToken());
        // 가장 어려운 문제와 가장 쉬운 문제의 난이도 차이는 X보다 크거나 같아야 한다
        int x = Integer.parseInt(st.nextToken());

        int[] levels = new int[n];
        boolean[] visits = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            levels[i] = Integer.parseInt(st.nextToken());
        }

        Integer MIN = Integer.MAX_VALUE;
        Integer MAX = Integer.MIN_VALUE;

        // 캠프에 사용할 문제는 두 문제 이상
        comb(0, n, l, r, x, levels, visits, 0, MIN, MAX);
        System.out.println(answer);
    }

    private static void comb(int idx, int n, int l, int r, int x, int[] levels, boolean[] visits, int sum, int min, int max) {
        // 합계가 R보다 큰경우는 더 이상 조합을 진행할 필요 없음
        if( sum  > r ) return;
        // 맞다고 리턴 시키면 안됨, 그 다음것도 정답이 될 수 있기때문
        if (sum >= l && sum <= r && (max - min) >= x && !(sum == min && sum == max)) {
            answer++;
        }
        for (int i = idx; i < n; i++) {
            if (visits[i]) continue;
            visits[i] = true;
            comb(i + 1, n, l, r, x, levels, visits, sum + levels[i], (levels[i] < min) ? levels[i] : min, (levels[i] > max) ? levels[i] : max);
            visits[i] = false;
        }
    }
}