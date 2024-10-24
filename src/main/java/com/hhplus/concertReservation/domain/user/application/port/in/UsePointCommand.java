package com.hhplus.concertReservation.domain.user.application.port.in;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UsePointCommand {
    long id;
    int price;
}