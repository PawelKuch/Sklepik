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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;


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

    @GetMapping("/users")
    public String getAddUser(Model model){
        model.addAttribute("users", dataBaseService.getUsers());
        model.addAttribute("usersPage", true);
        return "users";
    }

    @PostMapping("/users")
    public RedirectView addUser(@RequestParam(value = "userName") String userName,
                                RedirectAttributes ra){
        if(userName == null || userName.isEmpty()){
            ra.addFlashAttribute("isUserEmpty", true);
        }else if(dataBaseService.userExists(userName)) {
            ra.addFlashAttribute("userExists", true );
        }else{
            dataBaseService.addUser(userName);
        }
        return new RedirectView("/users");
    }

    @PostMapping("/delete-users")
    public RedirectView removeUsers(@RequestParam("selectedUser") List<String> usersList){
        dataBaseService.removeUsers(usersList);
        return new RedirectView("/users");
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

    @GetMapping("/products")
    public String getProducts (Model model){
        model.addAttribute("items", dataBaseService.getItems());
        model.addAttribute("productsPage", true);
        return "products";
    }

    @PostMapping("/products")
    public RedirectView addItem(@RequestParam(value = "itemName",required = false) String itemName,
                                RedirectAttributes ra){
       if(itemName == null || itemName.isEmpty()){
            ra.addFlashAttribute("isItemEmpty", true);
        }else if(dataBaseService.itemExists(itemName)) {
            ra.addFlashAttribute("itemExists", true );
        }else{
            dataBaseService.addItem(itemName);
        }
        return new RedirectView("/products");
    }
    @PostMapping("/delete-products")
    public RedirectView removeProducts(@RequestParam(value = "selectedItem") List<String> productsList){
        if (productsList != null) {
            dataBaseService.removeItems(productsList);
        }
        return new RedirectView("/products");
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