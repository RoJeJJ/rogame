package com.rojee.login.jpa;


import com.rojee.core.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<User,Long> {
    User findUserByAccountAndPassword(String account,String password);

    boolean existsUserByAccount(String account);
}
