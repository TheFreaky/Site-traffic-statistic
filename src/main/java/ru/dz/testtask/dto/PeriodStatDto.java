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
public class PeriodStatDto {

    private Long visitorsCount;
    private Long uniqueUserCount;
    private Long regularUserCount;
}
