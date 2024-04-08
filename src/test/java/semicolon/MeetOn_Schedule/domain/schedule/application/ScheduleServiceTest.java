package semicolon.MeetOn_Schedule.domain.schedule.application;

import jakarta.servlet.http.Cookie;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockCookie;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.transaction.annotation.Transactional;
import semicolon.MeetOn_Schedule.domain.schedule.dao.ScheduleRepository;
import semicolon.MeetOn_Schedule.domain.schedule.domain.Schedule;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import static semicolon.MeetOn_Schedule.domain.schedule.dto.ScheduleDto.*;

@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@Transactional
class ScheduleServiceTest {

    @Autowired
    ScheduleService scheduleService;

    @Autowired
    ScheduleRepository scheduleRepository;

    @MockBean
    ScheduleChannelService scheduleChannelService;

    MockHttpServletRequest request;
    MockHttpServletResponse response;
    Schedule schedule;

    /**
     * 사용하는 채널은 6552 -> 해당 채널의 Schedule은 없음 아마?
     */
    @BeforeEach
    void init() {
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
        request.addHeader("Authorization", "Bearer test-token");
        //스케줄 생성
        CreateRequestDto createRequestDto = CreateRequestDto
                .builder()
                .title("testTitle")
                .startTime(LocalDateTime.parse("2024-03-18T11:22:33"))
                .endTime(LocalDateTime.parse("2024-03-18T11:22:33"))
                .build();
        createSetCookie(String.valueOf(6552));
        Schedule scheduleSave = Schedule.toSchedule(createRequestDto, 6552L);
        schedule = scheduleRepository.save(scheduleSave);
    }

    @Test
    void 스케쥴_생성() {
        CreateRequestDto createRequestDto = CreateRequestDto
                .builder()
                .title("title")
                .startTime(LocalDateTime.parse("2023-01-18T11:22:33"))
                .endTime(LocalDateTime.parse("2023-01-18T11:22:33"))
                .build();
        Long channelId = 6552L;
        when(scheduleChannelService.channelExists(channelId, "Bearer test-token")).thenReturn(true);

        Schedule schedule = Schedule.toSchedule(createRequestDto, channelId);
        Long id = scheduleService.saveSchedule(createRequestDto, request);
        assertThat(id).isNotNull();
    }

    @Test
    void 스케줄_조회() {
        List<ScheduleResponseDto> scheduleList = scheduleService.getScheduleList(request, 2024L, 3L);
        ScheduleResponseDto scheduleResponseDto = scheduleList.get(0);
        assertThat(scheduleList.size()).isEqualTo(1);
        assertThat(scheduleResponseDto.getTitle()).isEqualTo("testTitle");
    }

    @Test
    void 스케줄_업데이트() {
        UpdateRequestDto updateRequestDto =
                UpdateRequestDto
                        .builder()
                        .scheduleId(schedule.getId())
                        .title("updateTitle")
                        .startTime(LocalDateTime.parse("2024-03-18T11:22:33"))
                        .endTime(LocalDateTime.parse("2024-03-18T11:22:33"))
                        .build();
        scheduleService.updateSchedule(updateRequestDto);

        assertThat(schedule.getTitle()).isEqualTo("updateTitle");
    }

    private void createSetCookie(String value) {
        MockCookie mockCookie = new MockCookie("channelId", value);
        mockCookie.setPath("/");
        mockCookie.setHttpOnly(true);
        response.addCookie(mockCookie);
        Cookie[] cookies = response.getCookies();
        request.setCookies(cookies);
    }
}