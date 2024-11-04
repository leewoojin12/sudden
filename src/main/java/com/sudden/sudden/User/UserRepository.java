package com.sudden.sudden.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


// JPA 식


public interface UserRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByusername(String nickname);
}