package ru.dz.testtask.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.dz.testtask.dto.DayStatsDto;
import ru.dz.testtask.dto.VisitDto;
import ru.dz.testtask.dto.PeriodStatDto;
import ru.dz.testtask.services.VisitsService;

import java.util.Date;

/**
 * 16.03.18
 *
 * @author Kuznetsov Maxim
 */
@RestController
public class VisitsController {

    private VisitsService visitsService;

    @Autowired
    public VisitsController(VisitsService visitsService) {
        this.visitsService = visitsService;
    }

    @PostMapping("/visit")
    public DayStatsDto registerVisit(VisitDto visitDto) {
        return visitsService.registerVisit(visitDto);
    }

    @GetMapping("/stats")
    public PeriodStatDto getPeriodStats(Date start, Date finish) {
        return visitsService.getPeriodStats(start, finish);
    }
}
