package semicolon.MeetOn_Schedule.domain.schedule.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import semicolon.MeetOn_Schedule.domain.schedule.domain.Schedule;

import java.time.LocalDateTime;

public class ScheduleDto {

    @Getter
    @NoArgsConstructor
    public static class CreateRequestDto {
        private String title;
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;
        @DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;

        @Builder
        public CreateRequestDto(String title, LocalDateTime startTime, LocalDateTime endTime) {
            this.title = title;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class UpdateRequestDto {
        private Long scheduleId;
        private String title;
        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;
        @DateTimeFormat (pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;

        @Builder
        public UpdateRequestDto(Long scheduleId, String title, LocalDateTime startTime, LocalDateTime endTime) {
            this.scheduleId = scheduleId;
            this.title = title;
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ScheduleResponseListDto<T> {
        private T result;

        @Builder
        public ScheduleResponseListDto(T result) {
            this.result = result;
        }
    }

    @Getter
    @NoArgsConstructor
    public static class ScheduleResponseDto {
        private Long scheduleId;
        private String title;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;

        @Builder
        public ScheduleResponseDto(Long scheduleId, String title, LocalDateTime startTime, LocalDateTime endTime) {
            this.scheduleId = scheduleId;
            this.title = title;
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public static ScheduleResponseDto toScheduleResponseDto(Schedule schedule) {
            return ScheduleResponseDto
                    .builder()
                    .scheduleId(schedule.getId())
                    .title(schedule.getTitle())
                    .startTime(schedule.getStartTime())
                    .endTime(schedule.getEndTime())
                    .build();
        }
    }
}
