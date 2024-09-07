package com.example.demo.controller;

import com.example.demo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class HomeController {
    DataBaseService dataBaseService;
    ToDataService toDataService;
    FileHandlerService fileHandlerService;
    ExpensesBaseService expensesBaseService;
    StatisticsService statisticsService;
    public HomeController(DataBaseService dataBaseService, ToDataService toDataService,
                          FileHandlerService fileHandlerService, ExpensesBaseService expensesBaseService,
                          StatisticsService statisticsService){
        this.dataBaseService = dataBaseService;
        this.toDataService = toDataService;
        this.fileHandlerService = fileHandlerService;
        this.expensesBaseService = expensesBaseService;
        this.statisticsService = statisticsService;
    }
    @GetMapping("/home-page")
    public String getShop(Model model){
        model.addAttribute("users", dataBaseService.getUsers());
        model.addAttribute("products", dataBaseService.getItems());
        model.addAttribute("orders", dataBaseService.getOrders());
        model.addAttribute("shopPage", true);
        return "shop";
    }
}