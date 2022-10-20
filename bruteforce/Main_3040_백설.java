package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * packageName :  bruteforce
 * fileName : Main_3040_백설
 * author :  wotjr210
 * date : 2022/10/20
 * description :
 * ===========================================================
 * DATE                 AUTHOR                NOTE
 * -----------------------------------------------------------
 * 2022/10/20                wotjr210             최초 생성
 */
public class Main_3040_백설 {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int len = 9;
        int[] arr = new int[len];
        int sum = 0;
        for(int i = 0 ; i < len ; i++){
            arr[i] = Integer.parseInt(reader.readLine());
            sum += arr[i];
        }

        sum = sum - 100;

        for (int j = 0; j < len; j++) {
            for (int k = 0; k < len; k++) {
                if( j == k) continue;
                if( arr[j] + arr[k] == sum) {
                    for (int l = 0 ; l < len ; l++) {
                        if(l==j || l==k) continue;
                        System.out.println(arr[l]);
                    }
                    return;
                }
            }
        }
    }
}
