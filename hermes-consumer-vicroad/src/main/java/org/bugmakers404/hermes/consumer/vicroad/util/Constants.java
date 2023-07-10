package org.bugmakers404.hermes.consumer.vicroad.util;

import java.time.format.DateTimeFormatter;

public class Constants {

  // Constants for the kafka cluster
  public final static String BLUETOOTH_DATA_TOPIC_LINKS = "vicroad-links";

  public final static String BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO = "vicroad-linksWithGeo";

  public final static String BLUETOOTH_DATA_TOPIC_ROUTES = "vicroad-routes";

  public final static String BLUETOOTH_DATA_TOPIC_ROUTES_WITH_GEO = "vicroad-routesWithGeo";

  public final static String BLUETOOTH_DATA_TOPIC_SITES = "vicroad-sites";

  public final static String BLUETOOTH_DATA_TOPIC_SITES_WITH_GEO = "vicroad-sitesWithGeo";

  public final static String KAFKA_PARTITION_COUNT = "1";

  // Constants for Timestamp format
  public final static String DATE_TIME_PATTERN_IN_EVENTS = "yyyy-MM-dd'T'HH:mm:ssXXX";

  public final static DateTimeFormatter DATE_TIME_FORMATTER_IN_EVENTS = DateTimeFormatter.ofPattern(
      DATE_TIME_PATTERN_IN_EVENTS);

  public final static DateTimeFormatter DATE_TIME_FORMATTER_FOR_FILENAME = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd_HH-mm-ssx");

  // Constants for AWS S3
  public final static String HERMES_DATA_BUCKET_NAME = "hermes-data-archives";

  // Constants for archiving data
  public final static String VICROAD_DATA_ARCHIVES_ROOT = "vicroad_data_archives";

  public final static String VICROAD_DATA_ARCHIVE_FAILED_DIR =
      VICROAD_DATA_ARCHIVES_ROOT + "/failed";

  public final static String BLUETOOTH_DATA_ARCHIVES_DIR =
      VICROAD_DATA_ARCHIVE_FAILED_DIR + "/bluetooth";

  public final static String BLUETOOTH_DATA_ARCHIVES_TOPIC_DIR =
      BLUETOOTH_DATA_ARCHIVES_DIR + "/%s";

  public final static String BLUETOOTH_DATA_ARCHIVES_EVENT_PATH =
      BLUETOOTH_DATA_ARCHIVES_TOPIC_DIR + "/%s.json";
}
