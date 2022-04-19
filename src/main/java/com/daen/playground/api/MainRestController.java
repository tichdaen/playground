package com.daen.playground.api;

import com.daen.playground.service.BusinessService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainRestController {

    @Autowired
    BusinessService service;

    @GetMapping("/a")
    public ResponseEntity<Object> a() {

        return ResponseEntity.ok("TRUE");
    }

    @GetMapping("/b")
    public ResponseEntity<Object> b(String codes) {
        System.out.println(" CODE 들 : " + codes);
        String result = service.requestOtherInst(codes);

        return ResponseEntity.ok(result);
    }

}
