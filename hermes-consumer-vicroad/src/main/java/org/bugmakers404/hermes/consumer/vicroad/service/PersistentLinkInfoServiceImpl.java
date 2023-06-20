package org.bugmakers404.hermes.consumer.vicroad.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    public LinkInfo save(LinkInfo event) {
        LinkInfo latestLinkInfo = linkInfoDAO.findTopByLinkIdOrderByTimestampDesc(
            event.getLinkId());

        if (latestLinkInfo == null || !latestLinkInfo.isSame(event)) {
            return linkInfoDAO.save(event);
        } else {
            return latestLinkInfo;
        }
    }

    @Override
    public List<LinkInfo> saveAll(List<LinkInfo> events) {
        List<LinkInfo> savedEvents = new ArrayList<>();
        for (LinkInfo event : events) {
            LinkInfo latestLinkInfo = linkInfoDAO.findTopByLinkIdOrderByTimestampDesc(
                event.getLinkId());
            if (Objects.isNull(latestLinkInfo) || !latestLinkInfo.isSame(event)) {
                savedEvents.add(event);
            }
        }
        return linkInfoDAO.saveAll(savedEvents);
    }
}
