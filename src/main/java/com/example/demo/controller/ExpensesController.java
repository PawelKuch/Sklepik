package com.example.demo.controller;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.DataBaseService;
import com.example.demo.service.ExpensesBaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class ExpensesController {

    DataBaseService dataBaseService;
    ExpensesBaseService expensesBaseService;

    public ExpensesController(DataBaseService dataBaseService, ExpensesBaseService expensesBaseService){
        this.dataBaseService = dataBaseService;
        this.expensesBaseService = expensesBaseService;
    }

    @GetMapping("/expenses")
    public String getExpenses(Model model){
        model.addAttribute("users", dataBaseService.getUsers());
        model.addAttribute("products", dataBaseService.getItems());
        model.addAttribute("expenses", expensesBaseService.getExpenses());
        model.addAttribute("expensesPage", true);
        return "expenses";
    }
    @PostMapping("/expenses")
    public RedirectView addExpenses(@RequestParam("selectedUser") String userId,
                                    @RequestParam("item") String item,
                                    @RequestParam("amount") String amount,
                                    @RequestParam("purchasePrice") String purchasePrice){
        if(!userId.isEmpty() && !item.isEmpty() && !amount.isEmpty() && !purchasePrice.isEmpty()){
            try {
                expensesBaseService.addExpense(userId, item, Integer.parseInt(amount), Double.parseDouble(purchasePrice));
            } catch (UserNotFoundException ex){
                return new RedirectView("/orderNotFound");
            }
        }
        return new RedirectView("/expenses");
    }
}
