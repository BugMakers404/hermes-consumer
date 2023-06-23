package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import java.util.List;

public interface PersistentGeoInfoService<T> extends BasicPersistentService<T> {

  T saveIfChanged(T infoEvent);

  List<T> saveAllIfChanged(List<T> infoEvent);
}
