package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/**
 * packageName :  bruteforce
 * fileName : Main_5555_반지
 * author :  wotjr210
 * date : 2022/10/21
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2022/10/21                wotjr210             최초 생성
 */

public class Main_5555_반지 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        int num  = Integer.parseInt(br.readLine());

        int cnt = 0;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < num; i++) {
            sb.setLength(0);
            sb.append(br.readLine());
            sb.append(sb);
            if (sb.indexOf(str) > -1) {
                cnt++;
                continue;
            }
        }
        System.out.println(cnt);
    }
}
