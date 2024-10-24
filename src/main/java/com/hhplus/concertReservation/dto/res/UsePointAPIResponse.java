package com.hhplus.concertReservation.dto.res;

public record UsePointAPIResponse (int point){
    public static UsePointAPIResponse from(int point) {
        return new UsePointAPIResponse(point);
    }
}