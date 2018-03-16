package ru.dz.testtask.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import ru.dz.testtask.repositories.UniqueUserRepository;
import ru.dz.testtask.utils.DateHelper;

import java.util.Date;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
@Repository
public class UniqueUserRepositoryRedisImpl implements UniqueUserRepository {

    private static final String KEY = "unique-user:";

    private final RedisTemplate<String, String> redisTemplate;

    @Autowired
    public UniqueUserRepositoryRedisImpl(RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void addUser(String userId) {
        String key = KEY + DateHelper.getFormattedDate();
        redisTemplate.opsForSet().add(key, userId);
        Date nextDay = DateHelper.getNextDay();
        redisTemplate.expireAt(key, nextDay);
    }

    @Override
    public boolean isUserUnique(String userId) {
        String key = KEY + DateHelper.getFormattedDate();
        return !redisTemplate.opsForSet().isMember(key, userId);
    }
}
