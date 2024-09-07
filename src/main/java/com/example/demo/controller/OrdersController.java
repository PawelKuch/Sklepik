package com.example.demo.controller;

import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.DataBaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class OrdersController {
    DataBaseService dataBaseService;
    public OrdersController(DataBaseService dataBaseService){
        this.dataBaseService = dataBaseService;
    }

    @PostMapping("/home-page")
    public RedirectView saveOrder(@RequestParam(value = "selectedUser", required = false) String userId,
                                  @RequestParam(value = "selectedItem", required = false) String itemId,
                                  @RequestParam(value = "amount", required = false) String amount,
                                  @RequestParam(value = "purchasePrice", required = false) String purchasePrice,
                                  @RequestParam(value = "sellPrice", required = false) String sellPrice,
                                  @RequestParam(value = "selectedOrder", required = false) List<String> orderIDs){
        if(!userId.isEmpty() && !itemId.isEmpty() && !purchasePrice.isEmpty() && !sellPrice.isEmpty()){
            try {
                dataBaseService.addOrder(userId, itemId, Integer.parseInt(amount), Double.parseDouble(purchasePrice), Double.parseDouble(sellPrice), false);
                return new RedirectView("/home-page");
            } catch (UserNotFoundException | ItemNotFoundException ex) {
                throw new RuntimeException("failed adding order", ex);
            }
        }
        return new RedirectView("/error");
    }

    @GetMapping("/home-page/{id}")
    public RedirectView settleTheOrders(@PathVariable String id){
        try {
            dataBaseService.settleTheOrder(id);
            return new RedirectView("/home-page");
        } catch (OrderNotFoundException e){
            return new RedirectView("/orderNotFound");
        }
    }

    @GetMapping("/order-page/{id}")
    public String updateOrder(@PathVariable String id, Model model){
        model.addAttribute("users", dataBaseService.getUsers());
        model.addAttribute("items", dataBaseService.getItems());
        try {
            model.addAttribute("originalOrder", dataBaseService.getOrder(id));
            return "order";
        }catch (OrderNotFoundException e){
            return "orderNotFound";
        }
    }

    @PostMapping("/order-page/{id}")
    public RedirectView getUpdatedOrder(@PathVariable String id, @RequestParam("selectedUser") String userId,
                                        @RequestParam("selectedItem")  String itemId,
                                        @RequestParam("amount") Integer amount, @RequestParam("purchasePrice") Double purchasePrice,
                                        @RequestParam("sellPrice") Double sellPrice){
        try {
            dataBaseService.updateOrder(id, userId, itemId, amount, purchasePrice, sellPrice);
            return new RedirectView("/home-page");
        } catch (OrderNotFoundException ex) {
            return new RedirectView("orderNotFound");
        }

    }
}
