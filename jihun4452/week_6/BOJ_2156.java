import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/**
		 * 1. 연속으로 3잔을 마실 수 없다.
		 * 2. i번째 잔에서 가능한 경우:
		 *    - 안 마심: dp[i-1]
		 *    - 1잔 연속 : dp[i-2] + wine[i]
		 *    - 2잔 연속 : dp[i-3] + wine[i-1] + wine[i]
		 * 3. dp[i] = 위 세 경우 중 최댓값
		 */

		int n = Integer.parseInt(br.readLine());

		int[] wine = new int[n + 1];
		int[] dp = new int[n + 1];

		for (int i = 1; i <= n; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}

		dp[1] = wine[1];
		if (n >= 2) {
			dp[2] = wine[1] + wine[2];
		}

		for (int i = 3; i <= n; i++) {
			dp[i] = Math.max(dp[i - 1], Math.max(dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i]));
		}
		System.out.println(dp[n]);
	}
}