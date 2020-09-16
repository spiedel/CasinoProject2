package com.example.techconnect.casinoservice.components;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ChipClient {

    public ChipClient() {
    }

    public int requestChipValue(int amountOfMoney){
        RestTemplate restTemplate = new RestTemplate();
        try{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("http://localhost:8081/chipValue?amountToBuy=").append(amountOfMoney);
            String url = stringBuilder.toString();
            return restTemplate.getForObject(url, Integer.class);
        } catch(Exception e){
            return 5;
        }
    }
}
