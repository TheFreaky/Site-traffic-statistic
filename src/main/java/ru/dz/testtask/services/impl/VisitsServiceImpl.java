package ru.dz.testtask.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dz.testtask.dto.DayStatsDto;
import ru.dz.testtask.dto.PeriodStatDto;
import ru.dz.testtask.dto.VisitDto;
import ru.dz.testtask.models.Visit;
import ru.dz.testtask.repositories.UniqueUserRepository;
import ru.dz.testtask.repositories.VisitsCountRepository;
import ru.dz.testtask.repositories.VisitsRepository;
import ru.dz.testtask.services.VisitsService;
import ru.dz.testtask.utils.DateHelper;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Provides methods for registering user views of web pages and
 * retrieving statistics such as the number of views, unique and regular users
 * <p>
 * <p>
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
@Service
public class VisitsServiceImpl implements VisitsService {

    private VisitsCountRepository visitsCountRepository;
    private UniqueUserRepository uniqueUserRepository;
    private VisitsRepository visitsRepository;
    private ExecutorService executorService;

    @Autowired
    public VisitsServiceImpl(VisitsCountRepository visitsCountRepository,
                             UniqueUserRepository uniqueUserRepository,
                             VisitsRepository visitsRepository) {
        this.visitsCountRepository = visitsCountRepository;
        this.uniqueUserRepository = uniqueUserRepository;
        this.visitsRepository = visitsRepository;
        this.executorService = Executors.newCachedThreadPool();
    }

    /**
     * Register user web page visit and returns daily stats
     *
     * @param visitDto dto of visit
     * @return daily stats (views and unique user count)
     */
    @Override
    public DayStatsDto registerVisit(VisitDto visitDto) {
        String currentDate = DateHelper.getFormattedCurrentDate();

        DayStatsDto todayStats = getTodayStats(currentDate);
        executorService.execute(() -> updateTodayStats(visitDto, currentDate));

        return todayStats;
    }

    /**
     * Returns stats for the specified period: the number of views and
     * the number of unique and regular users.
     * The user is regular when he visited at least 10 different pages
     * for the specified period.
     *
     * @param start start of period in yyyy-MM-dd format
     * @param end   end of period in yyyy-MM-dd format
     * @return stats for period (views, unique and regular user count)
     */
    @Override
    public PeriodStatDto getPeriodStats(String start, String end) {

        List<String> dates = DateHelper.getFormattedPeriodDates(start, end);

        Long visitsCount = visitsCountRepository.countPeriodVisits(dates);
        Long uniqueUserCount = uniqueUserRepository.countPeriodUniqueUser(dates);
        Long regularUser = visitsRepository.countRegularUserByDateBetween(
                DateHelper.getDate(start),
                DateHelper.getDate(end));

        return PeriodStatDto.builder()
                .visitorsCount(visitsCount)
                .uniqueUserCount(uniqueUserCount)
                .regularUserCount(regularUser)
                .build();
    }

    private DayStatsDto getTodayStats(String currentDate) {

        Long visitsCount = visitsCountRepository.countTodayVisits(currentDate);
        Long uniqueUserCount = uniqueUserRepository.countTodayUniqueUser(currentDate);

        return DayStatsDto.builder()
                .visitorsCount(visitsCount)
                .uniqueVisitorsCount(uniqueUserCount)
                .build();
    }

    @Transactional
    protected void updateTodayStats(VisitDto visitDto, String currentDate) {

        visitsCountRepository.save(currentDate);
        String userId = visitDto.getUserId();
        uniqueUserRepository.save(userId, currentDate);

        Visit visit = Visit.builder()
                .pageId(visitDto.getPageId())
                .date(DateHelper.getDate(currentDate))
                .userId(visitDto.getUserId())
                .build();

        visitsRepository.save(visit);
    }
}
