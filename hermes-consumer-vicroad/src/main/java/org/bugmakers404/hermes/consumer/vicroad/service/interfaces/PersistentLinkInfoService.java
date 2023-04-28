package org.bugmakers404.hermes.consumer.vicroad.service.interfaces;

import java.util.List;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkInfo;

public interface PersistentLinkInfoService {

  LinkInfo saveLinkInfoIfChanged(LinkInfo linkGeoInfo);

  LinkInfo getLatestLinkInfoByLinkId(Integer linkId);

  List<LinkInfo> getAllLinkInfo();
}
