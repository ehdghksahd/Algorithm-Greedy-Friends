public class Solution {

	public String solution(String video_len,String current_pos,String opening_start,String opening_end,String[] commands) {

		int videoLengthSec = toSeconds(video_len);
		int currentSec = toSeconds(current_pos);
		int openingStartSec = toSeconds(opening_start);
		int openingEndSec = toSeconds(opening_end);

		// 시작 위치가 오프닝 구간이면 먼저 건너뛰어야함
		if (isInOpening(currentSec, openingStartSec, openingEndSec)) {
			currentSec = openingEndSec;
		}

		for (String command : commands) {
			if (command.equals("prev")) {
				currentSec -= 10;
				if (currentSec < 0) {
					currentSec = 0;
				}
			} else {
				currentSec += 10;
				if (currentSec > videoLengthSec) {
					currentSec = videoLengthSec;
				}
			}

			// 이동 후 오프닝 구간이면 또 건너뛰기
			if (isInOpening(currentSec, openingStartSec, openingEndSec)) {
				currentSec = openingEndSec;
			}
		}

		return toTimeString(currentSec);
	}

	private int toSeconds(String time) {
		String[] parts = time.split(":");
		int minutes = Integer.parseInt(parts[0]);
		int seconds = Integer.parseInt(parts[1]);
		return minutes * 60 + seconds;
	}

	private String toTimeString(int totalSeconds) {
		int minutes = totalSeconds / 60;
		int seconds = totalSeconds % 60;
		return String.format("%02d:%02d", minutes, seconds);
	}

	private boolean isInOpening(int currentSec, int openingStartSec, int openingEndSec) {
		return currentSec >= openingStartSec && currentSec <= openingEndSec;
	}
}
