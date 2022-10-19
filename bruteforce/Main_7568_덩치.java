package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * packageName :  PACKAGE_NAME
 * fileName : bruteforce.Main_7568_덩치
 * author :  wotjr210
 * date : 2022/10/19
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2022/10/19                wotjr210             최초 생성
 */
public class Main_7568_덩치 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.valueOf(br.readLine());
        int[] hights = new int[len];
        int[] weigts = new int[len];
        int[] result = new int[len];

        for (int i = 0; i < len; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            hights[i] = Integer.valueOf(st.nextToken());
            weigts[i] = Integer.valueOf(st.nextToken());
            result[i] = 1;
        }

        for (int j = 0; j < len; j++) {
            for (int k = 0; k < len; k++) {
                if(j==k) continue;
                if(hights[j]<hights[k]&&weigts[j]<weigts[k]) result[j] = result[j]+1;
            }
        }

        for (int i : result) {
            System.out.println("i = " + i);
        }
    }
}
