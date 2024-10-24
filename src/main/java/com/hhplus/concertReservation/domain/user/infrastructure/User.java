package com.hhplus.concertReservation.domain.user.infrastructure;

import exception.CustomException;
import exception.ErrorCode;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@Getter
@Builder


public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;
    private LocalDateTime createdAt;

    @Column(name="point")
    // Point는 User와 생명주기가 비슷함 > 인덱싱을 사용해서 별도의 테이블로 구성하기보다는 같은 테이블에 넣는 게 낫지 않을까..?
    private Integer point;


    public User(){

    }

    public User(long userId, int point) {
    }

    public int getPoint() {
        return this.point;
    }

    public void chargePoint(int add){
        try{
            // 충전 요청의 값이 0 이하인 경우
            if(add <= 0){
                throw new CustomException(ErrorCode.INVALID_AMOUNT);
            }
            // 정상 충전
            this.point = Math.addExact(this.point, add);
        } catch (ArithmeticException e){
            // 포인트 충전 값 오류 제외 기타 point 연산 오류 발생 시 오류 메시지 표시
            throw new CustomException(ErrorCode.OPERATION_ERROR);
        }
    }

    public void usePoint(int sub){
        // 사용하려는 포인트 양이 보유 포인트보다 큰 경우
        if(this.point < sub){
            throw new CustomException(ErrorCode.NOT_ENOUGH_POINT);
        }

        // 사용하려는 포인트 양이 보유 포인트보다 작은 경우
        this.point -= sub;
    }


}
