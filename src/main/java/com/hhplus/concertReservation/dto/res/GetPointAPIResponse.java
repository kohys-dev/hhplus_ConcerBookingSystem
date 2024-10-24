package com.hhplus.concertReservation.dto.res;

public record GetPointAPIResponse
        (int point){

    public static GetPointAPIResponse from(int point) {
        return new GetPointAPIResponse(point);
    }
}