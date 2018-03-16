package ru.dz.testtask.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.dz.testtask.dto.DayStatsDto;
import ru.dz.testtask.dto.VisitDto;
import ru.dz.testtask.dto.PeriodStatDto;
import ru.dz.testtask.models.Visit;
import ru.dz.testtask.repositories.DayStatsRepository;
import ru.dz.testtask.repositories.UniqueUserRepository;
import ru.dz.testtask.repositories.VisitsRepository;
import ru.dz.testtask.services.VisitsService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
@Service
public class VisitsServiceImpl implements VisitsService {

    private VisitsRepository visitsRepository;
    private UniqueUserRepository uniqueUserRepository;
    private DayStatsRepository dayStatsRepository;

    @Autowired
    public VisitsServiceImpl(VisitsRepository visitsRepository, UniqueUserRepository uniqueUserRepository,
                             DayStatsRepository dayStatsRepository) {
        this.visitsRepository = visitsRepository;
        this.uniqueUserRepository = uniqueUserRepository;
        this.dayStatsRepository = dayStatsRepository;
    }

    @Override
    @Transactional
    public DayStatsDto registerVisit(VisitDto visitDto) {

        Visit visit = buildVisit(visitDto);
        String visitorId = visitDto.getUserId();
        DayStatsDto dayStatsDto = incrementDayStat(visitorId);

        visitsRepository.save(visit);

        return dayStatsDto;
    }

    @Override
    public PeriodStatDto getPeriodStats(Date start, Date finish) {
        return null;
    }

    private Visit buildVisit(VisitDto visitDto) {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        return Visit.builder()
                .pageId(visitDto.getPageId())
                .userId(visitDto.getUserId())
                .time(currentTime)
                .build();
    }

    private DayStatsDto incrementDayStat(String visitorId) {
        Long uniqueVisitorsCount;
        Long visitorsCount = dayStatsRepository.incrementVisitorsCount();

        if (uniqueUserRepository.isUserUnique(visitorId)) {
            uniqueUserRepository.addUser(visitorId);
            uniqueVisitorsCount = dayStatsRepository.incrementUniqueVisitorsCount();
        } else {
            uniqueVisitorsCount = dayStatsRepository.getUniqueVisitorsCount();
        }

        return DayStatsDto.builder()
                .uniqueVisitorsCount(uniqueVisitorsCount)
                .visitorsCount(visitorsCount)
                .build();
    }
}
