package ru.dz.testtask.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.dz.testtask.repositories.VisitsCountRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
@Repository
public class VisitsCountRepositoryRedisImpl implements VisitsCountRepository {

    private static final String KEY = "visits:";

    private RedisTemplate<String, Long> redisTemplate;

    @Autowired
    public VisitsCountRepositoryRedisImpl(RedisTemplate<String, Long> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(String currentDate) {
        String key = KEY + currentDate;
        redisTemplate.opsForValue().increment(key, 1);
    }

    @Override
    public Long countTodayVisits(String currentDate) {
        String key = KEY + currentDate;
        return Optional.ofNullable(redisTemplate.opsForValue().get(key)).orElse(0L);
    }

    @Override
    public Long countPeriodVisits(List<String> dates) {
        Long visitsCount = 0L;

        List<String> keys = new ArrayList<>();
        dates.forEach(s -> keys.add(KEY + s));

        List<Long> visits = redisTemplate.opsForValue().multiGet(keys);

        for (Long visit : visits) {

            if (visit != null) {
                visitsCount += visit;
            }
        }
        return visitsCount;
    }
}
