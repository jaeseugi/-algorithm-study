package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_15649_N과M {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 숫자
        int n = Integer.parseInt(st.nextToken());
        // 자릿수
        int m = Integer.parseInt(st.nextToken());
        // 숫자에 대한 방문
        boolean[] visit = new boolean[n];
        // 출력할 숫자를 담을 배열
        int[] number = new int[m];
        StringBuffer sb = new StringBuffer();
        dfs(m, 0, n, visit, number, sb);
        System.out.println(sb);
    }

    // 전체 자릿수, 현재 자릿수, 사용할수있는 최대 수 , 방문배열, 출력시 사용할 숫자를 담은 배열
    private static void dfs(int m, int p, int n, boolean[] visit, int[] number, StringBuffer sb) {
        // 현재 자리수가 마지막 자리수 인경우 해당 재귀함수 종료
        if (p == m) {
            for (int i : number) {
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }
        // 사용 가능한 최대 수 만큼 루프
        for (int i = 0; i < n; i++) {
            if (visit[i]) continue;
            visit[i] = true;
            number[p] = i + 1;
            dfs(m, p + 1, n, visit, number, sb);
            visit[i] = false;
        }
    }

}
