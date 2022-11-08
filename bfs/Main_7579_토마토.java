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
        Queue<int[]> queue = new LinkedList<>();
        // 익어야될 토마토 개수
        int zeroCnt = 0;

        for (int k = 0; k < h; k++) {
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < m; j++) {
                    map[k][i][j] = Integer.parseInt(st.nextToken());
                    // 익은 토마토 기준으로 탐색
                    if (map[k][i][j] == 1) {
                        queue.offer(new int[]{k, i, j});
                    }else if(map[k][i][j] == 0){
                        zeroCnt++;
                    }
                }
            }
        }
        int[] sol = bfs(h, n, m, map, queue, zeroCnt);
        System.out.println(sol[1] == 0 ? sol[0] : -1);
    }

    private static int[] bfs(int h, int n, int m, int[][][] map, Queue<int[]> queue ,int zeroCnt) {
        int hour = -1;
        int size = queue.size();
        while (!queue.isEmpty()) {
            // 동일한 레벨의 큐의 사이즈의 루프 이후에는 다음 레벨을 의미한다.
            for (int l = 0; l < size; l++) {
                int[] cur = queue.poll();
                int k = cur[0];
                int i = cur[1];
                int j = cur[2];

                for (int d = 0; d < 6; d++) {
                    int nk = k + dk[d];
                    int ni = i + di[d];
                    int nj = j + dj[d];

                    if (ni < 0 || nj < 0 || nk < 0 || ni > n - 1 || nj > m - 1 || nk > h - 1 ) continue;
                    if (map[nk][ni][nj] == 0) {
                        map[nk][ni][nj] = 1;
                        zeroCnt--;
                        queue.offer(new int[]{nk, ni, nj});
                    }
                }
            }
            // 동일한 레벨의 큐 루프가 돌고 나면 다음 시간을 의미
            hour++;
            // 다음 레벨의 큐 사이즈 설정
            size = queue.size();
        }
        return new int[]{hour, zeroCnt};
    }
}
