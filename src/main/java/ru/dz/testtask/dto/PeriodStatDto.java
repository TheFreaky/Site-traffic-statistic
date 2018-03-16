package ru.dz.testtask.dto;

import lombok.Data;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
@Data
public class PeriodStatDto {

    private Integer visitorsCount;
    private Integer uniqueUserCount;
    private Integer regularUserCount;
}
