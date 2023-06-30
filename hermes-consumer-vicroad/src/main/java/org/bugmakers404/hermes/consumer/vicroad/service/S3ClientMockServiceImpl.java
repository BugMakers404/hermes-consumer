package org.bugmakers404.hermes.consumer.vicroad.service;

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
  public void archiveFailedLinkEvents(OffsetDateTime timestamp, Integer linkId, String linkEvent) {
    log.info("{} - Succeed to archive the failed event with key {}-{} in S3 bucket {}",
        Constants.BLUETOOTH_DATA_TOPIC_LINKS, timestamp, linkId,
        Constants.HERMES_DATA_BUCKET_NAME);
  }

  @Override
  public void archiveFailedLinkWithGeoEvents(OffsetDateTime timestamp, Integer linkId,
      String linkWithGeoEvent) {
    log.info("{} - Succeed to archive the failed event with key {}-{} in S3 bucket {}",
        Constants.BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO, timestamp, linkId,
        Constants.HERMES_DATA_BUCKET_NAME);
  }

  @Override
  public void archiveFailedRouteEvents(OffsetDateTime timestamp, Integer routeId,
      String routeEvent) {
    log.info("{} - Succeed to archive the failed event with key {}-{} in S3 bucket {}",
        Constants.BLUETOOTH_DATA_TOPIC_ROUTES, timestamp, routeId,
        Constants.HERMES_DATA_BUCKET_NAME);
  }

  @Override
  public void archiveFailedSiteEvents(OffsetDateTime timestamp, Integer siteId, String siteEvent) {
    log.info("{} - Succeed to archive the failed event with key {}-{} in S3 bucket {}",
        Constants.BLUETOOTH_DATA_TOPIC_SITES, timestamp, siteId,
        Constants.HERMES_DATA_BUCKET_NAME);
  }
}
