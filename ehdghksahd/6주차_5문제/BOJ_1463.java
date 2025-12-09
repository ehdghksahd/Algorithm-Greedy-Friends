import java.util.*;
import java.io.*;

public class Main{
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[1000001];

        for(int i = 2; i <= N; i++) {

            nums[i] = nums[i - 1] + 1; // nums[i] i번째 값의 최소 횟수

            if(i % 2 == 0) nums[i] = Math.min(nums[i], nums[i / 2] + 1);
            if(i % 3 == 0) nums[i] = Math.min(nums[i], nums[i / 3] + 1);

        }
        System.out.println(nums[N]);
    }
}
