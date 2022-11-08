package bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_2178_미로찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] miro = new char[n][m];

        for (int i = 0; i < n; i++) {
            miro[i] = br.readLine().toCharArray();
        }

        bfs(n, m, miro);
    }

    private static void bfs(int n, int m, char[][] miro) {
        int[] di = {-1, 1, 0, 0};
        int[] dj = {0, 0, -1, 1};

        // 시작은 0,0
        Queue<int[]> queue = new LinkedList<>();
        miro[0][0] = '0';
        queue.offer(new int[]{0, 0});

        int size = queue.size();
        int cnt = 1;

        while (!queue.isEmpty()) {
            cnt++;
            for (int s = 0; s < size; s++) {
                int[] cur = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int ni = cur[0] + di[d];
                    int nj = cur[1] + dj[d];
                    if(ni < 0 || nj < 0 || ni > n -1 || nj > m -1 || (miro[ni][nj] != '1')) continue;

                    // 종료지점 n,m
                    // 항상 마지막 종료지점(n,m) 도달 가능한 입력값만 주어짐
                    // 따로 못갔을 경우 체크할 필요 없음
                    if(ni == n-1 && nj == m-1) {
                        System.out.println(cnt);
                        return;
                    }
                    miro[ni][nj] = '0';
                    queue.offer(new int[]{ni, nj});
                }
            }
            size = queue.size();
        }
        System.out.println(cnt);
    }
}
