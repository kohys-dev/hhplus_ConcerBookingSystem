package com.hhplus.concertReservation.domain.user.infrastructure;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    
    // 비관적 락 적용
    Optional<User> findByIdWithPessimisticLock(@Param("id")long id);
}
