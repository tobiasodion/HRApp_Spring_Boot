package com.example.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AdminController {
    /** Transition to the admin authority only screen */
    @GetMapping ("/admin" )
    public String getAdmin() {
        return "admin/admin" ;
    }
}