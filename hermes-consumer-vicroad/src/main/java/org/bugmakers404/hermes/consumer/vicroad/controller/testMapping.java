package org.bugmakers404.hermes.consumer.vicroad.controller;

import java.time.OffsetDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.bugmakers404.hermes.consumer.vicroad.entities.links.LinkEvent;
import org.bugmakers404.hermes.consumer.vicroad.service.interfaces.PersistentLinkEventService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class testMapping {

  public final PersistentLinkEventService persistentLinkService;

  @RequestMapping(value = {"/link/{linkId}"})
  public List<LinkEvent> getAllLinkEventsByLindId(@PathVariable Integer linkId) {
    return persistentLinkService.getAllLinkEventsByLinkId(linkId);
  }

  @RequestMapping(value = {"/link/timestamp/{timestamp}"})
  public List<LinkEvent> getAllLinkEventsByLindId(@PathVariable OffsetDateTime timestamp) {
    return persistentLinkService.getAllLinkEventsByTimestamp(timestamp);
  }

  @RequestMapping(value = {"/link/{linkId}/timestamp/{timestamp}"})
  public LinkEvent getLinkEventByLindIdAndTimestamp(@PathVariable Integer linkId, @PathVariable OffsetDateTime timestamp) {
    System.out.println(timestamp);
    return persistentLinkService.getLinkEventByLinkIdAndTimestamp(linkId, timestamp);
  }

}
