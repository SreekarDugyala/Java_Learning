package com.example.demo.service;

import com.example.demo.model.Entitlements;
import com.example.demo.repository.EntitlementsRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EntitlementsService {

    @Autowired
    private EntitlementsRepository entitlementsRepository;

    public List<Entitlements> getEntitlmentsFromDB() {
        try {
            List<Entitlements> entitlements = entitlementsRepository.findAll();
            return entitlements;
        } catch (Exception exception) {
            log.error("Error fetching data from entitlements");
            return null;
        }
    }

    public Optional<Entitlements> getEntitlementById(Integer id) {
        Optional<Entitlements> entitlements = entitlementsRepository.findById(id);

        return entitlements;
    }

}
