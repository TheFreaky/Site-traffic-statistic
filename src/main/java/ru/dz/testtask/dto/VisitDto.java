package ru.dz.testtask.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
@Data
public class VisitDto {

    @NotNull
    private String userId;

    @NotNull
    private String pageId;
}
