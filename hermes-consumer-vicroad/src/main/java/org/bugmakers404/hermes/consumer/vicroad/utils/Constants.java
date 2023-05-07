package org.bugmakers404.hermes.consumer.vicroad.utils;

import java.time.format.DateTimeFormatter;

public class Constants {

  // Constants for the kafka cluster
  public final static String BLUETOOTH_DATA_TOPIC_LINKS = "vicroad-links";

  public final static String BLUETOOTH_DATA_TOPIC_LINKS_WITH_GEO = "vicroad-linksWithGeo";

  public final static String BLUETOOTH_DATA_TOPIC_ROUTES = "vicroad-routes";

  public final static String BLUETOOTH_DATA_TOPIC_SITES = "vicroad-sites";

  public final static String KAFKA_PARTITION_COUNT = "1";

  // Constants for Timestamp format
  public final static DateTimeFormatter DATE_TIME_FORMATTER_FOR_FILENAME = DateTimeFormatter.ofPattern(
      "yyyy-MM-dd_HH-mm-ss");

  // Constants for AWS S3
  public final static String HERMES_DATA_BUCKET_NAME = "hermes-data-archives";

  // Constants for archiving data
  public final static String VICROAD_DATA_ARCHIVES_ROOT = "vicroad_data_archives";

  public final static String VICROAD_DATA_ARCHIVE_COMMON_DIR =
      VICROAD_DATA_ARCHIVES_ROOT + "/failed";

  public final static String BLUETOOTH_DATA_ARCHIVES_DIR =
      VICROAD_DATA_ARCHIVE_COMMON_DIR + "/bluetooth";

  public final static String LINKS_ARCHIVES_DIR = BLUETOOTH_DATA_ARCHIVES_DIR + "/links";

  public final static String LINKS_FILE_PATH = LINKS_ARCHIVES_DIR + "/%s_%d.json";

  public final static String LINKS_WITH_GEO_ARCHIVES_DIR =
      BLUETOOTH_DATA_ARCHIVES_DIR + "/links_with_geo";

  public final static String LINKS_WITH_GEO_FILE_PATH = LINKS_WITH_GEO_ARCHIVES_DIR + "/%s_%d.json";

  public final static String ROUTES_ARCHIVES_DIR = BLUETOOTH_DATA_ARCHIVES_DIR + "/routes";

  public final static String ROUTES_FILE_PATH = ROUTES_ARCHIVES_DIR + "/%s_%d.json";

  public final static String SITES_ARCHIVES_DIR = BLUETOOTH_DATA_ARCHIVES_DIR + "/sites";

  public final static String SITES_FILE_PATH = SITES_ARCHIVES_DIR + "/%s_%d.json";

}
