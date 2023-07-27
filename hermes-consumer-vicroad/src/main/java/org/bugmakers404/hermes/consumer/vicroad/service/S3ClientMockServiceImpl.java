package org.bugmakers404.hermes.consumer.vicroad.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.FailedEventsArchiveService;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("!prod")
@RequiredArgsConstructor
public class S3ClientMockServiceImpl implements FailedEventsArchiveService {

  @Override
  public void archiveFailedEvent(@NonNull String topic, @NonNull String key, String event) {
    String filePath = Constants.BLUETOOTH_DATA_ARCHIVES_EVENT_PATH.formatted(topic, key);
    storeEventsToLocalFiles(topic, filePath, event);

    log.info("{} - Succeed to archive the failed event with key {} in S3 bucket {}", topic,
        key, Constants.HERMES_DATA_BUCKET_NAME);
  }

  private void storeEventsToLocalFiles(String topic, String filePath, String content) {

    Path targetPath = Paths.get(filePath);

    try {
      Files.createDirectories(targetPath.getParent());
      Files.writeString(targetPath, content);
      log.info("{} - Succeed to archive the non-persistent event locally at {}", topic, filePath);
    } catch (IOException e) {
      log.error("{} - Failed to archive the non-persistent event locally: {}", topic,
          e.getMessage(), e);
    }
  }
}
