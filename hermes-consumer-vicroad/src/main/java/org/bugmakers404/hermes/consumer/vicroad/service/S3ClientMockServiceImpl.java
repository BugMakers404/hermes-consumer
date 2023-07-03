package org.bugmakers404.hermes.consumer.vicroad.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.FailedEventsArchiveService;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Slf4j
@Service
@Profile("!prod")
@RequiredArgsConstructor
public class S3ClientMockServiceImpl implements FailedEventsArchiveService {

  @Override
  public void archiveFailedEvent(@NonNull String topic, @NonNull OffsetDateTime timestamp,
      @NonNull Integer id, String event) {
    log.info("{} - Succeed to archive the failed event with key {}-{} in S3 bucket {}", topic,
        timestamp, id, Constants.HERMES_DATA_BUCKET_NAME);
  }
}
