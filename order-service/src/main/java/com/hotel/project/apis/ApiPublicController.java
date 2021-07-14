package com.hotel.project.apis;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/public/")
public class ApiPublicController {
    @GetMapping("home")
    public String home(){
        return "Home";
    }
}
