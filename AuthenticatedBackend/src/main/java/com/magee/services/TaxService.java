package com.magee.services;

import com.magee.models.TaxResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;

public class TaxService {



    public static final String API_BASE_URL = "https://teapi.netlify.app/api";

    private RestTemplate restTemplate = new RestTemplate();


    public BigDecimal getTaxRate(String stateCode) {

        String url = API_BASE_URL + "/statetax?state=" + stateCode.toUpperCase();

        try {

            TaxResponseDto taxResponseDto = restTemplate.getForObject(url, TaxResponseDto.class);

            return taxResponseDto.getSalesTax().divide(new BigDecimal(100));
        } catch (HttpClientErrorException e) {
            if (e.getRawStatusCode() == 404) {
                // tax was not found, throw error
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tax not found for state '" + stateCode.toUpperCase() + "'. Please check your address");
            } else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was an error getting the tax rate", e);
            }
        }





    }



}
