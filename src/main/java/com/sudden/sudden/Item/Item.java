package com.sudden.sudden.Item;

import static jakarta.persistence.FetchType.*;

import com.sudden.sudden.User.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Item {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Item_id")
    private Long id;



    private String wp_name;



    private int price;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;



    public void setMember(Member member) {
        this.member =member;
        member.getItems().add(this);
    }



}
