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
public class UsersController {
    DataBaseService dataBaseService;

    public UsersController(DataBaseService dataBaseService) {
        this.dataBaseService = dataBaseService;
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
}
