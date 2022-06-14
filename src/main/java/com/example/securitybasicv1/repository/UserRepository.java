package com.example.securitybasicv1.repository;


import com.example.securitybasicv1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

//CRUD 함수를 JpaRepository가 들고 있음.
//@Repository라는 어노테이션이 없어도 Ioc된다. 이유는 JpaRepository를 상속 했기 때문에.
public interface UserRepository extends JpaRepository<User, Integer> {
    public User findByUsername(String username);
}
