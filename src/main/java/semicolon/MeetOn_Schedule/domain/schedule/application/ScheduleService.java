package semicolon.MeetOn_Schedule.domain.schedule.application;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semicolon.MeetOn_Schedule.domain.schedule.dao.ScheduleRepository;
import semicolon.MeetOn_Schedule.domain.schedule.domain.Schedule;
import semicolon.MeetOn_Schedule.global.util.CookieUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.stream.Collectors;

import static semicolon.MeetOn_Schedule.domain.schedule.dto.ScheduleDto.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final CookieUtil cookieUtil;

    @Transactional
    public void saveSchedule(CreateRequestDto createRequestDto, HttpServletRequest request) {
        Long channelId = Long.valueOf(cookieUtil.getCookieValue("channelId", request));
        Schedule schedule = Schedule.toSchedule(createRequestDto, channelId);
        scheduleRepository.save(schedule);
    }

    public List<ScheduleResponseDto> getScheduleList(HttpServletRequest request, Long year, Long month) {
        Long channelId = Long.valueOf(cookieUtil.getCookieValue("channelId", request));
        YearMonth yearMonth = YearMonth.of(year.intValue(), month.intValue());
        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime endOfMonth = yearMonth.atEndOfMonth().atStartOfDay();
        return scheduleRepository.findByChannelIdAndStartTimeBetween(channelId, startOfMonth, endOfMonth)
                .stream() // 컬렉션을 스트림으로 변환
                .map(ScheduleResponseDto::toScheduleResponseDto) // Schedule 객체를 ScheduleResponseDto 객체로 매핑
                .collect(Collectors.toList());
    }
}
