package semicolon.MeetOn_Schedule.domain.schedule.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import semicolon.MeetOn_Schedule.domain.schedule.dao.ScheduleRepository;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleKafkaService {

    private final ScheduleRepository scheduleRepository;
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final static String CHANNEL_DELETED_TOPIC = "channel_deleted_topic";

    @Transactional
    @KafkaListener(topics = CHANNEL_DELETED_TOPIC, groupId = "channel-group")
    public void deletedByChannelDeleted(String channelIdStr) {
        log.info("Channel 삭제 channelId={}", channelIdStr);
        Long channelId = Long.valueOf(channelIdStr);
        int c = scheduleRepository.deleteSchedulesByChannelId(channelId);
        log.info("Schedule {}개 삭제 완료", c);
    }
}
