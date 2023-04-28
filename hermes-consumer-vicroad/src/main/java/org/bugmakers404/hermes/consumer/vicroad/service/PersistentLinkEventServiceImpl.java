package org.bugmakers404.hermes.consumer.vicroad.service;

import java.time.OffsetDateTime;
import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkEvent;
import org.bugmakers404.hermes.consumer.vicroad.dao.LinkEventDAO;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class PersistentLinkEventServiceImpl implements PersistentLinkEventService {

  @NonNull
  private final LinkEventDAO linkEventDAO;

  @Override
  public LinkEvent saveLinkEvent(LinkEvent linkEvent) {
    return linkEventDAO.save(linkEvent);
  }

  @Override
  public List<LinkEvent> getAllLinkEvents() {
    return linkEventDAO.findAll();
  }

  @Override
  public List<LinkEvent> getAllLinkEventsByLinkId(Integer linkId) {
    return linkEventDAO.findAllByLinkId(linkId);
  }

  @Override
  public LinkEvent getLatestLinkEventByLinkId(Integer linkId) {
    return linkEventDAO.findTopByLinkIdOrderByTimestampDesc(linkId);
  }

  @Override
  public List<LinkEvent> getAllLinkEventsByTimestamp(OffsetDateTime timestamp) {
    return linkEventDAO.findAllByTimestamp(timestamp);
  }

  @Override
  public LinkEvent getLinkEventByLinkIdAndTimestamp(Integer linkId, OffsetDateTime timestamp) {
    return linkEventDAO.findByLinkIdAndTimestamp(linkId, timestamp);
  }

}
