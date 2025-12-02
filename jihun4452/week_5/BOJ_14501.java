import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/**
		 * 1. N일까지 상담 일정이 있음.
		 * 2. i일 상담은 T[i]날 걸리고 P[i] 수익을 줌.
		 * 3. i일부터 시작해서 얻을 수 있는 최대 수익을 dp[i]에 넣음.
		 * 4. 마지막 날부터 역순으로 계산.
		 */

		int N = Integer.parseInt(br.readLine());
		int[] T = new int[N + 2];
		int[] P = new int[N + 2];
		int[] dp = new int[N + 2];

		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = N; i > 0; i--) {
			int end = i + T[i];

			// 상담을 할 수 있으면 수익 비교
			if (end <= N + 1) {
				dp[i] = Math.max(P[i] + dp[end], dp[i + 1]);
			} else {
				dp[i] = dp[i + 1];
			}
		}

		System.out.println(dp[1]);
	}
}
