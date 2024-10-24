package com.hhplus.concertReservation.domain.user.usecase;

import com.hhplus.concertReservation.domain.user.application.port.in.CreateUserCommand;

public interface CreateUserUseCase {
    long execute(CreateUserCommand command);
}
