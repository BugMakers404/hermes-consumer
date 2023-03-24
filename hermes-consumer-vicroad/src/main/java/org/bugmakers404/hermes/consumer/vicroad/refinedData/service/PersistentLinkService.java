package org.bugmakers404.hermes.consumer.vicroad.refinedData.service;

import java.util.List;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.dao.LinkDAO;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.dao.LinkInfoDAO;
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

  @NonNull
  private final LinkInfoDAO linkInfoDAO;

  @Override
  public LinkEvent saveOneLinkEvent(LinkEvent linkEvent) {
    return linkDAO.save(linkEvent);
  }

  @Override
  public LinkEvent findLinkEventByLinkId(Integer linkId) {
    return linkDAO.findByLinkId(linkId);
  }

  @Override
  public List<LinkEvent> getAllLinkEvents() {
    return linkDAO.findAll();
  }
}
