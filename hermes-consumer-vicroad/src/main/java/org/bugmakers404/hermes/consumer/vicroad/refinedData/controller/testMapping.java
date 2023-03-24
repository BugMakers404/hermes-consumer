package org.bugmakers404.hermes.consumer.vicroad.refinedData.controller;

import lombok.RequiredArgsConstructor;
import org.bugmakers404.hermes.consumer.vicroad.refinedData.service.PersistentLinkService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class testMapping {

  public final PersistentLinkService persistentLinkService;

  @RequestMapping(value = {"/data"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
  public String getAllData() {
    return persistentLinkService.findLinkEventByLinkId(1).toString();
  }


}
