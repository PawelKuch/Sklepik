package com.example.demo.controller;

import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;



@Controller
public class ShopController {
    DataBaseService dataBaseService;
    ToDataService toDataService;
    FileHandlerService fileHandlerService;
    ExpensesBaseService expensesBaseService;
    StatisticsService statisticsService;
    public ShopController(DataBaseService dataBaseService, ToDataService toDataService,
                          FileHandlerService fileHandlerService, ExpensesBaseService expensesBaseService,
                          StatisticsService statisticsService){

        this.dataBaseService = dataBaseService;
        this.toDataService = toDataService;
        this.fileHandlerService = fileHandlerService;
        this.expensesBaseService = expensesBaseService;
        this.statisticsService = statisticsService;
    }
    @GetMapping({"/home-page", "/"})
    public String getShop(Model model){
        model.addAttribute("users", dataBaseService.getUsers());
        model.addAttribute("products", dataBaseService.getItems());
        model.addAttribute("orders", dataBaseService.getOrders());
        model.addAttribute("shopPage", true);
        return "shop";
    }

    @PostMapping("/home-page")
    public RedirectView saveOrder(@RequestParam(value = "selectedUser", required = false) String userId,
                                  @RequestParam(value = "selectedItem", required = false) String itemId,
                                  @RequestParam(value = "amount", required = false) String amount,
                                  @RequestParam(value = "purchasePrice", required = false) String purchasePrice,
                                  @RequestParam(value = "sellPrice", required = false) String sellPrice,
                                  @RequestParam(value = "isMultipack", required = false) String isMultipack) throws UserNotFoundException, ItemNotFoundException{
        if(!userId.isEmpty() && !itemId.isEmpty() && !purchasePrice.isEmpty() && !sellPrice.isEmpty()){
            boolean isMultipackFlag = isMultipack != null;
            dataBaseService.addOrder(userId, itemId, Integer.parseInt(amount), Double.parseDouble(purchasePrice), Double.parseDouble(sellPrice), false, isMultipackFlag);
            return new RedirectView("/home-page");
        }
        return new RedirectView("/error");
    }

    @GetMapping("/home-page/{id}")
    public RedirectView settleTheOrders(@PathVariable String id) throws OrderNotFoundException{
        dataBaseService.settleTheOrder(id);
        return new RedirectView("/home-page");
    }

    @GetMapping("/order-page/{id}")
    public String updateOrder(@PathVariable String id, Model model) throws OrderNotFoundException{
        model.addAttribute("users", dataBaseService.getUsers());
        model.addAttribute("items", dataBaseService.getItems());
        model.addAttribute("originalOrder", dataBaseService.getOrder(id));
        return "order";

    }

    @PostMapping("/order-page/{id}")
    public RedirectView getUpdatedOrder(@PathVariable String id, @RequestParam("selectedUser") String userId,
                                        @RequestParam("selectedItem")  String itemId,
                                        @RequestParam("amount") Integer amount, @RequestParam("purchasePrice") Double purchasePrice,
                                        @RequestParam("sellPrice") Double sellPrice) throws OrderNotFoundException{

        dataBaseService.updateOrder(id, userId, itemId, amount, purchasePrice, sellPrice);
        return new RedirectView("/home-page");

    }

    @GetMapping("/delete-order/{id}")
    public RedirectView deleteOrder(@PathVariable String id) throws OrderNotFoundException{
        dataBaseService.deleteOrder(id);
        return new RedirectView("/home-page");

    }
}