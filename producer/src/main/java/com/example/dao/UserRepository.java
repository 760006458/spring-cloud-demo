package com.example.dao;

import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author xuan
 * @create 2018-05-14 19:39
 **/
public interface UserRepository extends JpaRepository<User,Long> {

}
