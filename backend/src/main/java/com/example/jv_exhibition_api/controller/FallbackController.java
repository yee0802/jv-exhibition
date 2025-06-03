package com.example.jv_exhibition_api.controller;

import com.example.jv_exhibition_api.exception.UnavailableRouteException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @RequestMapping("/**")
    public ResponseEntity<String> handleUnavailableRoutes() {
        throw new UnavailableRouteException("The requested endpoint was not found");
    }
}
