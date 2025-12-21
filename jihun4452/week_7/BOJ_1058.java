import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int n = Integer.parseInt(br.readLine().trim());
		boolean[][] friend = new boolean[n][n];

		for (int i = 0; i < n; i++) {
			String line = br.readLine();
			for (int j = 0; j < n; j++) {
				friend[i][j] = (line.charAt(j) == 'Y');
			}
		}

		int maxTwoFriends = 0;

		for (int i = 0; i < n; i++) {
			int count = 0;

			for (int j = 0; j < n; j++) {
				if (i == j) continue;  // 자기 자신은 제외

				if (friend[i][j]) {
					// 직접 친구인 경우
					count++;
				} else {
					for (int k = 0; k < n; k++) {
						if (friend[i][k] && friend[k][j]) {
							count++;
							break;
						}
					}
				}
			}

			maxTwoFriends = Math.max(maxTwoFriends, count);
		}

		System.out.println(maxTwoFriends);
	}
}