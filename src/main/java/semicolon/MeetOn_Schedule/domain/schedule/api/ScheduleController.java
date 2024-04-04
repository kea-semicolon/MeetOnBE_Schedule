package semicolon.MeetOn_Schedule.domain.schedule.api;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import semicolon.MeetOn_Schedule.domain.schedule.application.ScheduleService;

import static semicolon.MeetOn_Schedule.domain.schedule.dto.ScheduleDto.*;

@Slf4j
@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<String> createSchedule(@RequestBody CreateRequest createRequest,
                                                 HttpServletRequest request) {
        scheduleService.saveSchedule(createRequest, request);
        return ResponseEntity.status(HttpStatus.CREATED).body("Created");
    }
}
