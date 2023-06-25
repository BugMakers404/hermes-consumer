package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import java.util.List;

public interface PersistentGeoEventService<T> {

  T saveIfChanged(T infoEvent);

  List<T> saveAllIfChanged(List<T> allInfoEvents);
}
