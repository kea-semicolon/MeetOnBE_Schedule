package semicolon.MeetOn_Schedule.domain.schedule.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import semicolon.MeetOn_Schedule.domain.schedule.application.ScheduleService;

import java.util.List;

import static semicolon.MeetOn_Schedule.domain.schedule.dto.ScheduleDto.*;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    /**
     * 공유 일정 생성
     * @param createRequestDto
     * @param request
     * @return
     */
    @PostMapping
    public ResponseEntity<String> createSchedule(@RequestBody CreateRequestDto createRequestDto,
                                                 HttpServletRequest request) {
        Long l = scheduleService.saveSchedule(createRequestDto, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(l + " Created");
    }

    /**
     * 공유 일정 조회(year, month @RequestParam)
     * @param request
     * @param year
     * @param month
     * @return
     */
    @GetMapping
    public ResponseEntity<ScheduleResponseListDto<List<ScheduleResponseDto>>> getSchedule(@RequestParam Long year,
                                                                                          @RequestParam Long month,
                                                                                          HttpServletRequest request) {
        List<ScheduleResponseDto> scheduleList = scheduleService.getScheduleList(request, year, month);
        return ResponseEntity.ok(new ScheduleResponseListDto<>(scheduleList));
    }

    /**
     * 공유 일정 수정(id,title, startTime, endTime)
     * @param updateRequestDto
     * @return
     */
    @PutMapping
    public ResponseEntity<String> updateSchedule(@RequestBody UpdateRequestDto updateRequestDto) {
        scheduleService.updateSchedule(updateRequestDto);
        return ResponseEntity.ok("Ok");
    }

    /**
     * 공유 일정 삭제
     * @param scheduleId
     * @return
     */
    @DeleteMapping
    public ResponseEntity<String> deleteSchedule(@RequestParam Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.ok(scheduleId + " 삭제합니다.");
    }
}
