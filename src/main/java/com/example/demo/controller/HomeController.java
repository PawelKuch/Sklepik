package com.example.demo.controller;

import com.example.demo.service.DataBaseService;
import com.example.demo.service.ToDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.servlet.view.RedirectView;


@Controller
public class HomeController {
    DataBaseService dataBaseService;
    ToDataService toDataService;
    public HomeController( DataBaseService dataBaseService, ToDataService toDataService){
        this.dataBaseService = dataBaseService;
        this.toDataService = toDataService;
    }
    @GetMapping("/")
    public String getShop(Model model){
        boolean shopPage = true;
        model.addAttribute("users", dataBaseService.getUsers());
        model.addAttribute("products", dataBaseService.getItems());
        model.addAttribute("orders", dataBaseService.getOrders());
        model.addAttribute("shopPage", shopPage);
        return "shop";
    }

    @PostMapping("/")
    public RedirectView saveOrder(@RequestParam("selectedUser") String userId,
                                  @RequestParam("selectedItem") String itemId,
                                  @RequestParam("amount") String amount,
                                  @RequestParam("purchasePrice") String purchasePrice,
                                  @RequestParam("sellPrice") String sellPrice,
                                  @RequestParam("isExpense") boolean isExpense){
        if(!userId.isEmpty() && !itemId.isEmpty() && !purchasePrice.isEmpty()){
            dataBaseService.addOrder(userId, itemId, Integer.parseInt(amount), Double.parseDouble(purchasePrice), Double.parseDouble(sellPrice), isExpense);
            return new RedirectView("/");
        }
        return new RedirectView("/error");
    }

    @GetMapping("/users")
    public String getAddUser(Model model){
        boolean usersPage = true;
        model.addAttribute("users", dataBaseService.getUsers());
        model.addAttribute("usersPage", usersPage);
        return "users";
    }

    @PostMapping("/users")
    public RedirectView addUser(@RequestParam("userName") String userName){
        if(!userName.isEmpty()){
            dataBaseService.addUser(userName);
        }
        return new RedirectView("/users");
    }
    @GetMapping("/products")
    public String getProducts (Model model){
        boolean productsPage = true;
        model.addAttribute("items", dataBaseService.getItems());
        model.addAttribute("productsPage", productsPage);
        return "products";
    }

    @PostMapping("/products")
    public RedirectView addItem(@RequestParam String itemName){
        if(!itemName.isEmpty()){
            dataBaseService.addItem(itemName);
        }
        return new RedirectView("/products");
    }
}
