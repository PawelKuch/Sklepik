package com.example.demo.controller;

import com.example.demo.service.DataBaseService;
import com.example.demo.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class StatisticsController {

    DataBaseService dataBaseService;
    StatisticsService statisticsService;

    public StatisticsController(DataBaseService dataBaseService, StatisticsService statisticsService) {
        this.dataBaseService = dataBaseService;
        this.statisticsService = statisticsService;
    }
    
    @GetMapping("/statistics")
    public String getStatistics(Model model){
        model.addAttribute("statisticsPage", true);
        model.addAttribute("users", dataBaseService.getUsers());
        model.addAttribute("ordersStatistics", statisticsService.getUserOrdersStatistics());
        model.addAttribute("expensesStatistics", statisticsService.getUserExpensesStatistics());
        model.addAttribute("generalStatistics", statisticsService.getGeneralStatistics());
        return "statistics";
    }
}
