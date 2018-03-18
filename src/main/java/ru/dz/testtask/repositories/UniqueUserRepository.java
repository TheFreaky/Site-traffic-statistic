package ru.dz.testtask.repositories;

import java.util.List;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
public interface UniqueUserRepository {

    void save(String userId, String currentDate);
    Long countTodayUniqueUser(String currentDate);
    Long countPeriodUniqueUser(List<String> dates);
}
