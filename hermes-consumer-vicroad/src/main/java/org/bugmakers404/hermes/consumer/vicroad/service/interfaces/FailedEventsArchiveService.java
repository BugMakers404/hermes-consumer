package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import java.time.OffsetDateTime;
import lombok.NonNull;

public interface FailedEventsArchiveService {

  void archiveFailedEvent(@NonNull String topic, @NonNull OffsetDateTime timestamp,
      @NonNull Integer id, String event);

}
