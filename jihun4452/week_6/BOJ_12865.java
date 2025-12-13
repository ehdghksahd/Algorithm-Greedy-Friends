import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/**
		 * 1. 각 물건은 넣거나 안 넣거나 두 가지 선택만 가능 1,0으로 표시하면 될거같음
		 * 2. dp[i][w] = i번째 물건까지 생각, 무게 w 이하로 얻는 최대 가치
		 * 3. 현재 물건 무게가 w보다 크면 못 넣고, 작거나 같으면 안 넣는 경우와 넣는 경우 중 최댓값 선택
		 */

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		int[] W = new int[N + 1];
		int[] V = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			W[i] = Integer.parseInt(st.nextToken());
			V[i] = Integer.parseInt(st.nextToken());
		}

		int[][] dp = new int[N + 1][K + 1];

		for (int i = 1; i <= N; i++) {
			for (int w = 1; w <= K; w++) {
				if (W[i] > w) {
					dp[i][w] = dp[i - 1][w];
				} else {
					dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - W[i]] + V[i]);
				}
			}
		}

		System.out.println(dp[N][K]);
	}
}