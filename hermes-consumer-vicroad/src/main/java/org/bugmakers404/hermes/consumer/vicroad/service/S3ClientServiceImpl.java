package org.bugmakers404.hermes.consumer.vicroad.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

@Slf4j
@Service
@Profile("prod")
@RequiredArgsConstructor
public class S3ClientServiceImpl implements FailedEventsArchiveService {

  private final S3Client s3Client;

  @Override
  public void archiveFailedEvent(@NonNull String topic, @NonNull String key, String event) {

    String filePath = Constants.BLUETOOTH_DATA_ARCHIVES_EVENT_PATH.formatted(topic, key);

    try {
      saveStringAsJsonFile(filePath, event);
      log.info("{} - Succeed to archive the non-persistent event with key {} in S3 bucket {}",
          topic, key, Constants.HERMES_DATA_BUCKET_NAME);
    } catch (Exception e) {
      log.error(
          "{} - Failed to archive the non-persistent event with key {} in S3 bucket {}: {}",
          topic, key, Constants.HERMES_DATA_BUCKET_NAME, e.getMessage(), e);
      storeEventsToLocalFiles(topic, filePath, event);
    }

  }

  private void saveStringAsJsonFile(String objectKey, String jsonString) {

    PutObjectRequest putObjectRequest = PutObjectRequest.builder()
        .bucket(Constants.HERMES_DATA_BUCKET_NAME).key(objectKey).contentType("application/json")
        .build();

    RequestBody requestBody = RequestBody.fromString(jsonString, StandardCharsets.UTF_8);
    s3Client.putObject(putObjectRequest, requestBody);
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
