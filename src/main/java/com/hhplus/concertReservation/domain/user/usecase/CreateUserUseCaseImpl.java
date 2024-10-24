package com.hhplus.concertReservation.domain.user.usecase;


import com.hhplus.concertReservation.common.UseCaseCustom;
import com.hhplus.concertReservation.domain.user.application.port.in.CreateUserCommand;
import com.hhplus.concertReservation.domain.user.infrastructure.User;
import com.hhplus.concertReservation.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@UseCaseCustom
public class CreateUserUseCaseImpl implements CreateUserUseCase{
    private final UserService userService;


    @Override
    public long execute(CreateUserCommand command){

        User user = User.builder().id(command.getId()).point(0).build();
        User saved = userService.save(user);

        return saved.getId();
    }
}
