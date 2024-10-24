package com.hhplus.concertReservation.domain.user.usecase;

import com.hhplus.concertReservation.domain.user.application.port.in.GetPointCommand;
import exception.CustomException;
import exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PessimisticLockingFailureException;


public interface GetPointUseCase {
    int execute(GetPointCommand command);
}