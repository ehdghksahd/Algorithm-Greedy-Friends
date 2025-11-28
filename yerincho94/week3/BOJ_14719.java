package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
    아이디어:
        1) 높이가 0 일때는 -> 물은 왼max, 오max 구한 후, 이걸 두개 비교해서 min 벽까지만 참 (왼쪽벽, 오른쪽벽 비교시)
        2) 높이가 1 이상일때는 -> 왼쪽 max 높이, 오른쪽 max 높이 비교 후 낮은 벽 - 내 높이를 해줘야함
 */

// 빗물 (골드5)
public class BOJ_14719 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int H = Integer.parseInt(st.nextToken()); // 세로
        int W = Integer.parseInt(st.nextToken()); // 가로(높이)

        int[] currHeight = new int[W];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            currHeight[i] = Integer.parseInt(st.nextToken()); // 현재 높이 저장
        }

        // 현재 기준 왼쪽 max 높이
        int[] leftMax = new int[W];
        leftMax[0] = currHeight[0];
        for (int i = 1; i < W; i++) {
            leftMax[i] = Math.max(leftMax[i-1], currHeight[i]);
        }

        // 현재 기준 오른쪽 max 높이를 어케구하지....
        int[] rightMax = new int[W]; // 3 0 1 4
        rightMax[W-1] = currHeight[W-1];
        for (int i = W-2; i >= 0 ; i--) { // 오른쪽부터
            rightMax[i] = Math.max(rightMax[i+1], currHeight[i]);
        }

        // 이제 왼max, 오max 중 min 비교후, 고이는 빗물 누적
        int totalRain = 0;
        for (int i = 0; i < W; i++) {
            int waterHeight = Math.min(leftMax[i], rightMax[i]) - currHeight[i]; // <-- 이 부분!!!!!

            if (waterHeight > 0) { // 음수면 No count
                totalRain += waterHeight;
            }
        }
        System.out.println(totalRain);
    }
}
