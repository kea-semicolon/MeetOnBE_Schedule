package semicolon.MeetOn_Schedule.domain.schedule.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import semicolon.MeetOn_Schedule.BaseTimeEntity;

import java.time.LocalDateTime;

import static semicolon.MeetOn_Schedule.domain.schedule.dto.ScheduleDto.*;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseTimeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    private Long channelId;

    @Builder
    public Schedule(Long id, String title, LocalDateTime startTime, LocalDateTime endTime, Long channelId) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.channelId = channelId;
    }

    public static Schedule toSchedule(CreateRequestDto request, Long channelId) {
        return Schedule
                .builder()
                .title(request.getTitle())
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .channelId(channelId)
                .build();
    }

    public void updateSchedule(UpdateRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.startTime = requestDto.getStartTime();
        this.endTime = requestDto.getEndTime();
    }
}
