package org.bugmakers404.hermes.consumer.vicroad.refinedData.entities;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@AllArgsConstructor
public class SiteInfo {

  private Integer id;

  private String name;

  private List<Double> location;
}
