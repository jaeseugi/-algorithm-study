package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_7576_토마토 {

    // 사방 탐색
    // -1 (토마토 X), 0 (안익은 토마토), 1(익은 토마토)
    // 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아 익게 된다.
    // 토마토가 모두 익을 때까지의 최소 날짜를 출력해야 한다.
    // 저장될 때부터 모든 토마토가 익어있는 상태이면 0을 출력
    // 토마토가 모두 익지는 못하는 상황이면 -1을 출력

    private static int[] di = {1, -1, 0, 0};
    private static int[] dj = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 가로
        int m = Integer.parseInt(st.nextToken());
        // 세로
        int n = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        Queue<int[]> queue = new LinkedList<>();

        // 모두 익지 못할 경우를 체크 하기 위함
        int zeroCnt = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                // -1인 경운 방문할 필요가 없다.
                if (map[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                } else if(map[i][j] == 0) {
                    // 익어여햘 토마토 수 카운팅
                    zeroCnt++;
                }
            }
        }
        int[] sol = bfs(n, m, zeroCnt, map, queue);
        System.out.println(sol[1] == 0 ? sol[0] : -1);
    }

    private static int[] bfs(int n, int m, int zeroCnt, int[][] map, Queue<int[]> queue) {
        // 시간
        int hour = -1;
        // 현재 탐색 레벨의 큐 사이즈
        int size = queue.size();

        while (!queue.isEmpty()) {

            // 큐 사이즈 만큼 루프  ➝ 루프 종료시 새로운 레벨의 큐(다음시간)
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                int i = cur[0];
                int j = cur[1];

                // 사방탐색 기준으로 체크
                for (int d = 0; d < 4; d++) {
                    int ni = i + di[d];
                    int nj = j + dj[d];
                    if (ni < 0 || nj < 0 || ni > n - 1 || nj > m - 1 ) continue;
                    // 0인 경우, 아직 덜 익은 곳이므로 탐색 진행을 위해 큐에 담고 방문 처리를 한다.
                    if (map[ni][nj] == 0) {
                        map[ni][nj] = 1;
                        // 익어여할 토마토 수 차감
                        --zeroCnt;
                        queue.offer(new int[]{ni, nj});
                    }
                }
            }
            hour++;
            // 다음 레벨의 큐 사이즈를 설정
            size = queue.size();
        }
        return new int[]{hour, zeroCnt};
    }
}
