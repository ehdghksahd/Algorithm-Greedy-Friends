package week7;

public class PG_PCCP_1 {
    class Solution {
        public String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
            int endPoint = timeToInt(video_len);
            int currentPoint = timeToInt(pos);
            int opStartPoint = timeToInt(op_start);
            int opEndPoint = timeToInt(op_end);

            for(String command: commands){
                currentPoint = opSkip(opStartPoint, currentPoint, opEndPoint);
                switch(command){
                    case "prev":
                        currentPoint -= 10;
                        if(currentPoint < 0){
                            currentPoint = 0;
                        }
                        break;
                    case "next":
                        currentPoint += 10;
                        if(currentPoint > endPoint){
                            currentPoint = endPoint;
                        }
                        break;
                }
                currentPoint = opSkip(opStartPoint, currentPoint, opEndPoint);
            }
            String answer = intToTime(currentPoint);

            return answer;
        }

        static int timeToInt(String time){ // 초 단위로 변경
            String[] parts = time.split(":");
            int m = Integer.parseInt(parts[0]);
            int s = Integer.parseInt(parts[1]);

            return m*60 + s;
        }

        static String intToTime(int time){ // 시간 표기로 변경
            int m = time / 60;
            int s = time % 60;

            return String.format("%02d:%02d", m, s);
        }

        static int opSkip(int opStartPoint, int currentPoint, int opEndPoint){
            if(opStartPoint <= currentPoint && currentPoint <= opEndPoint){
                return opEndPoint;
            }
            return currentPoint;
        }
    }
}
