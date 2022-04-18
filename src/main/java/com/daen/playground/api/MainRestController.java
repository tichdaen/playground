package com.daen.playground.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainRestController {

    @GetMapping("/a")
    public ResponseEntity<Object> a() {

        return ResponseEntity.ok("TRUE");
    }

}
