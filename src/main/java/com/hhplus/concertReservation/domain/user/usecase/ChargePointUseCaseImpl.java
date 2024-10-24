package com.hhplus.concertReservation.domain.user.usecase;

import com.hhplus.concertReservation.common.UseCaseCustom;
import com.hhplus.concertReservation.domain.user.service.UserService;
import com.hhplus.concertReservation.domain.user.infrastructure.User;
import com.hhplus.concertReservation.domain.user.application.port.in.ChargePointCommand;
import exception.CustomException;
import exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.PessimisticLockingFailureException;


@RequiredArgsConstructor
@Slf4j
@UseCaseCustom
public class ChargePointUseCaseImpl implements ChargePointUseCase{
    private final UserService userService;

    @Override
    public int execute(ChargePointCommand command){
        try {
            User user = userService.chargePoint(command.getId(), command.getAdd());
            return user.getPoint();
        }
        catch(PessimisticLockingFailureException e){
            throw new CustomException((ErrorCode.CONCURRENT_LOCK));
        }
    }
}
