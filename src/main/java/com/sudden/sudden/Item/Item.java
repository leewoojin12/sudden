package com.sudden.sudden.Item;


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
    private Long id;



    private String wp_name;

    @OneToMany(mappedBy = "item")
    private List<Quantity> wp_quantity;

    private int price;



}
