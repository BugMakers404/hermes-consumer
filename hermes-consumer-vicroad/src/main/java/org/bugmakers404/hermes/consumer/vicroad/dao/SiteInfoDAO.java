package org.bugmakers404.hermes.consumer.vicroad.dao;

import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.entities.sites.SiteInfo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SiteInfoDAO extends MongoRepository<SiteInfo, String> {

  List<SiteInfo> findAllBySiteId(Integer linkId);

  SiteInfo findTopBySiteIdOrderByTimestampDesc(Integer siteId);

}
