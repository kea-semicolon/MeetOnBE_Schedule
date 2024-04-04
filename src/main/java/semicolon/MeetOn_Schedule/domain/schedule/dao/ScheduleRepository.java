package semicolon.MeetOn_Schedule.domain.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import semicolon.MeetOn_Schedule.domain.schedule.domain.Schedule;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    List<Schedule> findByChannelIdAndStartTimeBetween(Long channelId, LocalDateTime startOfMonth, LocalDateTime endOfMonth);
}
