package ru.dz.testtask.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;
import ru.dz.testtask.repositories.DayStatsRepository;
import ru.dz.testtask.utils.DateHelper;

import java.util.Date;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
@Repository
public class DayStatsRepositoryRedisImpl implements DayStatsRepository {

    private static final String VISITORS = "visitors";
    private static final String UNIQUE_VISITORS = "unique";

    private final RedisTemplate<String, Long> redisTemplate;

    @Autowired
    public DayStatsRepositoryRedisImpl(RedisTemplate<String, Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public Long incrementVisitorsCount() {
        Date nextDay = DateHelper.getNextDay();
        redisTemplate.expireAt(VISITORS, nextDay);
        return redisTemplate.opsForValue().increment(VISITORS, 1);
    }

    @Override
    public Long incrementUniqueVisitorsCount() {
        Date nextDay = DateHelper.getNextDay();
        redisTemplate.expireAt(UNIQUE_VISITORS, nextDay);
        return redisTemplate.opsForValue().increment(UNIQUE_VISITORS, 1);
    }


    @Override
    public Long getUniqueVisitorsCount() {
        return redisTemplate.opsForValue().get(UNIQUE_VISITORS);
    }


}
