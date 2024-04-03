package semicolon.MeetOn_Schedule.domain.schedule.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import semicolon.MeetOn_Schedule.domain.schedule.domain.Schedule;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
