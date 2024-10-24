package com.hhplus.concertReservation.dto.res;

public record ChargePointAPIResponse (int point){

    public static ChargePointAPIResponse from(int point) {
        return new ChargePointAPIResponse(point);
    }
}