package org.bugmakers404.hermes.consumer.vicroad.entity.routes;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RouteStats implements Serializable {

    @JsonAlias("travel_time")
    private Integer travelTime;

    private Integer delay;

    private Integer speed;

    @JsonAlias("excess_delay")
    private Integer excessDelay;

    @JsonAlias("data_status")
    private String dataStatus;

    private Integer length;
}
