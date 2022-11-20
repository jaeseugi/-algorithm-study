package comb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main_1010_다리놓기 {
    // 강 주변에서 다리를 짓기에 적합한 곳을 사이트라고 한다.
    // 재원이는 강 주변을 면밀히 조사해 본 결과 강의 서쪽에는 N개의 사이트가 있고 동쪽에는 M개의 사이트가 있다는 것을 알았다. (N ≤ M)
    // 서쪽의 사이트와 동쪽의 사이트를 다리로 연결
    // 이때 한 사이트에는 최대 한 개의 다리만 연결
    // 서쪽의 사이트 개수만큼 (N개) 다리를 지으려고 한다.
    // 다리끼리는 서로 겹쳐질 수 없다고 할 때 다리를 지을 수 있는 경우의 수

    private static int cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력의 첫 줄에는 테스트 케이스의 개수 T
        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            // 서쪽과 동쪽에 있는 사이트의 개수 정수 N, M (0 < N ≤ M < 30)
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 서쪽
            int N = Integer.parseInt(st.nextToken());
            // 동쪽
            int M = Integer.parseInt(st.nextToken());
            // mCn

            // 동쪽 사이트 연결된곳 체크
            boolean[] visits = new boolean[M];
            AtomicInteger result = new AtomicInteger(0);

            long sTime = System.currentTimeMillis();
            comb(0, 0, N, M, visits, result);
            long eTime = System.currentTimeMillis();
            System.out.println((double) (eTime - sTime)/ 1000 + "초");

            System.out.println(result);
        }
    }

    private static void comb(int idx, int start, int N, int M, boolean[] visits, AtomicInteger result) {

        if (N - idx > M - start) {
            return;
        }

        if (idx == N) {
            result.incrementAndGet();
            return;
        }
        for (int i = start; i < M; i++) {
            if (visits[i]) continue;
            visits[i] = true;
            comb(idx + 1, i + 1, N, M, visits, result);
            visits[i] = false;
        }
    }

}
