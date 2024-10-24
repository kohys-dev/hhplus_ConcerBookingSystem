package com.hhplus.concertReservation.domain.user.usecase;

import com.hhplus.concertReservation.domain.user.application.port.in.GetPointCommand;
import com.hhplus.concertReservation.domain.user.service.UserService;
import exception.CustomException;
import exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PessimisticLockingFailureException;

@RequiredArgsConstructor
public class GetPointUseCaseImpl implements GetPointUseCase{
    private final UserService userService;

    @Override
    public int execute(GetPointCommand command){
        try{
            return userService.getPoint(command.getId());
        } catch(PessimisticLockingFailureException e){
            throw new CustomException(ErrorCode.CONCURRENT_LOCK);

        }

    }


}
