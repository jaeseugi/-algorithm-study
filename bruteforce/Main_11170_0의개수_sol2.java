package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * packageName :  bruteforce
 * fileName : Main_11170_0의개수_sol1
 * author :  wotjr210
 * date : 2022/10/21
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2022/10/21                wotjr210             최초 생성
 */
public class Main_11170_0의개수_sol2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine());
        String[] arr = new String[t];
        for (int i = 0; i < t; i++) {
            arr[i] = br.readLine();
        }

        int n, m;
        int[] rslt = new int[t];

        for (int i = 0; i < t; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(arr[i]);
            n = Integer.parseInt(stringTokenizer.nextToken());
            m = Integer.parseInt(stringTokenizer.nextToken());
            int cnt = 0;
            for (int j = n; j <= m; j++) {
                if(j == 0 ) {
                    cnt++;
                    continue;
                }
                if(j < 10) continue;

                int o = j;
                // 10으로 계속 나누고 나머지가 0 이면 카운팅
                while(o/10>0){
                    if(o%10==0) cnt++;
                    o = o / 10;
                }
            }
            System.out.println(cnt);
        }
    }
}
