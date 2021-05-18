package com.spring.el.demo;


import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    
    @Autowired
    private ELService elService;
    
    @PostMapping("/evaluate")
    public Object evaluateExpression(@RequestBody ObjectNode params) {
        return elService.evaluateExpression(params);
    }
}
