package com.example.demo.repository;

import com.example.demo.model.Entitlements;
import jakarta.persistence.Entity;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntitlementsRepository extends JpaRepository<Entitlements, Integer> {

}
