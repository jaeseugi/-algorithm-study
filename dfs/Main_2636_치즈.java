package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2636_치즈 {

    // 상 하 좌 우
    static int[] di = {-1, 1, 0, 0};
    static int[] dj = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());


        // 세로
        int n = Integer.parseInt(st.nextToken());
        // 가로
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        boolean[][] visit = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int meltCnt = 0;
        int preMeltCnt = 0;
        int totalCnt = 0;

        while (true) {
            meltCnt = 0;
            visit[0][0] = true;
            dfs(n, m, 0, 0, arr, visit);

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    visit[i][j] = false;
                    if (arr[i][j] == 3) {
                        meltCnt++;
                        arr[i][j] = 0;
                    }
                }
            }

            if (meltCnt != 0) {
                totalCnt++;
                preMeltCnt = meltCnt;
            }
            if (meltCnt == 0) {
                System.out.println(totalCnt);
                System.out.println(preMeltCnt);
                return;
            }
        }
    }

    static private void dfs(int n, int m, int i, int j, int[][] arr, boolean[][] visit) {
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];
            if (ni > n - 1 || nj > m - 1 || ni < 0 || nj < 0 || visit[ni][nj]) continue;
            // 인접한 곳 0 으로 만들기
            visit[ni][nj] = true;
            if (arr[ni][nj] == 0) {
                dfs(n, m, ni, nj, arr, visit);
            } else {
                arr[ni][nj] = 3;
            }
        }
    }
}
