package com.czx.easydemo.service;

import com.czx.easydemo.model.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {

    @Transactional
    int creatUser(User user);

    int deletcUser(Long user);

    int changeUser(User user);

    User findUser(Long id);

}