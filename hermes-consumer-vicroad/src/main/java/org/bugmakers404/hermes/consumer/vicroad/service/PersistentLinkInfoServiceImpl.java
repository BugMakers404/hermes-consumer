package org.bugmakers404.hermes.consumer.vicroad.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.dao.LinkInfoDAO;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkInfo;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkInfoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersistentLinkInfoServiceImpl implements PersistentLinkInfoService {

  @NonNull
  private final LinkInfoDAO linkInfoDAO;

  @Override
  public LinkInfo saveLinkInfoIfChanged(@NonNull LinkInfo linkGeoInfo) {
      LinkInfo latestLinkInfo = linkInfoDAO.findTopByLinkIdOrderByTimestampDesc(
              linkGeoInfo.getLinkId());

      if (latestLinkInfo == null || !latestLinkInfo.isSame(linkGeoInfo)) {
          return linkInfoDAO.save(linkGeoInfo);
      } else {
          return latestLinkInfo;
      }
  }

}
