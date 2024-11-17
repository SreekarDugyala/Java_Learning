package com.example.demo.controller;

import com.example.demo.model.Entitlements;
import com.example.demo.repository.EntitlementsRepository;
import com.example.demo.service.EntitlementsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(EntitlementsController.class)
public class EntitlementsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EntitlementsService entitlementsService;

    @MockBean
    private EntitlementsRepository entitlementsRepository;

    @Test
    public void testGetEntitlements_ReturnsListOfEntitlements() throws Exception {
        List<Entitlements> entitlementsList = new ArrayList<>();
        Entitlements entitlements1 = setEntitlements();
        entitlementsList.add(entitlements1);

        when(entitlementsService.getEntitlmentsFromDB()).thenReturn(entitlementsList);

        mockMvc.perform(get("/getEntitlements/allEntitlements"))
                .andExpect(status().isOk())
                .andExpect(content().json("[{'id':1,'access_id':'Access_id1','account_number':'Account1','gbv':'GBVSet'}]"));

    }

    @Test
    public void testGetEntitlements_ReturnsNoContent() throws Exception {
        when(entitlementsService.getEntitlmentsFromDB()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/getEntitlements/allEntitlements"))
                .andExpect(status().isNoContent());
    }

    private Entitlements setEntitlements () {
        Entitlements entitlements1 = new Entitlements();
        entitlements1.setId(1);
        entitlements1.setAccess_id("Access_id1");
        entitlements1.setAccount_number("Account1");
        entitlements1.setGbv("GBVSet");
        return entitlements1;
    }


}
