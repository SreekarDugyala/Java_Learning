package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name="Entitlements")
public class Entitlements implements Serializable {
    @Id
    private Integer id;
    private String client_id;
    private String access_id;
    private String email;
    private String bank_id;
    private String branch_id;
    private String account_number;
    private String created_dt;
    private String last_update_dt;
    private String account_type;
    private String cis_bank_number;
    private String gbv;
    private String rundate;
    private String job_id;

}


