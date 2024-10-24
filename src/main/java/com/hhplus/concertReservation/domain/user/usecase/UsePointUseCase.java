package com.hhplus.concertReservation.domain.user.usecase;

import com.hhplus.concertReservation.domain.user.application.port.in.UsePointCommand;

public interface UsePointUseCase {
    int execute(UsePointCommand command);
}
