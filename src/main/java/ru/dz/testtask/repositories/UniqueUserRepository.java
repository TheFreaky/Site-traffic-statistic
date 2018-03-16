package ru.dz.testtask.repositories;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
public interface UniqueUserRepository {
    void addUser(String userId);

    boolean isUserUnique(String userId);
}
