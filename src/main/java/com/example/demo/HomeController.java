package com.example.demo;

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
    @GetMapping("/home")
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
    public RedirectView saveOrder(@RequestParam("selectedUser") String userId, @RequestParam("selectedItem") String itemId, RedirectAttributes ra){
        if(!userId.isEmpty() && !itemId.isEmpty()){
            Order order = new Order();
            order.setUser(dataBaseService.getUser(userId)); //znajdz usera po id
            order.addProductToOrder(dataBaseService.getItem(itemId));
            dataBaseService.addOrder(order);
        }
        return new RedirectView("/shop");
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

    @GetMapping("/orderDetails/{id}")
    public String getOrderDetails(@PathVariable String id, Model model){
        Order order = dataBaseService.getOrder(id);
        List<Item> products = dataBaseService.getItemsForOrder(order);
        User user = dataBaseService.getUserForOrder(order);
        model.addAttribute("order", order);
        model.addAttribute("user", user);
        model.addAttribute("products", products);
        return "orderDetails";
    }

}
