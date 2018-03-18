package ru.dz.testtask.repositories;

import java.util.List;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
public interface VisitsCountRepository {

    void save(String currentDate);
    Long countTodayVisits(String currentDate);
    Long countPeriodVisits(List<String> dates);
}
