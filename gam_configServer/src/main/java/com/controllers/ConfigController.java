package com.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/config")
public class ConfigController {

    @Value("${dburl}")
    private String dbUrl;

    @GetMapping(value = "/getDbURL")
    public String getDbURL() {
        return this.dbUrl;
    }
}
