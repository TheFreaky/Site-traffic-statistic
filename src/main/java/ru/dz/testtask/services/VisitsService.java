package ru.dz.testtask.services;

import ru.dz.testtask.dto.DayStatsDto;
import ru.dz.testtask.dto.PeriodStatDto;
import ru.dz.testtask.dto.VisitDto;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
public interface VisitsService {

    DayStatsDto registerVisit(VisitDto visitDto);
    PeriodStatDto getPeriodStats(String start, String finish);
}
