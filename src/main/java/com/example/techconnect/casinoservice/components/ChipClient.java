package com.example.techconnect.casinoservice.components;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ChipClient {



    public ChipClient() {
    }

    public int requestChipValue(){
        RestTemplate restTemplate = new RestTemplate();
        try{
            return restTemplate.getForObject("http://localhost:8081/chipValue",Integer.class);
        } catch(Exception e){
            return 5;
        }
    }
}
