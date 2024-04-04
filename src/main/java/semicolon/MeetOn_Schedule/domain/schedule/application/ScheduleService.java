package semicolon.MeetOn_Schedule.domain.schedule.application;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semicolon.MeetOn_Schedule.domain.schedule.dao.ScheduleRepository;
import semicolon.MeetOn_Schedule.domain.schedule.domain.Schedule;
import semicolon.MeetOn_Schedule.global.util.CookieUtil;

import static semicolon.MeetOn_Schedule.domain.schedule.dto.ScheduleDto.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CookieUtil cookieUtil;

    @Transactional
    public void saveSchedule(CreateRequest createRequest, HttpServletRequest request) {
        Long channelId = Long.valueOf(cookieUtil.getCookieValue("channelId", request));
        Schedule schedule = Schedule.toSchedule(createRequest, channelId);
        scheduleRepository.save(schedule);
    }
}
