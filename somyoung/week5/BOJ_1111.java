// [BOJ] 1111: IQ Test
package somyoung.week5;

import java.io.*;
import java.util.*;

public class BOJ_1111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // n=1일 때, 규칙을 특정할 수 없으므로 A
        if(n == 1){
            System.out.print('A');
            return;
        }

        // n=2일 때,
        if(n == 2){
            // 두 수가 같으면 같은 수
            if(arr[0] == arr[1]) System.out.print(arr[0]);
                // 두 수가 다르면 규칙을 특정할 수 없으므로 A
            else System.out.print('A');
            return;
        }

        // 규칙을 찾기 위해서는 a, b를 구해야 함
        // arr[i] = arr[i-1] * a + b
        // arr[1] = arr[0] * a + b
        // arr[2] = arr[1] * a + b
        // arr[2] - arr[1] = (arr[1] - arr[0]) * a
        // a = (arr[2] - arr[1]) / (arr[1] - arr[0])
        // b = arr[1] - (arr[0] * a)
        int a, b;
        if (arr[1] == arr[0]) { // 두 수가 같으면 모든 수열을 같은 수로 추정
            a = 1;
            b = 0;
        } else {
            a = (arr[2] - arr[1]) / (arr[1] - arr[0]);
            b = arr[1] - (arr[0] * a);
        }

        // 모든 i에 대해 arr[i] = arr[i-1] * a + b가 성립하는지 확인
        for (int i=1; i<n; i++) {
            if (arr[i] != (arr[i - 1] * a + b)){ // 하나라도 성립하지 않으면
                System.out.print('B');
                return;
            }
        }

        // 규칙 성립 시
        System.out.print(arr[n - 1] * a + b);
    }
}