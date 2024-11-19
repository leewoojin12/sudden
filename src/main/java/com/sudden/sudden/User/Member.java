package com.sudden.sudden.User;

import com.sudden.sudden.Item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

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


    @OneToMany(mappedBy = "member")
    private List<Item> items = new ArrayList<>();



    //== 연관 관계 메서드 ==//
    public void addItem(Item item) {
        items.add(item);
        item.setMember(this);
    }



}
