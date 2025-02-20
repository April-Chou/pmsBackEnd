package com.fdm.domain;

import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * this pojo is from pccaculator
 */

@Component
@Data
public class PositionDto {

    private String ticker;
    private String totalQty;
    private String avgPrice;
    private String currency;
    private String unrealizedPnl;
    private String lastPrice;
    private String positionId;
}
