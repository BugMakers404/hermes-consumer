package org.bugmakers404.hermes.consumer.vicroad.refinedData.dao;

import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.RouteEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteDAO extends MongoRepository<RouteEvent, String> {

}
