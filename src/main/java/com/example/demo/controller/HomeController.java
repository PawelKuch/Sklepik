package com.example.demo.controller;

import com.example.demo.service.DataBaseService;
import com.example.demo.service.ExpensesBaseService;
import com.example.demo.service.FileHandlerService;
import com.example.demo.service.ToDataService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;



@Controller
public class HomeController {
    DataBaseService dataBaseService;
    ToDataService toDataService;
    FileHandlerService fileHandlerService;
    ExpensesBaseService expensesBaseService;
    public HomeController(DataBaseService dataBaseService, ToDataService toDataService,
                          FileHandlerService fileHandlerService, ExpensesBaseService expensesBaseService){
        this.dataBaseService = dataBaseService;
        this.toDataService = toDataService;
        this.fileHandlerService = fileHandlerService;
        this.expensesBaseService = expensesBaseService;
    }
    @GetMapping("/")
    public String getShop(Model model){
        model.addAttribute("users", dataBaseService.getUsers());
        model.addAttribute("products", dataBaseService.getItems());
        model.addAttribute("orders", dataBaseService.getOrders());
        if(expensesBaseService.getExpenses() != null){
            model.addAttribute("expenses", expensesBaseService.getExpenses());
        }
        model.addAttribute("shopPage", true);
        return "shop";
    }

    @PostMapping("/")
    public RedirectView saveOrder(@RequestParam("selectedUser") String userId,
                                  @RequestParam("selectedItem") String itemId,
                                  @RequestParam("amount") String amount,
                                  @RequestParam("purchasePrice") String purchasePrice,
                                  @RequestParam("sellPrice") String sellPrice,
                                  @RequestParam("isExpense") Boolean isExpense){
        if(!userId.isEmpty() && !itemId.isEmpty() && !purchasePrice.isEmpty() && !sellPrice.isEmpty() && isExpense != null){
            if(isExpense){
                expensesBaseService.addExpense(userId, itemId, Integer.parseInt(amount), Double.parseDouble(purchasePrice));
            }else{
                dataBaseService.addOrder(userId, itemId, Integer.parseInt(amount), Double.parseDouble(purchasePrice), Double.parseDouble(sellPrice));
            }
            return new RedirectView("/");
        }
        return new RedirectView("/error");
    }

    @GetMapping("/users")
    public String getAddUser(Model model){
        model.addAttribute("users", dataBaseService.getUsers());
        model.addAttribute("usersPage", true);
        return "users";
    }

    @PostMapping("/users")
    public RedirectView addUser(@RequestParam("userName") String userName, RedirectAttributes ra){
        if(userName.isEmpty()){
            ra.addFlashAttribute("isUserEmpty", true);
        }else if(dataBaseService.userExists(userName)) {
            ra.addFlashAttribute("userExists", true );
        }else{
            dataBaseService.addUser(userName);
        }
        return new RedirectView("/users");
    }
    @GetMapping("/products")
    public String getProducts (Model model){
        model.addAttribute("items", dataBaseService.getItems());
        model.addAttribute("productsPage", true);
        return "products";
    }

    @PostMapping("/products")
    public RedirectView addItem(@RequestParam String itemName, RedirectAttributes ra){
        if(itemName.isEmpty()){
            ra.addFlashAttribute("isItemEmpty", true);
        }else if(dataBaseService.itemExists(itemName)) {
            ra.addFlashAttribute("itemExists", true );
        }else{
            dataBaseService.addItem(itemName);
        }
        return new RedirectView("/products");
    }
}