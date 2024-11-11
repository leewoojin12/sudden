package com.sudden.sudden.service;

import com.sudden.sudden.Item.Item;
import com.sudden.sudden.Repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class ItemService {

    @Autowired
    ItemRepository itemRepository;



    @Transactional
    public Long update(String wp_name , int price){
        Item item = new Item();


        item.setWp_name(wp_name);
        item.setPrice(price);


        itemRepository.save(item);




        return item.getId();

    }



}
