package com.hhplus.concertReservation.domain.user.application.port.in;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetPointCommand {

    long id;

}
