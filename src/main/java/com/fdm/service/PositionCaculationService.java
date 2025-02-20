package com.fdm.service;

import com.fdm.domain.PositionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author April Chou
 * @Classname PositionCaculationService
 * @Description TODO
 * @Version 1.0
 * @Date 2025/2/16 20:29
 */
@Service
public interface PositionCaculationService {

    void callPositionCalculator();

//    do a get
}