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

import java.util.List;


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
        model.addAttribute("shopPage", true);
        return "shop";
    }

    @PostMapping("/")
    public RedirectView saveOrder(@RequestParam(value = "selectedUser", required = false) String userId,
                                  @RequestParam(value = "selectedItem", required = false) String itemId,
                                  @RequestParam(value = "amount", required = false) String amount,
                                  @RequestParam(value = "purchasePrice", required = false) String purchasePrice,
                                  @RequestParam(value = "sellPrice", required = false) String sellPrice,
                                  @RequestParam(value = "selectedOrder", required = false) List<String> orderIDs){
        if(!userId.isEmpty() && !itemId.isEmpty() && !purchasePrice.isEmpty() && !sellPrice.isEmpty()){
            dataBaseService.addOrder(userId, itemId, Integer.parseInt(amount), Double.parseDouble(purchasePrice), Double.parseDouble(sellPrice));
            return new RedirectView("/");
        }
        return new RedirectView("/error");
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
                                    @RequestParam("selectedItem") String itemId,
                                    @RequestParam("amount") String amount,
                                    @RequestParam("purchasePrice") String purchasePrice){
        if(!userId.isEmpty() && !itemId.isEmpty() && !amount.isEmpty() && !purchasePrice.isEmpty()){
            expensesBaseService.addExpense(userId, itemId, Integer.parseInt(amount), Double.parseDouble(purchasePrice));
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
    public RedirectView addUser(@RequestParam(value = "userName", required = false) String userName,
                                @RequestParam(value = "selectedUser", required = false) List<String> userList,
                                RedirectAttributes ra){
        if (userList != null){
            dataBaseService.removeUsers(userList);
        }else if(userName == null || userName.isEmpty()){
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
    public RedirectView addItem(@RequestParam(value = "itemName",required = false) String itemName,
                                @RequestParam(value = "selectedItem", required = false) List<String> productsList,
                                RedirectAttributes ra){
        if(productsList != null){
            dataBaseService.removeItems(productsList);
        }else if(itemName.isEmpty()){
            ra.addFlashAttribute("isItemEmpty", true);
        }else if(dataBaseService.itemExists(itemName)) {
            ra.addFlashAttribute("itemExists", true );
        }else{
            dataBaseService.addItem(itemName);
        }
        return new RedirectView("/products");
    }
}