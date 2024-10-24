package com.hhplus.concertReservation.unit.user.usecase;

import com.hhplus.concertReservation.domain.user.application.port.in.GetPointCommand;
import com.hhplus.concertReservation.domain.user.service.UserService;
import com.hhplus.concertReservation.domain.user.usecase.GetPointUseCase;
import com.hhplus.concertReservation.domain.user.usecase.GetPointUseCaseImpl;
import exception.CustomException;
import exception.ErrorCode;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;


class GetPointUseCaseTest {
    private final UserService userService = Mockito.mock(UserService.class);
    private final GetPointUseCase useCase = new GetPointUseCaseImpl(userService);

    @Test
    @DisplayName("유저 포인트 조회 성공")
    void execute_ShouldReturnPointFromReserverService() {
        // Given
        long reserverId = 1L;
        int expectedPoint = 100;
        GetPointCommand command = GetPointCommand.builder()
                .id(reserverId)
                .build();
        when(userService.getPoint(reserverId)).thenReturn(expectedPoint);

        // When
        int result = useCase.execute(command);

        // Then
        assertEquals(expectedPoint, result);
        verify(userService).getPoint(reserverId);
    }

    @Test
    @DisplayName("예약자를 찾을 수 없을 때 - CustomException")
    void execute_WhenReserverServiceThrowsException_ShouldThrowException() {
        // Given
        long reserverId = 1L;
        int expectedPoint = 100;
        GetPointCommand command = GetPointCommand.builder()
                .id(reserverId)
                .build();
        when(userService.getPoint(reserverId)).thenThrow(new CustomException(ErrorCode.USER_NOT_FOUND));

        // When
        assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(CustomException.class)
                .satisfies(thrown -> {
                    CustomException exception = (CustomException) thrown;
                });

        // Then
        verify(userService).getPoint(reserverId);
    }
}