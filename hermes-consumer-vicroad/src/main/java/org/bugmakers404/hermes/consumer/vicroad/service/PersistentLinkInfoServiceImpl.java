package org.bugmakers404.hermes.consumer.vicroad.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
  public LinkInfo save(LinkInfo linkInfo) {
    return linkInfoDAO.save(linkInfo);
  }

  @Override
  public List<LinkInfo> saveAll(List<LinkInfo> allLinkInfo) {
    return linkInfoDAO.saveAll(allLinkInfo);
  }

  @Override
  public LinkInfo saveIfChanged(LinkInfo linkInfo) {
    LinkInfo latestLinkInfo = linkInfoDAO.findTopByLinkIdOrderByTimestampDesc(linkInfo.getLinkId());

    if (latestLinkInfo == null || !latestLinkInfo.isSame(linkInfo)) {
      return linkInfoDAO.save(linkInfo);
    } else {
      return latestLinkInfo;
    }
  }

  @Override
  public List<LinkInfo> saveAllIfChanged(List<LinkInfo> allLinkInfo) {
    List<LinkInfo> savedLinkInfo = allLinkInfo.stream().filter(event -> {
      LinkInfo latestLinkInfo = linkInfoDAO.findTopByLinkIdOrderByTimestampDesc(event.getLinkId());
      return Objects.isNull(latestLinkInfo) || !latestLinkInfo.isSame(event);
    }).collect(Collectors.toList());
    return linkInfoDAO.saveAll(savedLinkInfo);
  }
}
