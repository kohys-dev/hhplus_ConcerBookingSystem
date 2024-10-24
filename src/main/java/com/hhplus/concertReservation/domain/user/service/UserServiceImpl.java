package com.hhplus.concertReservation.domain.user.service;

import com.hhplus.concertReservation.domain.user.infrastructure.User;
import com.hhplus.concertReservation.domain.user.infrastructure.UserRepository;
import exception.CustomException;
import exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.hibernate.StaleObjectStateException;
import org.springframework.dao.PessimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;


    @Override
    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    public User getUserWithLock(long id) {
        return userRepository.findByIdWithPessimisticLock(id).orElseThrow(()-> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    @Override
    @Transactional
    public int getPoint(long id) {
        try{
            // 포인트 관련 오류 방지를 위해 Lock 사용
            User user = this.getUserWithLock(id);
            return user.getPoint();

        } catch (StaleObjectStateException | PessimisticLockingFailureException e) {
            throw new CustomException(ErrorCode.CONCURRENT_LOCK);
        }
    }

    @Override
    @Transactional
    public User chargePoint(long id, int add) {
        try{
            // 포인트 충전 시 동시성 처리를 위해 Lock 사용
            User user = this.getUserWithLock(id);
            user.chargePoint(add);
            User saved = userRepository.save(user);
            return saved;

        } catch(StaleObjectStateException | PessimisticLockingFailureException e){
            throw new CustomException(ErrorCode.CONCURRENT_LOCK);
        }
    }

    @Override
    @Transactional
    public User usePoint(long id, Integer price) {
        try {
            User user = this.getUserWithLock(id);
            user.usePoint(price);
            User saved = userRepository.save(user);
            return saved;

        } catch (StaleObjectStateException | PessimisticLockingFailureException e) {
            throw new CustomException(ErrorCode.CONCURRENT_LOCK);
        }
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
