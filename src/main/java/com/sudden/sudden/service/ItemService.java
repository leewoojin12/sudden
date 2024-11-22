package com.sudden.sudden.service;

import com.sudden.sudden.Item.Item;
import com.sudden.sudden.Item.My_item;
import com.sudden.sudden.Repository.ItemRepository;
import com.sudden.sudden.Repository.MemberRepository;
import com.sudden.sudden.Repository.WpRepository;
import com.sudden.sudden.User.Member;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.Page;



@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor

public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Autowired
    WpRepository wpRepository;

    private final MemberRepository memberRepository;





    @Transactional
    public Long update(String wp_name , int price , String nickname){
        List<Member> members = memberRepository.findAllByNickname(nickname);

        Member get_mem = members.get(0);
        Item item = new Item();


        item.setWp_name(wp_name);
        item.setPrice(price);
        item.setMember(get_mem);


        itemRepository.save(item);




        return item.getId();

    }



    public Page<Item> getList(int page) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, 16, Sort.by(sorts));
        return wpRepository.findAll(pageable);
    }






    public Item getfindwp(Long id){
        return itemRepository.findOne(id);

    }







}
