package com.sudden.sudden.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member {


    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;



    private String username;


    @Column(unique = true)
    private String nickname;

    private String password;


    private int my_sp=99999999;








}
