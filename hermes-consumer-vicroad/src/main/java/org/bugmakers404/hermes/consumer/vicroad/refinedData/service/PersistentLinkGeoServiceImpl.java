package org.bugmakers404.hermes.consumer.vicroad.refinedData.service;

import java.util.List;
import java.util.Optional;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.dao.LinkGeoInfoDAO;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkGeoInfo;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.service.interfaces.PersistentLinkGeoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersistentLinkGeoServiceImpl implements PersistentLinkGeoService {

  @NonNull
  private final LinkGeoInfoDAO linkGeoInfoDAO;

  @Override
  public LinkGeoInfo saveLinkGeoInfoIfChanged(LinkGeoInfo linkGeoInfo) {
    Optional<LinkGeoInfo> latestLinkGeoInfoOpt = linkGeoInfoDAO.findTopByLinkIdOrderByTimestampDesc(
        linkGeoInfo.getLinkId());

    if (latestLinkGeoInfoOpt.isEmpty() || !latestLinkGeoInfoOpt.get().isSame(linkGeoInfo)) {
      return linkGeoInfoDAO.save(linkGeoInfo);
    } else {
      return latestLinkGeoInfoOpt.get();
    }
  }

  @Override
  public LinkGeoInfo findLinkGeoInfoByLinkId(Integer linkId) {
    return null;
  }

  @Override
  public List<LinkGeoInfo> getAllLinkGeoInfo() {
    return null;
  }
}
