package ru.dz.testtask.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.dz.testtask.repositories.UniqueUserRepository;

import java.util.List;
import java.util.Optional;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
@Repository
public class UniqueUserRepositoryRedisImpl implements UniqueUserRepository {

    private static final String KEY = "unique-user:";

    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    public UniqueUserRepositoryRedisImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void save(String userId, String currentDate) {
        String key = KEY + currentDate;
        redisTemplate.opsForHyperLogLog().add(key, userId);
    }

    @Override
    public Long countTodayUniqueUser(String currentDate) {
        String key = KEY + currentDate;
        return Optional.ofNullable(redisTemplate.opsForHyperLogLog().size(key)).orElse(0L);
    }

    @Override
    public Long countPeriodUniqueUser(List<String> dates) {
        Long uniqueUserCount = 0L;

        for (String date : dates) {
            uniqueUserCount += countTodayUniqueUser(date);
        }

        return uniqueUserCount;
    }
}
