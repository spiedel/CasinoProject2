package com.example.techconnect.casinoservice.components;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ChipClient {

    public ChipClient() {
    }

    public double requestBuyValue(int amountOfMoney){
        RestTemplate restTemplate = new RestTemplate();
        try{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("http://localhost:8081/chipValue?amountToBuy=").append(amountOfMoney);
            String url = stringBuilder.toString();
            return restTemplate.getForObject(url, Double.class);
        } catch(Exception e){
            return 5;
        }
    }

    public double requestSellValue() {
        RestTemplate restTemplate = new RestTemplate();
        try{
            return restTemplate.getForObject("http://localhost:8081/chipValue", Double.class);
        } catch(Exception e){
            return 5;
        }
    }
}
