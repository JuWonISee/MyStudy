package com.firstassignment.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index(HttpSession session, Model model) {
        model.addAttribute("memberId", session.getAttribute("memberId"));
        model.addAttribute("memberName", session.getAttribute("memberName"));

        return "index";
    }
}
