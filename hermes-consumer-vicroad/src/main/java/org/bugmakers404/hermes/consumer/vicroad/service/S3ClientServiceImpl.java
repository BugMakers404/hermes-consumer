package org.bugmakers404.hermes.consumer.vicroad.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.FailedEventsArchiveService;
import org.bugmakers404.hermes.consumer.vicroad.utils.Constants;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;

@Slf4j
@Service
@Profile("prod")
@RequiredArgsConstructor
public class S3ClientServiceImpl implements FailedEventsArchiveService {

    private final S3Client s3Client;

    @Override
    public void archiveFailedLinkEvents(OffsetDateTime timestamp, Integer linkId, String linkEvent) {
        String filePath = Constants.LINKS_FILE_PATH.formatted(
                timestamp.format(Constants.DATE_TIME_FORMATTER_FOR_FILENAME), linkId);

        try {

            saveStringAsJsonFile(Constants.HERMES_DATA_BUCKET_NAME, filePath, linkEvent);
            log.info("{} - Succeed to archive the non-persistent event with key {}-{} in S3 bucket {}",
                    Constants.BLUETOOTH_DATA_TOPIC_LINKS, timestamp, linkId,
                    Constants.HERMES_DATA_BUCKET_NAME);

        } catch (Exception e) {

            log.error(
                    "{} - Failed to archive the non-persistent event with key {}-{} in S3 bucket {}: {}",
                    Constants.BLUETOOTH_DATA_TOPIC_LINKS, timestamp, linkId,
                    Constants.HERMES_DATA_BUCKET_NAME,
                    e.getMessage(), e);
            storeEventsToLocalFiles(Constants.BLUETOOTH_DATA_TOPIC_LINKS, filePath, linkEvent);

        }
    }

    @Override
    public void archiveFailedLinkWithGeoEvents(OffsetDateTime timestamp, Integer linkId,
                                               String linkWithGeoEvents) {
        String filePath = Constants.LINKS_WITH_GEO_FILE_PATH.formatted(
                timestamp.format(Constants.DATE_TIME_FORMATTER_FOR_FILENAME), linkId);

        try {

            saveStringAsJsonFile(Constants.HERMES_DATA_BUCKET_NAME, filePath, linkWithGeoEvents);
            log.info("{} - Succeed to archive the non-persistent event with key {}-{} in S3 bucket {}",
                    Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, timestamp, linkId,
                    Constants.HERMES_DATA_BUCKET_NAME);

        } catch (Exception e) {

            log.error(
                    "{} - Failed to archive the non-persistent event with key {}-{} in S3 bucket {}: {}",
                    Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, timestamp, linkId,
                    Constants.HERMES_DATA_BUCKET_NAME,
                    e.getMessage(), e);
            storeEventsToLocalFiles(Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, filePath,
                    linkWithGeoEvents);

        }

    }

    @Override
    public void archiveFailedRouteEvents(OffsetDateTime timestamp, Integer routeId,
                                         String routeEvent) {
        String filePath = Constants.ROUTES_FILE_PATH.formatted(
                timestamp.format(Constants.DATE_TIME_FORMATTER_FOR_FILENAME), routeId);

        try {

            saveStringAsJsonFile(Constants.HERMES_DATA_BUCKET_NAME, filePath, routeEvent);
            log.info("{} - Succeed to archive the non-persistent event with key {}-{} in S3 bucket {}",
                    Constants.BLUETOOTH_DATA_TOPIC_ROUTES, timestamp, routeId,
                    Constants.HERMES_DATA_BUCKET_NAME);

        } catch (Exception e) {

            log.error(
                    "{} - Failed to archive the non-persistent event with key {}-{} in S3 bucket {}: {}",
                    Constants.BLUETOOTH_DATA_TOPIC_ROUTES, timestamp, routeId,
                    Constants.HERMES_DATA_BUCKET_NAME,
                    e.getMessage(), e);
            storeEventsToLocalFiles(Constants.BLUETOOTH_DATA_TOPIC_ROUTES, filePath, routeEvent);

        }
    }

    @Override
    public void archiveFailedSiteEvents(OffsetDateTime timestamp, Integer siteId, String siteEvent) {
        String filePath = Constants.SITES_FILE_PATH.formatted(
                timestamp.format(Constants.DATE_TIME_FORMATTER_FOR_FILENAME), siteId);

        try {

            saveStringAsJsonFile(Constants.HERMES_DATA_BUCKET_NAME, filePath, siteEvent);
            log.info("{} - Succeed to archive the non-persistent event with key {}-{} in S3 bucket {}",
                    Constants.BLUETOOTH_DATA_TOPIC_SITES, timestamp, siteId,
                    Constants.HERMES_DATA_BUCKET_NAME);

        } catch (Exception e) {

            log.error(
                    "{} - Failed to archive the non-persistent event with key {}-{} in S3 bucket {}: {}",
                    Constants.BLUETOOTH_DATA_TOPIC_SITES, timestamp, siteId,
                    Constants.HERMES_DATA_BUCKET_NAME,
                    e.getMessage(), e);
            storeEventsToLocalFiles(Constants.BLUETOOTH_DATA_TOPIC_SITES, filePath, siteEvent);

        }
    }

    public void saveStringAsJsonFile(String bucketName, String objectKey,
                                     String jsonString) {

        PutObjectRequest putObjectRequest = PutObjectRequest.builder().bucket(bucketName).key(objectKey)
                .contentType("application/json").build();

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
