package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.entity.Order;
import com.example.demo.entity.User;
import com.example.demo.service.DataBaseService;
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
    public HomeController( DataBaseService dataBaseService){

        this.dataBaseService = dataBaseService;
    }
    @GetMapping("")
    public String getHome(){
        return "home";
    }

    @GetMapping("/shop")
    public String getShop(Model model){
        model.addAttribute("users", dataBaseService.getUsers());
        model.addAttribute("products", dataBaseService.getItems());
        model.addAttribute("orders", dataBaseService.getOrders());
        return "shop";
    }
    @PostMapping("/shop")
    public RedirectView saveOrder(@RequestParam("selectedUser") String userId,
                                  @RequestParam("selectedItem") String itemId,
                                  @RequestParam("amount") String amount,
                                  RedirectAttributes ra){
        if(!userId.isEmpty() && !itemId.isEmpty()){
            dataBaseService.addOrder(dataBaseService.getUser(userId), dataBaseService.getItem(itemId), Integer.parseInt(amount));
            return new RedirectView("/shop");
        }
        return new RedirectView("/error");
    }

    @GetMapping("/addUser")
    public String getAddUser(Model model){
        model.addAttribute("users", dataBaseService.getUsers());
        return "addUser";
    }
    @PostMapping("/addUser")
    public RedirectView addUser(@RequestParam("userName") String userName, RedirectAttributes ra){
        if(!userName.isEmpty()){
            dataBaseService.addUser(userName);
        }
        return new RedirectView("/addUser");
    }


    @GetMapping("/products")
    public String getProducts (Model m){
        m.addAttribute("items", dataBaseService.getItems());
        return "products";
    }

    @PostMapping("/products")
    public RedirectView addItem(@RequestParam String itemName,
                                @RequestParam("purchasePrice") String purchasePrice){
        if(!itemName.isEmpty()){
            dataBaseService.addItem(itemName, Double.parseDouble(purchasePrice));
        }
        return new RedirectView("/products");
    }

    @GetMapping("/statistics")
    public String getOwnerStatistics(Model model){

        return "statistics";
    }

    @GetMapping("/orderDetails/{id}")
    public String getOrderDetails(@PathVariable String id, Model model){
        Order order = dataBaseService.getOrder(id);
        model.addAttribute("order", order);
        model.addAttribute("user", dataBaseService.getUserForOrder(order));
        model.addAttribute("product", dataBaseService.getItemForOrder(order));
        return "orderDetails";
    }

}
