package org.bugmakers404.hermes.consumer.vicroad.refinedData.service;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.dao.LinkGeoInfoDAO;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkGeoInfo;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.service.interfaces.LinkGeoService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersistentLinkGeoService implements LinkGeoService {

  @NonNull
  private final LinkGeoInfoDAO linkGeoInfoDAO;

  @Override
  public LinkGeoInfo saveLinkGeoInfo(LinkGeoInfo linkGeoInfo) {
    return linkGeoInfoDAO.save(linkGeoInfo);
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
