package com.sudden.sudden.service;


import com.sudden.sudden.Item.Item;
import com.sudden.sudden.Item.My_item;
import com.sudden.sudden.Repository.ItemRepository;
import com.sudden.sudden.Repository.MemberRepository;
import com.sudden.sudden.Repository.OrderRepository;
import com.sudden.sudden.User.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor


public class OrderService {


    private final ItemService itemService;
    private final MemberService memberService;


    private final OrderRepository orderRepository;
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













}
