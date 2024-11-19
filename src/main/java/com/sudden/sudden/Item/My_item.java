package com.sudden.sudden.Item;


import static jakarta.persistence.FetchType.*;

import com.sudden.sudden.User.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class My_item {




    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "my_items_id")
    private Long id;



    /*
        @OneToMany(mappedBy = "item")
    */
        private String wp_name;


/*

    @OneToMany(mappedBy = "item")
    private List<Quantity> wp_quantity;
*/

    private int price;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "member_id")
    private Member member;



    public void setMember(Member member) {
        this.member =member;
        member.getMy_items().add(this);
    }



}
