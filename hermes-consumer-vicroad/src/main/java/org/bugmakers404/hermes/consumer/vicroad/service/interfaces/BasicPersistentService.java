package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import java.util.List;

public interface BasicPersistentService<T> {

  T save(T event);

  List<T> saveAll(List<T> events);

}
