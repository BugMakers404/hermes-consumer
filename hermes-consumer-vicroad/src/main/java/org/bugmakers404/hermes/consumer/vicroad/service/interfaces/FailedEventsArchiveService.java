package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import lombok.NonNull;

public interface FailedEventsArchiveService {

  void archiveFailedEvent(@NonNull String topic, @NonNull String key, String event);

}
