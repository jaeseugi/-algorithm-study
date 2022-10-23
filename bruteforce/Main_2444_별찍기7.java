package bruteforce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main_2444_별찍기7 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());

        String empty = " ";
        String start = "*";

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < num; i++) {
            for (int j = i; j < num - 1; j++) {
                sb.append(empty);
            }
            for (int k = 0; k < i * 2 + 1; k++) {
                sb.append(start);
            }
            sb.append("\n");
        }

        for (int i = 0; i < num - 1; i++) {
            for (int k = 0; k < i + 1; k++) {
                sb.append(empty);
            }
            for (int j = 0; j < (num * 2 - 3) - (i * 2); j++) {
                sb.append(start);
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
