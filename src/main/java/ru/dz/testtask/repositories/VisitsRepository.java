package ru.dz.testtask.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.dz.testtask.models.Visit;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
@Repository
public interface VisitsRepository extends JpaRepository<Visit, Long> {

}
