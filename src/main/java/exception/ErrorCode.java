package exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // 400 Bad Request : 잘못 들어온 요청
    NOT_ENOUGH_POINT(BAD_REQUEST, "포인트가 부족 합니다."),
    INVALID_AMOUNT(BAD_REQUEST, "충전할 수 없는 금액 입니다."),
    INVALID_RESERVATION_STATUS(BAD_REQUEST, "결제할 수 없는 예약 상태입니다."),
    INVALID_CONCERT(BAD_REQUEST, "유효하지 않은 콘서트ID 입니다."),
    EXPIRED_RESERVATION(BAD_REQUEST, "임시 배정이 만료되었습니다."),



    // 401

    // 404
    USER_NOT_FOUND(NOT_FOUND, "사용자를 찾을 수 없습니다."),
    CONCERT_NOT_FOUND(NOT_FOUND, "콘서트를 찾을 수 없습니다"),
    CONCERT_SCHEDULE_NOT_FOUND(NOT_FOUND, "콘서트 날짜를 찾을 수 없습니다"),
    CONCERT_SCHEDULE_OR_SEAT_NOT_FOUND(NOT_FOUND, "콘서트 날짜/좌석을 찾을 수 없습니다"),
    RESERVATION_NOT_FOUND(NOT_FOUND, "예약을 찾을 수 없습니다"),
    PAYMENT_NOT_FOUND(NOT_FOUND, "결제 정보를 찾을 수 없습니다."),
    TOKEN_NOT_FOUND(NOT_FOUND, "토큰이 존재하지 않습니다"),
    DISCONNECTED_TOKEN_NOT_FOUND(NOT_FOUND, "연결이 끊긴 토큰을 찾을 수 없습니다"),

    // 500 Interner Server Error : 서버 내부 처리 오류
    OPERATION_ERROR(INTERNAL_SERVER_ERROR, "데이터 연산 오류가 발생했습니다."),
    CONCURRENT_LOCK(INTERNAL_SERVER_ERROR, "트랜잭션 충돌이 발생하였습니다. "),


    TIME_PARADOX(INTERNAL_SERVER_ERROR, "업데이트 시간이 현재 시간보다 앞선 시간입니다."),

}
