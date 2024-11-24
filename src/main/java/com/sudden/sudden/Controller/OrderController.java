package com.sudden.sudden.Controller;
import com.sudden.sudden.Item.Item;
import com.sudden.sudden.Item.My_item;
import com.sudden.sudden.Repository.OrderJpaRepository;
import com.sudden.sudden.User.Member;
import com.sudden.sudden.service.MemberService;
import com.sudden.sudden.User.UserSecurityService;
import com.sudden.sudden.service.ItemService;
import com.sudden.sudden.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Getter
@RequestMapping("Order")

public class OrderController {
    private final ItemService itemService;
    private final UserSecurityService USS;
    private final MemberService memberService;
    private final OrderService orderService;
    private final OrderJpaRepository orderJpaRepository;

    //구매
    @GetMapping("/detail/{id}")
    public String buy_wp(@PathVariable Long id, Model model ){
        Item item = itemService.getfindwp(id);

        if(getUSS().isUserLoggedIn()){
            model.addAttribute(item);
            return "product-detail.html";
        }else  {


            return "login.html";

        }

    }
    //구매 완료 로직
    @PostMapping("/detail/{id}")
    public String buy_wp(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        orderService.buy_item(id,userDetails.getUsername());

        return "redirect:/";
    }




    @PostMapping("/sell/{id}")
    public String update_post_wp(){


        return null;
    }

    @GetMapping("/sell/{id}")
    public String update_get_wp(@PathVariable Long id, Model model ){


        System.out.println("실행 중 실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중실행 중1");


        My_item my_item = orderService.get_item_list(id);
        System.out.println(my_item.getWp_name());
        System.out.println(my_item.getId());


        if(getUSS().isUserLoggedIn()){

            System.out.println("로그인뒤 로직 실행중 ------");
            System.out.println(my_item.getId());
            System.out.println(my_item.getWp_name());

            model.addAttribute(my_item);


            return "product-detail1.html";
        }
        else {

            return "login.html";
        }
    }



}
