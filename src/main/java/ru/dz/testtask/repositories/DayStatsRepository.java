package ru.dz.testtask.repositories;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
public interface DayStatsRepository {
    Long incrementVisitorsCount();

    Long incrementUniqueVisitorsCount();

    Long getUniqueVisitorsCount();
}
