package com.hhplus.concertReservation.domain.user.usecase;


import com.hhplus.concertReservation.domain.user.application.port.in.UsePointCommand;
import com.hhplus.concertReservation.domain.user.infrastructure.User;
import com.hhplus.concertReservation.domain.user.service.UserService;
import exception.CustomException;
import exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.PessimisticLockingFailureException;

@RequiredArgsConstructor
public class UsePointUseCaseImpl implements UsePointUseCase {
    private final UserService userService;


    @Override
    public int execute(UsePointCommand command){
        try{
            User user = userService.usePoint(command.getId(), command.getPrice());
            return user.getPoint();
        } catch (PessimisticLockingFailureException e){
            throw new CustomException(ErrorCode.CONCURRENT_LOCK);
        }
    }
}
