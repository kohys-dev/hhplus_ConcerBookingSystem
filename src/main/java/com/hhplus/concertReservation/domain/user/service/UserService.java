package com.hhplus.concertReservation.domain.user.service;

import com.hhplus.concertReservation.domain.user.infrastructure.User;

public interface UserService {

    User getUser(long id);
    User getUserWithLock(long id);

    int getPoint(long id);
    User chargePoint(long id, int add);
    User usePoint(long id, Integer price);

    User save(User user);
}
