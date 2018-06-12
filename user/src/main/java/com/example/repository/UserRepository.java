package com.example.repository;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xuan
 * @create 2018-06-07 13:39
 **/
public interface UserRepository extends JpaRepository<User, Long> {
    User findByOpenid(String openid);
}
