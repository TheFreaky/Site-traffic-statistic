package ru.dz.testtask.services;

import ru.dz.testtask.dto.DayStatsDto;
import ru.dz.testtask.dto.VisitDto;
import ru.dz.testtask.dto.PeriodStatDto;

import java.util.Date;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
public interface VisitsService {

    DayStatsDto registerVisit(VisitDto visitDto);
    PeriodStatDto getPeriodStats(Date start, Date finish);
}
