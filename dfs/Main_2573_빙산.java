package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_2573_빙산 {

    // 상 하 좌 우
    static int[] di = {1, -1, 0, 0};
    static int[] dj = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        // 실제 맵
        int[][] arr = new int[n][m];
        // 복사 맵
        int[][] copy = new int[n][m];
        // 방문 맵
        boolean[][] visit = new boolean[n][m];
        // 섬 개수
        int island = 0;
        // 년
        int year = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                copy[i][j] = arr[i][j];
            }
        }

        label:
        while (true) {
            // 섬 두개 인지 체크
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] > 0 && !visit[i][j]) {
                        visit[i][j] = true;
                        dfs(n, m, i, j, arr, visit);
                        if (++island > 1 ){
                            System.out.println(year);
                            break label;
                        }
                    }
                }
            }

            // 섬 개수 초기화
            island = 0;

            // 해마다 섬이 낮아짐을 처리
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (arr[i][j] > 0) {
                        after(n, m, i, j, arr, copy);
                    }
                }
            }

            // 섬이 하나도 없는지 체크
            if (checkZero(copy)) {
                System.out.println("0");
                return;
            }

            // 처리 후 년 증가
            year++;

            // 깊은 복사
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    arr[i][j] = copy[i][j];
                    visit[i][j] = false;
                }
            }
        }

    }

    public static boolean checkZero(int[][] copy) {
        for (int[] ints : copy) {
            for (int anInt : ints) {
                if (anInt > 0) {
                    return false;
                }
            }
        }
        // 전체 0인 경우
        return true;
    }

    public static void after(int n, int m, int i, int j, int[][] arr, int[][] copy) {
        int cnt = 0;
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];


            if (ni > n - 1 || nj > m - 1 || ni < 0 || nj < 0) continue;

            if (arr[ni][nj] == 0) cnt++;
        }

        int val = copy[i][j] - cnt;
        copy[i][j] = (val < 0) ? 0 : val;
    }
    public static void dfs(int n, int m, int i, int j, int[][] arr, boolean[][] visit) {
        for (int d = 0; d < 4; d++) {
            int ni = i + di[d];
            int nj = j + dj[d];

            if (ni > n - 1 || nj > m - 1 || ni < 0 || nj < 0 || visit[ni][nj]) continue;
            visit[ni][nj] = true;
            if (arr[ni][nj] > 0) dfs(n, m, ni, nj, arr, visit);
        }
    }
}
