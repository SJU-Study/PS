import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        int[] dp = new int[n];

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(input[i]);
        }

        int maxNum = 0;

        dp[0] = arr[0];
        for (int i = 1; i < n; i++) {
            int max = arr[i];
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    max = Math.max(max, dp[j] + arr[i]);
                }
            }
            dp[i] = max;
        }

        Arrays.sort(dp);
        System.out.println(dp[n - 1]);
        br.close();
    }
}