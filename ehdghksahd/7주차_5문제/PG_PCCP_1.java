import java.util.*;

// https://school.programmers.co.kr/learn/courses/30/lessons/340213
// 동영상 재생기

class Solution {
    public int util1(String time) { // 받은 시간을 초로 변환
        String[] mt = time.split(":");

        int m = Integer.parseInt(mt[0]);
        int s = Integer.parseInt(mt[1]);
        return (m * 60) + s;
    }
    public String util2(int time) { // 받은 시간을 다시 mm:ss로 변환
        int m = time / 60;
        int s = time % 60;

        return String.format("%02d:%02d", m, s);
    }
    public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        String answer = "";

        int videoTime = util1(video_len);
        int posTime = util1(pos);
        int opTime = util1(op_start);
        int endTime = util1(op_end);

        for(int i = 0; i < commands.length; i++) {
            if(opTime <= posTime  && posTime <= endTime) posTime = endTime; // 시작이 오프닝 구간일 때

            if(commands[i].equals("next")){
                posTime += 10;

                if(posTime > videoTime) posTime = videoTime;
            }
            else {
                posTime -= 10;
                if(posTime < 0) posTime = 0;
            }
        }

        if(opTime <= posTime  && posTime <= endTime) posTime = endTime; // 끝났는데 오프닝 구간일 때

        answer = util2(posTime);
        return answer;
    }
}