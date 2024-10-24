package com.hhplus.concertReservation.unit.user.usecase;
import com.hhplus.concertReservation.domain.user.application.port.in.ChargePointCommand;
import com.hhplus.concertReservation.domain.user.infrastructure.User;
import com.hhplus.concertReservation.domain.user.service.UserService;
import com.hhplus.concertReservation.domain.user.usecase.ChargePointUseCase;
import com.hhplus.concertReservation.domain.user.usecase.ChargePointUseCaseImpl;
import exception.CustomException;
import exception.ErrorCode;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



class ChargePointUseCaseTest {
    private final UserService userService = Mockito.mock(UserService.class);
    private final ChargePointUseCase useCase = new ChargePointUseCaseImpl(userService);



    @BeforeEach
    void setUp() {

    }

    @Test
    void execute_ShouldChargePointsAndReturnUpdatedBalance() {
        User reserver = new User(1L, 500);
        ChargePointCommand command = ChargePointCommand.builder()
                .id(1L)
                .add(500)
                .build();
        // Given
        when(userService.chargePoint(command.getId(),command.getAdd()))
                .thenReturn(reserver);

        // When
        int result = useCase.execute(command);

        // Then
        assertThat(result).isEqualTo(500);
        verify(userService).chargePoint(1L, 500);
    }

    @Test
    void execute_WhenUserNotFound_ShouldThrowException() {
        // Given
        ChargePointCommand command = ChargePointCommand.builder()
                .id(1L)
                .add(500)
                .build();
        when(userService.chargePoint(command.getId(), command.getAdd())).thenThrow(new CustomException(ErrorCode.USER_NOT_FOUND));

        // When & Then
        assertThatThrownBy(() -> useCase.execute(command))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.USER_NOT_FOUND);
    }

    @Test
    void execute_WhenAmountIsNegative_ShouldThrowException() {
        // Given
        ChargePointCommand invalidCommand = ChargePointCommand.builder()
                .id(1L)
                .add(-500)
                .build();
        when(userService.chargePoint(invalidCommand.getId(), invalidCommand.getAdd())).thenThrow(new CustomException(ErrorCode.INVALID_AMOUNT));

        // When & Then
        assertThatThrownBy(() -> useCase.execute(invalidCommand))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.INVALID_AMOUNT);
    }

    @Test
    void execute_WhenAmountIsZero_ShouldThrowException() {
        // Given
        ChargePointCommand invalidCommand = ChargePointCommand.builder()
                .id(1L)
                .add(0)
                .build();
        when(userService.chargePoint(1L, 0)).thenThrow(new CustomException(ErrorCode.INVALID_AMOUNT));

        // When & Then
        assertThatThrownBy(() -> useCase.execute(invalidCommand))
                .isInstanceOf(CustomException.class)
                .hasFieldOrPropertyWithValue("errorCode", ErrorCode.INVALID_AMOUNT);
    }
}