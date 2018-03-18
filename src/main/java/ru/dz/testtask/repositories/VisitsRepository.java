package ru.dz.testtask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.dz.testtask.models.Visit;

import java.util.Date;


/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
@Repository
public interface VisitsRepository extends JpaRepository<Visit, Long> {

    @Query(value = "SELECT count(*) FROM " +
            "(SELECT user_id FROM visits WHERE date BETWEEN :start AND :end " +
            "GROUP BY user_id HAVING count(page_id) >= 10 ) AS ru", nativeQuery = true)
    Long countRegularUserByDateBetween(@Param("start") Date start, @Param("end") Date end);
}
