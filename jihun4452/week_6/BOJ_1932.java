import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[][] tri = new int[n + 1][n + 1]; //방금 푼 문제와 비슷함
		int[][] dp = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= i; j++) {
				tri[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		dp[1][1] = tri[1][1];

		for (int i = 2; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				if (j == 1) {
					dp[i][j] = dp[i - 1][j] + tri[i][j];
				} else if (j == i) {
					dp[i][j] = dp[i - 1][j - 1] + tri[i][j];
				} else {
					dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j]) + tri[i][j];
				}
			}
		}//앞에것만 더해서 하믄 댈듯

		int answer = 0;
		for (int j = 1; j <= n; j++) {
			answer = Math.max(answer, dp[n][j]);
		}

		System.out.println(answer);
	}
}
