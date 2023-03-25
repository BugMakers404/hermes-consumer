package org.bugmakers404.hermes.consumer.vicroad.refinedData.service;

import java.time.LocalDateTime;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.dao.LinkDAO;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.entities.LinkEvent;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.service.interfaces.LinkService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersistentLinkService implements LinkService {

  @NonNull
  private final LinkDAO linkDAO;

  @Override
  public LinkEvent saveOneLinkEvent(LinkEvent linkEvent) {
    return linkDAO.save(linkEvent);
  }

  @Override
  public List<LinkEvent> getAllLinkEvents() {
    return linkDAO.findAll();
  }

  @Override
  public List<LinkEvent> getAllLinkEventsByLinkId(Integer linkId) {
    return linkDAO.findAllByLinkId(linkId);
  }

  @Override
  public List<LinkEvent> getAllLinkEventsByTimestamp(LocalDateTime timestamp) {
    return linkDAO.findAllByTimestamp(timestamp);
  }

  @Override
  public LinkEvent getALinkEventByLinkIdAndTimestamp(Integer linkId, LocalDateTime timestamp) {
    System.out.println(timestamp);
    System.out.println(timestamp + "_" + linkId);
    return linkDAO.findById(timestamp + "_" + linkId).orElseThrow();
  }

}
