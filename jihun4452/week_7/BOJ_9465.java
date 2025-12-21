import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int s = Integer.parseInt(br.readLine());

		for (int t = 0; t < s; t++) {
			int n = Integer.parseInt(br.readLine());
			int[][] a = new int[2][n];

			for (int i = 0; i < 2; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < n; j++) {
					a[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int[] dp = {0, a[0][0], a[1][0]};

			for (int j = 1; j < n; j++) {
				int[] next = new int[3];
				next[0] = Math.max(dp[0], Math.max(dp[1], dp[2]));
				next[1] = Math.max(dp[0], dp[2]) + a[0][j];
				next[2] = Math.max(dp[0], dp[1]) + a[1][j];
				dp = next;
			}

			System.out.println(Math.max(dp[0], Math.max(dp[1], dp[2])));
		}
	}
}
// 이 문제도 마지막 로직이 생각이 나지않아서 AI도움을 받아서 마지막을 작성했습니다!