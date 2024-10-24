package com.hhplus.concertReservation.adapter;


import com.hhplus.concertReservation.domain.user.application.port.in.ChargePointCommand;
import com.hhplus.concertReservation.domain.user.application.port.in.GetPointCommand;
import com.hhplus.concertReservation.domain.user.application.port.in.UsePointCommand;
import com.hhplus.concertReservation.domain.user.usecase.ChargePointUseCase;
import com.hhplus.concertReservation.domain.user.usecase.CreateUserUseCase;
import com.hhplus.concertReservation.domain.user.usecase.GetPointUseCase;
import com.hhplus.concertReservation.domain.user.usecase.UsePointUseCase;

import com.hhplus.concertReservation.dto.req.ChargePointAPIRequest;
import com.hhplus.concertReservation.dto.req.UsePointAPIRequest;
import com.hhplus.concertReservation.dto.res.ChargePointAPIResponse;
import com.hhplus.concertReservation.dto.res.GetPointAPIResponse;
import com.hhplus.concertReservation.dto.res.UsePointAPIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping('/api/')
public class UserController {

    private final CreateUserUseCase createUserUseCase;
    private final GetPointUseCase getPointUseCase;
    private final ChargePointUseCase chargePointUseCase;
    private final UsePointUseCase usePointUseCase;


    // 잔액 조회
    @GetMapping("/{userId}/point")
    public ResponseEntity<GetPointAPIResponse> getPoint (
            @RequestHeader(value = "Authorization", required =false) String accessKey,
            @PathVariable long userId){
        GetPointCommand command = GetPointCommand.builder()
                .id(userId)
                .build();
        int point = getPointUseCase.execute(command);
        return ResponseEntity.ok(GetPointAPIResponse.from(point));
    }

    @PatchMapping("/{userId}/charge")
    public ResponseEntity<ChargePointAPIResponse> chargePoint (
            @RequestHeader(value = "Authorization", required =false) String accessKey,
            @PathVariable long userId,
            @RequestBody ChargePointAPIRequest request){

        ChargePointCommand command = ChargePointCommand.builder()
                .id(userId)
                .add(request.amount()).build();

        int point = chargePointUseCase.execute(command);

        return ResponseEntity.ok(ChargePointAPIResponse.from(point));
    }

    @PatchMapping("/{userId}/use")
    public ResponseEntity<UsePointAPIResponse> usePoint (
            @RequestHeader(value = "Authorization", required =false) String accessKey,
            @PathVariable long userId,
            @RequestBody UsePointAPIRequest request){

        UsePointCommand command = UsePointCommand.builder()
                .id(userId)
                .price(request.amount()).build();

        int point = usePointUseCase.execute(command);

        return ResponseEntity.ok(UsePointAPIResponse.from(point));
    }
}
