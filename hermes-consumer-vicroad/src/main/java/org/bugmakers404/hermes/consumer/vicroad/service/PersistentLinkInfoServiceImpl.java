package org.bugmakers404.hermes.consumer.vicroad.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.dao.LinkInfoDAO;
import org.bugmakers404.hermes.consumer.vicroad.entity.links.LinkInfo;
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
  public LinkInfo saveIfChanged(LinkInfo infoEvent) {
    LinkInfo latestLinkInfo = linkInfoDAO.findTopByLinkIdOrderByTimestampDesc(
        infoEvent.getLinkId());

    if (latestLinkInfo == null || !latestLinkInfo.isSame(infoEvent)) {
      return linkInfoDAO.save(infoEvent);
    } else {
      return latestLinkInfo;
    }
  }

  @Override
  public List<LinkInfo> saveAllIfChanged(List<LinkInfo> allInfoEvents) {
    List<LinkInfo> savedLinkInfo = allInfoEvents.stream().filter(linkInfo -> {
      LinkInfo latestLinkInfo = linkInfoDAO.findTopByLinkIdOrderByTimestampDesc(
          linkInfo.getLinkId());
      return Objects.isNull(latestLinkInfo) || !latestLinkInfo.isSame(linkInfo);
    }).collect(Collectors.toList());
    return linkInfoDAO.saveAll(savedLinkInfo);
  }
}
