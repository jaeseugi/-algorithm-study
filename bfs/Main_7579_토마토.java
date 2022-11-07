package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7579_토마토 {

    // -1 (토마토 X), 0 (안익은 토마토), 1(익은 토마토)
    // 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다.
    // 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다.
    // 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력
    // 토마토가 모두 익지는 못하는 상황이면 -1을 출력

    // 위, 아래 , 왼, 오, 앞 , 뒤
    public static int[] dk = {-1, 1, 0, 0, 0, 0};
    public static int[] di = {0, 0, -1, 1, 0, 0};
    public static int[] dj = {0, 0, 0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 가로
        int m = Integer.parseInt(st.nextToken());
        // 세로
        int n = Integer.parseInt(st.nextToken());
        // 높이
        int h = Integer.parseInt(st.nextToken());

        int[][][] map = new int[h][n][m];
        boolean[][][] visit = new boolean[h][n][m];

        Queue<int[]> queue = new LinkedList<>();


        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    map[k][i][j] = Integer.parseInt(st.nextToken());
                    if (map[k][i][j] == -1) visit[k][i][j] = true;
                    else if (map[k][i][j] == 1) {
                        visit[k][i][j] = true;
                        queue.offer(new int[]{k, i, j, 0});
                    }
                }
            }
        }
        int hoour = bfs(h, n, m, map, visit, queue);
        // visit중 방문하지 않는 곳이 있다면, 모든 토마토가 익지 못하는 상황이므로 체크를 해줘야한다.
        System.out.println(everyVisited(visit) ? hoour : -1);
    }

    private static int bfs(int h, int n, int m, int[][][] map, boolean[][][] visit, Queue<int[]> queue) {

        // 시간
        int hour = 0;
        // 현재 큐가 가진 시간, 시간이 바뀐는 지점을 체크 하기 위한 비교
        int temp = 0, change = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int k = cur[0];
            int i = cur[1];
            int j = cur[2];
            int cnt = temp = cur[3];

            // 큐에서 꺼낸 시간 값이 이전의 시간 값이 다르다는것은 시간이 흐른것을 의미 한다.
            if (change != temp) {
                change = temp;
                hour++;
            }
            for (int d = 0; d < 6; d++) {
                int nk = k + dk[d];
                int ni = i + di[d];
                int nj = j + dj[d];

                if (ni < 0 || nj < 0 || nk < 0 || ni > n - 1 || nj > m - 1 || nk > h - 1 || visit[nk][ni][nj]) continue;
                // 0인 경우, 아직 덜 익은 곳이므로 탐색 진행을 위해 큐에 담고 방문 처리를 한다.
                if (map[nk][ni][nj] == 0) {
                    visit[nk][ni][nj] = true;
                    queue.offer(new int[]{nk, ni, nj, cnt + 1});
                }
            }
        }
        return hour;
    }

    private static boolean everyVisited(boolean[][][] visit) {
        for (boolean[][] booleans : visit) {
            for (boolean[] aBoolean : booleans) {
                for (boolean b : aBoolean) {
                    if (b == false) return false;
                }
            }
        }
        return true;
    }
}
