package com.hhplus.concertReservation.domain.user.infrastructure;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@RequiredArgsConstructor
@Repository
// UserRepo에 커스텀 기능 추가
public class UserRepositoryImpl implements UserRepository{

    private final UserJpaRepository userRepository;


    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Optional<User> findByIdWithPessimisticLock(long id) {
        return userRepository.findByIdWithPessimisticLock(id);
    }
}
