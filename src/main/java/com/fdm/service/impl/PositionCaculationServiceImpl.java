package com.fdm.service.impl;

import com.fdm.config.RestConfig;
import com.fdm.domain.PositionResponse;
import com.fdm.service.PositionCaculationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author April Chou
 * @Classname PositionCaculationServiceImpl
 * @Description TODO
 * @Version 1.0
 * @Date 2025/2/20 10:13
 */
@Service
public class PositionCaculationServiceImpl implements PositionCaculationService {
    @Autowired
    RestTemplate restTemplate;
    // autowire SimpMessagingTemplate simpMessagingTemplate

//    // Inject RestTemplate as a Bean
//    public PositionCalculatorService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public PositionCaculationService(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }

    // Scheduled method to make a REST request every 10 seconds (example)
    @Scheduled(fixedRate = 1000) // Every 1 seconds
    public void callPositionCalculator() {
        String url = "https://api.example.com/calculatePosition"; // Replace with actual API endpoint

        try {
//            this part needs to be changed
            // Make the REST call (example get request)
            ResponseEntity<PositionResponse> response = restTemplate.getForEntity(url, PositionResponse.class);

            // Log the response
            if (response.getStatusCode().is2xxSuccessful()) {
                System.out.println("Position calculated: " + response.getBody());

                //check with Tony on what data structure he is expecting. and also the topic name

                //simpMessagingTemplate.convertAndSend(topic, response.getBody());
            } else {
                System.out.println("Failed to get position: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Error during the REST request: " + e.getMessage());
        }
    }

}
