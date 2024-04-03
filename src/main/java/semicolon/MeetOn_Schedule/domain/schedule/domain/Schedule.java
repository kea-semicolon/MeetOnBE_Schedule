package semicolon.MeetOn_Schedule.domain.schedule.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import semicolon.MeetOn_Schedule.BaseTimeEntity;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Schedule extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    private String title;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @Builder
    public Schedule(Long id, String title, LocalDateTime startTime, LocalDateTime endTime) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
