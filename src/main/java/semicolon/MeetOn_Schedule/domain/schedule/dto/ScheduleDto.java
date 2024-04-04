package semicolon.MeetOn_Schedule.domain.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public class ScheduleDto {

    @Getter
    public static class CreateRequest {
        private String title;
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;
        @DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;

        @Builder
        public CreateRequest(String title, String startTime, String endTime) {
            this.title = title;
            this.startTime = LocalDateTime.parse(startTime);
            this.endTime = LocalDateTime.parse(endTime);
        }
    }
}
