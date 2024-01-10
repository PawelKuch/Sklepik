package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.service.DataBaseService;
import com.example.demo.service.ToDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class HomeController {

    DataBaseService dataBaseService;
    ToDataService toDataService;
    public HomeController( DataBaseService dataBaseService, ToDataService toDataService){
        this.dataBaseService = dataBaseService;
        this.toDataService = toDataService;
    }
    @GetMapping("")
    public String getHome(){
        return "home";
    }

    @GetMapping("/shop")
    public String getShop(Model model){
        model.addAttribute("users", toDataService.getUsers(dataBaseService.getUsers()));
        model.addAttribute("products", toDataService.getItems(dataBaseService.getItems()));
        model.addAttribute("orders", toDataService.getOrders(dataBaseService.getOrders()));
        return "shop";
    }
    @PostMapping("/shop")
    public RedirectView saveOrder(@RequestParam("selectedUser") String userId,
                                  @RequestParam("selectedItem") String itemId,
                                  @RequestParam("amount") String amount,
                                  @RequestParam("purchasePrice") String purchasePrice,
                                  RedirectAttributes ra){
        if(!userId.isEmpty() && !itemId.isEmpty() && !purchasePrice.isEmpty()){
            dataBaseService.addOrder(userId, itemId, Integer.parseInt(amount), Double.parseDouble(purchasePrice));
            return new RedirectView("/shop");
        }
        return new RedirectView("/error");
    }

    @GetMapping("/users")
    public String getAddUser(Model model){
        model.addAttribute("users", dataBaseService.getUsers());
        return "users";
    }
    @PostMapping("/users")
    public RedirectView addUser(@RequestParam("userName") String userName, RedirectAttributes ra){
        if(!userName.isEmpty()){
            dataBaseService.addUser(userName);
        }
        return new RedirectView("/users");
    }


    @GetMapping("/products")
    public String getProducts (Model m){
        m.addAttribute("items", dataBaseService.getItems());
        return "products";
    }

    @PostMapping("/products")
    public RedirectView addItem(@RequestParam String itemName){
        if(!itemName.isEmpty()){
            dataBaseService.addItem(itemName);
        }
        return new RedirectView("/products");
    }

    @GetMapping("/statistics")
    public String getOwnerStatistics(Model model){

        return "statistics";
    }


}
