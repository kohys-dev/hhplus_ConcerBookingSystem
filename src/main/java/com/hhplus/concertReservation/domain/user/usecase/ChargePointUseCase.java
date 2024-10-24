package com.hhplus.concertReservation.domain.user.usecase;

import com.hhplus.concertReservation.domain.user.application.port.in.ChargePointCommand;

public interface ChargePointUseCase {
    int execute(ChargePointCommand command);
}
