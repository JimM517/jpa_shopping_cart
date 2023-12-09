package com.magee.services;

import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

public class TaxService {



    public static final String API_BASE_URL = "https://teapi.netlify.app/api";

    private RestTemplate restTemplate = new RestTemplate();


    public BigDecimal getTaxRate(String stateCode) {
        return null;
    }



}
