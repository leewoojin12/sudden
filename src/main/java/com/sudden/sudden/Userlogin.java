package com.sudden.sudden;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class Userlogin {





    @NotBlank(message = "닉네임 없음")
    private String nickname;



    @NotBlank(message = "패스워드 없음")
    private String password;



}
