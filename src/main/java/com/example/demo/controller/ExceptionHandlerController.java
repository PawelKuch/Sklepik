package com.example.demo.controller;

import com.example.demo.exception.ItemNotFoundException;
import com.example.demo.exception.OrderNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(ItemNotFoundException.class)
    public String ItemNotFoundExceptionHandler(ItemNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "errorException";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String UserNotFoundExceptionHandler(UserNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "errorException";
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public String OrderNotFoundExceptionHandler(OrderNotFoundException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "errorException";
    }
}
