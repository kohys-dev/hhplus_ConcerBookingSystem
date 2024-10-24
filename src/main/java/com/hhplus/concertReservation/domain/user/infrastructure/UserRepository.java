package com.hhplus.concertReservation.domain.user.infrastructure;

import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);
    User save(User user);

    // additional
    Optional<User> findByIdWithPessimisticLock(long id);
}
