package ru.dz.testtask.dto;

import lombok.Builder;
import lombok.Data;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
@Data
@Builder
public class DayStatsDto {

    private Long visitorsCount;
    private Long uniqueVisitorsCount;
}
