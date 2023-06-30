package org.bugmakers404.hermes.consumer.vicroad.dao;

import org.bugmakers404.hermes.consumer.vicroad.entity.routes.RouteEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteEventDAO extends MongoRepository<RouteEvent, String> {

}
