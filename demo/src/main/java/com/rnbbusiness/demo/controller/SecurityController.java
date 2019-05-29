package com.rnbbusiness.demo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SecurityController {
    @RequestMapping(value = {"", "/home"}, method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('admin', 'user')")
    public String home() {
        return "home";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('admin', 'user')")
    public String userPage() {
        return "user";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('admin')")
    public String adminPage() {
        return "admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login";
    }


}
