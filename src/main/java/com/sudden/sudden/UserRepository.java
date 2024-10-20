package com.sudden.sudden;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Member ,Long> {
/*
    Optional<Member> findByMemberId(String memeberId);
*/


}
