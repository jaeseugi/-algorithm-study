package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class Main_16173_점프왕쩰리 {

    /**
     * '쩰리’가 정사각형 구역의 외부로 나가는 경우엔 바닥으로 떨어져 즉시 게임에서 패배
     * ‘쩰리’의 출발점은 항상 정사각형의 가장 왼쪽, 가장 위의 칸  → (0,0)
     * ‘쩰리’가 이동 가능한 방향은 오른쪽과 아래  → dx,dy
     * ‘쩰리’가 가장 오른쪽, 가장 아래 칸에 도달하는 순간, 그 즉시 ‘쩰리’의 승리  → (n-1,n-1)
     * ‘쩰리’가 한 번에 이동할 수 있는 칸의 수는, 현재 밟고 있는 칸에 쓰여 있는 수  → move
     */

    // 우, 하
    static int[] dx = {1, 0};
    static int[] dy = {0, 1};
    static boolean isArrived = false;
    static final String SUCC = "HaruHaru";
    static final String FAIL = "Hing";

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[][] sq = new int[num][num];


        for (int i = 0; i < num; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < num; j++) {
                sq[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<int[]> q = new LinkedList<>();
        // 큐 맨 뒤에 값 삽입
        q.offer(new int[]{0, 0, sq[0][0]});
        bfs(num, sq, q);
        String msg = isArrived ? SUCC : FAIL;
        System.out.println(msg);

    }

    public static void bfs(int num, int[][] sq, Queue<int[]> q) {

        label:
        while (!q.isEmpty()) {

            // 현재 좌표
            int[] cur = q.poll();

            // 이방 탐색 시작
            for (int i = 0; i < 2; i++) {
                int nx = cur[0] + dx[i] * cur[2];
                int ny = cur[1] + dy[i] * cur[2];

                if(cur[0] == nx && cur[1] == ny) break;

                // x,y 중 하나라도 이동한 값이 n-1 보다 큰 경우 그 즉시 종료
                if (nx >= num || ny >= num) continue;

                // (x,y)가 (n-1,n-1) 도달시 정상 종료
                if (nx == num - 1 && ny == num - 1) {
                    isArrived = true;
                    break label;
                }

                q.offer(new int[]{nx, ny, sq[nx][ny]});
            }
        }
    }
}