package com.oguzhanuzman.github.gardirop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping
    public String getIndex() {
        return "index";
    }
}
