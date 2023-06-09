package org.bugmakers404.hermes.consumer.vicroad.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.FailedEventsArchiveService;
import org.bugmakers404.hermes.consumer.vicroad.util.Constants;
import org.bugmakers404.hermes.consumer.vicroad.util.FileUtils;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

public class S3ClientServiceImplTest {

  @Mock
  private S3Client s3Client;

  private FailedEventsArchiveService s3ClientService;

  @BeforeMethod
  public void setup() {
    MockitoAnnotations.openMocks(this);
    s3ClientService = new S3ClientServiceImpl(s3Client);
  }

  @AfterMethod
  public void tearDown() throws IOException {
    FileUtils.deleteDirectory(Path.of(Constants.VICROAD_DATA_ARCHIVES_ROOT));
  }

  @Test
  public void testArchiveFailedLinkEvents() throws IOException {

    String key = OffsetDateTime.now() + "_" + 1234;
    String linkEvent = "linkEvent";

    String filePath = Constants.BLUETOOTH_DATA_ARCHIVES_EVENT_PATH.formatted(
        Constants.BLUETOOTH_DATA_TOPIC_LINKS, key);

    // When no exception thrown by S3Client
    s3ClientService.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_LINKS, key, linkEvent);

    // Verify
    verify(s3Client, times(1)).putObject(any(PutObjectRequest.class), any(RequestBody.class));

    // When exception thrown by S3Client
    doThrow(new RuntimeException("S3 error")).when(s3Client)
        .putObject(any(PutObjectRequest.class), any(RequestBody.class));
    s3ClientService.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_LINKS, key, linkEvent);

    // Verify that file is written locally in case of S3 error
    Path localFilePath = Paths.get(filePath);
    assertTrue(Files.exists(localFilePath));
    assertEquals(Files.readString(localFilePath), linkEvent);

    // Cleanup
    Files.delete(localFilePath);
  }

  @Test
  public void testArchiveFailedLinkWithGeoEvents() throws IOException {
    String key = LocalDate.now() + "_" + 1234;
    String linkWithGeoEvent = "linkWithGeoEvent";
    String filePath = Constants.BLUETOOTH_DATA_ARCHIVES_EVENT_PATH.formatted(
        Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, key);

    // When no exception thrown by S3Client
    s3ClientService.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, key,
        linkWithGeoEvent);

    // Verify
    verify(s3Client, times(1)).putObject(any(PutObjectRequest.class), any(RequestBody.class));

    // When exception thrown by S3Client
    doThrow(new RuntimeException("S3 error")).when(s3Client)
        .putObject(any(PutObjectRequest.class), any(RequestBody.class));
    s3ClientService.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, key,
        linkWithGeoEvent);

    // Verify that file is written locally in case of S3 error
    Path localFilePath = Paths.get(filePath);
    assertTrue(Files.exists(localFilePath));
    assertEquals(Files.readString(localFilePath), linkWithGeoEvent);

    // Cleanup
    Files.delete(localFilePath);
  }

  @Test
  public void testArchiveFailedRouteEvents() throws IOException {
    String key = OffsetDateTime.now() + "_" + 1234;
    String routeEvent = "routeEvent";
    String filePath = Constants.BLUETOOTH_DATA_ARCHIVES_EVENT_PATH.formatted(
        Constants.BLUETOOTH_DATA_TOPIC_ROUTES, key);

    // When no exception thrown by S3Client
    s3ClientService.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_ROUTES, key, routeEvent);

    // Verify
    verify(s3Client, times(1)).putObject(any(PutObjectRequest.class), any(RequestBody.class));

    // When exception thrown by S3Client
    doThrow(new RuntimeException("S3 error")).when(s3Client)
        .putObject(any(PutObjectRequest.class), any(RequestBody.class));
    s3ClientService.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_ROUTES, key, routeEvent);

    // Verify that file is written locally in case of S3 error
    Path localFilePath = Paths.get(filePath);
    assertTrue(Files.exists(localFilePath));
    assertEquals(Files.readString(localFilePath), routeEvent);

    // Cleanup
    Files.delete(localFilePath);
  }

  @Test
  public void testArchiveFailedSiteEvents() throws IOException {
    String key = OffsetDateTime.now() + "_" + 1234;
    String siteEvent = "siteEvent";
    String filePath = Constants.BLUETOOTH_DATA_ARCHIVES_EVENT_PATH.formatted(
        Constants.BLUETOOTH_DATA_TOPIC_SITES, key);

    // When no exception thrown by S3Client
    s3ClientService.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_SITES, key, siteEvent);

    // Verify
    verify(s3Client, times(1)).putObject(any(PutObjectRequest.class), any(RequestBody.class));

    // When exception thrown by S3Client
    doThrow(new RuntimeException("S3 error")).when(s3Client)
        .putObject(any(PutObjectRequest.class), any(RequestBody.class));
    s3ClientService.archiveFailedEvent(Constants.BLUETOOTH_DATA_TOPIC_SITES, key, siteEvent);

    // Verify that file is written locally in case of S3 error
    Path localFilePath = Paths.get(filePath);
    assertTrue(Files.exists(localFilePath));
    assertEquals(Files.readString(localFilePath), siteEvent);

    // Cleanup
    Files.delete(localFilePath);
  }
}
