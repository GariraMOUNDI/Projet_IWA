package org.projet_iwa.auth.api.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/home")
public class HomeController {

    @Value("${app.version}")
    private String appVersion;

    @GetMapping
    public Map<String, String> getAppVersion(){
        Map<String, String> app = new HashMap<>();
        app.put("appVersion", appVersion);
        return app;
    }
}
