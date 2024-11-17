package com.example.demo.model;

import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.Date;

public class JobsOpenTodayPost implements Serializable {
    @Id
    private Long jobId;
    private String title;
    private Integer experience;
    private String Description;
    private Date postedDate;
    
}
