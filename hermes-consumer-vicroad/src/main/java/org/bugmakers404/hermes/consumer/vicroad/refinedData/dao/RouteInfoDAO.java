package org.bugmakers404.hermes.consumer.vicroad.refinedData.dao;

import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.RouteInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RouteInfoDAO extends MongoRepository<RouteInfo, String> {

}
