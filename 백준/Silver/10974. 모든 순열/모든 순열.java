import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;

public class Main {

  public static void main(String[] args) throws IOException {

    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int n = Integer.parseInt(br.readLine());

    boolean[] visits = new boolean[n];
    int[] arr = IntStream.rangeClosed(1, n).toArray();
    int[] copiedArr = new int[n];
    StringBuilder sb = new StringBuilder();

    dfs(arr, copiedArr,visits, sb, 0, n);

    System.out.println(sb.toString());
  }

  private static void dfs(int[] arr, int[] copiedArr, boolean[] visits, StringBuilder sb, int depth, int n) {

    if (depth == n) {
      Arrays.stream(copiedArr).forEach( a -> sb.append(a).append(" "));
      sb.append("\n");
      return;
    }

    for (int i = 0; i < n; i++) {
      if (visits[i]) continue;
      visits[i] = true;
      copiedArr[depth] = arr[i];
      dfs(arr,copiedArr, visits, sb, depth+1, n);
      visits[i] = false;
    }

  }
}