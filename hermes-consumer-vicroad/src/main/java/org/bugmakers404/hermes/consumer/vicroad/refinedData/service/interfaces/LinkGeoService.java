package org.bugmakers404.hermes.consumer.vicroad.refinedData.service.interfaces;

import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkGeoInfo;

public interface LinkGeoService {

  LinkGeoInfo saveLinkGeoInfo(LinkGeoInfo linkGeoInfo);

  LinkGeoInfo findLinkGeoInfoByLinkId(Integer linkId);

  List<LinkGeoInfo> getAllLinkGeoInfo();
}
