package com.fdm.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Map;


/**
 * @author April Chou
 * @Classname PositionResponse
 * @Description TODO
 * @Version 1.0
 * @Date 2025/2/16 20:27
 */
@Component
@Data
public class PositionResponse {
    private Map<String, PositionDto> response;

}