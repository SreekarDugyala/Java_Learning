package com.example.demo.controller;

import com.example.demo.model.Entitlements;
import com.example.demo.service.EntitlementsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/getEntitlements")
public class EntitlementsController {

    @Autowired
    private EntitlementsService entitlementsService;

    @GetMapping("/allEntitlements")
    public ResponseEntity<List<Entitlements>> getEntitlements() {
        List<Entitlements> entitlements = entitlementsService.getEntitlmentsFromDB();
        if(!entitlements.isEmpty()){
            return new ResponseEntity(entitlements, HttpStatus.OK);
        }

        return new ResponseEntity(entitlements, HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entitlements> getEntitlementById(@PathVariable Integer id){
        Optional<Entitlements> entitlements = entitlementsService.getEntitlementById(id);
        if(entitlements.isPresent()){
            return new ResponseEntity<>(entitlements.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
