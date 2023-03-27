package org.bugmakers404.hermes.consumer.vicroad.refinedData.task;

import static org.bugmakers404.hermes.consumer.vicroad.utils.KafkaConstants.BLUETOOTH_DATA_TOPIC_LINKS;
import static org.bugmakers404.hermes.consumer.vicroad.utils.KafkaConstants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.time.OffsetDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkEvent;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkGeoInfo;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.service.interfaces.PersistentLinkGeoService;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.service.interfaces.PersistentLinkService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class KafkaEventsConsumer {

  private final PersistentLinkService persistentLinkService;

  private final PersistentLinkGeoService persistentLinkGeoService;

  private final ObjectMapper objectMapper = new ObjectMapper().findAndRegisterModules();


  @KafkaListener(topics = {BLUETOOTH_DATA_TOPIC_LINKS})
  public void persistLinkEvents(ConsumerRecord<String, String> consumerRecord,
      Acknowledgment acknowledgment)
      throws JsonProcessingException {
    LinkEvent linkEvent = objectMapper.readValue(consumerRecord.value(), LinkEvent.class);

    String[] timestampAndLinkId = consumerRecord.key().split("_");
    String timestamp = timestampAndLinkId[0];
    Integer linkId = Integer.parseInt(timestampAndLinkId[1]);

    linkEvent.setId(consumerRecord.key());
    linkEvent.setLinkId(linkId);
    linkEvent.setTimestamp(OffsetDateTime.parse(timestamp));

    persistentLinkService.saveLinkEvent(linkEvent);
    acknowledgment.acknowledge();
  }

  @KafkaListener(topics = {BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO})
  public void persistLinkWithGeoEvents(ConsumerRecord<String, String> consumerRecord,
      Acknowledgment acknowledgment)
      throws JsonProcessingException {

    LinkGeoInfo linkGeoInfo = objectMapper.readValue(consumerRecord.value(), LinkGeoInfo.class);

    String[] timestampAndLinkId = consumerRecord.key().split("_");
    String timestamp = timestampAndLinkId[0];
    Integer linkId = Integer.parseInt(timestampAndLinkId[1]);

    linkGeoInfo.setId(consumerRecord.key());


  }


}
