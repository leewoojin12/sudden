package com.sudden.sudden;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Getter
@Setter
public class Member {


    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;



    private String username;

    private String nickname;


    private String password;







}
