// PG / 동영상 재생기 / Lv. 1
package somyoung.week7;

class PG_동영상재생기 {
    public static String solution(String video_len, String pos, String op_start, String op_end, String[] commands) {
        int videoLen = convertSec(video_len); // 비디오 전체 길이
        int current = convertSec(pos); // 비디오 시작 위치
        int opStart = convertSec(op_start); // 오프닝 시작 위치
        int opEnd = convertSec(op_end); // 오프닝 종료 위치

        for(String cmd : commands){
            // 오프닝 건너뛰기
            if(opStart <= current && current <= opEnd){
                current = opEnd;
            }

            if(cmd.equals("prev")){
                if(current < 10){ // 10초 미만인 경우 영상의 처음 위치로 이동
                    current = 0;
                } else {
                    current -= 10;
                }
            } else if(cmd.equals("next")){
                if((videoLen - current) < 10){ //  동영상의 남은 시간이 10초 미만일 경우 영상의 마지막 위치로 이동
                    current = videoLen;
                } else {
                    current += 10;
                }
            }
        }

        // 오프닝 건너뛰기
        if(opStart <= current && current <= opEnd){
            current = opEnd;
        }

        int min = current / 60;
        int sec = current % 60;

        return String.format("%02d:%02d", min, sec);
    }

    // 분 단위 String -> 초 단위 int로 변환
    static int convertSec(String time){
        String[] list = time.split(":");
        return Integer.parseInt(list[0]) * 60 + Integer.parseInt(list[1]);
    }

    // 테스트 케이스
    public static void main(String[] args) {
        System.out.println(solution("34:33", "13:00", "00:55", "02:55", new String[]{"next", "prev"}));
        System.out.println(solution("10:55", "00:05", "00:15", "06:55", new String[]{"prev", "next", "next"}));
        System.out.println(solution("07:22", "04:05", "00:15", "04:07", new String[]{"next"}));
    }
}
