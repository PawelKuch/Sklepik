package com.example.demo.controller;

import com.example.demo.service.DataBaseService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
public class ProductsController {
    DataBaseService dataBaseService;

    public ProductsController(DataBaseService dataBaseService){
        this.dataBaseService = dataBaseService;
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
}
