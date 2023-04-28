package org.bugmakers404.hermes.consumer.vicroad.dao;

import java.time.OffsetDateTime;
import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.entities.sites.SiteEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SiteEventDAO extends MongoRepository<SiteEvent, String> {

  List<SiteEvent> findAllBySiteId(Integer siteId);

  List<SiteEvent> findAllByTimestamp(OffsetDateTime timestamp);

  SiteEvent findBySiteIdAndTimestamp(Integer siteId, OffsetDateTime timestamp);

  SiteEvent findTopBySiteIdOrderByTimestampDesc(Integer siteId);
}
