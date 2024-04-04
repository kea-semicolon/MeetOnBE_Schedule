package semicolon.MeetOn_Schedule.domain.schedule.application;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import semicolon.MeetOn_Schedule.domain.schedule.dao.ScheduleRepository;
import semicolon.MeetOn_Schedule.domain.schedule.domain.Schedule;
import semicolon.MeetOn_Schedule.domain.schedule.dto.ScheduleDto;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static semicolon.MeetOn_Schedule.domain.schedule.dto.ScheduleDto.*;

@Slf4j
@SpringBootTest
@Transactional
class ScheduleServiceTest {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    ScheduleRepository scheduleRepository;

    @Test
    void 스케쥴_생성() {
        CreateRequest createRequest = CreateRequest
                .builder()
                .title("title")
                .startTime("2023-01-18T11:22:33")
                .endTime("2023-01-18T11:22:33")
                .build();
        Schedule schedule = Schedule.toSchedule(createRequest, 6552L);
        Schedule save = scheduleRepository.save(schedule);
        assertThat(save).isEqualTo(schedule);
    }
}