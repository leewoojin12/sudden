package com.sudden.sudden.service;


import com.sudden.sudden.Item.Item;
import com.sudden.sudden.Item.My_item;
import com.sudden.sudden.Repository.ItemRepository;
import com.sudden.sudden.Repository.MemberRepository;
import com.sudden.sudden.Repository.OrderJpaRepository;
import com.sudden.sudden.Repository.OrderRepository;
import com.sudden.sudden.User.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor


public class OrderService {


    private final ItemService itemService;
    private final MemberService memberService;


    private final OrderRepository orderRepository;
    private final OrderJpaRepository orderJpaRepository;





    private final MemberRepository  memberRepository;
    private final ItemRepository itemRepository;




    public String buy_item(Long id , String username ){

        List<Member> get_user_information = memberRepository.findAllByNickname(username);
        Item get_item_information = itemRepository.findOne(id);
        Member user = get_user_information.get(0);



        System.out.println("@@@@@@@@@@@@");
        if(get_item_information.getPrice()>user.getMy_sp()){

            System.out.println(" 돈 모자람 ");
            return "구매할수 없습니다";
        }else {

            // sp 값 받아서 저장하는거
            int updatesp = user.getMy_sp() - get_item_information.getPrice();
            user.setMy_sp(updatesp);


            orderRepository.set_my_sp(user.getId() , user.getMy_sp());
            Member a = memberRepository.findOne(user.getId());

            // 상점 - > 내 리스트로 이동

            My_item my_item = new My_item();
            my_item.setWp_name(get_item_information.getWp_name());
            my_item.setMember(a);

            orderRepository.save(my_item);
            System.out.println(get_item_information.getId());


            itemRepository.soldout(get_item_information.getId());

        }









        return null;




    }


    public My_item get_item_list(Long id){
        //TODO orderJpaRepository 로 id에 맞는 my_item 가져와야함

        System.out.println("실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중2");
        List<My_item> my_item = orderRepository.find_by_wp_name(id);
        System.out.println(my_item.get(0).toString());
        return my_item.get(0);


    }

    //내 가지고 있는 아이템
    public Page<My_item> getList(int page, Long id) {
        List<Sort.Order> sorts = new ArrayList<>();
        sorts.add(Sort.Order.desc("id"));
        Pageable pageable = PageRequest.of(page, 16, Sort.by(sorts));
        return orderJpaRepository.findAllByMemberId( id, pageable);
    }















}
