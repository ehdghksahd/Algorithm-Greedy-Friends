package livecoding;

public class PG_두_큐_합_같게_만들기 {
    class Solution {
        public int solution(int[] queue1, int[] queue2) {
            // 주어진 숫자들을 조합하여 총합의 절반값을 만들 수 있는지 체크
            // q1과 q2를 붙여버리면..?

            int l = queue1.length;
            int[] nums = new int[l * 2];
            long sum1 = 0;
            long sum2 = 0;

            int idx = 0;

            for(int n: queue1){
                nums[idx++] = n;
                sum1 += n; // queue1의 총합
            }
            for(int n: queue2){
                nums[idx++] = n;
                sum2 += n; // queue2의 총합
            }

            // 총합이 홀수면 정수로 같은 값을 만들 수 없다.
            long sum = sum1 + sum2;
            if(sum % 2 != 0){
                return -1;
            }

            long goal = sum / 2; // 두 큐의 목표값

            // 둘 다로 생각하지 말자.
            // 1개 배열의 총합을 목표에 맞추면 저절로 다른 배열도 일치...
            // q1의 앞에서 뺄거 빼고, q2의 앞에서 더할거 더하고?
            // -> q1에서 길이만큼 다 빼면 q1이랑 q2의 위치만 바뀐거니까
            int left = 0;
            int right = l;
            int count = 0;

            while(left < l*2 && right < l*2){
                if(sum1 == goal){
                    return count;
                }
                if(sum1 < goal){
                    sum1 += nums[right++];
                } else if(sum1 > goal){
                    sum1 -= nums[left++];
                }
                count++;
            }
            return -1;
        }
    }
}
