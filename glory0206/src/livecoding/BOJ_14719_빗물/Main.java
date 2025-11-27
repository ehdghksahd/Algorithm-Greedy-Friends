package livecoding.BOJ_14719_빗물;

import java.util.*;
import java.io.*;

public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        // 블록 높이 배열
        int[] blocks = new int[W];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<W; i++){
            blocks[i] = Integer.parseInt(st.nextToken());
        }

        int water = 0;
        for(int i=1; i<W-1; i++){
            int leftMax = 0;
            int rightMax = 0;

            // 현재 위치로부터 왼쪽의 가장 긴 블록
            for(int j=0; j<i; j++){
                if(leftMax<blocks[j]){
                    leftMax = blocks[j];
                }
            }

            // 현재 위치로부터 오른쪽의 가장 긴 블록
            for(int z = W - 1; z > i; z--){
                if(rightMax < blocks[z]){
                    rightMax = blocks[z];
                }
            }

            // 왼쪽, 오른쪽 중 더 작은 블록 선택(물이 쌓일 높이의 기준)
            int criterion = Math.min(leftMax, rightMax);

            // 현재 위치의 블록이 더 크면 쌓일 수 없음
            if(criterion > blocks[i]){
                water += (criterion-blocks[i]);
            }
        }
        System.out.println(water);
    }
}
