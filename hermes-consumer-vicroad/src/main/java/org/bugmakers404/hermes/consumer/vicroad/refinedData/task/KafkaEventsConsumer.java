package org.bugmakers404.hermes.consumer.vicroad.refinedData.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkEvent;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.service.interfaces.LinkService;
import org.bugmakers404.hermes.consumer.vicroad.utils.Constants;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaEventsConsumer {

  private final LinkService linkService;

  private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();


  @KafkaListener(topics = { Constants.BLUETOOTH_DATA_TOPIC_LINKS })
  public void onMessage(ConsumerRecord<String, String> consumerRecord, Acknowledgment acknowledgment) throws JsonProcessingException {
    LinkEvent linkEvent = objectMapper.readValue(consumerRecord.value(), LinkEvent.class);

    String[] timestampAndLinkId = consumerRecord.key().split("_");
    String timestamp = timestampAndLinkId[0];
    Integer linkId = Integer.parseInt(timestampAndLinkId[1]);

    LocalDateTime timestampWithoutZone = OffsetDateTime.parse(timestamp).atZoneSameInstant(ZoneId.of("Australia/Sydney"))
        .toLocalDateTime();

    linkEvent.setId(consumerRecord.key());
    linkEvent.setLinkId(linkId);
    linkEvent.setTimestamp(timestampWithoutZone);

    linkService.saveOneLinkEvent(linkEvent);
    acknowledgment.acknowledge();
  }




}
