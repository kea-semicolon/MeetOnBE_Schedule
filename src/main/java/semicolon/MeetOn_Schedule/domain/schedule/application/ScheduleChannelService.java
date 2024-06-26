package semicolon.MeetOn_Schedule.domain.schedule.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScheduleChannelService {

    private final WebClient webClient;

    @Value("${app.gateway.url}")
    private String gateway;

    Boolean channelExists(Long channelId, String accessToken) {
        String uri = UriComponentsBuilder.fromUriString(gateway + "/channel/find")
                .queryParam("channelId", channelId).toUriString();

        return webClient.get()
                .uri(uri)
                .header(HttpHeaders.AUTHORIZATION, accessToken)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
    }
}
